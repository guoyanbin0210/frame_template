package com.lt.base.controller;

import com.github.pagehelper.PageHelper;
import com.lt.base.aop.ControllerMethodLog;
import com.lt.base.constant.BaseConstant;
import com.lt.base.poi.PoiExcelUtils;
import com.lt.base.model.*;
import com.lt.base.service.*;
import com.lt.base.shrio.MyUsernamePasswordToken;
import com.lt.base.sms.SMSUtils;
import com.lt.base.util.BaseUtils;
import com.lt.base.util.BeanRefUtil;
import com.lt.base.util.secretUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.tomcat.util.security.MD5Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.testng.annotations.Test;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.FutureTask;

/**
 * Created with GaoShan.
 * Description: 接口
 * Date: 2018-12-03
 * Time: 17:08
 */
@RestController
@ApiIgnore
public class BaseApiController extends BaseController {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());

//    @ControllerMethodLog(remark = "菜单-初始化")
//    @GetMapping("/base/SysMenu/init")
//    public void initMenuPermission() {
//        ArrayList<SysMenuModel> sysMenuModels = sysMenuService.selectAll();
//        for (SysMenuModel sysMenuModel : sysMenuModels) {
//            SysPermissionModel sysPermissionModel = new SysPermissionModel();
//            sysPermissionModel.init(sysPermissionModel);
//            sysPermissionModel.setPermission_name(sysMenuModel.getMenu_name());
//            sysPermissionModel.setPermission_code(BaseConstant.PERMISSION_MENU_PRE + PinYinHelper.getFirstSpell(sysMenuModel.getMenu_name()).toUpperCase());
//            sysPermissionModel.setPermission_band_menu_id(sysMenuModel.getId());
//            sysPermissionModel.setPermission_type(BaseConstant.PERMISSION_TYPE_ENUM.TYPE_MENU.getCode());
//            sysPermissionService.insert(sysPermissionModel);
//        }
//    }
//--------------------------RBAC-------------------------------


    @ControllerMethodLog(remark = "RBAC-插入")
    @PostMapping("/base/RBAC/insert.do")
    public HashMap insert_RBACLoginRole(@RequestParam("login_id") String login_id, @RequestParam("role_id") String role_id) {
        SysLoginRoleModel sysLoginRoleModel = new SysLoginRoleModel();
        sysLoginRoleModel.init(sysLoginRoleModel);
        sysLoginRoleModel.setLogin_id(login_id);
        sysLoginRoleModel.setRole_id(role_id);
        return insert(sysLoginRoleService, sysLoginRoleModel);
    }

    @ControllerMethodLog(remark = "RBAC-插入角色权限")
    @PostMapping("/base/RBAC/RolePermissionInsert.do")
    public HashMap insert_RBACRolePermission(
            @RequestParam("pId") String pId,
            @RequestParam("role_id") String role_id,
            @RequestParam("permission_id") String permission_id) {
        sysRolePermissionService.deleteByPermissionIdAndRoleId(permission_id, role_id);
        sysRolePermissionService.deleteByPermissionIdAndRoleId(pId, role_id);
        SysRolePermissionModel sysRolePermissionModel = new SysRolePermissionModel();
        sysRolePermissionModel.init(sysRolePermissionModel);
        sysRolePermissionModel.setRole_id(role_id);
        sysRolePermissionModel.setPermission_id(pId);
        sysRolePermissionService.insert(sysRolePermissionModel);

        sysRolePermissionModel = new SysRolePermissionModel();
        sysRolePermissionModel.init(sysRolePermissionModel);
        sysRolePermissionModel.setRole_id(role_id);
        sysRolePermissionModel.setPermission_id(permission_id);
        return insert(sysRolePermissionService, sysRolePermissionModel);
    }

    @ControllerMethodLog(remark = "RBAC-删除")
    @PostMapping("/base/RBAC/deleteById.do")
    public HashMap deleteById_RBACLoginRole(@RequestParam("login_id") String login_id, @RequestParam("role_id") String role_id) {
        return getHashMap(sysLoginRoleService.deleteByLoginIdAndRoleId(login_id, role_id));
    }


    @ControllerMethodLog(remark = "RBAC-删除")
    @PostMapping("/base/RBAC/RolePermissionDeleteById.do")
    public HashMap deleteById_RBACRolePermission(@RequestParam("role_id") String role_id, @RequestParam("permission_id") String permission_id) {
        return getHashMap(sysRolePermissionService.deleteByPermissionIdAndRoleId(permission_id, role_id));
    }

    private HashMap getHashMap(Integer integer2) {
        HashMap<String, Object> returnMap = getReturnMap();
        if (integer2 == 1) {
            getReturnMapSuccess(returnMap);
        }
        return returnMap;
    }

    @ControllerMethodLog(remark = "菜单-初始化")
    @PostMapping("/base/RBAC/All")
    public List selectAllRolePermission() {
        List<SysRoleModel> sysRoleModels = sysRoleService.selectListDetails(new SysRoleModel());
        ArrayList<Object> resultDataList = new ArrayList<>();
        if (sysRoleModels != null) {
            getRolesTree(resultDataList, sysRoleModels);
        }
        return resultDataList;
    }

    @ControllerMethodLog(remark = "菜单-初始化")
    @PostMapping("/base/RBAC/permissions")
    public List selectPermission() {
        ArrayList<SysPermissionModel> sysPermissionModels = sysPermissionService.selectAll();
        ArrayList<SysMenuModel> sysMenuModels = sysMenuService.selectAll();
        ArrayList<Object> resultDataList = new ArrayList<>();
        if (sysPermissionModels != null) {
            createRBACPermission(sysMenuModels, resultDataList, sysPermissionModels);
        }
        return resultDataList;
    }

    @ControllerMethodLog(remark = "RBAC-查询权限")
    @PostMapping("/base/RBAC/selectByLoginId.do")
    public HashMap selectByLoginId_RBAC(@RequestParam("id") String id) {
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_FAILED);
        //查询一个角色
        SysLoginModel sysRoleModel = sysLoginService.selectDetailsById(id);
        ArrayList<Object> resultDataList = new ArrayList<>();
        if (sysRoleModel != null) {
            List<SysRoleModel> roles = sysRoleModel.getRoles();
            if (roles != null) {
                for (SysRoleModel roleModel : roles) {
                    HashMap<String, Object> stringObjectHashMap = new HashMap<>();
                    stringObjectHashMap.put("id", roleModel.getId());
                    stringObjectHashMap.put("name", roleModel.getRole_name());
                    stringObjectHashMap.put("isParent", true);
                    resultDataList.add(stringObjectHashMap);
                }
                resultMap.put("data", resultDataList);
                getReturnMapSuccess(resultMap);
            }
        }
        return resultMap;
    }

    @ControllerMethodLog(remark = "RBAC-查询权限")
    @PostMapping("/base/RBAC/selectPermissionsByRoleId.do")
    public HashMap selectByRoleId_RBAC(@RequestParam("role_id") String role_id) {
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_FAILED);
        //查询一个角色
        SysRoleModel sysRoleModel = sysRoleService.selectDetailsById(role_id);
        ArrayList<SysMenuModel> sysMenuModels = sysMenuService.selectAll();
        ArrayList<Object> resultDataList = new ArrayList<>();
        if (sysRoleModel != null) {
            List<SysPermissionModel> roles = sysRoleModel.getPermissions();
            if (roles != null) {
                createRBACPermission(sysMenuModels, resultDataList, roles);
                resultMap.put("data", resultDataList);
                getReturnMapSuccess(resultMap);
            }
        }
        return resultMap;
    }

    private void createRBACPermission(ArrayList<SysMenuModel> sysMenuModels, ArrayList<Object> resultDataList, List<SysPermissionModel> roles) {
        for (SysPermissionModel permissionModel : roles) {
            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            if (sysMenuModels != null) {
                for (SysMenuModel sysMenuModel : sysMenuModels) {
                    if (sysMenuModel.getId().equals(permissionModel.getPermission_band_menu_id())) {
                        stringObjectHashMap.put("id", permissionModel.getId());
                        stringObjectHashMap.put("name", permissionModel.getPermission_name());

                        stringObjectHashMap.put("tid", sysMenuModel.getId());
                        if ("无".equals(sysMenuModel.getMenu_parent_id())) {
                            stringObjectHashMap.put("isParent", true);
                        } else
                            stringObjectHashMap.put("tpid", sysMenuModel.getMenu_parent_id());
//                chileMenuMap.put("nocheck", true);
                        resultDataList.add(stringObjectHashMap);
                        break;
                    }
                }
            }
        }
    }

    private void getRolesTree(ArrayList<Object> resultDataList, List<SysRoleModel> roles) {
        for (SysRoleModel roleModel : roles) {
            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("id", roleModel.getId());
            stringObjectHashMap.put("name", roleModel.getRole_name());
            List<SysPermissionModel> permissions = roleModel.getPermissions();
            if (permissions != null) {
                for (SysPermissionModel permission : permissions) {
                    HashMap<String, Object> chileMenuMap = new HashMap<>();
                    chileMenuMap.put("id", permission.getId());
                    chileMenuMap.put("name", permission.getPermission_name());
                    chileMenuMap.put("pId", roleModel.getId());
                    chileMenuMap.put("nocheck", true);
                    resultDataList.add(chileMenuMap);
                }
            }
            resultDataList.add(stringObjectHashMap);
        }
    }

    //--------------------------RBAC-------------------------------


    @ControllerMethodLog(remark = "登陆")
    @PostMapping("/base/doLogin.do")
    public HashMap doLogin(@RequestParam("userName") String userName, @RequestParam("password") String password) {

        //解密
/*        byte[] name = secretUtils.decode(userName);
        byte[] pass = secretUtils.decode(password);
        String realName;
        String realPass;*/
        HashMap<String, Object> returnMap = getReturnMap();
        try {
        /*    realName = new String(name,"UTF-8");
            realPass = new String(pass,"UTF-8");
            realName=java.net.URLDecoder.decode(realName, "UTF-8");
            realPass=java.net.URLDecoder.decode(realPass, "UTF-8");*/

             MyUsernamePasswordToken token = new MyUsernamePasswordToken(userName, password);
             Subject subject = SecurityUtils.getSubject();

            subject.login(token);
            Serializable id = subject.getSession().getId();
            returnMap.put("token", id);
            SysLoginModel currLoginModel = BaseUtils.getCurrLoginModel();
            if (currLoginModel != null) {
                FutureTask<Integer> getCountTask
                        = new FutureTask<>(() -> sysLoginLogService.selectCountByCreateBy(currLoginModel.getId()));
                new Thread(getCountTask).start();
                SysLoginLogModel lastLog = sysLoginLogService.selectLastByCreateBy(currLoginModel.getId());
                if (lastLog != null) {
                    lastLog.setLogin_count(getCountTask.get() != null ? getCountTask.get() + 1 : 1);
                }
                new Thread(new FutureTask<>(() -> {
                    //添加登录日志
                    SysLoginLogModel sysLoginLogModel = new SysLoginLogModel();
                    sysLoginLogModel.init(sysLoginLogModel);
                    sysLoginLogModel.setSll_login_id(currLoginModel.getId());
                    sysLoginLogModel.setSsl_login_name(currLoginModel.getUser_name());
                    sysLoginLogModel.setSll_login_time(new Date());
                    sysLoginLogModel.setSll_login_ip(subject.getSession().getHost());
                    sysLoginLogService.insert(sysLoginLogModel);
                    return null;
                })).start();

                currLoginModel.setPassword(null);//返回时去掉用户信息
//                currLoginModel.setRoles(null);//返回时去掉角色集合
                currLoginModel.setLastLoginLog(lastLog);//返回最后一次登录日志,用户第一次登陆的时候是空的
                returnMap.put("login", currLoginModel);
                getReturnMapSuccess(returnMap);
            }


        } catch (IncorrectCredentialsException ice) {
            return getReturnMap(BaseConstant.Response_MENU.REQUEST_PASSWORD_FAILED);
        } catch (UnknownAccountException uae) {
            return getReturnMap(BaseConstant.Response_MENU.REQUEST_USER_NAME_FAILED);
        } catch (ExcessiveAttemptsException eae) {
            returnMap.put("message", "登录超时!");
        } catch (LockedAccountException lockedAccountException) {
            returnMap.put("message", "用户被冻结!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnMap;
    }

    @ControllerMethodLog(remark = "未登陆")
    @GetMapping("/base/unLogin")
    public HashMap unLogin() {
        HashMap<String, Object> returnMap = new HashMap<>();
        returnMap.put("message", "未登录");
        return returnMap;
    }

    @ControllerMethodLog(remark = "退出")
    @GetMapping("/base/doLogout.do")
    public HashMap doLogout() {
        HashMap<String, Object> returnMap = getReturnMap();
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
            getReturnMapSuccess(returnMap);
        }
        return returnMap;
    }


    //---------------------------菜单-----------------------start
    @Resource
    private SysMenuService sysMenuService;

    @ControllerMethodLog(remark = "菜单-插入")
    @PostMapping("/base/SysMenu/insert.do")
    public HashMap insert_SysMenu(HttpServletRequest request) {
        SysMenuModel sysMenuModel = new SysMenuModel();
        sysMenuModel.getInstanceForInsert(request, sysMenuModel);
        HashMap<String, Object> returnMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_INSERT_FAILED);
        //定制
        int insert = sysMenuService.insertRBAC(sysMenuModel);
        if (insert != 0) {
            returnMap.put("data", sysMenuModel);
            getReturnMapSuccess(returnMap);
        }
        return returnMap;
    }


    @ControllerMethodLog(remark = "菜单-删除")
    @PostMapping("/base/SysMenu/deleteById.do")
    public HashMap deleteById_SysMenu(@RequestParam("id") String id) {
        HashMap<String, Object> returnMap = getReturnMap();
        Integer delete = sysMenuService.deleteRBAC(id);
        if (delete != null && delete != 0) {
            getReturnMapSuccess(returnMap);
        }
        return returnMap;
    }

    @ControllerMethodLog(remark = "菜单-更新")
    @PostMapping("/base/SysMenu/updateById.do")
    public HashMap update_SysMenu(HttpServletRequest request) {
        SysMenuModel sysMenuModel = new SysMenuModel();
        sysMenuModel.getInstanceForUpdate(request, sysMenuModel);
        HashMap<String, Object> returnMap = getReturnMap();
        String create_time = request.getParameter("create_time");
        if (create_time != null) {
            Date date = new Date(Long.parseLong(create_time));
            sysMenuModel.setCreate_time(date);
        }

        int update = sysMenuService.updateRBAC(sysMenuModel);
        if (update != 0) {
            getReturnMapSuccess(returnMap);
        }
        return returnMap;
    }


    @ControllerMethodLog(remark = "菜单-查询一个")
    @PostMapping("/base/SysMenu/selectById.do")
    public HashMap selectById_SysMenu(@RequestParam("id") String id) {
        return selectById(sysMenuService, id);
    }

    @ControllerMethodLog(remark = "菜单-查询多个")
    @PostMapping("/base/SysMenu/selectAll.do")
    public HashMap selectList_SysMenu(HttpServletRequest request) {
        return selectListByCondition(request, sysMenuService, new SysMenuModel());
    }


    @ControllerMethodLog(remark = "菜单-查询多个")
    @PostMapping("/base/SysMenu/tree/selectRBAC.do")
    public List selectTreeRBAC_SysMenu() {
        return sysMenuService.selectRBAC();
    }

    @ControllerMethodLog(remark = "菜单-查询多个")
    @PostMapping("/base/SysMenu/tree/selectAll.do")
    public ArrayList selectTree_SysMenu() {
        return sysMenuService.selectAll();
    }


    @ControllerMethodLog(remark = "菜单-查询分页")
    @PostMapping("/base/SysMenu/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_SysMenu(HttpServletRequest request) {
        return selectListByPageHelper(request, sysMenuService, new SysMenuModel());
    }

    @ControllerMethodLog(remark = "菜单-批量导入-下载模版")
    @GetMapping("/base/SysMenu/downloadTemplate.do")
    public void downloadTemplate_SysMenu(HttpServletResponse response) throws IOException {
        PoiExcelUtils.doDownloadWorkbook("菜单模版.xlsx", response, SysMenuModel.class);
    }

    @ControllerMethodLog(remark = "菜单-批量导入-导入")
    @PostMapping("/base/SysMenu/insertMoreByExcel.do")
    public HashMap insertMoreByExcel_SysMenu(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        HashMap<String, Object> returnMap = getReturnMap();
        if (multipartFile != null) {
            List<HashMap<String, Object>> listByFile = PoiExcelUtils.getListByFile(multipartFile, SysMenuModel.class);
            if (listByFile != null) {
                for (HashMap<String, Object> stringObjectHashMap : listByFile) {
                    if (stringObjectHashMap.isEmpty())
                        continue;
                    SysMenuModel baseModel = new SysMenuModel();
                    baseModel.init(baseModel);
                    BeanRefUtil.setFieldValue(baseModel, stringObjectHashMap);
                    sysMenuService.insert(baseModel);
                }
            } else {
                returnMap.put("message", "未能读取到数据");
            }
        } else {
            returnMap.put("message", "未找到文件，请核对参数名：[file]");
        }
        return returnMap;
    }
    //---------------------------菜单-----------------------end

    //---------------------------账户-----------------------start
    @Resource
    private SysLoginService sysLoginService;

    @ControllerMethodLog(remark = "账户-插入")
    @PostMapping("/base/SysLogin/insert.do")
    public HashMap insert_SysLogin(HttpServletRequest request) {
        SysLoginModel sysLoginModel = new SysLoginModel();
        sysLoginModel.getInstanceForInsert(request, sysLoginModel);
        BaseUtils.encryptionPassword(sysLoginModel);
        HashMap<String, Object> returnMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_INSERT_FAILED);
        try{

        int insert = sysLoginService.insert(sysLoginModel);
        if (insert != 0) {
            returnMap.put("data", sysLoginModel);
            getReturnMap(BaseConstant.Response_MENU.REQUEST_DO_SUCCESS, returnMap);
        }
        return returnMap;
        }catch (Exception e){
            returnMap.put("message","用户名重复，请重新输入");
            return returnMap;
        }
    }

    @ControllerMethodLog(remark = "账户-删除")
    @PostMapping("/base/SysLogin/deleteById.do")
    public HashMap deleteById_SysLogin(@RequestParam("id") String id) {
        return delete(sysLoginService, id);
    }

    @ControllerMethodLog(remark = "账户-更新")
    @PostMapping("/base/SysLogin/updateById.do")
    public HashMap update_SysLogin(HttpServletRequest request) {
        SysLoginModel sysLoginModel = new SysLoginModel();
        sysLoginModel.getInstanceForUpdate(request, sysLoginModel);
        BaseUtils.encryptionPassword(sysLoginModel);
        return update(sysLoginService, sysLoginModel);
    }


    @ControllerMethodLog(remark = "账户-查询一个")
    @PostMapping("/base/SysLogin/selectById.do")
    public HashMap selectById_SysLogin(@RequestParam("id") String id, @RequestParam(name = "selectDetails", required = false) boolean selectDetails) {
        if (selectDetails) {
            HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_FAILED);
            //查询一个角色
            SysLoginModel sysRoleModel = sysLoginService.selectDetailsById(id);
            if (sysRoleModel != null) {

                //解析uuid



                getReturnMapSelect(resultMap, sysRoleModel);
            }
            return resultMap;
        } else {
            return selectById(sysLoginService, id);
        }
    }


    @ControllerMethodLog(remark = "账户-查询多个")
    @PostMapping("/base/SysLogin/selectList.do")
    public HashMap selectList_SysLogin(HttpServletRequest request) {
        return selectListByCondition(request, sysLoginService, new SysLoginModel());
    }

    @ControllerMethodLog(remark = "账户-查询分页")
    @PostMapping("/base/SysLogin/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_SysLogin(HttpServletRequest request) {
        SysLoginModel sysLoginModel = new SysLoginModel();
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_FAILED);
        sysLoginModel.getInstanceForSelect(request, sysLoginModel);
        String keyword = request.getParameter("keyWord");
        int[] pageStringToIntArray = getPageStringToIntArray(request);
        PageHelper.startPage(pageStringToIntArray[0], pageStringToIntArray[1]);
        ArrayList list;
        if (StringUtils.isEmpty(keyword)) {
            list = sysLoginService.selectListByModel(sysLoginModel);
        } else {
            list = sysLoginService.selectListByKeyWord(keyword);
        }
        getReturnMapSelectByPage(resultMap, list);
        return resultMap;
    }

    @ControllerMethodLog(remark = "账户-批量导入-下载模版")
    @GetMapping("/base/SysLogin/downloadTemplate.do")
    public void downloadTemplate_SysLogin(HttpServletResponse response) throws IOException {
        PoiExcelUtils.doDownloadWorkbook("账户模版.xlsx", response, SysLoginModel.class);
    }

    @ControllerMethodLog(remark = "账户-批量导入-导入")
    @PostMapping("/base/SysLogin/insertMoreByExcel.do")
    public HashMap insertMoreByExcel_SysLogin(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        HashMap<String, Object> returnMap = getReturnMap();
        if (multipartFile != null) {
            List<HashMap<String, Object>> listByFile = PoiExcelUtils.getListByFile(multipartFile, SysLoginModel.class);
            if (listByFile != null) {
                for (HashMap<String, Object> stringObjectHashMap : listByFile) {
                    if (stringObjectHashMap.isEmpty())
                        continue;
                    SysLoginModel baseModel = new SysLoginModel();
                    baseModel.init(baseModel);
                    BeanRefUtil.setFieldValue(baseModel, stringObjectHashMap);
                    sysLoginService.insert(baseModel);
                }
            } else {
                returnMap.put("message", "未能读取到数据");
            }
        } else {
            returnMap.put("message", "未找到文件，请核对参数名：[file]");
        }
        return returnMap;
    }
    //---------------------------账户-----------------------end


    //---------------------------角色-----------------------start
    @Resource
    private SysRoleService sysRoleService;

    @ControllerMethodLog(remark = "角色-插入")
    @PostMapping("/base/SysRole/insert.do")
    public HashMap insert_SysRole(HttpServletRequest request) {
        return insert(request, sysRoleService, new SysRoleModel());
    }

    @ControllerMethodLog(remark = "角色-删除")
    @PostMapping("/base/SysRole/deleteById.do")
    public HashMap deleteById_SysRole(@RequestParam("id") String id) {
        return delete(sysRoleService, id);
    }

    @ControllerMethodLog(remark = "角色-更新")
    @PostMapping("/base/SysRole/updateById.do")
    public HashMap update_SysRole(HttpServletRequest request) {
        return update(request, sysRoleService, new SysRoleModel());
    }

    @ControllerMethodLog(remark = "角色-查询一个")
    @PostMapping("/base/SysRole/selectById.do")
    public HashMap selectById_SysRole(@RequestParam("id") String id, @RequestParam(name = "selectDetails", required = false) boolean selectDetails) {
        if (selectDetails) {
            HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_FAILED);
            //查询一个角色
            SysRoleModel sysRoleModel = sysRoleService.selectDetailsById(id);
            if (sysRoleModel != null) {
                getReturnMapSelect(resultMap, sysRoleModel);
            }
            return resultMap;
        } else {
            return selectById(sysRoleService, id);
        }
    }


    @ControllerMethodLog(remark = "角色-查询多个")
    @GetMapping("/base/SysRole/selectList.do")
    public HashMap selectList_SysRole(HttpServletRequest request, @RequestParam(name = "selectDetails", required = false) boolean selectDetails) {
        SysRoleModel baseModel = new SysRoleModel();
        if (selectDetails) {
            HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_FAILED);
            baseModel.getInstanceForSelect(request, baseModel);
            List<SysRoleModel> sysRoleModels = sysRoleService.selectListDetails(baseModel);
            getReturnMapSelect(resultMap, sysRoleModels);
            return resultMap;
        } else {
            return selectListByCondition(request, sysRoleService, baseModel);
        }
    }


    @ControllerMethodLog(remark = "角色-查询分页")
    @PostMapping("/base/SysRole/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_SysRole(HttpServletRequest request) {
        return selectListByPageHelper(request, sysRoleService, new SysRoleModel());
    }

    @ControllerMethodLog(remark = "角色-批量导入-下载模版")
    @GetMapping("/base/SysRole/downloadTemplate.do")
    public void downloadTemplate_SysRole(HttpServletResponse response) throws IOException {
        PoiExcelUtils.doDownloadWorkbook("角色模版.xlsx", response, SysRoleModel.class);
    }

    @ControllerMethodLog(remark = "角色-批量导入-导入")
    @PostMapping("/base/SysRole/insertMoreByExcel.do")
    public HashMap insertMoreByExcel_SysRole(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        HashMap<String, Object> returnMap = getReturnMap();
        if (multipartFile != null) {
            List<HashMap<String, Object>> listByFile = PoiExcelUtils.getListByFile(multipartFile, SysRoleModel.class);
            if (listByFile != null) {
                for (HashMap<String, Object> stringObjectHashMap : listByFile) {
                    if (stringObjectHashMap.isEmpty())
                        continue;
                    SysRoleModel baseModel = new SysRoleModel();
                    baseModel.init(baseModel);
                    BeanRefUtil.setFieldValue(baseModel, stringObjectHashMap);
                    sysRoleService.insert(baseModel);
                }
            } else {
                returnMap.put("message", "未能读取到数据");
            }
        } else {
            returnMap.put("message", "未找到文件，请核对参数名：[file]");
        }
        return returnMap;
    }

    //---------------------------角色-----------------------end
    //---------------------------权限-----------------------start
    @Resource
    private SysPermissionService sysPermissionService;

    @ControllerMethodLog(remark = "权限-插入")
    @PostMapping("/base/SysPermission/insert.do")
    public HashMap insert_SysPermission(HttpServletRequest request) {
        return insert(request, sysPermissionService, new SysPermissionModel());
    }

    @ControllerMethodLog(remark = "权限-删除")
    @PostMapping("/base/SysPermission/deleteById.do")
    public HashMap deleteById_SysPermission(@RequestParam("id") String id) {
        return delete(sysPermissionService, id);
    }

    @ControllerMethodLog(remark = "权限-更新")
    @PostMapping("/base/SysPermission/updateById.do")
    public HashMap update_SysPermission(HttpServletRequest request) {
        return update(request, sysPermissionService, new SysPermissionModel());
    }

    @ControllerMethodLog(remark = "权限-查询一个")
    @PostMapping("/base/SysPermission/selectById.do")
    public HashMap selectById_SysPermission(@RequestParam("id") String id) {
        return selectById(sysPermissionService, id);
    }

    @ControllerMethodLog(remark = "权限-查询多个")
    @PostMapping("/base/SysPermission/selectList.do")
    public HashMap selectList_SysPermission(HttpServletRequest request) {
        return selectListByCondition(request, sysPermissionService, new SysPermissionModel());
    }

    @ControllerMethodLog(remark = "权限-查询分页")
    @PostMapping("/base/SysPermission/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_SysPermission(HttpServletRequest request) {
        return selectListByPageHelper(request, sysPermissionService, new SysPermissionModel());
    }

    @ControllerMethodLog(remark = "权限-批量导入-下载模版")
    @GetMapping("/base/SysPermission/downloadTemplate.do")
    public void downloadTemplate_SysPermission(HttpServletResponse response) throws IOException {
        PoiExcelUtils.doDownloadWorkbook("权限模版.xlsx", response, SysPermissionModel.class);
    }

    @ControllerMethodLog(remark = "权限-批量导入-导入")
    @PostMapping("/base/SysPermission/insertMoreByExcel.do")
    public HashMap insertMoreByExcel_SysPermission(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        HashMap<String, Object> returnMap = getReturnMap();
        if (multipartFile != null) {
            List<HashMap<String, Object>> listByFile = PoiExcelUtils.getListByFile(multipartFile, SysPermissionModel.class);
            if (listByFile != null) {
                for (HashMap<String, Object> stringObjectHashMap : listByFile) {
                    if (stringObjectHashMap.isEmpty())
                        continue;
                    SysPermissionModel baseModel = new SysPermissionModel();
                    baseModel.init(baseModel);
                    BeanRefUtil.setFieldValue(baseModel, stringObjectHashMap);
                    sysPermissionService.insert(baseModel);
                }
            } else {
                returnMap.put("message", "未能读取到数据");
            }
        } else {
            returnMap.put("message", "未找到文件，请核对参数名：[file]");
        }
        return returnMap;
    }
    //---------------------------权限-----------------------end

    //---------------------------登陆角色-----------------------start
    @Resource
    private SysLoginRoleService sysLoginRoleService;

    @ControllerMethodLog(remark = "登陆角色-插入")
    @PostMapping("/base/SysLoginRole/insert.do")
    public HashMap insert_SysLoginRole(HttpServletRequest request) {
        return insert(request, sysLoginRoleService, new SysLoginRoleModel());
    }

    @ControllerMethodLog(remark = "登陆角色-删除")
    @PostMapping("/base/SysLoginRole/deleteById.do")
    public HashMap deleteById_SysLoginRole(@RequestParam("id") String id) {
        return delete(sysLoginRoleService, id);
    }

    @ControllerMethodLog(remark = "登陆角色-更新")
    @PostMapping("/base/SysLoginRole/updateById.do")
    public HashMap update_SysLoginRole(HttpServletRequest request) {
        return update(request, sysLoginRoleService, new SysLoginRoleModel());
    }

    @ControllerMethodLog(remark = "登陆角色-查询一个")
    @PostMapping("/base/SysLoginRole/selectById.do")
    public HashMap selectById_SysLoginRole(@RequestParam("id") String id) {
        return selectById(sysLoginRoleService, id);
    }

    @ControllerMethodLog(remark = "登陆角色-查询多个")
    @PostMapping("/base/SysLoginRole/selectList.do")
    public HashMap selectList_SysLoginRole(HttpServletRequest request) {
        return selectListByCondition(request, sysLoginRoleService, new SysLoginRoleModel());
    }

    @ControllerMethodLog(remark = "登陆角色-查询分页")
    @PostMapping("/base/SysLoginRole/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_SysLoginRole(HttpServletRequest request) {
        return selectListByPageHelper(request, sysLoginRoleService, new SysLoginRoleModel());
    }

    @ControllerMethodLog(remark = "登陆角色-批量导入-下载模版")
    @GetMapping("/base/SysLoginRole/downloadTemplate.do")
    public void downloadTemplate_SysLoginRole(HttpServletResponse response) throws IOException {
        PoiExcelUtils.doDownloadWorkbook("登陆角色模版.xlsx", response, SysLoginRoleModel.class);
    }

    @ControllerMethodLog(remark = "登陆角色-批量导入-导入")
    @PostMapping("/base/SysLoginRole/insertMoreByExcel.do")
    public HashMap insertMoreByExcel_SysLoginRole(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        HashMap<String, Object> returnMap = getReturnMap();
        if (multipartFile != null) {
            List<HashMap<String, Object>> listByFile = PoiExcelUtils.getListByFile(multipartFile, SysLoginRoleModel.class);
            if (listByFile != null) {
                for (HashMap<String, Object> stringObjectHashMap : listByFile) {
                    if (stringObjectHashMap.isEmpty())
                        continue;
                    SysLoginRoleModel baseModel = new SysLoginRoleModel();
                    baseModel.init(baseModel);
                    BeanRefUtil.setFieldValue(baseModel, stringObjectHashMap);
                    sysLoginRoleService.insert(baseModel);
                }
            } else {
                returnMap.put("message", "未能读取到数据");
            }
        } else {
            returnMap.put("message", "未找到文件，请核对参数名：[file]");
        }
        return returnMap;
    }

    //---------------------------登陆角色-----------------------end
    //---------------------------角色权限-----------------------start
    @Resource
    private SysRolePermissionService sysRolePermissionService;

    @ControllerMethodLog(remark = "角色权限-插入")
    @PostMapping("/base/SysRolePermission/insert.do")
    public HashMap insert_SysRolePermission(HttpServletRequest request) {
        return insert(request, sysRolePermissionService, new SysRolePermissionModel());
    }

    @ControllerMethodLog(remark = "角色权限-删除")
    @PostMapping("/base/SysRolePermission/deleteById.do")
    public HashMap deleteById_SysRolePermission(@RequestParam("id") String id) {
        return delete(sysRolePermissionService, id);
    }

    @ControllerMethodLog(remark = "角色权限-更新")
    @PostMapping("/base/SysRolePermission/updateById.do")
    public HashMap update_SysRolePermission(HttpServletRequest request) {
        return update(request, sysRolePermissionService, new SysRolePermissionModel());
    }

    @ControllerMethodLog(remark = "角色权限-查询一个")
    @PostMapping("/base/SysRolePermission/selectById.do")
    public HashMap selectById_SysRolePermission(@RequestParam("id") String id) {
        return selectById(sysRolePermissionService, id);
    }

    @ControllerMethodLog(remark = "角色权限-查询多个")
    @PostMapping("/base/SysRolePermission/selectList.do")
    public HashMap selectList_SysRolePermission(HttpServletRequest request) {
        return selectListByCondition(request, sysRolePermissionService, new SysRolePermissionModel());
    }

    @ControllerMethodLog(remark = "角色权限-查询分页")
    @PostMapping("/base/SysRolePermission/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_SysRolePermission(HttpServletRequest request) {
        return selectListByPageHelper(request, sysRolePermissionService, new SysRolePermissionModel());
    }

    @ControllerMethodLog(remark = "角色权限-批量导入-下载模版")
    @GetMapping("/base/SysRolePermission/downloadTemplate.do")
    public void downloadTemplate_SysRolePermission(HttpServletResponse response) throws IOException {
        PoiExcelUtils.doDownloadWorkbook("角色权限模版.xlsx", response, SysRolePermissionModel.class);
    }

    @ControllerMethodLog(remark = "角色权限-批量导入-导入")
    @PostMapping("/base/SysRolePermission/insertMoreByExcel.do")
    public HashMap insertMoreByExcel_SysRolePermission(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        HashMap<String, Object> returnMap = getReturnMap();
        if (multipartFile != null) {
            List<HashMap<String, Object>> listByFile = PoiExcelUtils.getListByFile(multipartFile, SysRolePermissionModel.class);
            if (listByFile != null) {
                for (HashMap<String, Object> stringObjectHashMap : listByFile) {
                    if (stringObjectHashMap.isEmpty())
                        continue;
                    SysRolePermissionModel baseModel = new SysRolePermissionModel();
                    baseModel.init(baseModel);
                    BeanRefUtil.setFieldValue(baseModel, stringObjectHashMap);
                    sysRolePermissionService.insert(baseModel);
                }
            } else {
                returnMap.put("message", "未能读取到数据");
            }
        } else {
            returnMap.put("message", "未找到文件，请核对参数名：[file]");
        }
        return returnMap;
    }
    //---------------------------角色权限-----------------------end


    //---------------------------登录日志-----------------------start
    @Resource
    private SysLoginLogService sysLoginLogService;

    @ControllerMethodLog(remark = "登录日志-插入")
    @PostMapping("/base/SysLoginLog/insert.do")
    public HashMap insert_SysLoginLog(HttpServletRequest request) {
        return insert(request, sysLoginLogService, new SysLoginLogModel());
    }

    @ControllerMethodLog(remark = "登录日志-删除")
    @PostMapping("/base/SysLoginLog/deleteById.do")
    public HashMap deleteById_SysLoginLog(@RequestParam("id") String id) {
        return delete(sysLoginLogService, id);
    }

    @ControllerMethodLog(remark = "登录日志-更新")
    @PostMapping("/base/SysLoginLog/updateById.do")
    public HashMap update_SysLoginLog(HttpServletRequest request) {
        return update(request, sysLoginLogService, new SysLoginLogModel());
    }

    @ControllerMethodLog(remark = "登录日志-查询一个")
    @PostMapping("/base/SysLoginLog/selectById.do")
    public HashMap selectById_SysLoginLog(@RequestParam("id") String id) {
        return selectById(sysLoginLogService, id);
    }

    @ControllerMethodLog(remark = "登录日志-查询多个")
    @PostMapping("/base/SysLoginLog/selectAll.do")
    public HashMap selectList_SysLoginLog(HttpServletRequest request) {
        return selectListByCondition(request, sysLoginLogService, new SysLoginLogModel());
    }

    @ControllerMethodLog(remark = "登录日志-查询分页")
    @PostMapping("/base/SysLoginLog/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_SysLoginLog(HttpServletRequest request) {
        return selectListByPageHelper(request, sysLoginLogService, new SysLoginLogModel());
    }

    @ControllerMethodLog(remark = "登录日志-批量导入-下载模版")
    @GetMapping("/base/SysLoginLog/downloadTemplate.do")
    public void downloadTemplate_SysLoginLog(HttpServletResponse response) throws IOException {
        PoiExcelUtils.doDownloadWorkbook("登录日志模版.xlsx", response, SysLoginLogModel.class);
    }

    @ControllerMethodLog(remark = "登录日志-批量导入-导入")
    @PostMapping("/base/SysLoginLog/insertMoreByExcel.do")
    public HashMap insertMoreByExcel_SysLoginLog(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        HashMap<String, Object> returnMap = getReturnMap();
        if (multipartFile != null) {
            List<HashMap<String, Object>> listByFile = PoiExcelUtils.getListByFile(multipartFile, SysLoginLogModel.class);
            if (listByFile != null) {
                for (HashMap<String, Object> stringObjectHashMap : listByFile) {
                    if (stringObjectHashMap.isEmpty())
                        continue;
                    SysLoginLogModel baseModel = new SysLoginLogModel();
                    baseModel.init(baseModel);
                    BeanRefUtil.setFieldValue(baseModel, stringObjectHashMap);
                    sysLoginLogService.insert(baseModel);
                }
            } else {
                returnMap.put("message", "未能读取到数据");
            }
        } else {
            returnMap.put("message", "未找到文件，请核对参数名：[file]");
        }
        return returnMap;
    }
    //---------------------------登录日志-----------------------end


    //---------------------------操作日志-----------------------start
    @Resource
    private SysOperationLogService sysOperationLogService;

    @ControllerMethodLog(remark = "操作日志-插入")
    @PostMapping("/base/SysOperationLog/insert.do")
    public HashMap insert_SysOperationLog(HttpServletRequest request) {
        return insert(request, sysOperationLogService, new SysOperationLogModel());
    }

    @ControllerMethodLog(remark = "操作日志-删除")
    @PostMapping("/base/SysOperationLog/deleteById.do")
    public HashMap deleteById_SysOperationLog(@RequestParam("id") String id) {
        return delete(sysOperationLogService, id);
    }

    @ControllerMethodLog(remark = "操作日志-更新")
    @PostMapping("/base/SysOperationLog/updateById.do")
    public HashMap update_SysOperationLog(HttpServletRequest request) {
        return update(request, sysOperationLogService, new SysOperationLogModel());
    }

    @ControllerMethodLog(remark = "操作日志-查询一个")
    @PostMapping("/base/SysOperationLog/selectById.do")
    public HashMap selectById_SysOperationLog(@RequestParam("id") String id) {
        return selectById(sysOperationLogService, id);
    }

    @ControllerMethodLog(remark = "操作日志-查询多个")
    @PostMapping("/base/SysOperationLog/selectAll.do")
    public HashMap selectList_SysOperationLog(HttpServletRequest request) {
        return selectListByCondition(request, sysOperationLogService, new SysOperationLogModel());
    }

    @ControllerMethodLog(remark = "操作日志-查询分页")
    @PostMapping("/base/SysOperationLog/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_SysOperationLog(HttpServletRequest request) {
        return selectListByPageHelper(request, sysOperationLogService, new SysOperationLogModel());
    }

    @ControllerMethodLog(remark = "操作日志-批量导入-下载模版")
    @GetMapping("/base/SysOperationLog/downloadTemplate.do")
    public void downloadTemplate_SysOperationLog(HttpServletResponse response) throws IOException {
        PoiExcelUtils.doDownloadWorkbook("操作日志模版.xlsx", response, SysOperationLogModel.class);
    }

    @ControllerMethodLog(remark = "操作日志-批量导入-导入")
    @PostMapping("/base/SysOperationLog/insertMoreByExcel.do")
    public HashMap insertMoreByExcel_SysOperationLog(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        HashMap<String, Object> returnMap = getReturnMap();
        if (multipartFile != null) {
            List<HashMap<String, Object>> listByFile = PoiExcelUtils.getListByFile(multipartFile, SysOperationLogModel.class);
            if (listByFile != null) {
                for (HashMap<String, Object> stringObjectHashMap : listByFile) {
                    if (stringObjectHashMap.isEmpty())
                        continue;
                    SysOperationLogModel baseModel = new SysOperationLogModel();
                    baseModel.init(baseModel);
                    BeanRefUtil.setFieldValue(baseModel, stringObjectHashMap);
                    sysOperationLogService.insert(baseModel);
                }
            } else {
                returnMap.put("message", "未能读取到数据");
            }
        } else {
            returnMap.put("message", "未找到文件，请核对参数名：[file]");
        }
        return returnMap;
    }
    //---------------------------操作日志-----------------------end


    //---------------------------短信模板-----------------------start
    @Resource
    private SysSmsModelService sysSmsModelService;

    @ControllerMethodLog(remark = "短信模板-插入")
    @PostMapping("/base/SysSmsModel/insert.do")
    public HashMap insert_SysSmsModel(HttpServletRequest request) {
        return insert(request, sysSmsModelService, new SysSmsModelModel());
    }

    @ControllerMethodLog(remark = "短信模板-删除")
    @PostMapping("/base/SysSmsModel/deleteById.do")
    public HashMap deleteById_SysSmsModel(@RequestParam("id") String id) {
        return delete(sysSmsModelService, id);
    }

    @ControllerMethodLog(remark = "短信模板-更新")
    @PostMapping("/base/SysSmsModel/updateById.do")
    public HashMap update_SysSmsModel(HttpServletRequest request) {
        return update(request, sysSmsModelService, new SysSmsModelModel());
    }

    @ControllerMethodLog(remark = "短信模板-查询一个")
    @PostMapping("/base/SysSmsModel/selectById.do")
    public HashMap selectById_SysSmsModel(@RequestParam("id") String id) {
        return selectById(sysSmsModelService, id);
    }

    @ControllerMethodLog(remark = "短信模板-查询多个")
    @PostMapping("/base/SysSmsModel/selectAll.do")
    public HashMap selectList_SysSmsModel(HttpServletRequest request) {
        return selectListByCondition(request, sysSmsModelService, new SysSmsModelModel());
    }

    @ControllerMethodLog(remark = "短信模板-查询分页")
    @PostMapping("/base/SysSmsModel/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_SysSmsModel(HttpServletRequest request) {
        return selectListByPageHelper(request, sysSmsModelService, new SysSmsModelModel());
    }

    @ControllerMethodLog(remark = "短信模板-批量导入-下载模版")
    @GetMapping("/base/SysSmsModel/downloadTemplate.do")
    public void downloadTemplate_SysSmsModel(HttpServletResponse response) throws IOException {
        PoiExcelUtils.doDownloadWorkbook("短信模板模版.xlsx", response, SysSmsModelModel.class);
    }

    @ControllerMethodLog(remark = "短信模板-批量导入-导入")
    @PostMapping("/base/SysSmsModel/insertMoreByExcel.do")
    public HashMap insertMoreByExcel_SysSmsModel(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        HashMap<String, Object> returnMap = getReturnMap();
        if (multipartFile != null) {
            List<HashMap<String, Object>> listByFile = PoiExcelUtils.getListByFile(multipartFile, SysSmsModelModel.class);
            if (listByFile != null) {
                for (HashMap<String, Object> stringObjectHashMap : listByFile) {
                    if (stringObjectHashMap.isEmpty())
                        continue;
                    SysSmsModelModel baseModel = new SysSmsModelModel();
                    baseModel.init(baseModel);
                    BeanRefUtil.setFieldValue(baseModel, stringObjectHashMap);
                    sysSmsModelService.insert(baseModel);
                }
            } else {
                returnMap.put("message", "未能读取到数据");
            }
        } else {
            returnMap.put("message", "未找到文件，请核对参数名：[file]");
        }
        return returnMap;
    }
    //---------------------------短信模板-----------------------end

    @ControllerMethodLog(remark = "短信发送-发送测试")
    @PostMapping("/base/SysSms/sendTest.do")
    public HashMap sysSmsSendTest(
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("templateCode") String templateCode,
            @RequestParam("params") String params
    ) {

        return SMSUtils.sendInformAndLog(phoneNumber, templateCode, params, sysSmsLogService, sysSmsModelService);
    }

    //---------------------------短信发送日志-----------------------start
    @Resource
    private SysSmsLogService sysSmsLogService;

    @ControllerMethodLog(remark = "短信发送日志-插入")
    @PostMapping("/base/SysSmsLog/insert.do")
    public HashMap insert_SysSmsLog(HttpServletRequest request) {
        return insert(request, sysSmsLogService, new SysSmsLogModel());
    }

    @ControllerMethodLog(remark = "短信发送日志-删除")
    @PostMapping("/base/SysSmsLog/deleteById.do")
    public HashMap deleteById_SysSmsLog(@RequestParam("id") String id) {
        return delete(sysSmsLogService, id);
    }

    @ControllerMethodLog(remark = "短信发送日志-更新")
    @PostMapping("/base/SysSmsLog/updateById.do")
    public HashMap update_SysSmsLog(HttpServletRequest request) {
        return update(request, sysSmsLogService, new SysSmsLogModel());
    }

    @ControllerMethodLog(remark = "短信发送日志-查询一个")
    @PostMapping("/base/SysSmsLog/selectById.do")
    public HashMap selectById_SysSmsLog(@RequestParam("id") String id) {
        return selectById(sysSmsLogService, id);
    }

    @ControllerMethodLog(remark = "短信发送日志-查询多个")
    @PostMapping("/base/SysSmsLog/selectAll.do")
    public HashMap selectList_SysSmsLog(HttpServletRequest request) {
        return selectListByCondition(request, sysSmsLogService, new SysSmsLogModel());
    }

    @ControllerMethodLog(remark = "短信发送日志-查询分页")
    @PostMapping("/base/SysSmsLog/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_SysSmsLog(HttpServletRequest request) {
        return selectListByPageHelper(request, sysSmsLogService, new SysSmsLogModel());
    }

    @ControllerMethodLog(remark = "短信发送日志-批量导入-下载模版")
    @GetMapping("/base/SysSmsLog/downloadTemplate.do")
    public void downloadTemplate_SysSmsLog(HttpServletResponse response) throws IOException {
        PoiExcelUtils.doDownloadWorkbook("短信发送日志模版.xlsx", response, SysSmsLogModel.class);
    }

    @ControllerMethodLog(remark = "短信发送日志-批量导入-导入")
    @PostMapping("/base/SysSmsLog/insertMoreByExcel.do")
    public HashMap insertMoreByExcel_SysSmsLog(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        HashMap<String, Object> returnMap = getReturnMap();
        if (multipartFile != null) {
            List<HashMap<String, Object>> listByFile = PoiExcelUtils.getListByFile(multipartFile, SysSmsLogModel.class);
            if (listByFile != null) {
                for (HashMap<String, Object> stringObjectHashMap : listByFile) {
                    if (stringObjectHashMap.isEmpty())
                        continue;
                    SysSmsLogModel baseModel = new SysSmsLogModel();
                    baseModel.init(baseModel);
                    BeanRefUtil.setFieldValue(baseModel, stringObjectHashMap);
                    sysSmsLogService.insert(baseModel);
                }
            } else {
                returnMap.put("message", "未能读取到数据");
            }
        } else {
            returnMap.put("message", "未找到文件，请核对参数名：[file]");
        }
        return returnMap;
    }


    @ControllerMethodLog(remark = "短信发送日志-统计")
    @PostMapping("/base/SysSmsLog/selectSendCount.do")
    public HashMap selectSendCount_SysSmsLog(@RequestParam(required = false, name = "ssl_model_code") String ssl_model_code) {
        HashMap<String, Object> resultMap = getReturnMap(BaseConstant.Response_MENU.REQUEST_SELECT_FAILED);

        HashMap hashMap = sysSmsLogService.selectSendCount(ssl_model_code);
        if (hashMap != null) {
            getReturnMapSelect(resultMap, hashMap);
        }

        return resultMap;
    }

    //---------------------------短信发送日志-----------------------end

    //---------------------------短信验证码-----------------------start
    @Resource
    private SysSmsCodeService sysSmsCodeService;

    @ControllerMethodLog(remark = "短信验证码-插入")
    @PostMapping("/base/SysSmsCode/insert.do")
    public HashMap insert_SysSmsCode(HttpServletRequest request) {
        return insert(request, sysSmsCodeService, new SysSmsCodeModel());
    }

    @ControllerMethodLog(remark = "短信验证码-删除")
    @PostMapping("/base/SysSmsCode/deleteById.do")
    public HashMap deleteById_SysSmsCode(@RequestParam("id") String id) {
        return delete(sysSmsCodeService, id);
    }

    @ControllerMethodLog(remark = "短信验证码-更新")
    @PostMapping("/base/SysSmsCode/updateById.do")
    public HashMap update_SysSmsCode(HttpServletRequest request) {
        return update(request, sysSmsCodeService, new SysSmsCodeModel());
    }

    @ControllerMethodLog(remark = "短信验证码-查询一个")
    @PostMapping("/base/SysSmsCode/selectById.do")
    public HashMap selectById_SysSmsCode(@RequestParam("id") String id) {
        return selectById(sysSmsCodeService, id);
    }

    @ControllerMethodLog(remark = "短信验证码-查询多个")
    @PostMapping("/base/SysSmsCode/selectAll.do")
    public HashMap selectList_SysSmsCode(HttpServletRequest request) {
        return selectListByCondition(request, sysSmsCodeService, new SysSmsCodeModel());
    }

    @ControllerMethodLog(remark = "短信验证码-查询分页")
    @PostMapping("/base/SysSmsCode/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_SysSmsCode(HttpServletRequest request) {
        return selectListByPageHelper(request, sysSmsCodeService, new SysSmsCodeModel());
    }

    @ControllerMethodLog(remark = "短信验证码-批量导入-下载模版")
    @GetMapping("/base/SysSmsCode/downloadTemplate.do")
    public void downloadTemplate_SysSmsCode(HttpServletResponse response) throws IOException {
        PoiExcelUtils.doDownloadWorkbook("短信验证码模版.xlsx", response, SysSmsCodeModel.class);
    }

    @ControllerMethodLog(remark = "短信验证码-批量导入-导入")
    @PostMapping("/base/SysSmsCode/insertMoreByExcel.do")
    public HashMap insertMoreByExcel_SysSmsCode(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        HashMap<String, Object> returnMap = getReturnMap();
        if (multipartFile != null) {
            List<HashMap<String, Object>> listByFile = PoiExcelUtils.getListByFile(multipartFile, SysSmsCodeModel.class);
            if (listByFile != null) {
                for (HashMap<String, Object> stringObjectHashMap : listByFile) {
                    if (stringObjectHashMap.isEmpty())
                        continue;
                    SysSmsCodeModel baseModel = new SysSmsCodeModel();
                    baseModel.init(baseModel);
                    BeanRefUtil.setFieldValue(baseModel, stringObjectHashMap);
                    sysSmsCodeService.insert(baseModel);
                }
            } else {
                returnMap.put("message", "未能读取到数据");
            }
        } else {
            returnMap.put("message", "未找到文件，请核对参数名：[file]");
        }
        return returnMap;
    }
    //---------------------------短信验证码-----------------------end


    //---------------------------上传文件-----------------------start
    @Resource
    private SysFileService sysFileService;

    @ControllerMethodLog(remark = "上传文件-插入")
    @PostMapping("/base/SysFile/insert.do")
    public HashMap insert_SysFile(HttpServletRequest request) {
        return insert(request, sysFileService, new SysFileModel());
    }

    @ControllerMethodLog(remark = "上传文件-删除")
    @PostMapping("/base/SysFile/deleteById.do")
    public HashMap deleteById_SysFile(@RequestParam("id") String id) {
        return delete(sysFileService, id);
    }

    @ControllerMethodLog(remark = "上传文件-更新")
    @PostMapping("/base/SysFile/updateById.do")
    public HashMap update_SysFile(HttpServletRequest request) {
        return update(request, sysFileService, new SysFileModel());
    }

    @ControllerMethodLog(remark = "上传文件-查询一个")
    @PostMapping("/base/SysFile/selectById.do")
    public HashMap selectById_SysFile(@RequestParam("id") String id) {
        return selectById(sysFileService, id);
    }

    @ControllerMethodLog(remark = "上传文件-查询多个")
    @PostMapping("/base/SysFile/selectAll.do")
    public HashMap selectList_SysFile(HttpServletRequest request) {
        return selectListByCondition(request, sysFileService, new SysFileModel());
    }

    @ControllerMethodLog(remark = "上传文件-查询分页")
    @PostMapping("/base/SysFile/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_SysFile(HttpServletRequest request) {
        return selectListByPageHelper(request, sysFileService, new SysFileModel());
    }

    @ControllerMethodLog(remark = "上传文件-批量导入-下载模版")
    @GetMapping("/base/SysFile/downloadTemplate.do")
    public void downloadTemplate_SysFile(HttpServletResponse response) throws IOException {
        PoiExcelUtils.doDownloadWorkbook("上传文件模版.xlsx", response, SysFileModel.class);
    }

    @ControllerMethodLog(remark = "上传文件-批量导入-导入")
    @PostMapping("/base/SysFile/insertMoreByExcel.do")
    public HashMap insertMoreByExcel_SysFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        HashMap<String, Object> returnMap = getReturnMap();
        if (multipartFile != null) {
            List<HashMap<String, Object>> listByFile = PoiExcelUtils.getListByFile(multipartFile, SysFileModel.class);
            if (listByFile != null) {
                for (HashMap<String, Object> stringObjectHashMap : listByFile) {
                    if (stringObjectHashMap.isEmpty())
                        continue;
                    SysFileModel baseModel = new SysFileModel();
                    baseModel.init(baseModel);
                    BeanRefUtil.setFieldValue(baseModel, stringObjectHashMap);
                    sysFileService.insert(baseModel);
                }
            } else {
                returnMap.put("message", "未能读取到数据");
            }
        } else {
            returnMap.put("message", "未找到文件，请核对参数名：[file]");
        }
        return returnMap;
    }
    //---------------------------上传文件-----------------------end

    //---------------------------关系表-----------------------start

    //---------------------------关系表-----------------------end

    //---------------------------字典-----------------------start
    @Resource
    private BaseDirService baseDirService;

    @ControllerMethodLog(remark = "字典-插入")
    @PostMapping("/base/BaseDir/insert.do")
    public HashMap insert_BaseDir(HttpServletRequest request) {
        return insert(request, baseDirService, new BaseDirModel());
    }

    @ControllerMethodLog(remark = "字典-删除")
    @PostMapping("/base/BaseDir/deleteById.do")
    public HashMap deleteById_BaseDir(@RequestParam("id") String id) {
        return delete(baseDirService, id);
    }

    @ControllerMethodLog(remark = "字典-更新")
    @PostMapping("/base/BaseDir/updateById.do")
    public HashMap update_BaseDir(HttpServletRequest request) {
        printlnEnumeration(request);
        return update(request, baseDirService, new BaseDirModel());
    }

    @ControllerMethodLog(remark = "字典-查询一个")
    @PostMapping("/base/BaseDir/selectById.do")
    public HashMap selectById_BaseDir(@RequestParam("id") String id) {
        return selectById(baseDirService, id);
    }

    @ControllerMethodLog(remark = "字典-查询多个")
    @PostMapping("/base/BaseDir/selectAll.do")
    public HashMap selectList_BaseDir(HttpServletRequest request) {
        return selectListByCondition(request, baseDirService, new BaseDirModel());
    }

    @ControllerMethodLog(remark = "字典-查询分页")
    @PostMapping("/base/BaseDir/selectListByPageHelper.do")
    public HashMap selectListByPageHelper_BaseDir(HttpServletRequest request) {
        return selectListByPageHelper(request, baseDirService, new BaseDirModel());
    }

    @ControllerMethodLog(remark = "字典-批量导入-下载模版")
    @GetMapping("/base/BaseDir/downloadTemplate.do")
    public void downloadTemplate_BaseDir(HttpServletResponse response) throws IOException {
        PoiExcelUtils.doDownloadWorkbook("字典模版.xlsx", response, BaseDirModel.class);
    }

    @ControllerMethodLog(remark = "字典-批量导入-导入")
    @PostMapping("/base/BaseDir/insertMoreByExcel.do")
    public HashMap insertMoreByExcel_BaseDir(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        HashMap<String, Object> returnMap = getReturnMap();
        if (multipartFile != null) {
            List<HashMap<String, Object>> listByFile = PoiExcelUtils.getListByFile(multipartFile, BaseDirModel.class);
            if (listByFile != null) {
                for (HashMap<String, Object> stringObjectHashMap : listByFile) {
                    if (stringObjectHashMap.isEmpty())
                        continue;
                    BaseDirModel baseModel = new BaseDirModel();
                    baseModel.init(baseModel);
                    BeanRefUtil.setFieldValue(baseModel, stringObjectHashMap);
                    baseDirService.insert(baseModel);
                }
            } else {
                returnMap.put("message", "未能读取到数据");
            }
        } else {
            returnMap.put("message", "未找到文件，请核对参数名：[file]");
        }
        return returnMap;
    }
    //---------------------------字典-----------------------end

}
