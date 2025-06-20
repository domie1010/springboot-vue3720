package com.dao;

import com.entity.WuzifafangEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.WuzifafangView;

/**
 * 发放 Dao 接口
 *
 * @author 
 */
public interface WuzifafangDao extends BaseMapper<WuzifafangEntity> {

   List<WuzifafangView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
