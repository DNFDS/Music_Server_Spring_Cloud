package com.example.demo.common.aop;

import com.example.standardconsumer.common.constants.UserLog;
import com.example.standardconsumer.common.domain.UserLogEnity;
import com.example.standardconsumer.common.service.UserLogService;
import com.example.standardconsumer.domain.User;
import com.example.standardconsumer.util.DateKit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * 用户日志记录切面
 * @author zhizhou
 */
@Aspect
@Component
public class UserLogAspect {

    @Autowired
    UserLogService userLogService;

    //定义切点@Pointcut
    //从注解的位置切入代码
    @Pointcut("@annotation(com.example.standardconsumer.common.constants.UserLog)")
    public void logPointCut() { }

    //切面 配置通知
    @AfterReturning(value = "logPointCut()", returning = "rtv")
    public void saveUserLog(JoinPoint joinPoint, Object rtv) {
        UserLogEnity userLogEnity = new UserLogEnity();

        //从切面织入点获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在方法
        Method method = signature.getMethod();

        //获取操作
        UserLog userLog = method.getAnnotation(UserLog.class);
        if (userLog != null) {
            userLogEnity.setType(userLog.value());
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        userLogEnity.setClassAndMethod(className + "." + methodName);

        //获取请求参数
        Object[] args = joinPoint.getArgs();
        StringBuffer param = new StringBuffer();
        for (int i=0; i<args.length; i++) {
            if(!(args[i] instanceof HttpSession)) {
                param.append(args[i]);
            } else {
                HttpSession session = (HttpSession) args[i];
                User user = (User) session.getAttribute("user");
                userLogEnity.setUserId(user.getUserid());
            }
        }

        userLogEnity.setReqMsg(param.toString().length() > 2000 ? param.toString().substring(0, 2000) : param.toString()  );
        userLogEnity.setResMsg(rtv.toString().length() > 5000 ? rtv.toString().substring(0, 5000) : rtv.toString()  );
        userLogEnity.setAddDate(DateKit.nowToTimeStr());
        userLogEnity.setAddTime(DateKit.nowToTimeStr());

        userLogService.addUserLog(userLogEnity);

    }
}
