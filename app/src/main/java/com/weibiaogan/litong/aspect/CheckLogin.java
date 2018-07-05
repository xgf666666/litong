package com.weibiaogan.litong.aspect;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

@Target({TYPE, METHOD, CONSTRUCTOR})
@Retention(RetentionPolicy.CLASS)
public @interface CheckLogin {
//    /**
//     * This sets the target api level for the type..
//     */
//    int value();
}