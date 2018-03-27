package org.pf9.pangu.framework.data.annotation;


import org.pf9.pangu.framework.data.service.CreateDefaultService;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by qiulin on 2017/5/17.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface InitialData {

    String value() default "";

    int order() default 99;

    Class<? extends CreateDefaultService> clazz();
}
