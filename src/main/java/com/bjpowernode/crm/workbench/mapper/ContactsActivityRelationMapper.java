package com.bjpowernode.crm.workbench.mapper;

import com.bjpowernode.crm.workbench.domain.ContactsActivityRelation;

import java.util.List;

public interface ContactsActivityRelationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbggenerated Fri Jul 03 11:02:12 CST 2020
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbggenerated Fri Jul 03 11:02:12 CST 2020
     */
    int insert(ContactsActivityRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbggenerated Fri Jul 03 11:02:12 CST 2020
     */
    int insertSelective(ContactsActivityRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbggenerated Fri Jul 03 11:02:12 CST 2020
     */
    ContactsActivityRelation selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbggenerated Fri Jul 03 11:02:12 CST 2020
     */
    int updateByPrimaryKeySelective(ContactsActivityRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbggenerated Fri Jul 03 11:02:12 CST 2020
     */
    int updateByPrimaryKey(ContactsActivityRelation record);

    /**
    * 批量添加联系人和市场活动的关联关系
    */
    int insertContactsActivityRelationByList(List<ContactsActivityRelation> relationList);
}