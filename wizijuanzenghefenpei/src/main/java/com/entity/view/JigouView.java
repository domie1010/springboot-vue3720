package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.JigouEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 机构
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("jigou")
public class JigouView extends JigouEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表




	public JigouView() {

	}

	public JigouView(JigouEntity jigouEntity) {
		try {
			BeanUtils.copyProperties(this, jigouEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}







	@Override
	public String toString() {
		return "JigouView{" +
			"} " + super.toString();
	}
}
