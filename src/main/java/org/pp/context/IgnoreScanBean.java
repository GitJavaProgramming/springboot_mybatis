package org.pp.context;

import java.lang.annotation.*;

/**
 * 排除扫描注解，在包、类或者方法上加上这个注解将会忽略这个bean的注入
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PACKAGE, ElementType.TYPE, ElementType.METHOD})
@Inherited
public @interface IgnoreScanBean {
}
