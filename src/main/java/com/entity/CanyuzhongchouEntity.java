package com.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * 参与的众筹
 *
 * @author 
 * @email
 */
@TableName("canyuzhongchou")
public class CanyuzhongchouEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public CanyuzhongchouEntity() {

	}

	public CanyuzhongchouEntity(T t) {
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
    @TableField(value = "id")

    private Integer id;


    /**
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 众筹
     */
    @TableField(value = "zhongchou_id")

    private Integer zhongchouId;


    /**
     * 参与金额
     */
    @TableField(value = "zizu_money")

    private Double zizuMoney;


    /**
     * 参与时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "jieshu_time")

    private Date jieshuTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 设置：众筹
	 */
    public Integer getZhongchouId() {
        return zhongchouId;
    }
    /**
	 * 获取：众筹
	 */

    public void setZhongchouId(Integer zhongchouId) {
        this.zhongchouId = zhongchouId;
    }
    /**
	 * 设置：参与金额
	 */
    public Double getZizuMoney() {
        return zizuMoney;
    }
    /**
	 * 获取：参与金额
	 */

    public void setZizuMoney(Double zizuMoney) {
        this.zizuMoney = zizuMoney;
    }
    /**
	 * 设置：参与时间
	 */
    public Date getJieshuTime() {
        return jieshuTime;
    }
    /**
	 * 获取：参与时间
	 */

    public void setJieshuTime(Date jieshuTime) {
        this.jieshuTime = jieshuTime;
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

    @Override
    public String toString() {
        return "Canyuzhongchou{" +
            "id=" + id +
            ", yonghuId=" + yonghuId +
            ", zhongchouId=" + zhongchouId +
            ", zizuMoney=" + zizuMoney +
            ", jieshuTime=" + jieshuTime +
            ", createTime=" + createTime +
        "}";
    }
}
