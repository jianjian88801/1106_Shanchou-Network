package com.thread;

import com.alibaba.fastjson.JSONObject;
import com.entity.ZhongchouEntity;
import com.entity.view.ZhongchouView;
import com.service.ZhongchouService;
import com.utils.PageUtils;
import com.utils.R;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 线程执行方法（做一些项目启动后 一直要执行的操作，比如根据时间自动更改订单状态，比如订单签收30天自动收货功能，比如根据时间来更改状态）
 */
public class MyThreadMethod extends Thread  {



    private ZhongchouService zhongchouService;


    public void run() {
        while (!this.isInterrupted()) {// 线程未中断执行循环

//params.put("yonghuId",request.getSession().getAttribute("userId"));

            System.out.println("----------------------------------------更新众筹状态----------------------------------------");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = simpleDateFormat.format(new Date());

            /**
             * 把正在众筹的项目并且众筹金额够的就
             */
            Map<String, Object> params1 = new HashMap<>();
            params1.put("zhongchouZhuangtaiTypes",1);
            params1.put("flag11111","buweikong");
            params1.put("zhongchouDeleteStart",1);params1.put("zhongchouDeleteEnd",1);
            params1.put("orderBy","id");
            PageUtils page1 = zhongchouService.queryPage(params1);//查出状态为正在众筹 并且超过众筹截止日期的
            List<ZhongchouView> faqidezhongchouList1 =(List<ZhongchouView>)page1.getList();
            if(faqidezhongchouList1.size()>0){
                List<ZhongchouEntity> zhongchouEntityList1 = new ArrayList<>();
                for(ZhongchouView z:faqidezhongchouList1){
                    ZhongchouEntity zhongchouEntity = new ZhongchouEntity();
                    zhongchouEntity.setId(z.getId());
                    zhongchouEntity.setZhongchouZhuangtaiTypes(2);
                    zhongchouEntity.setZhongchouKuaidiTypes(2);
                    zhongchouEntity.setZhongchouChenggongKuaidiname("顺丰快递");
                    zhongchouEntity.setZhongchouChenggongKuaidiaddress("sf"+new Date().getTime());
                    zhongchouEntityList1.add(zhongchouEntity);
                }
                System.out.println("--------------------------------------------------------------------------------"+zhongchouEntityList1.size()+"个商品众筹成功了");
                zhongchouService.updateBatchById(zhongchouEntityList1);
            }


            /**
             * 把当前正在众筹的超过截止日期的给改为众筹失败
             */
            Map<String, Object> params = new HashMap<>();
            params.put("jieshuTimeEnd",dateString);
            params.put("zhongchouZhuangtaiTypes",1);
            params.put("zhongchouDeleteStart",1);params.put("zhongchouDeleteEnd",1);
            params.put("orderBy","id");
            PageUtils page = zhongchouService.queryPage(params);//查出状态为正在众筹 并且超过众筹截止日期的
            //字典表数据转换
            List<ZhongchouView> faqidezhongchouList =(List<ZhongchouView>)page.getList();
            if(faqidezhongchouList.size()>0){
                List<ZhongchouEntity> zhongchouEntityList = new ArrayList<>();
                for(ZhongchouView z:faqidezhongchouList){
                    ZhongchouEntity zhongchouEntity = new ZhongchouEntity();
                    zhongchouEntity.setId(z.getId());
                    zhongchouEntity.setZhongchouZhuangtaiTypes(3);
                    zhongchouEntityList.add(zhongchouEntity);
                }
                System.out.println("--------------------------------------------------------------------------------"+zhongchouEntityList.size()+"个商品众筹失败了");
                zhongchouService.updateBatchById(zhongchouEntityList);
            }





            try {
                Thread.sleep(5000); //每隔2000ms执行一次
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//			 ------------------ 开始执行 ---------------------------
//            System.out.println("线程执行中:" + System.currentTimeMillis());
        }
    }


    public ZhongchouService getZhongchouService() {
        return zhongchouService;
    }

    public void setZhongchouService(ZhongchouService zhongchouService) {
        this.zhongchouService = zhongchouService;
    }
}
