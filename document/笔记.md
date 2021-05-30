
`@Configuration(proxyBeanMethods = false)` 里的proxyBeanMethods = false 什么作用

当proxyBeanMethods = false 时，`@Bean`不使用代理，不进入容器，每次获取到的对象都是不同的对象

这样可以加快启动速度。

当proxyBeanMethods = true 时，多次获取bean，都是同一个对象。