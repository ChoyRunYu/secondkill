package com.secondkill.system.user.controller;

import com.secondkill.api.user.dto.UserDTO;
import com.secondkill.api.user.entry.TbAdminUser;
import com.secondkill.api.user.entry.TbUser;
import com.secondkill.api.user.vo.AdminUserVo;
import com.secondkill.api.user.vo.UserVO;
import com.secondkill.common.enums.ResultErrEnum;
import com.secondkill.common.expection.AppRuntimeException;
import com.secondkill.common.utils.Result;
import com.secondkill.common.utils.ResultUtils;
import com.secondkill.system.user.service.UserAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Choy
 * @date 2021/03/01
 *
 */
@RestController
@RequestMapping("/user")
public class UserAdminController {


    private final static Logger logger = LoggerFactory.getLogger(UserAdminController.class);

    @Autowired
    private UserAdminService userAdminService;

    /**
     * 管理员登录接口-微服务调用，给鉴权中心调用
     * @param reqMap
     * @return
     */
    @PostMapping("/admin/login")
    public Result adminLogin(@RequestBody Map<String, String> reqMap){
        // 参数校验
        String username = reqMap.get("username");
        String password = reqMap.get("password");
        if (username == null || "".equals(username)){
            throw new AppRuntimeException(ResultErrEnum.EMPTY_PARAM_ERROR);
        }
        if (password == null || "".equals(password)){
            throw new AppRuntimeException(ResultErrEnum.EMPTY_PARAM_ERROR);
        }
        // 登录操作
        Optional<TbAdminUser> login = Optional.ofNullable(userAdminService.login(username, password));
        if (!login.isPresent()){
            throw new AppRuntimeException(ResultErrEnum.USER_NOT_FOUND);
        }
        AdminUserVo adminUserVo = new AdminUserVo();
        BeanUtils.copyProperties(login.get(), adminUserVo);
        return ResultUtils.success(adminUserVo);
    }


    /**
     * 根据token获取管理员用户信息
     * @param request
     * @return
     */
    @GetMapping("/admin/info")
    public Result getAdminInfo(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        TbAdminUser tbAdminUser = userAdminService.getAdminInfoById(token);
        logger.debug("返回的管理员信息为：{}", tbAdminUser);
        return ResultUtils.success(tbAdminUser);
    }

    /**
     * 列出商城所有会员, 默认10条
     * @param page 分页参数
     * @return
     */
    @GetMapping("/admin/listUser/{page}")
    public Result listUser(@PathVariable("page") Integer page){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 分页参数校验
        if (page == null || page == 0){
            page = 1;
        }
        // 获取用户总条数，分页模块用
        int countUser = userAdminService.countUser();
        int dbPage = countUser % 10 == 0 ? countUser / 10 : countUser / 10 + 1;
        // 如果传入超过数据库总页数，则默认返回最后一页
        if (dbPage < page){
            page = dbPage;
        }
        Optional<List<TbUser>> tbUsers = Optional.ofNullable(userAdminService.listUser(10 * (page - 1), 10));
        if (!tbUsers.isPresent()){
            throw new AppRuntimeException(ResultErrEnum.SYSTEM_ERROR);
        }
        // bean转换
        List<UserVO> userVOList = new ArrayList<>();
        for (TbUser tbUser : tbUsers.get()) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(tbUser, userVO);
            userVO.setCreateTime(simpleDateFormat.format(tbUser.getCreateTime()));
            userVOList.add(userVO);
        }
        HashMap<String, Object> reqMap = new HashMap<>(2);
        reqMap.put("total", countUser);
        reqMap.put("userlist", userVOList);
        logger.debug("返回的数据为：{}", reqMap);
        return ResultUtils.success(reqMap);
    }


    /**
     * 删除商城会员
     * @param reqMap
     * @return
     */
    @PostMapping("/admin/delUser")
    public Result delUser(@RequestBody HashMap<String, Integer[]> reqMap){
        Integer[] ids = reqMap.get("ids");
        if (ids == null || ids.length == 0){
            throw new AppRuntimeException(ResultErrEnum.INVALID_PARAM);
        }
        Integer affectRows = 0;
        for (Integer id : ids) {
            affectRows += userAdminService.delUser(id);
        }
        if (affectRows <= 0){
            throw new AppRuntimeException(ResultErrEnum.DEL_ACTION_FAIL);
        }
        logger.debug("删除成功，删除的用户id为：{}", ids);
        return ResultUtils.success("删除成功");
    }

    /**
     * 启用/停用商城会员接口
     * @return
     */
    @PostMapping("/admin/change/enable")
    public Result updateUserIsEnable(@RequestBody HashMap<String, Integer> reqMap){
        Integer userId = reqMap.get("userId");
        Integer enable = reqMap.get("enable");
        // 参数校验
        if (userId == null || userId == 0){
            throw new AppRuntimeException(ResultErrEnum.INVALID_PARAM);
        }
        // enable只能为1或者0
        if (enable == null || (enable != 0 && enable != 1)){
            throw new AppRuntimeException(ResultErrEnum.INVALID_PARAM);
        }
        int affectRows = userAdminService.updateUserIsEnable(userId, enable);
        if (affectRows <= 0){
            throw new AppRuntimeException(ResultErrEnum.MOD_ACTION_FAIL);
        }
        return ResultUtils.success("修改成功");
    }

    /**
     * 添加新会员
     * @param userVO
     * @return
     */
    @PostMapping("/admin/insertUser")
    public Result insertUser(@RequestBody @Validated UserVO userVO){
        TbUser tbUser = new TbUser();
        BeanUtils.copyProperties(userVO, tbUser);
        int affectRows = userAdminService.insertUser(tbUser);
        if (affectRows <= 0){
            throw new AppRuntimeException(ResultErrEnum.ADD_ACTION_FAIL);
        }
        logger.debug("添加会员：{}", tbUser);
        return ResultUtils.success("添加成功");
    }

    /**
     * 获取一个会员的具体信息
     * @param id
     * @return
     */
    @GetMapping("/admin/getUser/{id}")
    public Result getUserInfo(@PathVariable("id") Integer id){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (id == null || id == 0){
            throw new AppRuntimeException(ResultErrEnum.INVALID_PARAM);
        }
        Optional<TbUser> userById = Optional.ofNullable(userAdminService.getUserById(id));
        if (!userById.isPresent()){
            throw new AppRuntimeException(ResultErrEnum.SYSTEM_ERROR);
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userById.get(), userVO);
        userVO.setCreateTime(simpleDateFormat.format(userById.get().getCreateTime()));
        return ResultUtils.success(userVO);
    }

    /**
     * 修改用户信息
     * @param userDTO
     * @return
     */
    @PostMapping("/admin/updateUser")
    public Result updateUser(@RequestBody UserDTO userDTO){
        Integer affectRows = userAdminService.updateUser(userDTO);
        if (affectRows <= 0){
            throw new AppRuntimeException(ResultErrEnum.MOD_ACTION_FAIL);
        }
        return ResultUtils.success("修改成功");

    }
}
