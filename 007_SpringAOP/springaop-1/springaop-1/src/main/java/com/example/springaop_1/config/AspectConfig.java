package com.example.springaop_1.config;

import com.example.springaop_1.Exception.UniversalException;
import com.example.springaop_1.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;

@Aspect
@Component
public class AspectConfig {
    private final Logger log = LoggerFactory.getLogger(AspectConfig.class);

    private final boolean isDebuggingEnable = log.isDebugEnabled();

    @Value("${aspect.isLoggingAspectEnable}")
    private boolean isLoggingAspectEnable ;


    //private final AtomicInteger dbCallCount = new AtomicInteger(0);

    // Define pointcut for all REST controller methods

    @Pointcut("within(com.example.springaop_1.controller..*) && (" +
            "@annotation(org.springframework.web.bind.annotation.RequestMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping))")
    public void springControllerPointcut() {

    }


    // Define pointcut for all service  Class
    @Pointcut("within(com.example.springaop_1.service..*)")
    public void servicePointcut() {
    }

    //Define pointcut for all repository interfaces
//    @Pointcut("execution(* com.example.springaop_1.repository..*(..))")
//    public void repositoryPointcut() {
//    }

    // To calculate Number of Database calls for 1 request
//    @Before("execution(* org.springframework.data.repository.Repository+.*(..))")
//    public void logDatabaseCall() {
//      dbCallCount.incrementAndGet();
//    }
//
//    @AfterReturning(value = "springControllerPointcut()")
//    public  void getDBCount(){
//      log.debug("DB Call Count : " + dbCallCount.get());
//      dbCallCount.set(0);
//    }



    //Define before and after for controller
    @Before(value = "springControllerPointcut()")
    public void logBeforeForController(JoinPoint joinPoint) {
        log.info("isDebuggingEnable : {} ::: isLoggingAspectEnable {} ",isDebuggingEnable,isLoggingAspectEnable);
        try {
            if (isDebuggingEnable && isLoggingAspectEnable) {
                Object[] args = joinPoint.getArgs();     // method arguments(actual values)
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                Method method = signature.getMethod();
                String methodName = signature.getName();
                //String requestType = getRequestType(method);
                //String packageName = method.getDeclaringClass().getPackage().getName();

                // Get parameter names and their corresponding values
                String[] parameterNames = signature.getParameterNames();

                Object[] result = this.getParams(args, parameterNames);

                StringBuilder params = (StringBuilder) result[0];
                HttpServletRequest request = (HttpServletRequest) result[1];

                log.debug("{} API Call Started :: {} [{}] ", methodName, new Date(), params);

                //Printing request object details
            if (request != null) {
                logHttpRequestDetails(request);
            }

            }
        } catch (Exception e) {
            log.error("Error in logBeforeForController {}", e.getMessage());
            e.printStackTrace();
        }
    }


    @AfterReturning(value = "springControllerPointcut()", returning = "result")
    public void logAfterForController(JoinPoint joinPoint, Object result) {

        try {
            if (isDebuggingEnable && isLoggingAspectEnable) {
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                String methodName = signature.getName();

                //Method method = signature.getMethod();
                //Class<?> returnType = method.getReturnType();

                log.debug("{} API Call Ended :: {} {}", methodName, new Date(), result);
            }
        } catch (Exception e) {
            log.error("Error in LoggingAspect {}", e.getMessage());
            e.printStackTrace();
        }
    }


     //Define before and after for Service
    @Before(value = "servicePointcut()")
    public void logBeforeForService(JoinPoint joinPoint) {
        try {
            if (isDebuggingEnable && isLoggingAspectEnable) {
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                String methodName = signature.getName();
                log.debug("{} Service Call Started :: {} ", methodName, new Date());
            }

        } catch (Exception e) {
            log.error("Error in logBeforeForService {}", e.getMessage());
            e.printStackTrace();
        }
    }

