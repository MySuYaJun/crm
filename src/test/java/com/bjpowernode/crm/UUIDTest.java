/**
 * @项目名：crm-project
 * @创建人： Administrator
 * @创建时间： 2020-06-13
 * @公司： www.bjpowernode.com
 * @描述：
 */
package com.bjpowernode.crm;

import java.util.UUID;

/**
 * <p>NAME: UUIDTest</p>
 * @author Administrator
 * @date 2020-06-13 11:42:14
 */
public class UUIDTest {
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replaceAll("-",""));
    }
}
