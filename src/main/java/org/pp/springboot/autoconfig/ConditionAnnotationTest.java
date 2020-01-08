package org.pp.springboot.autoconfig;

import java.lang.reflect.InvocationTargetException;

public class ConditionAnnotationTest {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Context context = ContextFactory.factory().newInstance(Context.class);
        context.refresh();
        System.out.println(context.getBeanMapping());
        System.out.println(context.getConditionBeanMapping());
    }
}
