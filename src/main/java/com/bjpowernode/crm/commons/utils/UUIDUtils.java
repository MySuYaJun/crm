/**
 * @项目名：crm-project
 * @创建人： Administrator
 * @创建时间： 2020-06-13
 * @公司： www.bjpowernode.com
 * @描述：
 */
package com.bjpowernode.crm.commons.utils;

import java.util.UUID;

/**
 * <p>NAME: UUIDUtils</p>
 * @author Administrator
 * @date 2020-06-13 11:46:58
 */
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
