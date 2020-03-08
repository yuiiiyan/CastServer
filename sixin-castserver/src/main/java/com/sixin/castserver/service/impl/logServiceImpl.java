package com.sixin.castserver.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.castserver.mapper.logMapper;
import com.sixin.castserver.domain.log;
import com.sixin.castserver.service.IlogService;
import com.sixin.common.core.text.Convert;

/**
 * 终端日志信息Service业务层处理
 * 
 * @author txy
 * @date 2020-03-07
 */
@Service
public class logServiceImpl implements IlogService 
{
    @Autowired
    private logMapper logMapper;

    /**
     * 查询终端日志信息
     * 
     * @param logid 终端日志信息ID
     * @return 终端日志信息
     */
    @Override
    public log selectlogById(Long logid)
    {
        return logMapper.selectlogById(logid);
    }

    /**
     * 查询终端日志信息列表
     * 
     * @param log 终端日志信息
     * @return 终端日志信息
     */
    @Override
    public List<log> selectlogList(log log)
    {
        return logMapper.selectlogList(log);
    }

    /**
     * 新增终端日志信息
     * 
     * @param log 终端日志信息
     * @return 结果
     */
    @Override
    public int insertlog(log log)
    {
        return logMapper.insertlog(log);
    }

    /**
     * 修改终端日志信息
     * 
     * @param log 终端日志信息
     * @return 结果
     */
    @Override
    public int updatelog(log log)
    {
        return logMapper.updatelog(log);
    }

    /**
     * 删除终端日志信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletelogByIds(String ids)
    {
        return logMapper.deletelogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除终端日志信息信息
     * 
     * @param logid 终端日志信息ID
     * @return 结果
     */
    @Override
    public int deletelogById(Long logid)
    {
        return logMapper.deletelogById(logid);
    }
}
