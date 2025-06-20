package com.entity.vo;

import com.entity.JigouEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 机构
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("jigou")
public class JigouVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 账户
     */

    @TableField(value = "username")
    private String username;


    /**
     * 密码
     */

    @TableField(value = "password")
    private String password;


    /**
     * 机构姓名
     */

    @TableField(value = "jigou_name")
    private String jigouName;


    /**
     * 机构手机号
     */

    @TableField(value = "jigou_phone")
    private String jigouPhone;


    /**
     * 机构头像
     */

    @TableField(value = "jigou_photo")
    private String jigouPhoto;


    /**
     * 机构邮箱
     */

    @TableField(value = "jigou_email")
    private String jigouEmail;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：机构姓名
	 */
    public String getJigouName() {
        return jigouName;
    }


    /**
	 * 获取：机构姓名
	 */

    public void setJigouName(String jigouName) {
        this.jigouName = jigouName;
    }
    /**
	 * 设置：机构手机号
	 */
    public String getJigouPhone() {
        return jigouPhone;
    }


    /**
	 * 获取：机构手机号
	 */

    public void setJigouPhone(String jigouPhone) {
        this.jigouPhone = jigouPhone;
    }
    /**
	 * 设置：机构头像
	 */
    public String getJigouPhoto() {
        return jigouPhoto;
    }


    /**
	 * 获取：机构头像
	 */

    public void setJigouPhoto(String jigouPhoto) {
        this.jigouPhoto = jigouPhoto;
    }
    /**
	 * 设置：机构邮箱
	 */
    public String getJigouEmail() {
        return jigouEmail;
    }


    /**
	 * 获取：机构邮箱
	 */

    public void setJigouEmail(String jigouEmail) {
        this.jigouEmail = jigouEmail;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
