package com.entity.model;

import com.entity.JigouEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 机构
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JigouModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 机构姓名
     */
    private String jigouName;


    /**
     * 机构手机号
     */
    private String jigouPhone;


    /**
     * 机构头像
     */
    private String jigouPhoto;


    /**
     * 机构邮箱
     */
    private String jigouEmail;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
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

    }
