package cn.iocoder.oceans.webapp.bff.config;

import cn.iocoder.oceans.core.constants.ErrorCodeEnum;
import cn.iocoder.oceans.core.exception.ServiceException;
import cn.iocoder.oceans.webapp.bff.vo.RestResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public RestResult serviceExceptionHandler(HttpServletRequest req, Exception e) {
        ServiceException ex = (ServiceException) e;
        return RestResult.error(ex.getCode(), ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public RestResult resultExceptionHandler(HttpServletRequest req, Exception e) {
        // TODO 异常日志
        e.printStackTrace();
        // 返回
        return RestResult.error(ErrorCodeEnum.SYS_ERROR.getCode(), ErrorCodeEnum.SYS_ERROR.getMessage());
    }

}