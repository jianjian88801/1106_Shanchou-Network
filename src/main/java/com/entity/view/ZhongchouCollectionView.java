package com.entity.view;

import com.entity.ZhongchouCollectionEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 众筹收藏
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("zhongchou_collection")
public class ZhongchouCollectionView extends ZhongchouCollectionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 类型的值
		*/
		private String zhongchouCollectionValue;



		private String shangpinPhoto;



		//级联表 yonghu
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 用户手机号
			*/
			private String yonghuPhone;
			/**
			* 用户身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 用户头像
			*/
			private String yonghuPhoto;
			/**
			* 电子邮箱
			*/
			private String yonghuEmail;
			/**
			* 余额
			*/
			private Double newMoney;

		//级联表 zhongchou
			/**
			* 众筹 的 用户
			*/
			private Integer zhongchouYonghuId;
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
				* 众筹类型的值
				*/
				private String zhongchouValue;
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
				* 众筹状态的值
				*/
				private String zhongchouZhuangtaiValue;
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
				* 快递状态的值
				*/
				private String zhongchouKuaidiValue;
			/**
			* 众筹详情
			*/
			private String zhongchouContent;
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

	public ZhongchouCollectionView() {

	}

	public ZhongchouCollectionView(ZhongchouCollectionEntity zhongchouCollectionEntity) {
		try {
			BeanUtils.copyProperties(this, zhongchouCollectionEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 类型的值
			*/
			public String getZhongchouCollectionValue() {
				return zhongchouCollectionValue;
			}
			/**
			* 设置： 类型的值
			*/
			public void setZhongchouCollectionValue(String zhongchouCollectionValue) {
				this.zhongchouCollectionValue = zhongchouCollectionValue;
			}

















				//级联表的get和set yonghu

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
					* 获取： 电子邮箱
					*/
					public String getYonghuEmail() {
						return yonghuEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setYonghuEmail(String yonghuEmail) {
						this.yonghuEmail = yonghuEmail;
					}

					/**
					* 获取： 余额
					*/
					public Double getNewMoney() {
						return newMoney;
					}
					/**
					* 设置： 余额
					*/
					public void setNewMoney(Double newMoney) {
						this.newMoney = newMoney;
					}


				//级联表的get和set zhongchou

					/**
					* 获取：众筹 的 用户
					*/
					public Integer getZhongchouYonghuId() {
						return zhongchouYonghuId;
					}
					/**
					* 设置：众筹 的 用户
					*/
					public void setZhongchouYonghuId(Integer zhongchouYonghuId) {
						this.zhongchouYonghuId = zhongchouYonghuId;
					}



					/**
					* 获取： 众筹编号
					*/
					public String getZhongchouUuidNumber() {
						return zhongchouUuidNumber;
					}
					/**
					* 设置： 众筹编号
					*/
					public void setZhongchouUuidNumber(String zhongchouUuidNumber) {
						this.zhongchouUuidNumber = zhongchouUuidNumber;
					}

					/**
					* 获取： 众筹名称
					*/
					public String getZhongchouName() {
						return zhongchouName;
					}
					/**
					* 设置： 众筹名称
					*/
					public void setZhongchouName(String zhongchouName) {
						this.zhongchouName = zhongchouName;
					}

					/**
					* 获取： 众筹类型
					*/
					public Integer getZhongchouTypes() {
						return zhongchouTypes;
					}
					/**
					* 设置： 众筹类型
					*/
					public void setZhongchouTypes(Integer zhongchouTypes) {
						this.zhongchouTypes = zhongchouTypes;
					}


						/**
						* 获取： 众筹类型的值
						*/
						public String getZhongchouValue() {
							return zhongchouValue;
						}
						/**
						* 设置： 众筹类型的值
						*/
						public void setZhongchouValue(String zhongchouValue) {
							this.zhongchouValue = zhongchouValue;
						}

					/**
					* 获取： 众筹数量
					*/
					public Integer getZhongchouKucunNumber() {
						return zhongchouKucunNumber;
					}
					/**
					* 设置： 众筹数量
					*/
					public void setZhongchouKucunNumber(Integer zhongchouKucunNumber) {
						this.zhongchouKucunNumber = zhongchouKucunNumber;
					}

					/**
					* 获取： 已筹金额
					*/
					public Double getYichouMoney() {
						return yichouMoney;
					}
					/**
					* 设置： 已筹金额
					*/
					public void setYichouMoney(Double yichouMoney) {
						this.yichouMoney = yichouMoney;
					}

					/**
					* 获取： 众筹总额
					*/
					public Double getZhongchouMoney() {
						return zhongchouMoney;
					}
					/**
					* 设置： 众筹总额
					*/
					public void setZhongchouMoney(Double zhongchouMoney) {
						this.zhongchouMoney = zhongchouMoney;
					}

					/**
					* 获取： 热度
					*/
					public Integer getZhongchouClicknum() {
						return zhongchouClicknum;
					}
					/**
					* 设置： 热度
					*/
					public void setZhongchouClicknum(Integer zhongchouClicknum) {
						this.zhongchouClicknum = zhongchouClicknum;
					}

					/**
					* 获取： 众筹状态
					*/
					public Integer getZhongchouZhuangtaiTypes() {
						return zhongchouZhuangtaiTypes;
					}
					/**
					* 设置： 众筹状态
					*/
					public void setZhongchouZhuangtaiTypes(Integer zhongchouZhuangtaiTypes) {
						this.zhongchouZhuangtaiTypes = zhongchouZhuangtaiTypes;
					}


						/**
						* 获取： 众筹状态的值
						*/
						public String getZhongchouZhuangtaiValue() {
							return zhongchouZhuangtaiValue;
						}
						/**
						* 设置： 众筹状态的值
						*/
						public void setZhongchouZhuangtaiValue(String zhongchouZhuangtaiValue) {
							this.zhongchouZhuangtaiValue = zhongchouZhuangtaiValue;
						}

					/**
					* 获取： 收件人姓名
					*/
					public String getZhongchouChenggongName() {
						return zhongchouChenggongName;
					}
					/**
					* 设置： 收件人姓名
					*/
					public void setZhongchouChenggongName(String zhongchouChenggongName) {
						this.zhongchouChenggongName = zhongchouChenggongName;
					}

					/**
					* 获取： 收件人联系方式
					*/
					public String getZhongchouChenggongPhone() {
						return zhongchouChenggongPhone;
					}
					/**
					* 设置： 收件人联系方式
					*/
					public void setZhongchouChenggongPhone(String zhongchouChenggongPhone) {
						this.zhongchouChenggongPhone = zhongchouChenggongPhone;
					}

					/**
					* 获取： 收件人地址
					*/
					public String getZhongchouChenggongAddress() {
						return zhongchouChenggongAddress;
					}
					/**
					* 设置： 收件人地址
					*/
					public void setZhongchouChenggongAddress(String zhongchouChenggongAddress) {
						this.zhongchouChenggongAddress = zhongchouChenggongAddress;
					}

					/**
					* 获取： 快递名称
					*/
					public String getZhongchouChenggongKuaidiname() {
						return zhongchouChenggongKuaidiname;
					}
					/**
					* 设置： 快递名称
					*/
					public void setZhongchouChenggongKuaidiname(String zhongchouChenggongKuaidiname) {
						this.zhongchouChenggongKuaidiname = zhongchouChenggongKuaidiname;
					}

					/**
					* 获取： 快递号
					*/
					public String getZhongchouChenggongKuaidiaddress() {
						return zhongchouChenggongKuaidiaddress;
					}
					/**
					* 设置： 快递号
					*/
					public void setZhongchouChenggongKuaidiaddress(String zhongchouChenggongKuaidiaddress) {
						this.zhongchouChenggongKuaidiaddress = zhongchouChenggongKuaidiaddress;
					}

					/**
					* 获取： 快递状态
					*/
					public Integer getZhongchouKuaidiTypes() {
						return zhongchouKuaidiTypes;
					}
					/**
					* 设置： 快递状态
					*/
					public void setZhongchouKuaidiTypes(Integer zhongchouKuaidiTypes) {
						this.zhongchouKuaidiTypes = zhongchouKuaidiTypes;
					}


						/**
						* 获取： 快递状态的值
						*/
						public String getZhongchouKuaidiValue() {
							return zhongchouKuaidiValue;
						}
						/**
						* 设置： 快递状态的值
						*/
						public void setZhongchouKuaidiValue(String zhongchouKuaidiValue) {
							this.zhongchouKuaidiValue = zhongchouKuaidiValue;
						}

					/**
					* 获取： 众筹详情
					*/
					public String getZhongchouContent() {
						return zhongchouContent;
					}
					/**
					* 设置： 众筹详情
					*/
					public void setZhongchouContent(String zhongchouContent) {
						this.zhongchouContent = zhongchouContent;
					}

					/**
					* 获取： 众筹截止时间
					*/
					public Date getJieshuTime() {
						return jieshuTime;
					}
					/**
					* 设置： 众筹截止时间
					*/
					public void setJieshuTime(Date jieshuTime) {
						this.jieshuTime = jieshuTime;
					}

					/**
					* 获取： 逻辑删除
					*/
					public Integer getZhongchouDelete() {
						return zhongchouDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setZhongchouDelete(Integer zhongchouDelete) {
						this.zhongchouDelete = zhongchouDelete;
					}


	public String getShangpinPhoto() {
		return shangpinPhoto;
	}

	public void setShangpinPhoto(String shangpinPhoto) {
		this.shangpinPhoto = shangpinPhoto;
	}
}
