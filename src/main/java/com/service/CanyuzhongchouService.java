package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.CanyuzhongchouEntity;
import com.utils.PageUtils;

import java.util.Map;

/**
 * 参与的众筹 服务类
 */
public interface CanyuzhongchouService extends IService<CanyuzhongchouEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
}