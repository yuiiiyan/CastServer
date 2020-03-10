package com.sixin.castserver.mapper;

import com.sixin.castserver.domain.log;
import java.util.List;

/**
 * 终端日志信息Mapper接口
 * 
 * @author txy
 * @date 2020-03-07
 */
public interface logMapper 
{
    /**
     * 查询终端日志信息
     * 
     * @param logid 终端日志信息ID
     * @return 终端日志信息
     */
    public log selectlogById(Long logid);

    /**
     * 查询终端日志信息列表
     * 
     * @param log 终端日志信息
     * @return 终端日志信息集合
     */
    public List<log> selectlogList(log log);

    /**
     * 新增终端日志信息
     * 
     * @param log 终端日志信息
     * @return 结果
     */
    public int insertlog(log log);

    /**
     * 修改终端日志信息
     * 
     * @param log 终端日志信息
     * @return 结果
     */
    public int updatelog(log log);

    /**
     * 删除终端日志信息
     * 
     * @param logid 终端日志信息ID
     * @return 结果
     */
    public int deletelogById(Long logid);

    /**
     * 批量删除终端日志信息
     * 
     * @param logids 需要删除的数据ID
     * @return 结果
     */
    public int deletelogByIds(String[] logids);
}
