package com.sixin.castserver.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.castserver.mapper.clientMapper;
import com.sixin.castserver.domain.client;
import com.sixin.castserver.service.IclientService;
import com.sixin.common.core.text.Convert;

/**
 * 客户端Service业务层处理
 * 
 * @author txy
 * @date 2020-03-07
 */
@Service
public class clientServiceImpl implements IclientService 
{
    @Autowired
    private clientMapper clientMapper;

    /**
     * 查询客户端
     * 
     * @param imei 客户端ID
     * @return 客户端
     */
    @Override
    public client selectclientById(String imei)
    {
        return clientMapper.selectclientById(imei);
    }

    /**
     * 查询客户端列表
     * 
     * @param client 客户端
     * @return 客户端
     */
    @Override
    public List<client> selectclientList(client client)
    {
        return clientMapper.selectclientList(client);
    }

    /**
     * 新增客户端
     * 
     * @param client 客户端
     * @return 结果
     */
    @Override
    public int insertclient(client client)
    {
        return clientMapper.insertclient(client);
    }

    /**
     * 修改客户端
     * 
     * @param client 客户端
     * @return 结果
     */
    @Override
    public int updateclient(client client)
    {
        return clientMapper.updateclient(client);
    }

    /**
     * 删除客户端对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteclientByIds(String ids)
    {
        return clientMapper.deleteclientByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户端信息
     * 
     * @param imei 客户端ID
     * @return 结果
     */
    @Override
    public int deleteclientById(String imei)
    {
        return clientMapper.deleteclientById(imei);
    }
}
