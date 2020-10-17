/**
 * @项目名：crm-project
 * @创建人： Administrator
 * @创建时间： 2020-06-09
 * @公司： www.bjpowernode.com
 * @描述：
 */
package com.bjpowernode.crm.settings.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>NAME: SettingsController</p>
 * @author Administrator
 * @date 2020-06-09 12:02:07
 */
@Controller
public class SettingsController {
    @RequestMapping("/settings/index.do")
    public String index(){
        //请求转发
        return "settings/index";
    }
}
