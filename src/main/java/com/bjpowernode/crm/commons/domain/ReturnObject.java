/**
 * @项目名：crm-project
 * @创建人： Administrator
 * @创建时间： 2020-06-08
 * @公司： www.bjpowernode.com
 * @描述：
 */
package com.bjpowernode.crm.commons.domain;

/**
 * <p>NAME: ReturnObject</p>
 * @author Administrator
 * @date 2020-06-08 10:54:05
 */
public class ReturnObject {
    private String code;//操作成功或者失败的代码：0-失败,1-成功
    private String message;//提示信息
    private Object retData;// 返回的业务数据

    /**
     * Gets the value of code
     *
     * @return the value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code
     * <p>You can use getCode() to get the value of code</p>
     *
     * @param code code
     */
    public void setCode(String code) {
        this.code=code;
    }

    /**
     * Gets the value of message
     *
     * @return the value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message
     * <p>You can use getMessage() to get the value of message</p>
     *
     * @param message message
     */
    public void setMessage(String message) {
        this.message=message;
    }

    /**
     * Gets the value of retData
     *
     * @return the value of retData
     */
    public Object getRetData() {
        return retData;
    }

    /**
     * Sets the retData
     * <p>You can use getRetData() to get the value of retData</p>
     *
     * @param retData retData
     */
    public void setRetData(Object retData) {
        this.retData=retData;
    }
}
