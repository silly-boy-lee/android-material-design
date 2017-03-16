package com.zhu.material_design.common.util.network.okhttp.interceptor;


import com.zhu.material_design.common.util.network.okhttp.HttpInfo;

/**
 * 请求链路异常（非业务逻辑）拦截器
 * @author zhousf
 */
@SuppressWarnings({"unused"})
public interface ExceptionInterceptor {

    HttpInfo intercept(HttpInfo info) throws Exception;

}
