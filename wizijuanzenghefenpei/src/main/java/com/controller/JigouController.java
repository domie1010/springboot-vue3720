
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
 * 机构
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jigou")
public class JigouController {
    private static final Logger logger = LoggerFactory.getLogger(JigouController.class);

    private static final String TABLE_NAME = "jigou";

    @Autowired
    private JigouService jigouService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//物资论坛
    @Autowired
    private GonggaoService gonggaoService;//公告信息
    @Autowired
    private JuanzengYuyueService juanzengYuyueService;//物资捐赠
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
        PageUtils page = jigouService.queryPage(params);

        //字典表数据转换
        List<JigouView> list =(List<JigouView>)page.getList();
        for(JigouView c:list){
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
        JigouEntity jigou = jigouService.selectById(id);
        if(jigou !=null){
            //entity转view
            JigouView view = new JigouView();
            BeanUtils.copyProperties( jigou , view );//把实体数据重构到view中
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
    public R save(@RequestBody JigouEntity jigou, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jigou:{}",this.getClass().getName(),jigou.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<JigouEntity> queryWrapper = new EntityWrapper<JigouEntity>()
            .eq("username", jigou.getUsername())
            .or()
            .eq("jigou_phone", jigou.getJigouPhone())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JigouEntity jigouEntity = jigouService.selectOne(queryWrapper);
        if(jigouEntity==null){
            jigou.setCreateTime(new Date());
            jigou.setPassword("123456");
            jigouService.insert(jigou);
            return R.ok();
        }else {
            return R.error(511,"账户或者机构手机号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JigouEntity jigou, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,jigou:{}",this.getClass().getName(),jigou.toString());
        JigouEntity oldJigouEntity = jigouService.selectById(jigou.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(jigou.getJigouPhoto()) || "null".equals(jigou.getJigouPhoto())){
                jigou.setJigouPhoto(null);
        }

            jigouService.updateById(jigou);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<JigouEntity> oldJigouList =jigouService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        jigouService.deleteBatchIds(Arrays.asList(ids));

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
            List<JigouEntity> jigouList = new ArrayList<>();//上传的东西
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
                            JigouEntity jigouEntity = new JigouEntity();
//                            jigouEntity.setUsername(data.get(0));                    //账户 要改的
//                            jigouEntity.setPassword("123456");//密码
//                            jigouEntity.setJigouName(data.get(0));                    //机构姓名 要改的
//                            jigouEntity.setJigouPhone(data.get(0));                    //机构手机号 要改的
//                            jigouEntity.setJigouPhoto("");//详情和图片
//                            jigouEntity.setJigouEmail(data.get(0));                    //机构邮箱 要改的
//                            jigouEntity.setCreateTime(date);//时间
                            jigouList.add(jigouEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //机构手机号
                                if(seachFields.containsKey("jigouPhone")){
                                    List<String> jigouPhone = seachFields.get("jigouPhone");
                                    jigouPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> jigouPhone = new ArrayList<>();
                                    jigouPhone.add(data.get(0));//要改的
                                    seachFields.put("jigouPhone",jigouPhone);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<JigouEntity> jigouEntities_username = jigouService.selectList(new EntityWrapper<JigouEntity>().in("username", seachFields.get("username")));
                        if(jigouEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JigouEntity s:jigouEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //机构手机号
                        List<JigouEntity> jigouEntities_jigouPhone = jigouService.selectList(new EntityWrapper<JigouEntity>().in("jigou_phone", seachFields.get("jigouPhone")));
                        if(jigouEntities_jigouPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JigouEntity s:jigouEntities_jigouPhone){
                                repeatFields.add(s.getJigouPhone());
                            }
                            return R.error(511,"数据库的该表中的 [机构手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        jigouService.insertBatch(jigouList);
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
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        JigouEntity jigou = jigouService.selectOne(new EntityWrapper<JigouEntity>().eq("username", username));
        if(jigou==null || !jigou.getPassword().equals(password))
            return R.error("账号或密码不正确");
        String token = tokenService.generateToken(jigou.getId(),username, "jigou", "机构");
        R r = R.ok();
        r.put("token", token);
        r.put("role","机构");
        r.put("username",jigou.getJigouName());
        r.put("tableName","jigou");
        r.put("userId",jigou.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody JigouEntity jigou, HttpServletRequest request) {
//    	ValidatorUtils.validateEntity(user);
        Wrapper<JigouEntity> queryWrapper = new EntityWrapper<JigouEntity>()
            .eq("username", jigou.getUsername())
            .or()
            .eq("jigou_phone", jigou.getJigouPhone())
            ;
        JigouEntity jigouEntity = jigouService.selectOne(queryWrapper);
        if(jigouEntity != null)
            return R.error("账户或者机构手机号已经被使用");
        jigou.setCreateTime(new Date());
        jigouService.insert(jigou);

        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id, HttpServletRequest request) {
        JigouEntity jigou = jigouService.selectById(id);
        jigou.setPassword("123456");
        jigouService.updateById(jigou);
        return R.ok();
    }

	/**
	 * 修改密码
	 */
	@GetMapping(value = "/updatePassword")
	public R updatePassword(String  oldPassword, String  newPassword, HttpServletRequest request) {
        JigouEntity jigou = jigouService.selectById((Integer)request.getSession().getAttribute("userId"));
		if(newPassword == null){
			return R.error("新密码不能为空") ;
		}
		if(!oldPassword.equals(jigou.getPassword())){
			return R.error("原密码输入错误");
		}
		if(newPassword.equals(jigou.getPassword())){
			return R.error("新密码不能和原密码一致") ;
		}
        jigou.setPassword(newPassword);
		jigouService.updateById(jigou);
		return R.ok();
	}



    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        JigouEntity jigou = jigouService.selectOne(new EntityWrapper<JigouEntity>().eq("username", username));
        if(jigou!=null){
            jigou.setPassword("123456");
            jigouService.updateById(jigou);
            return R.ok();
        }else{
           return R.error("账号不存在");
        }
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrJigou(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        JigouEntity jigou = jigouService.selectById(id);
        if(jigou !=null){
            //entity转view
            JigouView view = new JigouView();
            BeanUtils.copyProperties( jigou , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }



    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = jigouService.queryPage(params);

        //字典表数据转换
        List<JigouView> list =(List<JigouView>)page.getList();
        for(JigouView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JigouEntity jigou = jigouService.selectById(id);
            if(jigou !=null){


                //entity转view
                JigouView view = new JigouView();
                BeanUtils.copyProperties( jigou , view );//把实体数据重构到view中

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
    public R add(@RequestBody JigouEntity jigou, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,jigou:{}",this.getClass().getName(),jigou.toString());
        Wrapper<JigouEntity> queryWrapper = new EntityWrapper<JigouEntity>()
            .eq("username", jigou.getUsername())
            .or()
            .eq("jigou_phone", jigou.getJigouPhone())
//            .notIn("jigou_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JigouEntity jigouEntity = jigouService.selectOne(queryWrapper);
        if(jigouEntity==null){
            jigou.setCreateTime(new Date());
            jigou.setPassword("123456");
        jigouService.insert(jigou);

            return R.ok();
        }else {
            return R.error(511,"账户或者机构手机号已经被使用");
        }
    }

}

