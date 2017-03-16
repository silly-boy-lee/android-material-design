package com.zhu.material_design.common.util.network.okhttp.interceptor;


import com.zhu.material_design.common.util.network.okhttp.HttpInfo;

/**
 * 请求结果拦截器
 * @author zhousf
 */
@SuppressWarnings({"unused"})
public interface ResultInterceptor {

    HttpInfo intercept(HttpInfo info) throws Exception;

}
