package com.dao;

import com.entity.ZhongchouLiuyanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhongchouLiuyanView;

/**
 * 众筹留言 Dao 接口
 *
 * @author 
 */
public interface ZhongchouLiuyanDao extends BaseMapper<ZhongchouLiuyanEntity> {

   List<ZhongchouLiuyanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
