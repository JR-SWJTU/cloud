package testFile;

import com.jr.cloud.dto.FileInfo;
import com.jr.cloud.service.IFileService;
import com.jr.cloud.service.impl.FileServiceImpl;
import com.jr.cloud.util.FileSystemUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.System.in;
import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;


/**
 * Created by Administrator on 2017/10/22.
 */
public class TestAccident {

    @Test
    public void test() {
//        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext-*.xml");
//        Date date= new Date(System.currentTimeMillis());
//        System.out.print(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
//        System.out.print(String.format("%.2f",3.244));
//        IFileService fileService = new FileServiceImpl();
//        try{
//
//            List<FileInfo> lsit = fileService.listFile("input");
//            System.out.print(lsit.get(0).getFileName());
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        String path = "/41/51/aaa";
        path = path.substring( path.indexOf("/")+1);
        System.out.println( path);
        path = path.substring( path.indexOf("/")+1);
        System.out.println( path);




    }

    @Test
    public void readFile(){
        // 获取读取源文件和目标文件位置参数
//        String local = "D:\\BugReport.txt";

//        String uri = "hdfs://192.168.229.13:9000/input/APIAccess.log.2016-09-05.241";
        String uri = "hdfs://192.168.229.13:9000/input";

        Configuration conf = new Configuration();
        try {

            // 获取目标文件信息
            FileSystem fs = FileSystem.get(URI.create(uri), conf);

            //获取指定文件夹下所包含的文件的名称
            FileStatus[] fileStatuses = fs.listStatus(new Path(uri));
            Path[] paths = FileUtil.stat2Paths(fileStatuses);
            for(Path p : paths){
                System.out.println(p);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        finally {

        }
    }

    @Test
    public void writeFile(){
        String local = "D:\\hhh\\a.txt";
        String uri = "hdfs://192.168.229.13:9000/input/a.txt";

        Configuration conf = new Configuration();
        OutputStream out = null;
        try {

            // 获取目标文件信息
            InputStream in = new BufferedInputStream(new FileInputStream(local));

            FileSystem fs = FileSystem.get(URI.create(uri), conf);
            out = fs.create(new Path(uri));
            IOUtils.copyBytes(in, out, 4096, true);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            IOUtils.closeStream(in);
            if(out != null)
                IOUtils.closeStream(out);
        }

    }

    @Test
    public   void deleteDir() throws IOException {
        String fullUri = FileSystemUtil.uri + "1";
        FileSystem fs = FileSystemUtil.getFileSystem(FileSystemUtil.uri);
        Path path1 = new Path(fullUri);
        fs.delete(path1,true);

    }

}
