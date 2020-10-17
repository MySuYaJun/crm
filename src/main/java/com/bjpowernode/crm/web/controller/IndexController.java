/**
 * @项目名：crm-project
 * @创建人： Administrator
 * @创建时间： 2020-06-05
 * @公司： www.bjpowernode.com
 * @描述：
 */
package com.bjpowernode.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>NAME: IndexController</p>
 * @author Administrator
 * @date 2020-06-05 11:42:16
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
