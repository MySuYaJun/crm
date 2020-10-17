/**
 * @项目名：crm-project
 * @创建人： Administrator
 * @创建时间： 2020-07-05
 * @公司： www.bjpowernode.com
 * @描述：
 */
package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.commons.contants.Contants;
import com.bjpowernode.crm.commons.domain.FunnelVO;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.commons.utils.UUIDUtils;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.workbench.domain.Customer;
import com.bjpowernode.crm.workbench.domain.Tran;
import com.bjpowernode.crm.workbench.domain.TranHistory;
import com.bjpowernode.crm.workbench.mapper.CustomerMapper;
import com.bjpowernode.crm.workbench.mapper.TranHistoryMapper;
import com.bjpowernode.crm.workbench.mapper.TranMapper;
import com.bjpowernode.crm.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>NAME: TranServiceImpl</p>
 * @author Administrator
 * @date 2020-07-05 11:58:41
 */
@Service("tranService")
public class TranServiceImpl implements TranService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private TranMapper tranMapper;

    @Autowired
    private TranHistoryMapper tranHistoryMapper;

    @Override
    public int saveCreateTran(Map<String, Object> map) {
        String customerId=(String) map.get("customerId");
        String customerName=(String)map.get("customerName");
        User user=(User) map.get(Contants.SESSION_USER);
        //如果客户不存在，则先创建客户
        if(customerId==null||customerId.trim().length()==0){
            Customer customer=new Customer();
            customer.setName(customerName);
            customer.setId(UUIDUtils.getUUID());
            customer.setOwner(user.getId());
            customer.setCreateTime(DateUtils.formatDateTime(new Date()));
            customer.setCreateBy(user.getId());
            customerMapper.insertCustomer(customer);

            //如果customerId为空，则把新生成的customer的id赋值过来
            customerId=customer.getId();
        }

        //保存交易
        Tran tran=new Tran();
        tran.setActivityId((String)map.get("activityId"));
        tran.setContactsId((String) map.get("contactsId"));
        tran.setContactSummary((String) map.get("contactSummary"));
        tran.setExpectedDate((String) map.get("expectedClosingDate"));
        tran.setMoney((String) map.get("amountOfMoney"));
        tran.setName((String) map.get("transactionName"));
        tran.setOwner((String)map.get("transactionOwner"));
        tran.setStage((String)map.get("transactionStage"));
        tran.setCustomerId(customerId);
        tran.setId(UUIDUtils.getUUID());
        tran.setCreateBy(user.getId());
        tran.setCreateTime(DateUtils.formatDateTime(new Date()));
        tran.setDescription((String)map.get("describe"));
        tran.setNextContactTime((String)map.get("nextContactTime"));
        tran.setSource((String)map.get("clueSource"));
        tran.setType((String)map.get("transactionType"));
        tranMapper.insertTran(tran);

        //保存交易历史
        TranHistory tranHistory=new TranHistory();
        tranHistory.setTranId(tran.getId());
        tranHistory.setStage(tran.getStage());
        tranHistory.setMoney(tran.getMoney());
        tranHistory.setId(UUIDUtils.getUUID());
        tranHistory.setExpectedDate(tran.getExpectedDate());
        tranHistory.setCreateTime(tran.getCreateTime());
        tranHistory.setCreateBy(user.getId());
        tranHistoryMapper.insertTranHistory(tranHistory);

        return 0;
    }

    @Override
    public Tran queryTranForDetailById(String id) {
        return tranMapper.selectTranForDetailById(id);
    }

    @Override
    public List<FunnelVO> queryCountOfTranGroupByStage() {
        return tranMapper.selectCountOfTranGroupStage();
    }
}
