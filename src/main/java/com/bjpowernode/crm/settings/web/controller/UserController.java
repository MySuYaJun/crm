/**
 * @项目名：crm-project
 * @创建人： Administrator
 * @创建时间： 2020-06-05
 * @公司： www.bjpowernode.com
 * @描述：
 */
package com.bjpowernode.crm.settings.web.controller;

import com.bjpowernode.crm.commons.contants.Contants;
import com.bjpowernode.crm.commons.domain.ReturnObject;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>NAME: UserController</p>
 * @author Administrator
 * @date 2020-06-05 12:00:17
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /*
        @RequestMapping("")中的url要和处理的资源路径保持一致！
        前台：http://ip:port/crm/settings/qx/user/toLogin.do
        springMVC接收到请求之后，会截取/settings/qx/user/toLogin.do，到各个controller的@RequestMapping的url中比对，比对成功的就执行该方法。

        settings/qx/user/login：给springMVC使用，把视图解析器的前缀+"settings/qx/user/login"+后缀，查找到页面，就可以跳转。

        根怎么理解：对于前台以及@RequestMapping而言，/settings/qx/user/toLogin.do中的第一个/就是根
                    对于springMVC跳转页面而言，实质上/WEB-INF/pages/中的第一个/就是根,最后一个/就相当于根。
                    本质上来讲，/settings/qx/user/toLogin.do第一个/和/WEB-INF/pages/中的第一个/在同一个目录，都是应用的根。

        啥是应用的根：一个项目就一个根，也就根目录
            web应用部署在tomcat的webapps目录下:
                   $tomcat_home
                        |->webapps
                              |->crm
                                   |->js、css、img
                                   |->WEB-INF
                                         |->web.xml
                                         |->classes
                                         |->lib
                                         |->pages
                                               |->jsp
     */
    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin(){

        return "settings/qx/user/login";
    }

    @RequestMapping("/settings/qx/user/login.do")
    public @ResponseBody Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpServletResponse response, HttpSession session){
        //封装参数
        Map<String,Object> map=new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        //调用service层方法，查询用户
        User user=userService.queryUserByLoginActAndPwd(map);

        //Map<String,Object> retMap=new HashMap<>();
        ReturnObject returnObject=new ReturnObject();
        //根据查询结果，生成响应信息
        if(user==null){
            //登录失败，用户名或者密码错误
            //{code:0,message:用户名或者密码错误}
            returnObject.setCode("0000");
            returnObject.setMessage("用户名或者密码错误");
        }else{//用户名和密码正确，登录成功？
            //new Date();//yyyy-MM-dd HH:mm:ss
            if(DateUtils.formatDateTime(new Date()).compareTo(user.getExpireTime())>0){
                //登录失败，账号已经过期
                //{code:0,message:账号已经过期}
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("账号已经过期");
            }else if("0".equals(user.getLockState())){
                //登录失败，状态被锁定
                //{code:0,message:状态被锁定}
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("状态被锁定");
            }else if(!user.getAllowIps().contains(request.getRemoteAddr())){
                //登录失败，ip受限
                //{code:0,message:ip受限}
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("ip受限");
            }else{
                //登录成功
                //{code:0}
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);

                //把用户信息保存到session中
                session.setAttribute(Contants.SESSION_USER,user);

                //如果用户选择了"记住密码"，则把用户名和密码以cookie的写到浏览机器
                if("true".equals(isRemPwd)){
                    Cookie c1=new Cookie("loginAct",loginAct);
                    c1.setMaxAge(10*24*60*60);
                    response.addCookie(c1);
                    Cookie c2=new Cookie("loginPwd",loginPwd);
                    c2.setMaxAge(10*24*60*60);
                    response.addCookie(c2);
                }else{
                    Cookie c1=new Cookie("loginAct","1");
                    c1.setMaxAge(0);
                    response.addCookie(c1);
                    Cookie c2=new Cookie("loginPwd","1");
                    c2.setMaxAge(0);
                    response.addCookie(c2);
                }
            }
        }
        return returnObject;
    }

    @RequestMapping("/settings/qx/user/logout.do")
    public String logout(HttpServletResponse response,HttpSession session){
        //清空cookie
        Cookie c1=new Cookie("loginAct","1");
        c1.setMaxAge(0);
        response.addCookie(c1);
        Cookie c2=new Cookie("loginPwd","1");
        c2.setMaxAge(0);
        response.addCookie(c2);
        //销毁session
        session.invalidate();
        //重定向到登录页面
        return "redirect:/";
    }
}
