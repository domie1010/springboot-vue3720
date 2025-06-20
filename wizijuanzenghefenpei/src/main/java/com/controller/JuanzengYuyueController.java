
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
 * 物资捐赠
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/juanzengYuyue")
public class JuanzengYuyueController {
    private static final Logger logger = LoggerFactory.getLogger(JuanzengYuyueController.class);

    private static final String TABLE_NAME = "juanzengYuyue";

    @Autowired
    private JuanzengYuyueService juanzengYuyueService;


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
    private WuziService wuziService;//物资
    @Autowired
    private WuziOrderService wuziOrderService;//物资申请
    @Autowired
    private WuziYuyueService wuziYuyueService;//用户物资捐赠
    @Autowired
    private WuzifafangService wuzifafangService;//发放
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
        CommonUtil.checkMap(params);
        PageUtils page = juanzengYuyueService.queryPage(params);

        //字典表数据转换
        List<JuanzengYuyueView> list =(List<JuanzengYuyueView>)page.getList();
        for(JuanzengYuyueView c:list){
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
        JuanzengYuyueEntity juanzengYuyue = juanzengYuyueService.selectById(id);
        if(juanzengYuyue !=null){
            //entity转view
            JuanzengYuyueView view = new JuanzengYuyueView();
            BeanUtils.copyProperties( juanzengYuyue , view );//把实体数据重构到view中
            //级联表 机构
            //级联表
            JigouEntity jigou = jigouService.selectById(juanzengYuyue.getJigouId());
            if(jigou != null){
            BeanUtils.copyProperties( jigou , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"
, "jigouId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setJigouId(jigou.getId());
            }
            //级联表 物资
            //级联表
            WuziEntity wuzi = wuziService.selectById(juanzengYuyue.getWuziId());
            if(wuzi != null){
            BeanUtils.copyProperties( wuzi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"
, "jigouId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setWuziId(wuzi.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(juanzengYuyue.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"
, "jigouId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
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
    public R save(@RequestBody JuanzengYuyueEntity juanzengYuyue, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,juanzengYuyue:{}",this.getClass().getName(),juanzengYuyue.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            juanzengYuyue.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("机构".equals(role))
            juanzengYuyue.setJigouId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<JuanzengYuyueEntity> queryWrapper = new EntityWrapper<JuanzengYuyueEntity>()
            .eq("jigou_id", juanzengYuyue.getJigouId())
            .eq("wuzi_id", juanzengYuyue.getWuziId())
            .eq("yonghu_id", juanzengYuyue.getYonghuId())
            .eq("juanzeng_yuyue_shuliang", juanzengYuyue.getJuanzengYuyueShuliang())
            .eq("wuzi_types", juanzengYuyue.getWuziTypes())
            .in("juanzeng_yuyue_yesno_types", new Integer[]{1,2})
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JuanzengYuyueEntity juanzengYuyueEntity = juanzengYuyueService.selectOne(queryWrapper);
        if(juanzengYuyueEntity==null){
            juanzengYuyue.setJuanzengYuyueYesnoTypes(1);
            juanzengYuyue.setInsertTime(new Date());
            juanzengYuyue.setCreateTime(new Date());
            juanzengYuyueService.insert(juanzengYuyue);
            return R.ok();
        }else {
            if(juanzengYuyueEntity.getJuanzengYuyueYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(juanzengYuyueEntity.getJuanzengYuyueYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JuanzengYuyueEntity juanzengYuyue, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,juanzengYuyue:{}",this.getClass().getName(),juanzengYuyue.toString());
        JuanzengYuyueEntity oldJuanzengYuyueEntity = juanzengYuyueService.selectById(juanzengYuyue.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            juanzengYuyue.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        else if("机构".equals(role))
//            juanzengYuyue.setJigouId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            juanzengYuyueService.updateById(juanzengYuyue);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody JuanzengYuyueEntity juanzengYuyueEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,juanzengYuyueEntity:{}",this.getClass().getName(),juanzengYuyueEntity.toString());

        JuanzengYuyueEntity oldJuanzengYuyue = juanzengYuyueService.selectById(juanzengYuyueEntity.getId());//查询原先数据

//        if(juanzengYuyueEntity.getJuanzengYuyueYesnoTypes() == 2){//通过
//            juanzengYuyueEntity.setJuanzengYuyueTypes();
//        }else if(juanzengYuyueEntity.getJuanzengYuyueYesnoTypes() == 3){//拒绝
//            juanzengYuyueEntity.setJuanzengYuyueTypes();
//        }
        juanzengYuyueEntity.setJuanzengYuyueShenheTime(new Date());//审核时间
        juanzengYuyueService.updateById(juanzengYuyueEntity);//审核

        return R.ok();
    }

    /**
     * 接受
     */
    @RequestMapping("/jieshou")
    public R jieshou(@RequestBody JuanzengYuyueEntity juanzengYuyueEntity, HttpServletRequest request){
        logger.debug("jieshou:,,Controller:{},,juanzengYuyueEntity:{}",this.getClass().getName(),juanzengYuyueEntity.toString());

        JuanzengYuyueEntity oldJuanzengYuyue = juanzengYuyueService.selectById(juanzengYuyueEntity.getId());//查询原先数据
        juanzengYuyueEntity.setJuanzengYuyueYesnoTypes(2);

        juanzengYuyueEntity.setJuanzengYuyueShenheTime(new Date());//审核时间
        juanzengYuyueService.updateById(juanzengYuyueEntity);//审核

        return R.ok();
    }

    /**
     * 拒绝
     */
    @RequestMapping("/jujue")
    public R jujue(@RequestBody JuanzengYuyueEntity juanzengYuyueEntity, HttpServletRequest request){
        logger.debug("jieshou:,,Controller:{},,juanzengYuyueEntity:{}",this.getClass().getName(),juanzengYuyueEntity.toString());

        JuanzengYuyueEntity oldJuanzengYuyue = juanzengYuyueService.selectById(juanzengYuyueEntity.getId());//查询原先数据
        juanzengYuyueEntity.setJuanzengYuyueYesnoTypes(3);

        juanzengYuyueEntity.setJuanzengYuyueShenheTime(new Date());//审核时间
        juanzengYuyueService.updateById(juanzengYuyueEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<JuanzengYuyueEntity> oldJuanzengYuyueList =juanzengYuyueService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        juanzengYuyueService.deleteBatchIds(Arrays.asList(ids));

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
            List<JuanzengYuyueEntity> juanzengYuyueList = new ArrayList<>();//上传的东西
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
                            JuanzengYuyueEntity juanzengYuyueEntity = new JuanzengYuyueEntity();
//                            juanzengYuyueEntity.setJuanzengYuyueUuidNumber(data.get(0));                    //报名编号 要改的
//                            juanzengYuyueEntity.setJigouId(Integer.valueOf(data.get(0)));   //机构 要改的
//                            juanzengYuyueEntity.setWuziId(Integer.valueOf(data.get(0)));   //物资 要改的
//                            juanzengYuyueEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            juanzengYuyueEntity.setJuanzengYuyueText(data.get(0));                    //物资信息 要改的
//                            juanzengYuyueEntity.setJuanzengYuyueShuliang(Integer.valueOf(data.get(0)));   //捐赠数量 要改的
//                            juanzengYuyueEntity.setWuziTypes(Integer.valueOf(data.get(0)));   //物资类型 要改的
//                            juanzengYuyueEntity.setJuanzengYuyueYesnoTypes(Integer.valueOf(data.get(0)));   //报名状态 要改的
//                            juanzengYuyueEntity.setJuanzengYuyueShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            juanzengYuyueEntity.setInsertTime(date);//时间
//                            juanzengYuyueEntity.setCreateTime(date);//时间
                            juanzengYuyueList.add(juanzengYuyueEntity);


                            //把要查询是否重复的字段放入map中
                                //报名编号
                                if(seachFields.containsKey("juanzengYuyueUuidNumber")){
                                    List<String> juanzengYuyueUuidNumber = seachFields.get("juanzengYuyueUuidNumber");
                                    juanzengYuyueUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> juanzengYuyueUuidNumber = new ArrayList<>();
                                    juanzengYuyueUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("juanzengYuyueUuidNumber",juanzengYuyueUuidNumber);
                                }
                        }

                        //查询是否重复
                         //报名编号
                        List<JuanzengYuyueEntity> juanzengYuyueEntities_juanzengYuyueUuidNumber = juanzengYuyueService.selectList(new EntityWrapper<JuanzengYuyueEntity>().in("juanzeng_yuyue_uuid_number", seachFields.get("juanzengYuyueUuidNumber")));
                        if(juanzengYuyueEntities_juanzengYuyueUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JuanzengYuyueEntity s:juanzengYuyueEntities_juanzengYuyueUuidNumber){
                                repeatFields.add(s.getJuanzengYuyueUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [报名编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        juanzengYuyueService.insertBatch(juanzengYuyueList);
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
        PageUtils page = juanzengYuyueService.queryPage(params);

        //字典表数据转换
        List<JuanzengYuyueView> list =(List<JuanzengYuyueView>)page.getList();
        for(JuanzengYuyueView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JuanzengYuyueEntity juanzengYuyue = juanzengYuyueService.selectById(id);
            if(juanzengYuyue !=null){


                //entity转view
                JuanzengYuyueView view = new JuanzengYuyueView();
                BeanUtils.copyProperties( juanzengYuyue , view );//把实体数据重构到view中

                //级联表
                    JigouEntity jigou = jigouService.selectById(juanzengYuyue.getJigouId());
                if(jigou != null){
                    BeanUtils.copyProperties( jigou , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setJigouId(jigou.getId());
                }
                //级联表
                    WuziEntity wuzi = wuziService.selectById(juanzengYuyue.getWuziId());
                if(wuzi != null){
                    BeanUtils.copyProperties( wuzi , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setWuziId(wuzi.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(juanzengYuyue.getYonghuId());
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
    public R add(@RequestBody JuanzengYuyueEntity juanzengYuyue, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,juanzengYuyue:{}",this.getClass().getName(),juanzengYuyue.toString());
        Wrapper<JuanzengYuyueEntity> queryWrapper = new EntityWrapper<JuanzengYuyueEntity>()
            .eq("juanzeng_yuyue_uuid_number", juanzengYuyue.getJuanzengYuyueUuidNumber())
            .eq("jigou_id", juanzengYuyue.getJigouId())
            .eq("wuzi_id", juanzengYuyue.getWuziId())
            .eq("yonghu_id", juanzengYuyue.getYonghuId())
            .eq("juanzeng_yuyue_text", juanzengYuyue.getJuanzengYuyueText())
            .eq("juanzeng_yuyue_shuliang", juanzengYuyue.getJuanzengYuyueShuliang())
            .eq("wuzi_types", juanzengYuyue.getWuziTypes())
            .in("juanzeng_yuyue_yesno_types", new Integer[]{1,2})
//            .notIn("juanzeng_yuyue_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JuanzengYuyueEntity juanzengYuyueEntity = juanzengYuyueService.selectOne(queryWrapper);
        if(juanzengYuyueEntity==null){
            juanzengYuyue.setJuanzengYuyueYesnoTypes(1);
            juanzengYuyue.setInsertTime(new Date());
            juanzengYuyue.setCreateTime(new Date());
        juanzengYuyueService.insert(juanzengYuyue);

            return R.ok();
        }else {
            if(juanzengYuyueEntity.getJuanzengYuyueYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(juanzengYuyueEntity.getJuanzengYuyueYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

}

