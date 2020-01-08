package org.pp.springboot.autoconfig;

@FunctionalInterface
public interface Condition {
    boolean matches(Context context);
}
