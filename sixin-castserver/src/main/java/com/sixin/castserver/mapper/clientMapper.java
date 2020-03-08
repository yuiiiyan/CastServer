package com.sixin.castserver.mapper;

import com.sixin.castserver.domain.client;
import java.util.List;

/**
 * 客户端Mapper接口
 * 
 * @author txy
 * @date 2020-03-07
 */
public interface clientMapper 
{
    /**
     * 查询客户端
     * 
     * @param imei 客户端ID
     * @return 客户端
     */
    public client selectclientById(String imei);

    /**
     * 查询客户端列表
     * 
     * @param client 客户端
     * @return 客户端集合
     */
    public List<client> selectclientList(client client);

    /**
     * 新增客户端
     * 
     * @param client 客户端
     * @return 结果
     */
    public int insertclient(client client);

    /**
     * 修改客户端
     * 
     * @param client 客户端
     * @return 结果
     */
    public int updateclient(client client);

    /**
     * 删除客户端
     * 
     * @param imei 客户端ID
     * @return 结果
     */
    public int deleteclientById(String imei);

    /**
     * 批量删除客户端
     * 
     * @param imeis 需要删除的数据ID
     * @return 结果
     */
    public int deleteclientByIds(String[] imeis);
}
