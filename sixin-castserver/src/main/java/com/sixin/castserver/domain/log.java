package com.sixin.castserver.domain;

import com.sixin.common.annotation.Excel;
import com.sixin.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 终端日志信息对象 shuofang-log
 * 
 * @author txy
 * @date 2020-03-07
 */
public class log extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 登录编号 */
    private Long logid;

    /** 终端编号 */
    @Excel(name = "终端编号")
    private Integer clientid;

    /** 日志类型 */
    @Excel(name = "日志类型")
    private String logtype;

    /** 操作类型 */
    @Excel(name = "操作类型")
    private String functions;

    /** 操作时间 */
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date logtime;

    public void setLogid(Long logid) 
    {
        this.logid = logid;
    }

    public Long getLogid() 
    {
        return logid;
    }
    public void setClientid(Integer clientid) 
    {
        this.clientid = clientid;
    }

    public Integer getClientid() 
    {
        return clientid;
    }
    public void setLogtype(String logtype) 
    {
        this.logtype = logtype;
    }

    public String getLogtype() 
    {
        return logtype;
    }
    public void setFunctions(String functions) 
    {
        this.functions = functions;
    }

    public String getFunctions() 
    {
        return functions;
    }
    public void setLogtime(Date logtime) 
    {
        this.logtime = logtime;
    }

    public Date getLogtime() 
    {
        return logtime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("logid", getLogid())
            .append("clientid", getClientid())
            .append("logtype", getLogtype())
            .append("functions", getFunctions())
            .append("logtime", getLogtime())
            .append("remark", getRemark())
            .toString();
    }
}
