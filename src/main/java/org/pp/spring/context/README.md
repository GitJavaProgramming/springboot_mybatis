资源的加载与抽象：Resource 将资源抽象为Resource  
DefaultResourceLoader.getResource加载同时使用协议解析ProtocolResolver确定Resource的具体类型  
BeanDefinitionReader.loadBeanDefinitions(Resource... resources)读取同时会BeanDefinitionRegistry注册缓存  
BeanDefinition  Bean信息的包装器    
getBean  