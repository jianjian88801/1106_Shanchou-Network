
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 众筹
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/zhongchou")
public class ZhongchouController {
    private static final Logger logger = LoggerFactory.getLogger(ZhongchouController.class);

    @Autowired
    private ZhongchouService zhongchouService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private ShangpinService shangpinService;
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private CanyuzhongchouService canyuzhongchouService;



    /**
    * 众筹
    */
    @RequestMapping("/zhongchou")
    public R zhongchou(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("zhongchou方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(!"用户".equals(role))
            return R.error("您没有权限参与众筹");



//        yonghuId:localStorage.getItem("userid"),
//                zhongchouId:this.detail.id,
//                canyuMoney
        YonghuEntity yonghuEntity = yonghuService.selectById(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        Double canyuMoney = Double.valueOf(String.valueOf(params.get("canyuMoney")));
        if(canyuMoney == null)
            return R.error("参与金额不能为空");
        else if(canyuMoney <=0)
            return R.error("参与金额必须大于0");
        Double balance = yonghuEntity.getNewMoney()-canyuMoney;
        if(balance <0 )
            return R.error("当前账户金额不够,请充值后再参与");
        yonghuEntity.setNewMoney(balance);

        Integer zhongchouId = Integer.valueOf(String.valueOf(params.get("zhongchouId")));
        ZhongchouEntity zhongchouEntity = zhongchouService.selectById(zhongchouId);
        if(zhongchouEntity == null)
            return R.error("查不到众筹项目");
        zhongchouEntity.setYichouMoney(zhongchouEntity.getYichouMoney()+canyuMoney);

        CanyuzhongchouEntity canyuzhongchouEntity = new CanyuzhongchouEntity();

        canyuzhongchouEntity.setCreateTime(new Date());
        canyuzhongchouEntity.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        canyuzhongchouEntity.setJieshuTime(new Date());
        canyuzhongchouEntity.setZhongchouId(zhongchouId);
        canyuzhongchouEntity.setZizuMoney(canyuMoney);

        zhongchouService.updateById(zhongchouEntity);
        canyuzhongchouService.insert(canyuzhongchouEntity);
        yonghuService.updateById(yonghuEntity);

        return R.ok();
    }

    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("zhongchouDeleteStart",1);params.put("zhongchouDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = zhongchouService.queryPage(params);

        //字典表数据转换
        List<ZhongchouView> list =(List<ZhongchouView>)page.getList();
        for(ZhongchouView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZhongchouEntity zhongchou = zhongchouService.selectById(id);
        if(zhongchou !=null){
            //entity转view
            ZhongchouView view = new ZhongchouView();
            BeanUtils.copyProperties( zhongchou , view );//把实体数据重构到view中

                //级联表
                ShangpinEntity shangpin = shangpinService.selectById(zhongchou.getShangpinId());
                if(shangpin != null){
                    BeanUtils.copyProperties( shangpin , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setShangpinId(shangpin.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(zhongchou.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ZhongchouEntity zhongchou, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zhongchou:{}",this.getClass().getName(),zhongchou.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role)){
            zhongchou.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
            zhongchou.setZhongchouZhuangtaiTypes(1);
        }

        Wrapper<ZhongchouEntity> queryWrapper = new EntityWrapper<ZhongchouEntity>()
            .eq("yonghu_id", zhongchou.getYonghuId())
            .eq("shangpin_id", zhongchou.getShangpinId())
            .eq("zhongchou_uuid_number", zhongchou.getZhongchouUuidNumber())
            .eq("zhongchou_name", zhongchou.getZhongchouName())
            .eq("zhongchou_types", zhongchou.getZhongchouTypes())
            .eq("zhongchou_kucun_number", zhongchou.getZhongchouKucunNumber())
            .eq("zhongchou_clicknum", zhongchou.getZhongchouClicknum())
            .eq("zhongchou_zhuangtai_types", zhongchou.getZhongchouZhuangtaiTypes())
            .eq("zhongchou_chenggong_name", zhongchou.getZhongchouChenggongName())
            .eq("zhongchou_chenggong_phone", zhongchou.getZhongchouChenggongPhone())
            .eq("zhongchou_chenggong_address", zhongchou.getZhongchouChenggongAddress())
            .eq("zhongchou_chenggong_kuaidiname", zhongchou.getZhongchouChenggongKuaidiname())
            .eq("zhongchou_chenggong_kuaidiaddress", zhongchou.getZhongchouChenggongKuaidiaddress())
            .eq("zhongchou_kuaidi_types", zhongchou.getZhongchouKuaidiTypes())
            .eq("zhongchou_delete", zhongchou.getZhongchouDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhongchouEntity zhongchouEntity = zhongchouService.selectOne(queryWrapper);
        if(zhongchouEntity==null){
            zhongchou.setZhongchouClicknum(1);


            ShangpinEntity shangpinEntity = shangpinService.selectById(zhongchou.getShangpinId());
            zhongchou.setZhongchouMoney(zhongchou.getZhongchouKucunNumber() * shangpinEntity.getShangpinNewMoney());


            zhongchou.setInsertTime(new Date());
            zhongchou.setZhongchouDelete(1);
            zhongchou.setCreateTime(new Date());
            zhongchouService.insert(zhongchou);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZhongchouEntity zhongchou, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,zhongchou:{}",this.getClass().getName(),zhongchou.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            zhongchou.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<ZhongchouEntity> queryWrapper = new EntityWrapper<ZhongchouEntity>()
            .notIn("id",zhongchou.getId())
            .andNew()
            .eq("yonghu_id", zhongchou.getYonghuId())
            .eq("shangpin_id", zhongchou.getShangpinId())
            .eq("zhongchou_uuid_number", zhongchou.getZhongchouUuidNumber())
            .eq("zhongchou_name", zhongchou.getZhongchouName())
            .eq("zhongchou_types", zhongchou.getZhongchouTypes())
            .eq("zhongchou_kucun_number", zhongchou.getZhongchouKucunNumber())
            .eq("zhongchou_clicknum", zhongchou.getZhongchouClicknum())
            .eq("zhongchou_zhuangtai_types", zhongchou.getZhongchouZhuangtaiTypes())
            .eq("zhongchou_chenggong_name", zhongchou.getZhongchouChenggongName())
            .eq("zhongchou_chenggong_phone", zhongchou.getZhongchouChenggongPhone())
            .eq("zhongchou_chenggong_address", zhongchou.getZhongchouChenggongAddress())
            .eq("zhongchou_chenggong_kuaidiname", zhongchou.getZhongchouChenggongKuaidiname())
            .eq("zhongchou_chenggong_kuaidiaddress", zhongchou.getZhongchouChenggongKuaidiaddress())
            .eq("zhongchou_kuaidi_types", zhongchou.getZhongchouKuaidiTypes())
            .eq("zhongchou_delete", zhongchou.getZhongchouDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhongchouEntity zhongchouEntity = zhongchouService.selectOne(queryWrapper);
        if(zhongchouEntity==null){


            ShangpinEntity shangpinEntity = shangpinService.selectById(zhongchou.getShangpinId());
            zhongchou.setZhongchouMoney(zhongchou.getZhongchouKucunNumber() * shangpinEntity.getShangpinNewMoney());
            zhongchouService.updateById(zhongchou);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        ArrayList<ZhongchouEntity> list = new ArrayList<>();
        for(Integer id:ids){
            ZhongchouEntity zhongchouEntity = new ZhongchouEntity();
            zhongchouEntity.setId(id);
            zhongchouEntity.setZhongchouDelete(2);
            list.add(zhongchouEntity);
        }
        if(list != null && list.size() >0){
            zhongchouService.updateBatchById(list);
        }
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<ZhongchouEntity> zhongchouList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            ZhongchouEntity zhongchouEntity = new ZhongchouEntity();
//                            zhongchouEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            zhongchouEntity.setShangpinId(Integer.valueOf(data.get(0)));   //商品 要改的
//                            zhongchouEntity.setZhongchouUuidNumber(data.get(0));                    //众筹编号 要改的
//                            zhongchouEntity.setZhongchouName(data.get(0));                    //众筹名称 要改的
//                            zhongchouEntity.setZhongchouTypes(Integer.valueOf(data.get(0)));   //众筹类型 要改的
//                            zhongchouEntity.setZhongchouKucunNumber(Integer.valueOf(data.get(0)));   //众筹数量 要改的
//                            zhongchouEntity.setYichouMoney(data.get(0));                    //已筹金额 要改的
//                            zhongchouEntity.setZhongchouMoney(data.get(0));                    //众筹总额 要改的
//                            zhongchouEntity.setZhongchouClicknum(Integer.valueOf(data.get(0)));   //热度 要改的
//                            zhongchouEntity.setZhongchouZhuangtaiTypes(Integer.valueOf(data.get(0)));   //众筹状态 要改的
//                            zhongchouEntity.setZhongchouChenggongName(data.get(0));                    //收件人姓名 要改的
//                            zhongchouEntity.setZhongchouChenggongPhone(data.get(0));                    //收件人联系方式 要改的
//                            zhongchouEntity.setZhongchouChenggongAddress(data.get(0));                    //收件人地址 要改的
//                            zhongchouEntity.setZhongchouChenggongKuaidiname(data.get(0));                    //快递名称 要改的
//                            zhongchouEntity.setZhongchouChenggongKuaidiaddress(data.get(0));                    //快递号 要改的
//                            zhongchouEntity.setZhongchouKuaidiTypes(Integer.valueOf(data.get(0)));   //快递状态 要改的
//                            zhongchouEntity.setZhongchouContent("");//详情和图片
//                            zhongchouEntity.setInsertTime(date);//时间
//                            zhongchouEntity.setJieshuTime(sdf.parse(data.get(0)));          //众筹截止时间 要改的
//                            zhongchouEntity.setZhongchouDelete(1);//逻辑删除字段
//                            zhongchouEntity.setCreateTime(date);//时间
                            zhongchouList.add(zhongchouEntity);


                            //把要查询是否重复的字段放入map中
                                //众筹编号
                                if(seachFields.containsKey("zhongchouUuidNumber")){
                                    List<String> zhongchouUuidNumber = seachFields.get("zhongchouUuidNumber");
                                    zhongchouUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> zhongchouUuidNumber = new ArrayList<>();
                                    zhongchouUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("zhongchouUuidNumber",zhongchouUuidNumber);
                                }
                        }

                        //查询是否重复
                         //众筹编号
                        List<ZhongchouEntity> zhongchouEntities_zhongchouUuidNumber = zhongchouService.selectList(new EntityWrapper<ZhongchouEntity>().in("zhongchou_uuid_number", seachFields.get("zhongchouUuidNumber")).eq("zhongchou_delete", 1));
                        if(zhongchouEntities_zhongchouUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ZhongchouEntity s:zhongchouEntities_zhongchouUuidNumber){
                                repeatFields.add(s.getZhongchouUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [众筹编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        zhongchouService.insertBatch(zhongchouList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = zhongchouService.queryPage(params);

        //字典表数据转换
        List<ZhongchouView> list =(List<ZhongchouView>)page.getList();
        for(ZhongchouView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZhongchouEntity zhongchou = zhongchouService.selectById(id);
            if(zhongchou !=null){

                //点击数量加1
                zhongchou.setZhongchouClicknum(zhongchou.getZhongchouClicknum()+1);
                zhongchouService.updateById(zhongchou);

                //entity转view
                ZhongchouView view = new ZhongchouView();
                BeanUtils.copyProperties( zhongchou , view );//把实体数据重构到view中

                //级联表
                    ShangpinEntity shangpin = shangpinService.selectById(zhongchou.getShangpinId());
                if(shangpin != null){
                    BeanUtils.copyProperties( shangpin , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setShangpinId(shangpin.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(zhongchou.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody ZhongchouEntity zhongchou, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,zhongchou:{}",this.getClass().getName(),zhongchou.toString());
        Wrapper<ZhongchouEntity> queryWrapper = new EntityWrapper<ZhongchouEntity>()
            .eq("yonghu_id", zhongchou.getYonghuId())
            .eq("shangpin_id", zhongchou.getShangpinId())
            .eq("zhongchou_uuid_number", zhongchou.getZhongchouUuidNumber())
            .eq("zhongchou_name", zhongchou.getZhongchouName())
            .eq("zhongchou_types", zhongchou.getZhongchouTypes())
            .eq("zhongchou_kucun_number", zhongchou.getZhongchouKucunNumber())
            .eq("zhongchou_clicknum", zhongchou.getZhongchouClicknum())
            .eq("zhongchou_zhuangtai_types", zhongchou.getZhongchouZhuangtaiTypes())
            .eq("zhongchou_chenggong_name", zhongchou.getZhongchouChenggongName())
            .eq("zhongchou_chenggong_phone", zhongchou.getZhongchouChenggongPhone())
            .eq("zhongchou_chenggong_address", zhongchou.getZhongchouChenggongAddress())
            .eq("zhongchou_chenggong_kuaidiname", zhongchou.getZhongchouChenggongKuaidiname())
            .eq("zhongchou_chenggong_kuaidiaddress", zhongchou.getZhongchouChenggongKuaidiaddress())
            .eq("zhongchou_kuaidi_types", zhongchou.getZhongchouKuaidiTypes())
            .eq("zhongchou_delete", zhongchou.getZhongchouDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhongchouEntity zhongchouEntity = zhongchouService.selectOne(queryWrapper);
        if(zhongchouEntity==null){
            zhongchou.setInsertTime(new Date());
            zhongchou.setZhongchouDelete(1);
            zhongchou.setCreateTime(new Date());
        zhongchouService.insert(zhongchou);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
