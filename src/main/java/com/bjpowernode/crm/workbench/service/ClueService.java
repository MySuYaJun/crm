package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.Clue;

import java.util.Map;

/**
 * @项目名：crm-project
 * @创建人： Administrator
 * @创建时间： 2020-06-30
 * @公司： www.bjpowernode.com
 * @描述：
 */
public interface ClueService {
    int saveCreateClue(Clue clue);

    Clue queryClueForDetailById(String id);

    int saveConvertClue(Map<String,Object> map);
}
