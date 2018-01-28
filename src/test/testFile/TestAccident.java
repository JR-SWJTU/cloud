package testFile;

import com.jr.cloud.dto.FileInfo;
import com.jr.cloud.service.IFileService;
import com.jr.cloud.service.impl.FileServiceImpl;
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


/**
 * Created by Administrator on 2017/10/22.
 */
public class TestAccident {

    @Test
    public void test() {
//        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext-*.xml");
        Date date= new Date(System.currentTimeMillis());
        System.out.print(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
        System.out.print(String.format("%.2f",3.244));
        IFileService fileService = new FileServiceImpl();
        try{

            List<FileInfo> lsit = fileService.listFile("input");
            System.out.print(lsit.get(0).getFileName());
        }catch (Exception e){
            e.printStackTrace();
        }
//        AccidentMapperCustom accidentMapperCustom = ctx.getBean(AccidentMapperCustom.class);

//        AccidentQueryCondition condition = new AccidentQueryCondition();
//        condition.setAreaName("金牛区");
//        condition.setRoadLevel("高速公路");
//        condition.setWeather("晴朗");
//        condition.setWorkPlaceRel("否");
//        condition.setRoadType("非交叉口");
//        condition.setTeamName("大队A");
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            condition.setStartTime(format.parse("2017-09-09") );
//            condition.setEndTime(format.parse("2017-12-30"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        condition.setCarCollisionType("翻车");
//        condition.setTroEscape("否");
//        condition.setAreaName("金牛区");
//        condition.setIsWorkDay(1);
//        condition.setCarType("小客车");
//
//        System.out.println(new Gson().toJson(condition));
//        System.out.println(new Gson().toJson(accidentMapperCustom.multiConditionQueryAccidentForSGS(condition)));

//        String name[] = {"一大队","二大队","二大队"};
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("teamName",name);
//        System.out.println(jsonObject.toString());

//        Map<String, Integer> map = new HashMap<String, Integer>();
//        map.put("轻伤",5);
//        System.out.println( new Gson().toJson(map));
//
//        deleteDir(new File("E:\\roadCheck\\")) ;



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

    private  boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
