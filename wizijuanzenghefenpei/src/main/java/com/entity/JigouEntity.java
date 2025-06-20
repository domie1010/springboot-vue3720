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
 * 机构
 *
 * @author 
 * @email
 */
@TableName("jigou")
public class JigouEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JigouEntity() {

	}

	public JigouEntity(T t) {
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
     * 账户
     */
    @ColumnInfo(comment="账户",type="varchar(200)")
    @TableField(value = "username")

    private String username;


    /**
     * 密码
     */
    @ColumnInfo(comment="密码",type="varchar(200)")
    @TableField(value = "password")

    private String password;


    /**
     * 机构姓名
     */
    @ColumnInfo(comment="机构姓名",type="varchar(200)")
    @TableField(value = "jigou_name")

    private String jigouName;


    /**
     * 机构手机号
     */
    @ColumnInfo(comment="机构手机号",type="varchar(200)")
    @TableField(value = "jigou_phone")

    private String jigouPhone;


    /**
     * 机构头像
     */
    @ColumnInfo(comment="机构头像",type="varchar(200)")
    @TableField(value = "jigou_photo")

    private String jigouPhoto;


    /**
     * 机构邮箱
     */
    @ColumnInfo(comment="机构邮箱",type="varchar(200)")
    @TableField(value = "jigou_email")

    private String jigouEmail;


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
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }
    /**
	 * 设置：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }
    /**
	 * 设置：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：机构姓名
	 */
    public String getJigouName() {
        return jigouName;
    }
    /**
	 * 设置：机构姓名
	 */

    public void setJigouName(String jigouName) {
        this.jigouName = jigouName;
    }
    /**
	 * 获取：机构手机号
	 */
    public String getJigouPhone() {
        return jigouPhone;
    }
    /**
	 * 设置：机构手机号
	 */

    public void setJigouPhone(String jigouPhone) {
        this.jigouPhone = jigouPhone;
    }
    /**
	 * 获取：机构头像
	 */
    public String getJigouPhoto() {
        return jigouPhoto;
    }
    /**
	 * 设置：机构头像
	 */

    public void setJigouPhoto(String jigouPhoto) {
        this.jigouPhoto = jigouPhoto;
    }
    /**
	 * 获取：机构邮箱
	 */
    public String getJigouEmail() {
        return jigouEmail;
    }
    /**
	 * 设置：机构邮箱
	 */

    public void setJigouEmail(String jigouEmail) {
        this.jigouEmail = jigouEmail;
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
        return "Jigou{" +
            ", id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", jigouName=" + jigouName +
            ", jigouPhone=" + jigouPhone +
            ", jigouPhoto=" + jigouPhoto +
            ", jigouEmail=" + jigouEmail +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
