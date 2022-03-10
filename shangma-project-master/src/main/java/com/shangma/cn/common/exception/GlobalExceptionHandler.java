package com.shangma.cn.common.exception;

import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.common.http.AxiosStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/24 11:01
 * 文件说明：
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public AxiosResult<Void> handlerFormValidException(ApiException e) {
        return AxiosResult.error(e.getAxiosStatus());
    }


    @ExceptionHandler(Throwable.class)
    public AxiosResult<Void> handlerThrowable(Throwable e) {
        System.out.println(e);
        AxiosStatus uploadError = AxiosStatus.UNKOWN_ERROR;
        if(!StringUtils.isEmpty(e.getMessage())) {
            uploadError.setMessage(e.getMessage());
        }
        return AxiosResult.error(uploadError);
    }

    @ExceptionHandler(FormValidException.class)
    public AxiosResult<Map<String, String>> handlerFormValidException(FormValidException e) {
        AxiosStatus axiosStatus = e.getAxiosStatus();
        return AxiosResult.error(axiosStatus, e.getMap());
    }

    /**
     * 处理表单校验异常
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AxiosResult<String> handlerFormValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        AxiosStatus formValidError = AxiosStatus.FORM_VALID_ERROR;
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (!CollectionUtils.isEmpty(fieldErrors)) {
            FieldError fieldError = fieldErrors.get(0);
            String defaultMessage = fieldError.getDefaultMessage();
            formValidError.setMessage(defaultMessage);
            return AxiosResult.error(formValidError);
        }
        formValidError.setMessage("表单校验错误");
        return AxiosResult.error(formValidError);
    }
}
