/**
 * @项目名：crm-project
 * @创建人： Administrator
 * @创建时间： 2020-06-08
 * @公司： www.bjpowernode.com
 * @描述：
 */
package com.bjpowernode.crm.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>NAME: DateUtils</p>
 * @author Administrator
 * @date 2020-06-08 10:39:58
 */
public class DateUtils {
    /**
    *对指定Date对象进行yyyy-MM-dd HH:mm:ss格式化处理
    */
    public static String formatDateTime(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr=sdf.format(date);
        return dateStr;
    }

    /**
    *获取当前系统时间，以yyyyMMddHHmmss格式的字符串格式返回
    */
    public static String getNowForStr(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }
}
