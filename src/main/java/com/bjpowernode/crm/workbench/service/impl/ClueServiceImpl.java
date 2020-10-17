/**
 * @项目名：crm-project
 * @创建人： Administrator
 * @创建时间： 2020-06-30
 * @公司： www.bjpowernode.com
 * @描述：
 */
package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.commons.contants.Contants;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.commons.utils.UUIDUtils;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.workbench.domain.*;
import com.bjpowernode.crm.workbench.mapper.*;
import com.bjpowernode.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>NAME: ClueServiceImpl</p>
 * @author Administrator
 * @date 2020-06-30 09:54:59
 */
@Service("clueService")
public class ClueServiceImpl implements ClueService {

    @Autowired
    private ClueMapper clueMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private ContactsMapper contactsMapper;

    @Autowired
    private ClueRemarkMapper clueRemarkMapper;

    @Autowired
    private CustomerRemarkMapper customerRemarkMapper;

    @Autowired
    private ContactsRemarkMapper contactsRemarkMapper;

    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;

    @Autowired
    private ContactsActivityRelationMapper contactsActivityRelationMapper;

    @Autowired
    private TranMapper tranMapper;

    @Autowired
    private TranRemarkMapper tranRemarkMapper;

    @Override
    public int saveCreateClue(Clue clue) {
        return clueMapper.insertClue(clue);
    }

    @Override
    public Clue queryClueForDetailById(String id) {
        return clueMapper.selectClueForDetailById(id);
    }

