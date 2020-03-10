package com.sixin.web.controller.castserver;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.castserver.domain.log;
import com.sixin.castserver.service.IlogService;
import com.sixin.common.core.controller.BaseController;
import com.sixin.common.core.domain.AjaxResult;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.common.core.page.TableDataInfo;

/**
 * 终端日志信息Controller
 * 
 * @author txy
 * @date 2020-03-07
 */
@Controller
@RequestMapping("/castserver/log")
public class logController extends BaseController
{
    private String prefix = "castserver/log";

    @Autowired
    private IlogService logService;

    @RequiresPermissions("castserver:log:view")
    @GetMapping()
    public String log()
    {
        return prefix + "/log";
    }

    /**
     * 查询终端日志信息列表
     */
    @RequiresPermissions("castserver:log:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(log log)
    {
        startPage();
        List<log> list = logService.selectlogList(log);
        return getDataTable(list);
    }

    /**
     * 导出终端日志信息列表
     */
    @RequiresPermissions("castserver:log:export")
    @Log(title = "终端日志信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(log log)
    {
        List<log> list = logService.selectlogList(log);
        ExcelUtil<log> util = new ExcelUtil<log>(log.class);
        return util.exportExcel(list, "log");
    }

    /**
     * 新增终端日志信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存终端日志信息
     */
    @RequiresPermissions("castserver:log:add")
    @Log(title = "终端日志信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(log log)
    {
        return toAjax(logService.insertlog(log));
    }

    /**
     * 修改终端日志信息
     */
    @GetMapping("/edit/{logid}")
    public String edit(@PathVariable("logid") Long logid, ModelMap mmap)
    {
        log log = logService.selectlogById(logid);
        mmap.put("log", log);
        return prefix + "/edit";
    }

    /**
     * 修改保存终端日志信息
     */
    @RequiresPermissions("castserver:log:edit")
    @Log(title = "终端日志信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(log log)
    {
        return toAjax(logService.updatelog(log));
    }

    /**
     * 删除终端日志信息
     */
    @RequiresPermissions("castserver:log:remove")
    @Log(title = "终端日志信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(logService.deletelogByIds(ids));
    }
}
