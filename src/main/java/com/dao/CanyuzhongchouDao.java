package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.CanyuzhongchouEntity;
import com.entity.view.CanyuzhongchouView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 参与的众筹 Dao 接口
 *
 * @author 
 */
public interface CanyuzhongchouDao extends BaseMapper<CanyuzhongchouEntity> {

   List<CanyuzhongchouView> selectListView(Pagination page, @Param("params") Map<String, Object> params);

}
