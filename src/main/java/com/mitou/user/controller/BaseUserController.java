package com.mitou.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mitou.common.auth.RoleAuth;
import com.mitou.common.response.Result;
import com.mitou.user.entity.BaseUser;
import com.mitou.user.entity.dto.BaseUserLoginDto;
import com.mitou.user.entity.dto.BaseUserSaveDto;
import com.mitou.user.entity.dto.BaseUserUpdateDto;
import com.mitou.user.entity.dto.BaseUserUpdatePwdDto;
import com.mitou.user.entity.query.BaseUserQuery;
import com.mitou.user.entity.vo.BaseUserLoginVo;
import com.mitou.user.entity.vo.BaseUserVo;
import com.mitou.user.service.IBaseUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 菜单api
 * <p/>
 *
 * @author rice
 * @since 2021-03-25
 */
@RestController
@RequestMapping("/user")
@Api(value = "/user", tags = "系统用户")
public class BaseUserController {

    @Resource
    private IBaseUserService baseUserService;

    @PostMapping("/login")
    @ApiOperation(value = "登录", httpMethod = "POST", response = String.class, notes = "返回token")
    public Result<BaseUserLoginVo> login(@RequestBody BaseUserLoginDto baseUserLoginDto) {
        return baseUserService.login(baseUserLoginDto);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "退出登录", httpMethod = "POST", response = Result.class, notes = "退出登录")
    public Result<Boolean> logout() {
        return Result.success(baseUserService.logout());
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "查询", httpMethod = "GET", response = BaseUserVo.class)
    @ApiImplicitParam(paramType = "path", name = "userId", value = "用户ID", required = true)
    public Result<BaseUserVo> getById(@PathVariable Long userId) {
        return Result.success(baseUserService.getById(userId));
    }

    @GetMapping
    @ApiOperation(value = "分页查询", httpMethod = "GET", response = BaseUser.class)
    //配置访问此方法的权限码。取的是菜单code
    @RoleAuth("role_user_page")
    public Result<Page<BaseUserVo>> page(@ModelAttribute BaseUserQuery baseUserQuery,
                                         @ApiParam(name = "pageNo", required = true, value = "当前页") @RequestParam("pageNo") Integer pageNo,
                                         @ApiParam(name = "pageSize", required = true, value = "每页记录数") @RequestParam("pageSize") Integer pageSize) {
        return Result.success(baseUserService.page(baseUserQuery, pageNo, pageSize));
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册", httpMethod = "POST", response = Result.class)
    public Result<Boolean> register(@RequestBody BaseUserSaveDto saveDto) {
        return Result.success(baseUserService.register(saveDto));
    }

    @GetMapping("/checkLegal")
    @ApiOperation(value = "检查登录名是否合法", httpMethod = "GET", response = Result.class, notes = "true：合法；false：不合法")
    public Result<Boolean> checkPhoneLegal(
            @ApiParam(value = "phone", name = "电话") @RequestParam(value = "phone") String phone) {
        return Result.success(baseUserService.checkPhoneLegal(phone));
    }

    @PutMapping("/{userId}")
    @ApiOperation(value = "更新", httpMethod = "PUT", response = Result.class)
    public Result<Boolean> update(@PathVariable("userId") Long userId, @RequestBody BaseUserUpdateDto updateDto) {
        BaseUser baseUser = new BaseUser();
        BeanUtils.copyProperties(updateDto, baseUser);
        baseUser.setUserId(userId);
        return Result.success(baseUserService.updateById(baseUser));
    }

    @PutMapping("/pwd")
    @ApiOperation(value = "更新密码", httpMethod = "PUT", response = Result.class)
    public Result<Boolean> updatePwd(@RequestBody BaseUserUpdatePwdDto updatePwdDto) {
        return baseUserService.updatePwd(updatePwdDto);
    }

    @DeleteMapping("/{userIds}")
    @ApiOperation(value = "根据主键删除", httpMethod = "DELETE", response = Result.class)
    @ApiImplicitParam(paramType = "path", name = "userIds", value = "用户IDs", required = true)
    public Result<Boolean> delete(@PathVariable List<Long> userIds) {
        return Result.success(baseUserService.deleteByIds(userIds));
    }
}
