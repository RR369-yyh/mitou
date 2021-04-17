package com.mitou.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mitou.common.response.Result;
import com.mitou.user.entity.BaseRole;
import com.mitou.user.entity.BaseUser;
import com.mitou.user.entity.dto.BaseUserRoleDto;
import com.mitou.user.entity.query.BaseRoleQuery;
import com.mitou.user.service.IBaseRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("/role")
@Api(value = "/role", tags = "系统角色")
public class BaseRoleController {

    @Resource
    private IBaseRoleService baseRoleService;

    @GetMapping("/{roleId}")
    @ApiOperation(value = "查询", httpMethod = "GET", response = BaseRole.class)
    @ApiImplicitParam(paramType = "path", name = "roleId", value = "角色ID", required = true)
    public Result<BaseRole> getById(@PathVariable Long roleId) {
        return Result.success(baseRoleService.getById(roleId));
    }

    @GetMapping
    @ApiOperation(value = "分页查询", httpMethod = "GET", response = BaseRole.class)
    public Result<Page<BaseRole>> page(@ModelAttribute BaseRoleQuery baseRoleQuery,
                                         @ApiParam(name = "pageNo", required = true, value = "当前页") @RequestParam("pageNo") Integer pageNo,
                                         @ApiParam(name = "pageSize", required = true, value = "每页记录数") @RequestParam("pageSize") Integer pageSize) {
        return Result.success(baseRoleService.page(baseRoleQuery, pageNo, pageSize));
    }

    @GetMapping("/user")
    @ApiOperation(value = "查询单个用户的角色列表", httpMethod = "GET", response = BaseUser.class)
    public Result<List<BaseRole>> getByUserId(@ApiParam(value = "用户ID，不传则查询当前登录人的") @RequestParam(value = "userId", required = false) Long userId) {
        return Result.success(baseRoleService.getByUserId(userId));
    }

    @PostMapping
    @ApiOperation(value = "新增", httpMethod = "POST", response = Result.class)
    public Result<Boolean> insert(@RequestBody BaseRole baseRole) {
        return Result.success(baseRoleService.save(baseRole));
    }

    @PostMapping("rel")
    @ApiOperation(value = "设置角色", httpMethod = "POST", response = Result.class, notes = "为用户设置角色，会覆盖之前的角色")
    public Result<Boolean> rel(@RequestBody BaseUserRoleDto baseUserRoleDto) {
        return Result.success(baseRoleService.rel(baseUserRoleDto));
    }

    @PutMapping
    @ApiOperation(value = "更新", httpMethod = "PUT", response = Result.class)
    public Result<Boolean> update(@RequestBody BaseRole baseRole) {
        return Result.success(baseRoleService.updateById(baseRole));
    }

    @DeleteMapping("/{roleId}")
    @ApiOperation(value = "根据主键删除", httpMethod = "DELETE", response = Result.class)
    @ApiImplicitParam(paramType = "path", name = "roleId", value = "角色ID", required = true)
    public Result<Boolean> delete(@PathVariable Long roleId) {
        return Result.success(baseRoleService.deleteById(roleId));
    }
}
