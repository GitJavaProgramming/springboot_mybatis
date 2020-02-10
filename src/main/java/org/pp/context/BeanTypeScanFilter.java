package org.pp.context;

import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * 定义包扫描过滤器，注意controller中的定义需要同时加上排除注解
 */
public class BeanTypeScanFilter implements TypeFilter {

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        String className = classMetadata.getClassName();
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        boolean ignore = clazz.getPackage().isAnnotationPresent(IgnoreScanBean.class);
//        if(ignore) {
////            System.out.println("package:---" + Arrays.toString(clazz.getPackage().getPackages()));
////            System.out.println(classMetadata.getClassName());
//            System.out.println(clazz.getPackage().getName());
//        }
        return ignore;
    }

}
