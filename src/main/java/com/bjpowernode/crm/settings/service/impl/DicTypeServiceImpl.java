/**
 * @项目名：crm-project
 * @创建人： Administrator
 * @创建时间： 2020-06-10
 * @公司： www.bjpowernode.com
 * @描述：
 */
package com.bjpowernode.crm.settings.service.impl;

import com.bjpowernode.crm.settings.domain.DicType;
import com.bjpowernode.crm.settings.mapper.DicTypeMapper;
import com.bjpowernode.crm.settings.mapper.DicValueMapper;
import com.bjpowernode.crm.settings.service.DicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>NAME: DicTypeServiceImpl</p>
 * @author Administrator
 * @date 2020-06-10 10:37:41
 */
@Service("dicTypeService")
public class DicTypeServiceImpl implements DicTypeService {
    @Autowired
    private DicTypeMapper dicTypeMapper;

    @Autowired
    private DicValueMapper dicValueMapper;

    @Override
    public List<DicType> queryAllDicTypes() {
        return dicTypeMapper.selectAllDicTypes();
    }

    @Override
    public DicType queryDicTypeByCode(String code) {
        return dicTypeMapper.selectDicTypeByCode(code);
    }

    @Override
    public int saveCreateDicType(DicType dicType) {
        return dicTypeMapper.insertDicType(dicType);
    }

    /**
     * Gets the value of dicTypeMapper
     *
     * @return the value of dicTypeMapper
     */
    public DicTypeMapper getDicTypeMapper() {
        return dicTypeMapper;
    }

    /**
     * Sets the dicTypeMapper
     * <p>You can use getDicTypeMapper() to get the value of dicTypeMapper</p>
     *
     * @param dicTypeMapper dicTypeMapper
     */
    public void setDicTypeMapper(DicTypeMapper dicTypeMapper) {
        this.dicTypeMapper=dicTypeMapper;
    }

    @Override
    public int deleteDicTypeByCodes(String[] codes) {
        //在根据codes批量删除属于字典类型之前，先把这些类型下所有的数据字典值删除
        dicValueMapper.deleteDicValueByTypeCode(codes);

        return dicTypeMapper.deleteDicTypeByCodes(codes);
    }

    @Override
    public int saveEditDicType(DicType dicType) {
        return dicTypeMapper.updateDicType(dicType);
    }
}