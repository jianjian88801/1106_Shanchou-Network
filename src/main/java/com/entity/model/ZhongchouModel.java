package com.entity.model;

import com.entity.ZhongchouEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 众筹
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ZhongchouModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 商品
     */
    private Integer shangpinId;


    /**
     * 众筹编号
     */
    private String zhongchouUuidNumber;


    /**
     * 众筹名称
     */
    private String zhongchouName;


    /**
     * 众筹类型
     */
    private Integer zhongchouTypes;


    /**
     * 众筹数量
     */
    private Integer zhongchouKucunNumber;


    /**
     * 已筹金额
     */
    private Double yichouMoney;


    /**
     * 众筹总额
     */
    private Double zhongchouMoney;


    /**
     * 热度
     */
    private Integer zhongchouClicknum;


    /**
     * 众筹状态
     */
    private Integer zhongchouZhuangtaiTypes;


    /**
     * 收件人姓名
     */
    private String zhongchouChenggongName;


    /**
     * 收件人联系方式
     */
    private String zhongchouChenggongPhone;


    /**
     * 收件人地址
     */
    private String zhongchouChenggongAddress;


    /**
     * 快递名称
     */
    private String zhongchouChenggongKuaidiname;


    /**
     * 快递号
     */
    private String zhongchouChenggongKuaidiaddress;


    /**
     * 快递状态
     */
    private Integer zhongchouKuaidiTypes;


    /**
     * 众筹详情
     */
    private String zhongchouContent;


    /**
     * 发起时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 众筹截止时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date jieshuTime;


    /**
     * 逻辑删除
     */
    private Integer zhongchouDelete;


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
	 * 获取：商品
	 */
    public Integer getShangpinId() {
        return shangpinId;
    }


    /**
	 * 设置：商品
	 */
    public void setShangpinId(Integer shangpinId) {
        this.shangpinId = shangpinId;
    }
    /**
	 * 获取：众筹编号
	 */
    public String getZhongchouUuidNumber() {
        return zhongchouUuidNumber;
    }


    /**
	 * 设置：众筹编号
	 */
    public void setZhongchouUuidNumber(String zhongchouUuidNumber) {
        this.zhongchouUuidNumber = zhongchouUuidNumber;
    }
    /**
	 * 获取：众筹名称
	 */
    public String getZhongchouName() {
        return zhongchouName;
    }


    /**
	 * 设置：众筹名称
	 */
    public void setZhongchouName(String zhongchouName) {
        this.zhongchouName = zhongchouName;
    }
    /**
	 * 获取：众筹类型
	 */
    public Integer getZhongchouTypes() {
        return zhongchouTypes;
    }


    /**
	 * 设置：众筹类型
	 */
    public void setZhongchouTypes(Integer zhongchouTypes) {
        this.zhongchouTypes = zhongchouTypes;
    }
    /**
	 * 获取：众筹数量
	 */
    public Integer getZhongchouKucunNumber() {
        return zhongchouKucunNumber;
    }


    /**
	 * 设置：众筹数量
	 */
    public void setZhongchouKucunNumber(Integer zhongchouKucunNumber) {
        this.zhongchouKucunNumber = zhongchouKucunNumber;
    }
    /**
	 * 获取：已筹金额
	 */
    public Double getYichouMoney() {
        return yichouMoney;
    }


    /**
	 * 设置：已筹金额
	 */
    public void setYichouMoney(Double yichouMoney) {
        this.yichouMoney = yichouMoney;
    }
    /**
	 * 获取：众筹总额
	 */
    public Double getZhongchouMoney() {
        return zhongchouMoney;
    }


    /**
	 * 设置：众筹总额
	 */
    public void setZhongchouMoney(Double zhongchouMoney) {
        this.zhongchouMoney = zhongchouMoney;
    }
    /**
	 * 获取：热度
	 */
    public Integer getZhongchouClicknum() {
        return zhongchouClicknum;
    }


    /**
	 * 设置：热度
	 */
    public void setZhongchouClicknum(Integer zhongchouClicknum) {
        this.zhongchouClicknum = zhongchouClicknum;
    }
    /**
	 * 获取：众筹状态
	 */
    public Integer getZhongchouZhuangtaiTypes() {
        return zhongchouZhuangtaiTypes;
    }


    /**
	 * 设置：众筹状态
	 */
    public void setZhongchouZhuangtaiTypes(Integer zhongchouZhuangtaiTypes) {
        this.zhongchouZhuangtaiTypes = zhongchouZhuangtaiTypes;
    }
    /**
	 * 获取：收件人姓名
	 */
    public String getZhongchouChenggongName() {
        return zhongchouChenggongName;
    }


    /**
	 * 设置：收件人姓名
	 */
    public void setZhongchouChenggongName(String zhongchouChenggongName) {
        this.zhongchouChenggongName = zhongchouChenggongName;
    }
    /**
	 * 获取：收件人联系方式
	 */
    public String getZhongchouChenggongPhone() {
        return zhongchouChenggongPhone;
    }


    /**
	 * 设置：收件人联系方式
	 */
    public void setZhongchouChenggongPhone(String zhongchouChenggongPhone) {
        this.zhongchouChenggongPhone = zhongchouChenggongPhone;
    }
    /**
	 * 获取：收件人地址
	 */
    public String getZhongchouChenggongAddress() {
        return zhongchouChenggongAddress;
    }


    /**
	 * 设置：收件人地址
	 */
    public void setZhongchouChenggongAddress(String zhongchouChenggongAddress) {
        this.zhongchouChenggongAddress = zhongchouChenggongAddress;
    }
    /**
	 * 获取：快递名称
	 */
    public String getZhongchouChenggongKuaidiname() {
        return zhongchouChenggongKuaidiname;
    }


    /**
	 * 设置：快递名称
	 */
    public void setZhongchouChenggongKuaidiname(String zhongchouChenggongKuaidiname) {
        this.zhongchouChenggongKuaidiname = zhongchouChenggongKuaidiname;
    }
    /**
	 * 获取：快递号
	 */
    public String getZhongchouChenggongKuaidiaddress() {
        return zhongchouChenggongKuaidiaddress;
    }


    /**
	 * 设置：快递号
	 */
    public void setZhongchouChenggongKuaidiaddress(String zhongchouChenggongKuaidiaddress) {
        this.zhongchouChenggongKuaidiaddress = zhongchouChenggongKuaidiaddress;
    }
    /**
	 * 获取：快递状态
	 */
    public Integer getZhongchouKuaidiTypes() {
        return zhongchouKuaidiTypes;
    }


    /**
	 * 设置：快递状态
	 */
    public void setZhongchouKuaidiTypes(Integer zhongchouKuaidiTypes) {
        this.zhongchouKuaidiTypes = zhongchouKuaidiTypes;
    }
    /**
	 * 获取：众筹详情
	 */
    public String getZhongchouContent() {
        return zhongchouContent;
    }


    /**
	 * 设置：众筹详情
	 */
    public void setZhongchouContent(String zhongchouContent) {
        this.zhongchouContent = zhongchouContent;
    }
    /**
	 * 获取：发起时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：发起时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：众筹截止时间
	 */
    public Date getJieshuTime() {
        return jieshuTime;
    }


    /**
	 * 设置：众筹截止时间
	 */
    public void setJieshuTime(Date jieshuTime) {
        this.jieshuTime = jieshuTime;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getZhongchouDelete() {
        return zhongchouDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setZhongchouDelete(Integer zhongchouDelete) {
        this.zhongchouDelete = zhongchouDelete;
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