    @AfterReturning(value = "servicePointcut()", returning = "result")
    public void logAfterForService(JoinPoint joinPoint, Object result) {
        try{
        if (isDebuggingEnable && isLoggingAspectEnable) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            String methodName = signature.getName();
            log.debug("{} Service Call Ended :: {}", methodName,  new Date());
        }
        } catch (Exception e) {
            log.error("Error in logAfterForService {}", e.getMessage());
            e.printStackTrace();
        }
    }

    // Define exception Controller and service packages
    @AfterThrowing(pointcut = "springControllerPointcut() || servicePointcut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        try {
            if (isDebuggingEnable && isLoggingAspectEnable) {
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                Method method = signature.getMethod();
                String methodName = joinPoint.getSignature().getName();
                String packageName = method.getDeclaringClass().getPackage().getName();
                log.error("Exception Occurred in {}.{}() :: {}", packageName, methodName, exception.getMessage());
            }

        } catch (Exception e) {
            log.error("Error in logException(LoggingAspect) {}", e.getMessage());
            e.printStackTrace();
        }
    }


    private Object[] getParams(Object[] args, String[] parameterNames) {
        HttpServletRequest request = null;
        StringBuilder params = new StringBuilder();

        try {
            for (int i = 0; i < parameterNames.length; i++) {
                if (args[i] instanceof HttpServletRequest) {
                    request = (HttpServletRequest) args[i];
                } else {
                        params.append(parameterNames[i]).append("=").append(args[i]).append(", ");
                }
            }
            // Remove the trailing comma and space
            if (params.length() > 0) {
                params.setLength(params.length() - 2);
            }

        } catch (Exception e) {
            log.error("Error in getParams(LoggingAspect) {}", e.getMessage());
            e.printStackTrace();
        }

        return new Object[]{params, request};
    }

//    private String getRequestType(Method method) {
//        try {
//            if (method.isAnnotationPresent(GetMapping.class)) {
//                return "GET";
//            } else if (method.isAnnotationPresent(PostMapping.class)) {
//                return "POST";
//            } else if (method.isAnnotationPresent(PutMapping.class)) {
//                return "PUT";
//            } else if (method.isAnnotationPresent(DeleteMapping.class)) {
//                return "DELETE";
//            } else if (method.isAnnotationPresent(RequestMapping.class)) {
//                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
//                RequestMethod[] methods = requestMapping.method();
//                if (methods.length > 0) {
//                    return methods[0].name();
//                }
//                return "REQUEST";
//            }
//            return "UNKNOWN";
//
//        } catch (Exception e) {
//            log.error("Error in getRequestType(LoggingAspect) {}", e.getMessage());
//            e.printStackTrace();
//        }
//        return null;
//    }

//    private String getRequestURL() {
//        try {
//            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            HttpServletRequest request = attributes.getRequest();
//            String requestURI = request.getRequestURI();
//            return requestURI;
//        } catch (Exception e) {
//            log.error("Error in getRequestURL(LoggingAspect) {}", e.getMessage());
//            e.printStackTrace();
//        }
//        return null;
//    }


    private void logHttpRequestDetails(HttpServletRequest request) {
        try{
        StringBuilder requestDetails = new StringBuilder();
        requestDetails.append("Request URI: ").append(request.getRequestURI()).append("\n");
        requestDetails.append("Request Method: ").append(request.getMethod()).append("\n");
        requestDetails.append("Request Headers: ").append("\n");

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            requestDetails.append("  ").append(headerName).append(": ").append(request.getHeader(headerName)).append("\n");
        }

        requestDetails.append("Request Parameters: ").append("\n");
        request.getParameterMap().forEach((paramName, paramValues) -> {
                requestDetails.append("  ").append(paramName).append(": ").append(Arrays.toString(paramValues)).append("\n");
        });

        log.debug("HttpServletRequest details: \n{}", requestDetails.toString());

        }catch (Exception e){
            log.error("Error in logHttpRequestDetails(LoggingAspect) {}", e.getMessage());
            e.printStackTrace();
        }
    }

    // To execute method
    @Autowired
    private AuthService authService;

    @Before("execution(* com.example.springaop_1.controller.EmployeeController.getEmployeeById(..))")
    public void authenticateBeforeMethod() throws UniversalException {
            if (isDebuggingEnable && isLoggingAspectEnable) {
                log.info("Authenticating before executing getEmployeeById method");
                boolean isAuthenticated = authService.authenticate();
                if (!isAuthenticated) {
                    log.error("Authentication failed");
                    throw new UniversalException("Authentication failed");
                }
            }
    }
}
