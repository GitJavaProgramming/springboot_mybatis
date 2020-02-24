## Spring5.2.1
### 准备容器初始化
### bean载入和解析
### 注册与注入
### bean的作用域和生命周期管理 
### 容器感知Aware
### BeanFactory FactoryBean
### 那些注解
### 组件扩展（web security orm tx rmi...）

    资源的加载，注册和解析
    
    Reader - Registry - loadBeanDefinitions
    
    public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader, EnvironmentCapable
    
    AbstractBeanDefinitionReader (org.springframework.beans.factory.support)
    PropertiesBeanDefinitionReader (org.springframework.beans.factory.support)
    GroovyBeanDefinitionReader (org.springframework.beans.factory.groovy)
    XmlBeanDefinitionReader (org.springframework.beans.factory.xml)
    
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry)
    public int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException
    public int registerBeanDefinitions(Document doc, Resource resource) throws BeanDefinitionStoreException
    documentReader.registerBeanDefinitions(doc, createReaderContext(resource));