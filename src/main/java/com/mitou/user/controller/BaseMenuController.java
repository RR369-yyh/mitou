package com.mitou.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mitou.common.response.Result;
import com.mitou.user.entity.BaseMenu;
import com.mitou.user.entity.dto.BaseRoleMenuDto;
import com.mitou.user.entity.query.BaseMenuHasQuery;
import com.mitou.user.entity.query.BaseMenuQuery;
import com.mitou.user.entity.vo.BaseMenuTreeVo;
import com.mitou.user.entity.vo.BaseMenuVo;
import com.mitou.user.service.IBaseMenuService;
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
@RequestMapping("/menu")
@Api(value = "/menu", tags = "系统菜单")
public class BaseMenuController {

    @Resource
    private IBaseMenuService baseMenuService;

    @GetMapping("/{menuId}")
    @ApiOperation(value = "查询", httpMethod = "GET", response = BaseMenu.class)
    @ApiImplicitParam(paramType = "path", name = "menuId", value = "菜单ID", required = true)
    public Result<BaseMenu> getById(@PathVariable Long menuId) {
        return Result.success(baseMenuService.getById(menuId));
    }

    @GetMapping
    @ApiOperation(value = "分页查询", httpMethod = "GET", response = BaseMenu.class)
    public Result<Page<BaseMenu>> page(@ModelAttribute BaseMenuQuery baseMenuQuery,
                                         @ApiParam(name = "pageNo", required = true, value = "当前页") @RequestParam("pageNo") Integer pageNo,
                                         @ApiParam(name = "pageSize", required = true, value = "每页记录数") @RequestParam("pageSize") Integer pageSize) {
        return Result.success(baseMenuService.page(baseMenuQuery, pageNo, pageSize));
    }

    @GetMapping("/has")
//    @RoleAuth("add")
    @ApiOperation(value = "查询当前用户拥有权限的菜单列表", httpMethod = "GET", response = BaseMenuVo.class)
    public Result<List<BaseMenuVo>> selectHas(
            @ApiParam(value = "非必填，默认查询顶级的、菜单类型的菜单") @RequestBody(required = false) BaseMenuHasQuery baseMenuHasQuery) {
        return Result.success(baseMenuService.selectHas(baseMenuHasQuery));
    }

    @GetMapping("/tree")
    @ApiOperation(value = "菜单树接口", httpMethod = "GET", response = Result.class)
    public Result<List<BaseMenuTreeVo>> getTree() {
        return Result.success(baseMenuService.getTree());
    }

    @PostMapping
    @ApiOperation(value = "新增", httpMethod = "POST", response = Result.class)
    public Result<Boolean> insert(@RequestBody BaseMenu baseMenu) {
        return Result.success(baseMenuService.save(baseMenu));
    }

    @PostMapping("rel")
    @ApiOperation(value = "设置菜单", httpMethod = "POST", response = Result.class, notes = "为角色设置菜单，会覆盖之前的菜单")
    public Result<Boolean> rel(@RequestBody BaseRoleMenuDto baseRoleMenuDto) {
        return Result.success(baseMenuService.rel(baseRoleMenuDto));
    }

    @PutMapping
    @ApiOperation(value = "更新", httpMethod = "PUT", response = Result.class)
    public Result<Boolean> update(@RequestBody BaseMenu baseMenu) {
        return Result.success(baseMenuService.updateById(baseMenu));
    }

    @DeleteMapping("/{menuId}")
    @ApiOperation(value = "根据主键删除", httpMethod = "DELETE", response = Result.class)
    @ApiImplicitParam(paramType = "path", name = "menuId", value = "菜单ID", required = true)
    public Result<Boolean> delete(@PathVariable Long menuId) {
        return Result.success(baseMenuService.deleteById(menuId));
    }
}
