package com.jr.cloud.util;

import com.jr.cloud.dto.FileInfo;

/**
 * Created by Administrator on 2018/1/27.
 */
public class FileSizeFormatUtil {

    /**
     * 将文件大小（字节）转化为MB或GB，用于显示，结果保留两位小数
     * @param fileInfo
     * @return
     */
    public static void sizeFormat(FileInfo fileInfo){
        Long len = fileInfo.getFileSize();
        double res = len;
        if(len >= 1000){
            res = len /1024;
            fileInfo.setUnit("KB");
            if(res >= 1000){
                res = res/ 1024;
                fileInfo.setUnit("MB");
                if(res >= 1000){
                    res = res / 1024;
                    fileInfo.setUnit("GB");
                }
            }
            fileInfo.setFormatSize( String.format("%.2f", res));
            return;
        }

        fileInfo.setFormatSize( String.format("%.2f", res));
        fileInfo.setUnit("B");
    }
}
