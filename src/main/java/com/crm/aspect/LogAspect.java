package com.crm.aspect;

import net.sf.json.JSONArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Aspect
@Component
public class LogAspect {

    Logger log= LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    private ThreadLocal<Map> tlocal = new ThreadLocal<Map>();

    @Pointcut("execution(public * com.crm.controller..*.*(..))")
    public void webRequestLog() {}

    // @Order(5)
    @Before("webRequestLog()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            Thread.currentThread().setName(UUID.randomUUID().toString());
            long beginTime = System.currentTimeMillis();

            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String beanName = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            String uri = request.getRequestURI();
            String remoteAddr = getIpAddr(request);
            String sessionId = request.getSession().getId();
            String user = (String) request.getSession().getAttribute("user");
            String method = request.getMethod();
            String params = "";
            if ("POST".equals(method)) {
                Object[] paramsArray = joinPoint.getArgs();
                params = argsArrayToString(paramsArray);
            } else {
                Map<?, ?> paramsMap = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
                params = paramsMap.toString();
                params=request.getQueryString();
            }

            System.out.println("123:"+params);
            log.debug("uri=" + uri + "; beanName=" + beanName + "; remoteAddr=" + remoteAddr + "; user=" + user
                    + "; methodName=" + methodName + "; params=" + params);

            Map optLog = new HashMap();
            optLog.put("beanName",beanName);
            optLog.put("curUser",user);
            optLog.put("methodName",methodName);
            optLog.put("params",params != null ? params.toString() : "");
            optLog.put("remoteAddr",remoteAddr);
            optLog.put("sessionId",sessionId);
            optLog.put("uri",uri);
            optLog.put("beginTime",beginTime);

            tlocal.set(optLog);

        } catch (Exception e) {
            log.error("***操作请求日志记录失败doBefore()***", e);
        }
    }

    // @Order(5)
    @AfterReturning(returning = "result", pointcut = "webRequestLog()")
    public void doAfterReturning(Object result) {
        try {
            // 处理完请求，返回内容
            Map optLog = tlocal.get();
            optLog.put("result",result.toString());

            long beginTime = (long) optLog.get("beginTime");
            long requestTime = (System.currentTimeMillis() - beginTime);
            optLog.put("requestTime",requestTime);

            log.info("请求耗时：" + optLog.get("requestTime") + optLog.get("uri") + "   **  " + optLog.get("params")+ " ** "
                    + optLog.get("methodName"));
            log.info("RESPONSE : " + result);

//            optLogService.saveLog(optLog);
        } catch (Exception e) {
            log.error("***操作请求日志记录失败doAfterReturning()***", e);
        }
    }


    /**
     * 获取登录用户远程主机ip地址
     *
     * @param request
     * @return
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 请求参数拼装
     *
     * @param paramsArray
     * @return
     */
    private String argsArrayToString(Object[] paramsArray) {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0) {
            for (int i = 0; i < paramsArray.length; i++) {
                JSONArray array = JSONArray.fromObject(paramsArray[i]);
                params += array.toString() + " ";
            }
        }
        return params.trim();
    }

}