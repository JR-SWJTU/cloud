package com.jr.cloud.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URI;

/**
 * Created by Administrator on 2017/12/26.
 */
public class WordCount {
    //hdfs当前活跃的namenode url
//    public static String hdfsUrl="hdfs://node1.hadoop.ptbird.cn:8020";
    public static String hdfsUrl = "hdfs://192.168.229.13:9000";

    //获取hdfs的句柄
    public static FileSystem getHdfs()throws Exception{
        //获取配置文件信息
        Configuration conf=new Configuration();
        //获取文件系统
        FileSystem hdfs= FileSystem.get(URI.create(hdfsUrl),conf);
        return hdfs;
    }

    //读取文件信息
    public static void testRead(Path p) throws Exception{
        FileSystem hdfs=getHdfs();
        //打开文件流
        FSDataInputStream inStream=hdfs.open(p);
        //读取文件内容到控制台显示
        IOUtils.copyBytes(inStream, System.out,4096,false);
        //关闭文件流
        IOUtils.closeStream(inStream);
    }

    public static void main(String args[]){
        // 获取读取源文件和目标文件位置参数
        String local = "D:\\BugReport.txt";
        String uri = "hdfs://192.168.229.13:9000/input/APIAccess.log.2016-09-05.241";

        FileInputStream in = null;
        OutputStream out = null;
        Configuration conf = new Configuration();
        try {
            // 获取读入文件数据
            in = new FileInputStream(new File(local));

            // 获取目标文件信息
            FileSystem fs = FileSystem.get(URI.create(uri), conf);
            out = fs.create(new Path(uri), new Progressable() {
                public void progress() {
                    System.out.println("*");
                }
            });

            // 跳过前100个字符
            byte[] buffer = new byte[20];

            // 从101的位置读取20个字符到buffer中
            int bytesRead = in.read(buffer);
            if (bytesRead >= 0) {
                out.write(buffer, 0, bytesRead);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            IOUtils.closeStream(in);
            IOUtils.closeStream(out);
        }
    }

}
