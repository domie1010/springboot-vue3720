package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.JuanzengYuyueEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 物资捐赠
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("juanzeng_yuyue")
public class JuanzengYuyueView extends JuanzengYuyueEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 物资类型的值
	*/
	@ColumnInfo(comment="物资类型的字典表值",type="varchar(200)")
	private String wuziValue;
	/**
	* 报名状态的值
	*/
	@ColumnInfo(comment="报名状态的字典表值",type="varchar(200)")
	private String juanzengYuyueYesnoValue;

	//级联表 机构
		/**
		* 机构姓名
		*/

		@ColumnInfo(comment="机构姓名",type="varchar(200)")
		private String jigouName;
		/**
		* 机构手机号
		*/

		@ColumnInfo(comment="机构手机号",type="varchar(200)")
		private String jigouPhone;
		/**
		* 机构头像
		*/

		@ColumnInfo(comment="机构头像",type="varchar(200)")
		private String jigouPhoto;
		/**
		* 机构邮箱
		*/

		@ColumnInfo(comment="机构邮箱",type="varchar(200)")
		private String jigouEmail;
	//级联表 物资
		/**
		* 物资名称
		*/

		@ColumnInfo(comment="物资名称",type="varchar(200)")
		private String wuziName;
		/**
		* 物资编号
		*/

		@ColumnInfo(comment="物资编号",type="varchar(200)")
		private String wuziUuidNumber;
		/**
		* 物资照片
		*/

		@ColumnInfo(comment="物资照片",type="varchar(200)")
		private String wuziPhoto;
		/**
		* 物资介绍
		*/

		@ColumnInfo(comment="物资介绍",type="longtext")
		private String wuziContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer wuziDelete;
	//级联表 用户
		/**
		* 用户姓名
		*/

		@ColumnInfo(comment="用户姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 用户头像
		*/

		@ColumnInfo(comment="用户头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 用户邮箱
		*/

		@ColumnInfo(comment="用户邮箱",type="varchar(200)")
		private String yonghuEmail;

	//重复字段


	public JuanzengYuyueView() {

	}

	public JuanzengYuyueView(JuanzengYuyueEntity juanzengYuyueEntity) {
		try {
			BeanUtils.copyProperties(this, juanzengYuyueEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 物资类型的值
	*/
	public String getWuziValue() {
		return wuziValue;
	}
	/**
	* 设置： 物资类型的值
	*/
	public void setWuziValue(String wuziValue) {
		this.wuziValue = wuziValue;
	}
	//当前表的
	/**
	* 获取： 报名状态的值
	*/
	public String getJuanzengYuyueYesnoValue() {
		return juanzengYuyueYesnoValue;
	}
	/**
	* 设置： 报名状态的值
	*/
	public void setJuanzengYuyueYesnoValue(String juanzengYuyueYesnoValue) {
		this.juanzengYuyueYesnoValue = juanzengYuyueYesnoValue;
	}


	//级联表的get和set 机构

		/**
		* 获取： 机构姓名
		*/
		public String getJigouName() {
			return jigouName;
		}
		/**
		* 设置： 机构姓名
		*/
		public void setJigouName(String jigouName) {
			this.jigouName = jigouName;
		}

		/**
		* 获取： 机构手机号
		*/
		public String getJigouPhone() {
			return jigouPhone;
		}
		/**
		* 设置： 机构手机号
		*/
		public void setJigouPhone(String jigouPhone) {
			this.jigouPhone = jigouPhone;
		}

		/**
		* 获取： 机构头像
		*/
		public String getJigouPhoto() {
			return jigouPhoto;
		}
		/**
		* 设置： 机构头像
		*/
		public void setJigouPhoto(String jigouPhoto) {
			this.jigouPhoto = jigouPhoto;
		}

		/**
		* 获取： 机构邮箱
		*/
		public String getJigouEmail() {
			return jigouEmail;
		}
		/**
		* 设置： 机构邮箱
		*/
		public void setJigouEmail(String jigouEmail) {
			this.jigouEmail = jigouEmail;
		}
	//级联表的get和set 物资

		/**
		* 获取： 物资名称
		*/
		public String getWuziName() {
			return wuziName;
		}
		/**
		* 设置： 物资名称
		*/
		public void setWuziName(String wuziName) {
			this.wuziName = wuziName;
		}

		/**
		* 获取： 物资编号
		*/
		public String getWuziUuidNumber() {
			return wuziUuidNumber;
		}
		/**
		* 设置： 物资编号
		*/
		public void setWuziUuidNumber(String wuziUuidNumber) {
			this.wuziUuidNumber = wuziUuidNumber;
		}

		/**
		* 获取： 物资照片
		*/
		public String getWuziPhoto() {
			return wuziPhoto;
		}
		/**
		* 设置： 物资照片
		*/
		public void setWuziPhoto(String wuziPhoto) {
			this.wuziPhoto = wuziPhoto;
		}

		/**
		* 获取： 物资介绍
		*/
		public String getWuziContent() {
			return wuziContent;
		}
		/**
		* 设置： 物资介绍
		*/
		public void setWuziContent(String wuziContent) {
			this.wuziContent = wuziContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getWuziDelete() {
			return wuziDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setWuziDelete(Integer wuziDelete) {
			this.wuziDelete = wuziDelete;
		}
	//级联表的get和set 用户

		/**
		* 获取： 用户姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 用户头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 用户头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 用户邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 用户邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}

	//重复字段

	@Override
	public String toString() {
		return "JuanzengYuyueView{" +
			", wuziValue=" + wuziValue +
			", juanzengYuyueYesnoValue=" + juanzengYuyueYesnoValue +
			", jigouName=" + jigouName +
			", jigouPhone=" + jigouPhone +
			", jigouPhoto=" + jigouPhoto +
			", jigouEmail=" + jigouEmail +
			", wuziName=" + wuziName +
			", wuziUuidNumber=" + wuziUuidNumber +
			", wuziPhoto=" + wuziPhoto +
			", wuziContent=" + wuziContent +
			", wuziDelete=" + wuziDelete +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			"} " + super.toString();
	}
}
