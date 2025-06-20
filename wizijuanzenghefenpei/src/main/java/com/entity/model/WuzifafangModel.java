package com.entity.model;

import com.entity.WuzifafangEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 发放
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class WuzifafangModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 物资
     */
    private Integer wuziId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 发放数量
     */
    private Integer wuzifafangShuliang;


    /**
     * 发放介绍
     */
    private String wuzifafangContent;


    /**
     * 逻辑删除
     */
    private Integer wuzifafangDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
