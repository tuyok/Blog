package com.tuyongkang.blog.controller.sys;

import com.tuyongkang.blog.model.entity.SysMenuEntity;
import com.tuyongkang.blog.model.vo.ResponseVo;
import com.tuyongkang.blog.service.sys.SysMenuService;
import com.tuyongkang.blog.util.ResponseUtil;
import com.tuyongkang.blog.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统 菜单管理 请求控制器
 */
@RestController
@RequestMapping("/sys/sysMenu")
public class SysMenuController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 添加一个菜单
     * @param sysMenuEntity
     * @return
     */
    @RequestMapping("/add")
    public ResponseVo add(SysMenuEntity sysMenuEntity){
        logger.info("进入SysMenuController.add()方法，方法入参：{}",sysMenuEntity);
        try{
            sysMenuService.add(sysMenuEntity);
        }catch (IllegalArgumentException e){
            return ResponseUtil.renderInfo("请求的参数不合法");
        }catch (BusinessException e){
            return ResponseUtil.renderInfo(e.getMessage());
        }catch (Exception e){
            logger.info("系统异常，异常信息：{}",e.getMessage(),e);
            return ResponseUtil.renderError();
        }
        return ResponseUtil.renderSuccess();
    }

    /**
     * 编辑一个菜单
     * @param sysMenuEntity
     * @return
     */
    @RequestMapping("/edit")
    public ResponseVo edit(SysMenuEntity sysMenuEntity){
        logger.info("进入SysMenuController.edit()方法，方法入参：{}",sysMenuEntity);
        try{
            sysMenuService.edit(sysMenuEntity);
        }catch (IllegalArgumentException e){
            return ResponseUtil.renderInfo("请求的参数不合法");
        }catch (BusinessException e){
            return ResponseUtil.renderInfo(e.getMessage());
        }catch (Exception e){
            logger.info("系统异常，异常信息：{}",e.getMessage(),e);
            return ResponseUtil.renderError();
        }
        return ResponseUtil.renderSuccess();
    }

    /**
     * 查询所有的菜单
     * @return
     */
    @RequestMapping("findAll")
    public ResponseVo<List<SysMenuEntity>> findAll(){
        logger.info("进入SysMenuController.findAll()方法，方法入参：{}","");
        List<SysMenuEntity> result;
        try{
            result = sysMenuService.findAll();
        }catch (IllegalArgumentException e){
            return ResponseUtil.renderInfo("请求的参数不合法");
        }catch (BusinessException e){
            return ResponseUtil.renderInfo(e.getMessage());
        }catch (Exception e){
            logger.info("系统异常，异常信息：{}",e.getMessage(),e);
            return ResponseUtil.renderError();
        }
        return ResponseUtil.renderSuccess(result);
    }

}
