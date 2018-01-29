package com.amqp.demo.strategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * created by panta on 2018/1/24.
 *
 * @author panta
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE, ElementType.CONSTRUCTOR })
public @interface StrategyAnnotation {
    String value();
}
