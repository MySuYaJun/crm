/**
 * @项目名：crm-project
 * @创建人： Administrator
 * @创建时间： 2020-06-10
 * @公司： www.bjpowernode.com
 * @描述：
 */
package com.bjpowernode.crm.settings.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>NAME: DicController</p>
 * @author Administrator
 * @date 2020-06-10 09:11:20
 */
@Controller
public class DicController {
    @RequestMapping("/settings/dictionary/index.do")
    public String index(){
        return "settings/dictionary/index";
    }
}
