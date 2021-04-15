package com.mitou.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mitou.common.response.Result;
import com.mitou.user.entity.BaseUser;
import com.mitou.user.entity.dto.BaseUserDto;
import com.mitou.user.entity.dto.BaseUserLoginDto;
import com.mitou.user.entity.query.BaseUserQuery;
import com.mitou.user.entity.vo.BaseUserLoginVo;
import com.mitou.user.entity.vo.BaseUserVo;
import com.mitou.user.service.IBaseUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    public Result logout() {
        return baseUserService.logout();
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "查询", httpMethod = "GET", response = BaseUserVo.class)
    @ApiImplicitParam(paramType = "path", name = "userId", value = "用户ID", required = true)
    public Result<BaseUserVo> selectByPrimaryKey(@PathVariable Long userId) {
        return baseUserService.selectByPrimaryKey(userId);
    }

    @GetMapping
    @ApiOperation(value = "分页查询", httpMethod = "GET", response = BaseUser.class)
    public Result<Page<BaseUserVo>> select(@ModelAttribute BaseUserQuery baseUserQuery,
                                           @ApiParam(name = "pageNo", required = true, value = "当前页") @RequestParam("pageNo") Integer pageNo,
                                           @ApiParam(name = "pageSize", required = true, value = "每页记录数") @RequestParam("pageSize") Integer pageSize) {
        return baseUserService.select(baseUserQuery, pageNo, pageSize);
    }

    @PostMapping
    @ApiOperation(value = "注册", httpMethod = "POST", response = Result.class)
    public Result insert(@RequestBody BaseUserDto baseUserDto) {
        return baseUserService.insert(baseUserDto);
    }

    @PutMapping
    @ApiOperation(value = "更新", httpMethod = "PUT", response = Result.class)
    public Result update(@RequestBody BaseUser baseUser) {
        return baseUserService.updateByPrimaryKeySelective(baseUser);
    }

    @DeleteMapping("/{userId}")
    @ApiOperation(value = "根据主键删除", httpMethod = "DELETE", response = Result.class)
    @ApiImplicitParam(paramType = "path", name = "userId", value = "用户ID", required = true)
    public Result delete(@PathVariable Long userId) {
        return baseUserService.deleteByPrimaryKey(userId);
    }
}
