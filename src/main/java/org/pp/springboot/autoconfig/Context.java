package org.pp.springboot.autoconfig;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理bean的容器的上下文
 */
public class Context<E> {

    private final ConcurrentHashMap<Class<E>, E/*Bean*/> beanMapping = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Class<E>, E> conditionBeanMapping = new ConcurrentHashMap<>();

    protected void refresh() {
        Class clazz = getClass();
        Class<E>[] classes = clazz.getDeclaredClasses();
        System.out.println(Arrays.toString(classes));
        // 收集bean
        Arrays.stream(classes).parallel().forEach((cl) -> {
            Annotation anno = cl.getAnnotation(Bean.class);
            if (anno != null) {
                System.out.println(cl.getName());
                E obj = null;
                try {
                    Constructor constructor = cl.getDeclaredConstructors()[0];
                    System.out.println(constructor.getName());
                    obj = (E) constructor.newInstance(this);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                beanMapping.put(cl, obj);
            }
        });
        System.out.println(beanMapping.containsKey(A.class));
        // 收集conditionBean
        Arrays.stream(classes).parallel().forEach((cl) -> {
            Annotation anno = cl.getAnnotation(ConditionalOnBean.class);
            if (anno != null) {
                System.out.println(cl.getName());
                System.out.println(anno.annotationType());
                Conditional conditional = anno.annotationType().getAnnotation(Conditional.class);
                // 如果beanFactory已经实例化A则实例化B
                long count = Arrays.stream(conditional.value()).filter(this::isInitA).count();
                if (count == 0) {
                    return;
                }
//                List<?/* extends Condition 小心类型转换出错*/> list = Arrays.stream(conditional.value()).filter(this::isInitA).collect(Collectors.toList());
//                System.out.println("list = " + list);
                Constructor constructor = cl.getDeclaredConstructors()[0];
                System.out.println(constructor.getName());
                E obj = null;
                try {
                    obj = (E) constructor.newInstance(this);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                conditionBeanMapping.put(cl, obj);
            }
        });
    }

    /**
     * 类A 是否实例化，实例化缓存中可以查到 就初始化ConditionBean B
     *
     * @param aClass
     * @return
     */
    private boolean isInitA(Class<? extends Condition> aClass) {
        Condition condition = context -> beanMapping.containsKey(A.class);
        boolean flag = condition.matches(this);
        return flag;
    }

    public Map<Class<?>, ?> getBeanMapping() {
        return Collections.unmodifiableMap(beanMapping);
    }

    public Map<Class<?>, ?> getConditionBeanMapping() {
        return Collections.unmodifiableMap(conditionBeanMapping);
    }

    @Bean
    public class A {
        public boolean fun() {
            return true;
        }
    }

    @ConditionalOnBean
    public class B {
        public boolean fun() {
            return true;
        }
    }

}
