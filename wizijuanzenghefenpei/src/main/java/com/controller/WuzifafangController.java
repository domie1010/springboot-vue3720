
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
 * 发放
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/wuzifafang")
public class WuzifafangController {
    private static final Logger logger = LoggerFactory.getLogger(WuzifafangController.class);

    private static final String TABLE_NAME = "wuzifafang";

    @Autowired
    private WuzifafangService wuzifafangService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//物资论坛
    @Autowired
    private GonggaoService gonggaoService;//公告信息
    @Autowired
    private JigouService jigouService;//机构
    @Autowired
    private JuanzengYuyueService juanzengYuyueService;//物资捐赠
    @Autowired
    private WuziService wuziService;//物资
    @Autowired
    private WuziOrderService wuziOrderService;//物资申请
    @Autowired
    private WuziYuyueService wuziYuyueService;//用户物资捐赠
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


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
        else if("机构".equals(role))
            params.put("jigouId",request.getSession().getAttribute("userId"));
        params.put("wuzifafangDeleteStart",1);params.put("wuzifafangDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = wuzifafangService.queryPage(params);

        //字典表数据转换
        List<WuzifafangView> list =(List<WuzifafangView>)page.getList();
        for(WuzifafangView c:list){
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
        WuzifafangEntity wuzifafang = wuzifafangService.selectById(id);
        if(wuzifafang !=null){
            //entity转view
            WuzifafangView view = new WuzifafangView();
            BeanUtils.copyProperties( wuzifafang , view );//把实体数据重构到view中
            //级联表 物资
            //级联表
            WuziEntity wuzi = wuziService.selectById(wuzifafang.getWuziId());
            if(wuzi != null){
            BeanUtils.copyProperties( wuzi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setWuziId(wuzi.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(wuzifafang.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
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
    public R save(@RequestBody WuzifafangEntity wuzifafang, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,wuzifafang:{}",this.getClass().getName(),wuzifafang.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            wuzifafang.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<WuzifafangEntity> queryWrapper = new EntityWrapper<WuzifafangEntity>()
            .eq("wuzi_id", wuzifafang.getWuziId())
            .eq("yonghu_id", wuzifafang.getYonghuId())
            .eq("wuzifafang_shuliang", wuzifafang.getWuzifafangShuliang())
            .eq("wuzifafang_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WuzifafangEntity wuzifafangEntity = wuzifafangService.selectOne(queryWrapper);
        if(wuzifafangEntity==null){
            wuzifafang.setWuzifafangDelete(1);
            wuzifafang.setInsertTime(new Date());
            wuzifafang.setCreateTime(new Date());
            wuzifafangService.insert(wuzifafang);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody WuzifafangEntity wuzifafang, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,wuzifafang:{}",this.getClass().getName(),wuzifafang.toString());
        WuzifafangEntity oldWuzifafangEntity = wuzifafangService.selectById(wuzifafang.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            wuzifafang.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            wuzifafangService.updateById(wuzifafang);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<WuzifafangEntity> oldWuzifafangList =wuzifafangService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<WuzifafangEntity> list = new ArrayList<>();
        for(Integer id:ids){
            WuzifafangEntity wuzifafangEntity = new WuzifafangEntity();
            wuzifafangEntity.setId(id);
            wuzifafangEntity.setWuzifafangDelete(2);
            list.add(wuzifafangEntity);
        }
        if(list != null && list.size() >0){
            wuzifafangService.updateBatchById(list);
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
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<WuzifafangEntity> wuzifafangList = new ArrayList<>();//上传的东西
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
                            WuzifafangEntity wuzifafangEntity = new WuzifafangEntity();
//                            wuzifafangEntity.setWuziId(Integer.valueOf(data.get(0)));   //物资 要改的
//                            wuzifafangEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            wuzifafangEntity.setWuzifafangShuliang(Integer.valueOf(data.get(0)));   //发放数量 要改的
//                            wuzifafangEntity.setWuzifafangContent("");//详情和图片
//                            wuzifafangEntity.setWuzifafangDelete(1);//逻辑删除字段
//                            wuzifafangEntity.setInsertTime(date);//时间
//                            wuzifafangEntity.setCreateTime(date);//时间
                            wuzifafangList.add(wuzifafangEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        wuzifafangService.insertBatch(wuzifafangList);
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

        CommonUtil.checkMap(params);
        PageUtils page = wuzifafangService.queryPage(params);

        //字典表数据转换
        List<WuzifafangView> list =(List<WuzifafangView>)page.getList();
        for(WuzifafangView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        WuzifafangEntity wuzifafang = wuzifafangService.selectById(id);
            if(wuzifafang !=null){


                //entity转view
                WuzifafangView view = new WuzifafangView();
                BeanUtils.copyProperties( wuzifafang , view );//把实体数据重构到view中

                //级联表
                    WuziEntity wuzi = wuziService.selectById(wuzifafang.getWuziId());
                if(wuzi != null){
                    BeanUtils.copyProperties( wuzi , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setWuziId(wuzi.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(wuzifafang.getYonghuId());
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
    public R add(@RequestBody WuzifafangEntity wuzifafang, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,wuzifafang:{}",this.getClass().getName(),wuzifafang.toString());
        Wrapper<WuzifafangEntity> queryWrapper = new EntityWrapper<WuzifafangEntity>()
            .eq("wuzi_id", wuzifafang.getWuziId())
            .eq("yonghu_id", wuzifafang.getYonghuId())
            .eq("wuzifafang_shuliang", wuzifafang.getWuzifafangShuliang())
            .eq("wuzifafang_delete", wuzifafang.getWuzifafangDelete())
//            .notIn("wuzifafang_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WuzifafangEntity wuzifafangEntity = wuzifafangService.selectOne(queryWrapper);
        if(wuzifafangEntity==null){
            wuzifafang.setWuzifafangDelete(1);
            wuzifafang.setInsertTime(new Date());
            wuzifafang.setCreateTime(new Date());
        wuzifafangService.insert(wuzifafang);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

