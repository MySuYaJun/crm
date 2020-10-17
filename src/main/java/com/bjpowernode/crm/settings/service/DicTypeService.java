package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.settings.domain.DicType;

import java.util.List;

/**
 * @项目名：crm-project
 * @创建人： Administrator
 * @创建时间： 2020-06-10
 * @公司： www.bjpowernode.com
 * @描述：
 */
public interface DicTypeService {
    List<DicType> queryAllDicTypes();

    DicType queryDicTypeByCode(String code);

    int saveCreateDicType(DicType dicType);

    int deleteDicTypeByCodes(String[] codes);

    int saveEditDicType(DicType dicType);
}
