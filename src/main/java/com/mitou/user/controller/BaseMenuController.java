package com.mitou.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mitou.user.response.Result;
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
    public Result<BaseMenu> selectByPrimaryKey(@PathVariable Long menuId) {
        return baseMenuService.selectByPrimaryKey(menuId);
    }

    @GetMapping
    @ApiOperation(value = "分页查询", httpMethod = "GET", response = BaseMenu.class)
    public Result<Page<BaseMenu>> select(@ModelAttribute BaseMenuQuery baseMenuQuery,
                                         @ApiParam(name = "pageNo", required = true, value = "当前页") @RequestParam("pageNo") Integer pageNo,
                                         @ApiParam(name = "pageSize", required = true, value = "每页记录数") @RequestParam("pageSize") Integer pageSize) {
        return baseMenuService.select(baseMenuQuery, pageNo, pageSize);
    }

    @GetMapping("/has")
//    @RoleAuth("add")
    @ApiOperation(value = "查询当前用户拥有权限的菜单列表", httpMethod = "GET", response = BaseMenuVo.class)
    public Result<List<BaseMenuVo>> selectHas(
            @ApiParam(value = "非必填，默认查询顶级的、菜单类型的菜单") @RequestBody(required = false) BaseMenuHasQuery baseMenuHasQuery) {
        return baseMenuService.selectHas(baseMenuHasQuery);
    }

    @GetMapping("/tree")
    @ApiOperation(value = "菜单树接口", httpMethod = "GET", response = Result.class)
    public Result<List<BaseMenuTreeVo>> getTree() {
        return baseMenuService.getTree();
    }

    @PostMapping
    @ApiOperation(value = "新增", httpMethod = "POST", response = Result.class)
    public Result insert(@RequestBody BaseMenu baseMenu) {
        return baseMenuService.insert(baseMenu);
    }

    @PostMapping("rel")
    @ApiOperation(value = "设置菜单", httpMethod = "POST", response = Result.class, notes = "为角色设置菜单，会覆盖之前的菜单")
    public Result rel(@RequestBody BaseRoleMenuDto baseRoleMenuDto) {
        return baseMenuService.rel(baseRoleMenuDto);
    }

    @PutMapping
    @ApiOperation(value = "更新", httpMethod = "PUT", response = Result.class)
    public Result update(@RequestBody BaseMenu baseMenu) {
        return baseMenuService.updateByPrimaryKeySelective(baseMenu);
    }

    @DeleteMapping("/{menuId}")
    @ApiOperation(value = "根据主键删除", httpMethod = "DELETE", response = Result.class)
    @ApiImplicitParam(paramType = "path", name = "menuId", value = "菜单ID", required = true)
    public Result delete(@PathVariable Long menuId) {
        return baseMenuService.deleteByPrimaryKey(menuId);
    }
}
