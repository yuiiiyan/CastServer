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
import com.sixin.castserver.domain.client;
import com.sixin.castserver.service.IclientService;
import com.sixin.common.core.controller.BaseController;
import com.sixin.common.core.domain.AjaxResult;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.common.core.page.TableDataInfo;

/**
 * 客户端Controller
 * 
 * @author txy
 * @date 2020-03-07
 */
@Controller
@RequestMapping("/castserver/client")
public class clientController extends BaseController
{
    private String prefix = "castserver/client";

    @Autowired
    private IclientService clientService;

    @RequiresPermissions("castserver:client:view")
    @GetMapping()
    public String client()
    {
        return prefix + "/client";
    }

    /**
     * 查询客户端列表
     */
    @RequiresPermissions("castserver:client:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(client client)
    {
        startPage();
        List<client> list = clientService.selectclientList(client);
        return getDataTable(list);
    }

    /**
     * 导出客户端列表
     */
    @RequiresPermissions("castserver:client:export")
    @Log(title = "客户端", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(client client)
    {
        List<client> list = clientService.selectclientList(client);
        ExcelUtil<client> util = new ExcelUtil<client>(client.class);
        return util.exportExcel(list, "client");
    }

    /**
     * 新增客户端
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存客户端
     */
    @RequiresPermissions("castserver:client:add")
    @Log(title = "客户端", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(client client)
    {
        return toAjax(clientService.insertclient(client));
    }

    /**
     * 修改客户端
     */
    @GetMapping("/edit/{imei}")
    public String edit(@PathVariable("imei") String imei, ModelMap mmap)
    {
        client client = clientService.selectclientById(imei);
        mmap.put("client", client);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户端
     */
    @RequiresPermissions("castserver:client:edit")
    @Log(title = "客户端", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(client client)
    {
        return toAjax(clientService.updateclient(client));
    }

    /**
     * 删除客户端
     */
    @RequiresPermissions("castserver:client:remove")
    @Log(title = "客户端", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(clientService.deleteclientByIds(ids));
    }
}
