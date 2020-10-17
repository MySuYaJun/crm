package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.settings.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @项目名：crm-project
 * @创建人： Administrator
 * @创建时间： 2020-06-06
 * @公司： www.bjpowernode.com
 * @描述：
 */
public interface UserService {
    User queryUserByLoginActAndPwd(Map<String,Object> map);

    List<User> queryAllUsers();
}
