package com.jr.cloud.util;

import com.jr.cloud.entity.UploadRecord;
import com.jr.cloud.web.exception.base.CustomException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

/**
 * Created by Administrator on 2018/1/29.
 */
public class FileUtil {

    public static void main(String[] args) {
        //初始化输入流
        InputStream in = null;
        //初始化输出流
        OutputStream out = null;
        try {
            //上传文件,从windows上读取要传的文件
            in = new BufferedInputStream(new FileInputStream("D:\\BugReport.txt"));
            //在hadoop上新建文件
            FileSystem fs = FileSystem.get(URI.create("hdfs://192.168.229.13:9000/input/APIAccess.log.2016-09-05.241"), new Configuration());
            //在hadoop上新建文件
            out = fs.create(new Path("hdfs://192.168.229.13:9000/input/APIAccess.log.2016-09-05.241"), new Progressable() {
                public void progress() {
                    System.out.println("********正在上传loading********");
                }
            });
            IOUtils.copyBytes(in, out, 4096, false);
            System.out.println("********上传结束finish********");
            //下载文件1
//            in = new URL("hdfs://hadoop1/input/fd.txt").openStream();
//            IOUtils.copyBytes(in, System.out, 4096, false);

            //下载文件2
//            FileSystem fs = FileSystem.get(URI.create("hdfs://hadoop1"), new Configuration());
//            in = fs.open(new Path("hdfs://hadoop1/input/fd.txt"));
//            IOUtils.copyBytes(in, System.out, 4096, false);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(in);
            IOUtils.closeStream(out);
        }
    }


    /**
     * 判断分片的文件是否已经存在
     * @param fileName  文件名称
     * @param chunkNum  分片序号
     * @param userId    用户id
     * @return
     * @throws Exception
     */
    public static boolean fileExists(String fileName, int chunkNum, int userId) throws Exception {
        String fullUri = FileSystemUtil.uri + String.valueOf(userId) + "//" + fileName +  "//" + String.valueOf(chunkNum);
        FileSystem fs = FileSystemUtil.getFileSystem(FileSystemUtil.uri);
        Path path = new Path(fullUri);
        if( fs.exists( path)){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 存储分片文件
     * @param fileName  文件名称
     * @param chunkNum  分片序号
     * @param userId    用户id
     * @param file      分片文件
     * @throws Exception
     */
    public static void saveFile( String fileName, int chunkNum, int userId, MultipartFile file) throws Exception {

        String fullUri = FileSystemUtil.uri + String.valueOf(userId) + "//" + fileName.substring( 0, fileName.lastIndexOf("."));
        FileSystem fs = FileSystemUtil.getFileSystem(FileSystemUtil.uri);
        Path path = new Path(fullUri);
        //判断分片存储的临时文件夹是否存在，不存在则创建
        if( !fs.exists( path)){
            fs.mkdirs( path);
        }

        //存储分片文件
        //初始化输入流
        InputStream in = null;
        //初始化输出流
        OutputStream out = null;
        try {
            //获取文件输入流
            in =  file.getInputStream();
            //在hadoop上新建文件
            out = fs.create(new Path(fullUri + "//" + String.valueOf(chunkNum)));
            //写文件
            IOUtils.copyBytes(in, out, 4096, false);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(in);
            IOUtils.closeStream(out);
        }
    }

    /**
     * 合并分片文件
     * @param fileName 文件名称
     * @param userId  用户id
     * @throws Exception
     */
    private static void mergeFile( String fileName, int userId) throws Exception{

        //源分片文件所在的目录
        String fullUri = FileSystemUtil.uri + String.valueOf(userId) + "//" + fileName.substring( 0, fileName.lastIndexOf("."));
        FileSystem fs = FileSystemUtil.getFileSystem(FileSystemUtil.uri);
        Path path = new Path(fullUri);
        //文件合并，并删除分片文件所在的文件夹
        org.apache.hadoop.fs.FileUtil.copyMerge(fs, path, fs, new Path(FileSystemUtil.uri + String.valueOf(userId) + "//" + fileName), true, new Configuration(), null);
    }


}
