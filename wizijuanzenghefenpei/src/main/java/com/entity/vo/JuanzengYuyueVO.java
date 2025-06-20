package com.entity.vo;

import com.entity.JuanzengYuyueEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 物资捐赠
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("juanzeng_yuyue")
public class JuanzengYuyueVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 报名编号
     */

    @TableField(value = "juanzeng_yuyue_uuid_number")
    private String juanzengYuyueUuidNumber;


    /**
     * 机构
     */

    @TableField(value = "jigou_id")
    private Integer jigouId;


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
     * 物资信息
     */

    @TableField(value = "juanzeng_yuyue_text")
    private String juanzengYuyueText;


    /**
     * 捐赠数量
     */

    @TableField(value = "juanzeng_yuyue_shuliang")
    private Integer juanzengYuyueShuliang;


    /**
     * 物资类型
     */

    @TableField(value = "wuzi_types")
    private Integer wuziTypes;


    /**
     * 报名状态
     */

    @TableField(value = "juanzeng_yuyue_yesno_types")
    private Integer juanzengYuyueYesnoTypes;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "juanzeng_yuyue_shenhe_time")
    private Date juanzengYuyueShenheTime;


    /**
     * 物资申报时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
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
	 * 设置：报名编号
	 */
    public String getJuanzengYuyueUuidNumber() {
        return juanzengYuyueUuidNumber;
    }


    /**
	 * 获取：报名编号
	 */

    public void setJuanzengYuyueUuidNumber(String juanzengYuyueUuidNumber) {
        this.juanzengYuyueUuidNumber = juanzengYuyueUuidNumber;
    }
    /**
	 * 设置：机构
	 */
    public Integer getJigouId() {
        return jigouId;
    }


    /**
	 * 获取：机构
	 */

    public void setJigouId(Integer jigouId) {
        this.jigouId = jigouId;
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
	 * 设置：物资信息
	 */
    public String getJuanzengYuyueText() {
        return juanzengYuyueText;
    }


    /**
	 * 获取：物资信息
	 */

    public void setJuanzengYuyueText(String juanzengYuyueText) {
        this.juanzengYuyueText = juanzengYuyueText;
    }
    /**
	 * 设置：捐赠数量
	 */
    public Integer getJuanzengYuyueShuliang() {
        return juanzengYuyueShuliang;
    }


    /**
	 * 获取：捐赠数量
	 */

    public void setJuanzengYuyueShuliang(Integer juanzengYuyueShuliang) {
        this.juanzengYuyueShuliang = juanzengYuyueShuliang;
    }
    /**
	 * 设置：物资类型
	 */
    public Integer getWuziTypes() {
        return wuziTypes;
    }


    /**
	 * 获取：物资类型
	 */

    public void setWuziTypes(Integer wuziTypes) {
        this.wuziTypes = wuziTypes;
    }
    /**
	 * 设置：报名状态
	 */
    public Integer getJuanzengYuyueYesnoTypes() {
        return juanzengYuyueYesnoTypes;
    }


    /**
	 * 获取：报名状态
	 */

    public void setJuanzengYuyueYesnoTypes(Integer juanzengYuyueYesnoTypes) {
        this.juanzengYuyueYesnoTypes = juanzengYuyueYesnoTypes;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getJuanzengYuyueShenheTime() {
        return juanzengYuyueShenheTime;
    }


    /**
	 * 获取：审核时间
	 */

    public void setJuanzengYuyueShenheTime(Date juanzengYuyueShenheTime) {
        this.juanzengYuyueShenheTime = juanzengYuyueShenheTime;
    }
    /**
	 * 设置：物资申报时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：物资申报时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
