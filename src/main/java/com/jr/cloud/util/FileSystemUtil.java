package com.jr.cloud.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.net.URI;

/**
 * Created by Administrator on 2018/1/27.
 */
public class FileSystemUtil {

    public static final String uri = "hdfs://192.168.229.13:9000/";

    public static FileSystem getFileSystem(String path){

        Configuration conf = new Configuration();
        try {
            return FileSystem.get(URI.create(uri+path), conf);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
}
