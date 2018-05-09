package com.tuyongkang.blog.controller;

import com.tuyongkang.blog.entity.SysNavEntity;
import com.tuyongkang.blog.model.vo.ResponseVo;
import com.tuyongkang.blog.service.SysNavService;
import com.tuyongkang.blog.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 导航栏管理请求控制器
 */
@RestController
@RequestMapping("/sys/sysNav")
public class SysNavController {

    @Autowired
    private SysNavService sysNavService;

    /**
     * 添加一个导航项
     * @param sysNavEntity
     * @return
     */
    @RequestMapping("/addOrEditSysNav")
    public ResponseVo addOrEditSysNav(SysNavEntity sysNavEntity){
        try {
            sysNavService.saveOrUpdate(sysNavEntity);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.renderError();
        }
        return ResponseUtil.renderSuccess();
    }


    /**
     * 删除一个导航项
     * @param sysNavEntity
     * @return
     */
    @RequestMapping("/deleteById")
    public ResponseVo deleteById(SysNavEntity sysNavEntity){
        try {
            sysNavService.deleteById(sysNavEntity.getId());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.renderError();
        }
        return ResponseUtil.renderSuccess();
    }

    /**
     * 查找所有的导航项
     * @return
     */
    @RequestMapping("/findAll")
    public ResponseVo findAll(){
        List<SysNavEntity> list;
        try {
            list = sysNavService.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.renderError();
        }
        return ResponseUtil.renderSuccess(list);
    }

}
