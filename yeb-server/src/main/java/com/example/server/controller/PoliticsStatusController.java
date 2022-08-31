package com.example.server.controller;


import com.example.server.pojo.PoliticsStatus;
import com.example.server.pojo.RespBean;
import com.example.server.service.IPoliticsStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mango
 * @since 2022-08-31
 */
@Api(tags = "政治面貌管理（测试）")
@RestController
@RequestMapping("/politics-status/all")
public class PoliticsStatusController {
    @Autowired
    private IPoliticsStatusService politicsStatusService;

    @ApiOperation(value = "获取所有的政治面貌列表")
    @GetMapping("/")
    public List<PoliticsStatus> getAllPoliticsStatus(){
        return politicsStatusService.list();
    }

    @ApiOperation(value = "添加政治面貌")
    @PostMapping("/")
    public RespBean addPoliticsStatus(@RequestBody PoliticsStatus politicsStatus){
        if(politicsStatusService.save(politicsStatus)){
            return RespBean.success("添加政治面貌成功！");
        }
        return RespBean.error("添加政治面貌失败！");
    }

    @ApiOperation(value = "删除政治面貌")
    @DeleteMapping("/{id}")
    public RespBean deletePoliticsStatus(@PathVariable Integer id){
       if(politicsStatusService.removeById(id)){
           return RespBean.success("删除政治面貌成功！");
       }
        return RespBean.error("删除政治面貌成功！");
    }

    @ApiOperation(value = "更新政治面貌")
    @PutMapping("/")
    public RespBean updatePoliticsStatus(@RequestBody PoliticsStatus politicsStatus){
        if(politicsStatusService.updateById(politicsStatus)){
            return RespBean.success("更新政治面貌成功！");
        }
        return RespBean.error("更新政治面貌失败！");
    }
}
