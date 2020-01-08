package org.pp.springboot.autoconfig;

import java.lang.reflect.InvocationTargetException;

public class OnBeanCondition implements Condition {

    @Override
    public boolean matches(Context context) {
        return false;
    }

    public boolean isInitA() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return matches(ContextFactory.factory().newInstance(Context.class));
    }
}