    @Override
    public int saveConvertClue(Map<String, Object> map) {
        String clueId=(String)map.get("clueId");
        User user=(User) map.get(Contants.SESSION_USER);
        String isCreateTran=(String)map.get("isCreateTran");
        //根据clueId查询线索信息
        Clue clue=clueMapper.selectClueById(clueId);
        //把线索中有关公司的信息转换到客户表中
        Customer customer=new Customer();
        customer.setCreateBy(user.getId());
        customer.setCreateTime(DateUtils.formatDateTime(new Date()));
        customer.setOwner(user.getId());//谁转的谁负责
        customer.setId(UUIDUtils.getUUID());
        customer.setName(clue.getCompany());
        customer.setWebsite(clue.getWebsite());
        customer.setPhone(clue.getPhone());
        customer.setDescription(clue.getDescription());
        customer.setContactSummary(clue.getContactSummary());
        customer.setAddress(clue.getAddress());
        customer.setNextcontactTime(clue.getNextContactTime());
        customerMapper.insertCustomer(customer);
        //把线索中有关个人的信息转换到联系人表中
        Contacts contacts=new Contacts();
        contacts.setSource(clue.getSource());
        contacts.setOwner(user.getId());
        contacts.setNextContactTime(clue.getNextContactTime());
        contacts.setId(UUIDUtils.getUUID());
        contacts.setMphone(clue.getMphone());
        contacts.setJob(clue.getJob());
        contacts.setEmail(clue.getEmail());
        contacts.setDescription(clue.getDescription());
        contacts.setCustomerId(customer.getId());
        contacts.setCreateTime(DateUtils.formatDateTime(new Date()));
        contacts.setCreateBy(user.getId());
        contacts.setContactSummary(clue.getContactSummary());
        contacts.setAppellation(clue.getAppellation());
        contacts.setAddress(clue.getAddress());
        contacts.setFullname(clue.getFullname());
        contactsMapper.insertContacts(contacts);

        //根据clueId查询该线索下所有的备注
        List<ClueRemark> crList=clueRemarkMapper.selectClueRemarkByClueId(clueId);

        //把该线索下的备注转换到客户备注表中一份
        if(crList!=null&&crList.size()>0){
            CustomerRemark cur=null;
            List<CustomerRemark> curList=new ArrayList<>();

            ContactsRemark cor=null;
            List<ContactsRemark> corList=new ArrayList<>();
            for(ClueRemark cr:crList){
                cur=new CustomerRemark();
                cur.setNoteContent(cr.getNoteContent());
                cur.setId(UUIDUtils.getUUID());
                cur.setEditTime(cr.getEditTime());
                cur.setEditFlag(cr.getEditFlag());
                cur.setEditBy(cr.getEditBy());
                cur.setCustomerId(customer.getId());
                cur.setCreateTime(cr.getCreateTime());
                cur.setCreateBy(cr.getCreateBy());
                curList.add(cur);

                cor=new ContactsRemark();
                cor.setNoteContent(cr.getNoteContent());
                cor.setId(UUIDUtils.getUUID());
                cor.setEditTime(cr.getEditTime());
                cor.setEditFlag(cr.getEditFlag());
                cor.setEditBy(cr.getEditBy());
                cor.setCreateTime(cr.getCreateTime());
                cor.setCreateBy(cr.getCreateBy());
                cor.setContactsId(contacts.getId());
                corList.add(cor);
            }

            customerRemarkMapper.insertCustomerRemarkByList(curList);
            contactsRemarkMapper.insertContactsRemarkByList(corList);
        }

        //根据clueId查询该线索和市场活动关联关系
        List<ClueActivityRelation> carList=clueActivityRelationMapper.selectClueActivityRelationByClueId(clueId);

        //把该线索和市场活动的关联关系转换到联系人和市场活动的关联系表中
        if(carList!=null&&carList.size()>0){
            ContactsActivityRelation coar=null;
            List<ContactsActivityRelation> coarList=new ArrayList<>();
            for(ClueActivityRelation car:carList){
                coar=new ContactsActivityRelation();
                coar.setId(UUIDUtils.getUUID());
                coar.setContactsId(contacts.getId());
                coar.setActivityId(car.getActivityId());
                coarList.add(coar);
            }
            contactsActivityRelationMapper.insertContactsActivityRelationByList(coarList);
        }

        //如果需要创建交易，往交易表中添加一条记录
        if("true".equals(isCreateTran)){
            Tran tran=new Tran();
            tran.setCreateTime(DateUtils.formatDateTime(new Date()));
            tran.setCreateBy(user.getId());
            tran.setId(UUIDUtils.getUUID());
            tran.setCustomerId(customer.getId());
            tran.setStage((String)map.get("stage"));
            tran.setOwner(user.getId());
            tran.setName((String)map.get("name"));
            tran.setMoney((String)map.get("money"));
            tran.setExpectedDate((String)map.get("expectedDate"));
            tran.setContactsId(contacts.getId());
            tran.setActivityId((String)map.get("activityId"));
            tranMapper.insertTran(tran);

            //如果需要创建交易，把该线索下的备注转换到交易备注表中一份
            if(crList!=null&&crList.size()>0){
                TranRemark tr=null;
                List<TranRemark> trList=new ArrayList<>();
                for(ClueRemark cr:crList){
                    tr=new TranRemark();
                    tr.setTranId(tran.getId());
                    tr.setNoteContent(cr.getNoteContent());
                    tr.setId(UUIDUtils.getUUID());
                    tr.setEditTime(cr.getEditTime());
                    tr.setEditFlag(cr.getEditFlag());
                    tr.setEditby(cr.getEditBy());
                    tr.setCreateTime(cr.getCreateTime());
                    tr.setCreateBy(cr.getCreateBy());
                    trList.add(tr);
                }
                tranRemarkMapper.insertTranRemarkByList(trList);
            }
        }

        //根据clueId删除该线索下的备注
        clueRemarkMapper.deleteClueRemarkByClueId(clueId);

        //根据clueId删除该线索和市场活动的关联关系
        clueActivityRelationMapper.deleteClueActivityRelationByClueId(clueId);

        //根据id删除线索
        clueMapper.deleteClueById(clueId);

        return 0;
    }
}
