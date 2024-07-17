package com.dao;

import com.entity.ZhongchouEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhongchouView;

/**
 * 众筹 Dao 接口
 *
 * @author 
 */
public interface ZhongchouDao extends BaseMapper<ZhongchouEntity> {

   List<ZhongchouView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
