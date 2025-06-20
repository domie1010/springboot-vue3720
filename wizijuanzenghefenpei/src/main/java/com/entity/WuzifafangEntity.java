package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 发放
 *
 * @author 
 * @email
 */
@TableName("wuzifafang")
public class WuzifafangEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public WuzifafangEntity() {

	}

	public WuzifafangEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 物资
     */
    @ColumnInfo(comment="物资",type="int(11)")
    @TableField(value = "wuzi_id")

    private Integer wuziId;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 发放数量
     */
    @ColumnInfo(comment="发放数量",type="int(11)")
    @TableField(value = "wuzifafang_shuliang")

    private Integer wuzifafangShuliang;


    /**
     * 发放介绍
     */
    @ColumnInfo(comment="发放介绍",type="longtext")
    @TableField(value = "wuzifafang_content")

    private String wuzifafangContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "wuzifafang_delete")

    private Integer wuzifafangDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：物资
	 */
    public Integer getWuziId() {
        return wuziId;
    }
    /**
	 * 设置：物资
	 */

    public void setWuziId(Integer wuziId) {
        this.wuziId = wuziId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：发放数量
	 */
    public Integer getWuzifafangShuliang() {
        return wuzifafangShuliang;
    }
    /**
	 * 设置：发放数量
	 */

    public void setWuzifafangShuliang(Integer wuzifafangShuliang) {
        this.wuzifafangShuliang = wuzifafangShuliang;
    }
    /**
	 * 获取：发放介绍
	 */
    public String getWuzifafangContent() {
        return wuzifafangContent;
    }
    /**
	 * 设置：发放介绍
	 */

    public void setWuzifafangContent(String wuzifafangContent) {
        this.wuzifafangContent = wuzifafangContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getWuzifafangDelete() {
        return wuzifafangDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setWuzifafangDelete(Integer wuzifafangDelete) {
        this.wuzifafangDelete = wuzifafangDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Wuzifafang{" +
            ", id=" + id +
            ", wuziId=" + wuziId +
            ", yonghuId=" + yonghuId +
            ", wuzifafangShuliang=" + wuzifafangShuliang +
            ", wuzifafangContent=" + wuzifafangContent +
            ", wuzifafangDelete=" + wuzifafangDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
