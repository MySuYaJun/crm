package com.bjpowernode.crm.workbench.mapper;

import com.bjpowernode.crm.workbench.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity_remark
     *
     * @mbggenerated Sun Jun 28 09:18:06 CST 2020
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity_remark
     *
     * @mbggenerated Sun Jun 28 09:18:06 CST 2020
     */
    int insert(ActivityRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity_remark
     *
     * @mbggenerated Sun Jun 28 09:18:06 CST 2020
     */
    int insertSelective(ActivityRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity_remark
     *
     * @mbggenerated Sun Jun 28 09:18:06 CST 2020
     */
    ActivityRemark selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity_remark
     *
     * @mbggenerated Sun Jun 28 09:18:06 CST 2020
     */
    int updateByPrimaryKeySelective(ActivityRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity_remark
     *
     * @mbggenerated Sun Jun 28 09:18:06 CST 2020
     */
    int updateByPrimaryKey(ActivityRemark record);

    /**
    * 根据activityId查询该市场活动下所有的备注
    */
    List<ActivityRemark> selectActivityRemarkForDetailByActivityId(String activityId);

    /**
    * 保存添加的市场活动备注
    */
    int insertActivityRemark(ActivityRemark remark);

    /**
    * 根据id删除市场活动备注
    */
    int deleteActivityRemarkById(String id);

    /**
    * 保存修改的市场活动备注
    */
    int updateActivityRemark(ActivityRemark remark);
}