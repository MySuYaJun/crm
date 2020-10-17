/**
 * @项目名：crm-project
 * @创建人： Administrator
 * @创建时间： 2020-06-30
 * @公司： www.bjpowernode.com
 * @描述：
 */
package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.ClueRemark;

import java.util.List;

/**
 * <p>NAME: ClueRemarkService</p>
 * @author Administrator
 * @date 2020-06-30 11:24:17
 */
public interface ClueRemarkService {
    List<ClueRemark> queryClueRemarkForDetailByClueId(String clueId);
}
