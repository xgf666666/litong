package com.weibiaogan.litong.aspect;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.weibiaogan.litong.common.Constants;
import com.weibiaogan.litong.ui.login.LoginActivity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * author: Gubr
 * date: 2018/6/5
 * describe:
 */

@Aspect
public class ActivityAspect {

    private static final String TAG = "ActivityAspect";

    @Pointcut("execution(@com.weibiaogan.mingchuang.aspect.CheckLogin * *(..))")
    public void methodChecklogin() {
    }


    @Around("methodChecklogin()")
    public void aroundCheckLogin(ProceedingJoinPoint joinPoint) throws Throwable {
        if (Constants.isLogin()) {
            joinPoint.proceed();
        } else {
            for (Object o : joinPoint.getArgs()) {
                if (o instanceof View) {
                    final Context context = ((View) o).getContext();
                    context.startActivity(new Intent(context, LoginActivity.class));
                    break;
                }
            }
        }

    }
}
