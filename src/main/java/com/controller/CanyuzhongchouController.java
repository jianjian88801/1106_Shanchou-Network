
package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.CanyuzhongchouEntity;
import com.entity.YonghuEntity;
import com.entity.ZhongchouEntity;
import com.entity.view.CanyuzhongchouView;
import com.service.*;
import com.utils.PageUtils;
import com.utils.PoiUtil;
import com.utils.R;
import com.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 参与的众筹
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/canyuzhongchou")
public class CanyuzhongchouController {
    private static final Logger logger = LoggerFactory.getLogger(CanyuzhongchouController.class);

    @Autowired
    private CanyuzhongchouService canyuzhongchouService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private ZhongchouService zhongchouService;



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
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = canyuzhongchouService.queryPage(params);

        //字典表数据转换
        List<CanyuzhongchouView> list =(List<CanyuzhongchouView>)page.getList();
        for(CanyuzhongchouView c:list){
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
        CanyuzhongchouEntity canyuzhongchou = canyuzhongchouService.selectById(id);
        if(canyuzhongchou !=null){
            //entity转view
            CanyuzhongchouView view = new CanyuzhongchouView();
            BeanUtils.copyProperties( canyuzhongchou , view );//把实体数据重构到view中

                //级联表
                YonghuEntity yonghu = yonghuService.selectById(canyuzhongchou.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                ZhongchouEntity zhongchou = zhongchouService.selectById(canyuzhongchou.getZhongchouId());
                if(zhongchou != null){
                    BeanUtils.copyProperties( zhongchou , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setZhongchouId(zhongchou.getId());
                    view.setZhongchouYonghuId(zhongchou.getYonghuId());
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
    public R save(@RequestBody CanyuzhongchouEntity canyuzhongchou, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,canyuzhongchou:{}",this.getClass().getName(),canyuzhongchou.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            canyuzhongchou.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<CanyuzhongchouEntity> queryWrapper = new EntityWrapper<CanyuzhongchouEntity>()
            .eq("yonghu_id", canyuzhongchou.getYonghuId())
            .eq("zhongchou_id", canyuzhongchou.getZhongchouId())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        CanyuzhongchouEntity canyuzhongchouEntity = canyuzhongchouService.selectOne(queryWrapper);
        if(canyuzhongchouEntity==null){
            canyuzhongchou.setCreateTime(new Date());
            canyuzhongchouService.insert(canyuzhongchou);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody CanyuzhongchouEntity canyuzhongchou, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,canyuzhongchou:{}",this.getClass().getName(),canyuzhongchou.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            canyuzhongchou.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<CanyuzhongchouEntity> queryWrapper = new EntityWrapper<CanyuzhongchouEntity>()
            .notIn("id",canyuzhongchou.getId())
            .andNew()
            .eq("yonghu_id", canyuzhongchou.getYonghuId())
            .eq("zhongchou_id", canyuzhongchou.getZhongchouId())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        CanyuzhongchouEntity canyuzhongchouEntity = canyuzhongchouService.selectOne(queryWrapper);
        if(canyuzhongchouEntity==null){
            canyuzhongchouService.updateById(canyuzhongchou);//根据id更新
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
        canyuzhongchouService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save(String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<CanyuzhongchouEntity> canyuzhongchouList = new ArrayList<>();//上传的东西
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
                            CanyuzhongchouEntity canyuzhongchouEntity = new CanyuzhongchouEntity();
//                            canyuzhongchouEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            canyuzhongchouEntity.setZhongchouId(Integer.valueOf(data.get(0)));   //众筹 要改的
//                            canyuzhongchouEntity.setZizuMoney(data.get(0));                    //参与金额 要改的
//                            canyuzhongchouEntity.setJieshuTime(sdf.parse(data.get(0)));          //参与时间 要改的
//                            canyuzhongchouEntity.setCreateTime(date);//时间
                            canyuzhongchouList.add(canyuzhongchouEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        canyuzhongchouService.insertBatch(canyuzhongchouList);
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
        PageUtils page = canyuzhongchouService.queryPage(params);

        //字典表数据转换
        List<CanyuzhongchouView> list =(List<CanyuzhongchouView>)page.getList();
        for(CanyuzhongchouView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        CanyuzhongchouEntity canyuzhongchou = canyuzhongchouService.selectById(id);
            if(canyuzhongchou !=null){


                //entity转view
                CanyuzhongchouView view = new CanyuzhongchouView();
                BeanUtils.copyProperties( canyuzhongchou , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(canyuzhongchou.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                    ZhongchouEntity zhongchou = zhongchouService.selectById(canyuzhongchou.getZhongchouId());
                if(zhongchou != null){
                    BeanUtils.copyProperties( zhongchou , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setZhongchouId(zhongchou.getId());
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
    public R add(@RequestBody CanyuzhongchouEntity canyuzhongchou, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,canyuzhongchou:{}",this.getClass().getName(),canyuzhongchou.toString());
        Wrapper<CanyuzhongchouEntity> queryWrapper = new EntityWrapper<CanyuzhongchouEntity>()
            .eq("yonghu_id", canyuzhongchou.getYonghuId())
            .eq("zhongchou_id", canyuzhongchou.getZhongchouId())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        CanyuzhongchouEntity canyuzhongchouEntity = canyuzhongchouService.selectOne(queryWrapper);
        if(canyuzhongchouEntity==null){
            canyuzhongchou.setCreateTime(new Date());
        canyuzhongchouService.insert(canyuzhongchou);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
