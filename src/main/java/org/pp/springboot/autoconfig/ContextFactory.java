package org.pp.springboot.autoconfig;

import java.lang.reflect.InvocationTargetException;

public class ContextFactory {
    private ContextFactory() {
    }

    public <C extends Context> Context newInstance(Class<C> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return clazz.getConstructor().newInstance();
    }

    public static ContextFactory factory() {
        return Holder.factory;
    }

    private static final class Holder {
        private static final ContextFactory factory = new ContextFactory();

        public static final ContextFactory getFactory() {
            return factory;
        }
    }
}
