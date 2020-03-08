package com.sixin.castserver.domain;

import com.sixin.common.annotation.Excel;
import com.sixin.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 客户端对象 client
 * 
 * @author txy
 * @date 2020-03-07
 */
public class client extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** IMEI */
    private String imei;

    /** 编号 */
    @Excel(name = "编号")
    private Long clientid;

    /** 名称 */
    @Excel(name = "名称")
    private String clientname;

    /** null */
    @Excel(name = "null")
    private String clientinfo;

    /** 记录时间 */
    @Excel(name = "记录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creatdate;

    /** 最后登录时间 */
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date finallogindate;

    /** 备注 */
    @Excel(name = "备注")
    private String note;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private Long isuse;

    /** 电池1电压 */
    @Excel(name = "电池1电压")
    private String pow1;

    /** 电池组电压 */
    @Excel(name = "电池组电压")
    private String grouppow;

    /** 18V1输出电压 */
    @Excel(name = "18V1输出电压")
    private String outv1;

    /** 18V2输出电压 */
    @Excel(name = "18V2输出电压")
    private String outv2;

    /** 24V输出电压 */
    @Excel(name = "24V输出电压")
    private String v24;

    /** 28V输出电压 */
    @Excel(name = "28V输出电压")
    private String v28;

    /** 外部电源电压 */
    @Excel(name = "外部电源电压")
    private String extendpow;

    /** 太阳能电压 */
    @Excel(name = "太阳能电压")
    private String solarpow;

    /** 充电状况 */
    @Excel(name = "充电状况")
    private String charge;

    /** 工作状况 */
    @Excel(name = "工作状况")
    private String work;

    /** IP地址 */
    @Excel(name = "IP地址")
    private String ip;

    /** 是否登记 */
    @Excel(name = "是否登记")
    private Long isregister;

    public void setImei(String imei) 
    {
        this.imei = imei;
    }

    public String getImei() 
    {
        return imei;
    }
    public void setClientid(Long clientid) 
    {
        this.clientid = clientid;
    }

    public Long getClientid() 
    {
        return clientid;
    }
    public void setClientname(String clientname) 
    {
        this.clientname = clientname;
    }

    public String getClientname() 
    {
        return clientname;
    }
    public void setClientinfo(String clientinfo) 
    {
        this.clientinfo = clientinfo;
    }

    public String getClientinfo() 
    {
        return clientinfo;
    }
    public void setCreatdate(Date creatdate) 
    {
        this.creatdate = creatdate;
    }

    public Date getCreatdate() 
    {
        return creatdate;
    }
    public void setFinallogindate(Date finallogindate) 
    {
        this.finallogindate = finallogindate;
    }

    public Date getFinallogindate() 
    {
        return finallogindate;
    }
    public void setNote(String note) 
    {
        this.note = note;
    }

    public String getNote() 
    {
        return note;
    }
    public void setIsuse(Long isuse) 
    {
        this.isuse = isuse;
    }

    public Long getIsuse() 
    {
        return isuse;
    }
    public void setPow1(String pow1) 
    {
        this.pow1 = pow1;
    }

    public String getPow1() 
    {
        return pow1;
    }
    public void setGrouppow(String grouppow) 
    {
        this.grouppow = grouppow;
    }

    public String getGrouppow() 
    {
        return grouppow;
    }
    public void setOutv1(String outv1) 
    {
        this.outv1 = outv1;
    }

    public String getOutv1() 
    {
        return outv1;
    }
    public void setOutv2(String outv2) 
    {
        this.outv2 = outv2;
    }

    public String getOutv2() 
    {
        return outv2;
    }
    public void setV24(String v24) 
    {
        this.v24 = v24;
    }

    public String getV24() 
    {
        return v24;
    }
    public void setV28(String v28) 
    {
        this.v28 = v28;
    }

    public String getV28() 
    {
        return v28;
    }
    public void setExtendpow(String extendpow) 
    {
        this.extendpow = extendpow;
    }

    public String getExtendpow() 
    {
        return extendpow;
    }
    public void setSolarpow(String solarpow) 
    {
        this.solarpow = solarpow;
    }

    public String getSolarpow() 
    {
        return solarpow;
    }
    public void setCharge(String charge) 
    {
        this.charge = charge;
    }

    public String getCharge() 
    {
        return charge;
    }
    public void setWork(String work) 
    {
        this.work = work;
    }

    public String getWork() 
    {
        return work;
    }
    public void setIp(String ip) 
    {
        this.ip = ip;
    }

    public String getIp() 
    {
        return ip;
    }
    public void setIsregister(Long isregister) 
    {
        this.isregister = isregister;
    }

    public Long getIsregister() 
    {
        return isregister;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("imei", getImei())
            .append("clientid", getClientid())
            .append("clientname", getClientname())
            .append("clientinfo", getClientinfo())
            .append("creatdate", getCreatdate())
            .append("finallogindate", getFinallogindate())
            .append("note", getNote())
            .append("isuse", getIsuse())
            .append("pow1", getPow1())
            .append("grouppow", getGrouppow())
            .append("outv1", getOutv1())
            .append("outv2", getOutv2())
            .append("v24", getV24())
            .append("v28", getV28())
            .append("extendpow", getExtendpow())
            .append("solarpow", getSolarpow())
            .append("charge", getCharge())
            .append("work", getWork())
            .append("ip", getIp())
            .append("isregister", getIsregister())
            .toString();
    }
}
