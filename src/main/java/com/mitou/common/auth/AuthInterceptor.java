package com.mitou.common.auth;

import com.alibaba.fastjson.JSON;
import com.mitou.common.response.Result;
import com.mitou.common.response.ResultCode;
import com.mitou.common.utils.BaseUserUtil;
import com.mitou.user.entity.query.BaseMenuHasQuery;
import com.mitou.user.entity.vo.BaseMenuVo;
import com.mitou.user.service.IBaseMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限校验拦截
 * <p/>
 *
 * @author rice
 * @since 2021-03-26
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private IBaseMenuService baseMenuService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        //检查token有效性
        BaseUserUtil baseUserUtil = factory.getBean(BaseUserUtil.class);
        baseUserUtil.checkTokenLegal();
        //bean有未注入的风险
        if (null == baseMenuService) {
            baseMenuService = factory.getBean(IBaseMenuService.class);
        }
        //验证权限
        if (this.hasPermission(handler)) {
            return true;
        }
        //如果没有权限，则返回403异常，数据可返回已经定义好的状态码。
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
        response.getOutputStream().write(JSON.toJSONBytes(Result.failure(ResultCode.PERMISSION_NO_ACCESS)));
        return false;
    }

    /**
     * 鉴权
     *
     * @param handler
     * @return
     */
    private boolean hasPermission(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //获取注解
            RoleAuth roleAuth = handlerMethod.getMethod().getAnnotation(RoleAuth.class);
            //如果方法上没有注解，则获取类上的注解
            if (null == roleAuth) {
                roleAuth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RoleAuth.class);
            }
            //仍没有注解则放行
            if (null == roleAuth) {
                return true;
            }
            //如果标记了注解，则校验权限
            if (StringUtils.isNotBlank(roleAuth.value())) {
                //获取该用户的权限信息进行鉴权
                List<BaseMenuVo> menuList = baseMenuService.selectHas(new BaseMenuHasQuery());
                //如果权限清单为空，则直接返回
                if (CollectionUtils.isEmpty(menuList)) {
                    return false;
                }
                List<String> permissionSet = menuList.stream().map(BaseMenuVo::getMenuCode).collect(Collectors.toList());
                return permissionSet.contains(roleAuth.value());
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }
}
