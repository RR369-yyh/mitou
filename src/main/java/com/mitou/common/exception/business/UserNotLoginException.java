package com.mitou.common.exception.business;

import com.mitou.common.response.ResultCode;

/**
 * 用户未登录异常
 *
 * @author rice
 * @since 2021-03-25
 */
public class UserNotLoginException extends BusinessException {

    private static final long serialVersionUID = -3230639425797390753L;

    public UserNotLoginException() {
        super(ResultCode.USER_NOT_LOGGED_IN);
    }

}
