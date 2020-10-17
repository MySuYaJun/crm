package com.bjpowernode.crm.workbench.mapper;

import com.bjpowernode.crm.workbench.domain.TranHistory;

import java.util.List;

public interface TranHistoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_history
     *
     * @mbggenerated Mon Jul 06 09:21:20 CST 2020
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_history
     *
     * @mbggenerated Mon Jul 06 09:21:20 CST 2020
     */
    int insert(TranHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_history
     *
     * @mbggenerated Mon Jul 06 09:21:20 CST 2020
     */
    int insertSelective(TranHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_history
     *
     * @mbggenerated Mon Jul 06 09:21:20 CST 2020
     */
    TranHistory selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_history
     *
     * @mbggenerated Mon Jul 06 09:21:20 CST 2020
     */
    int updateByPrimaryKeySelective(TranHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_history
     *
     * @mbggenerated Mon Jul 06 09:21:20 CST 2020
     */
    int updateByPrimaryKey(TranHistory record);
    /**
    * 保存创建的交易历史
    */
    int insertTranHistory(TranHistory tranHistoty);

    /**
    * 根据tranId查询该交易下所有的历史记录明细信息
    */
    List<TranHistory> selectTranHistoryForDetailByTranId(String tranId);
}