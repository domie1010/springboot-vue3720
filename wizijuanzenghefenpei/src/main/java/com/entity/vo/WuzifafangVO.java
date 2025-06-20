package com.entity.vo;

import com.entity.WuzifafangEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 发放
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("wuzifafang")
public class WuzifafangVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 物资
     */

    @TableField(value = "wuzi_id")
    private Integer wuziId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 发放数量
     */

    @TableField(value = "wuzifafang_shuliang")
    private Integer wuzifafangShuliang;


    /**
     * 发放介绍
     */

    @TableField(value = "wuzifafang_content")
    private String wuzifafangContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "wuzifafang_delete")
    private Integer wuzifafangDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 设置：物资
	 */
    public Integer getWuziId() {
        return wuziId;
    }


    /**
	 * 获取：物资
	 */

    public void setWuziId(Integer wuziId) {
        this.wuziId = wuziId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：发放数量
	 */
    public Integer getWuzifafangShuliang() {
        return wuzifafangShuliang;
    }


    /**
	 * 获取：发放数量
	 */

    public void setWuzifafangShuliang(Integer wuzifafangShuliang) {
        this.wuzifafangShuliang = wuzifafangShuliang;
    }
    /**
	 * 设置：发放介绍
	 */
    public String getWuzifafangContent() {
        return wuzifafangContent;
    }


    /**
	 * 获取：发放介绍
	 */

    public void setWuzifafangContent(String wuzifafangContent) {
        this.wuzifafangContent = wuzifafangContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getWuzifafangDelete() {
        return wuzifafangDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setWuzifafangDelete(Integer wuzifafangDelete) {
        this.wuzifafangDelete = wuzifafangDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
