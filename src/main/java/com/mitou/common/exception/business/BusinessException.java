package com.mitou.common.exception.business;

import com.mitou.common.response.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 全局异常处理
 *
 * @author rice
 * @since 2021-04-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -371546830261217755L;

    protected ResultCode resultCode;

    public BusinessException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

}
