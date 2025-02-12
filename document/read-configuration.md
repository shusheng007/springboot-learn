
SpringBoot 如何读取配置文件

# 配置文件类型

- Property文件

```
database.username=ss007
database.password=wb123
database.server.ip=192.168.1.1
database.server.port=8000
```

- Yml文件
```
database:
  username: ss007
  password: wb123
  server:
    ip: 192.168.1.1
    port:8000
```

# 读取配置文件的三种方式

当新建一个SpringBoot maven项目，默认会在`src/main/resources`路径下生成一个application.properties配置文件。
系统默认就能找到这个文件，神奇吧？就是因为springboot使用了约定大于配置的思想，再加上Spring以及SpringBoot自己的各种注解
使项目变成了一个黑盒。

## 使用@Value

将`@Value("${配置文件中某个键值对的key}")`注解添加到你要接收值的字段上即可，如下所示：

```
@Repository
public class UserModel {
    @Value("${database.username}")
    private String userName;
    
    //不需要setter
}
```
`@Repository` 是一种`@Component`，当需要的时候Spring就可以帮我注入UserModel的实例了

## 使用Environment

第二种方法是使用`Environment`对象，这个对象里面有很多获取值的方法，这种方法用的较少。

```
@Repository
public class UserModel {
        
       //使用setter的方式注入Enviroment对象
       private Environment environment;   
       @Autowired
       public void setEnvironment(Environment environment) {
           this.environment = environment;
       }
    
      //通过environment对象获取值
      public String getUserNameByEnv(){
          return environment.getProperty("database.username");
      }
}
```

## 使用@ConfigurationProperties

重点是这种方法，既灵活又迷惑。其思想是将配置文件中**相关**的一组配置映射为一个POJO(plain old java object),更准确的说是映射为一个JavaBean。
然后我们就可以通过这个JavaBean来使用配置文件里的值了。

不同版本这个注解的使用方式有一些不同，我使用的是SpringBoot2.4.5。

### 第一步：先识别一些自己的配置文件中是否是一组相关的属性配置。

例如我们此处是一组database相关的配置，其下面还有一个子组server。

```
database.username=ss007
database.password=wb123
database.server.ip=192.168.1.1
database.server.port=8000
```
### 第二步：按照配置文件的结构和命名构建Java类

这里有几个注意点

- 字段的命名

字段命名不需要完全一样，但是也不是随意命名的，要符合一定的规则
例如配置文件中为username, Java类中可以为：UserName,user-name,userName等等，但是在Java中一般为小驼峰，userName。

- 内部嵌套类

当配置文件中的属性结构存在层级时，就需要使用嵌套类。例如此处的server。

- Setter千万不可省略

```
public class DatabaseConfig {
    /**
     * 数据库用户名
     */
    private String userName;
    /**
     * 数据库密码
     */
    private String passWord;
    /**
     * 数据库服务器
     */
    private Server server;


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setServer(Server server) {
        this.server = server;
    }
    ...省略了getter

    public static class Server{
        /**
         * 服务器IP
         */
        private String ip;
        /**
         * 服务器端口号
         */
        private int port;

        public void setIp(String ip) {
            this.ip = ip;
        }

        public void setPort(int port) {
            this.port = port;
        }
        ...省略了getter
    }
}
```
## 第三步：使用@ConfigurationProperties(prefix="database")注解标记

其中prefix为属性配置文件中那组配置的最外层，此处为database。

## 第四步：让Spring感知到你这个类的存在

完成以上三步后，我们需要让Spring感知到这个类，那样当我们使用的时候，它才能帮我们注入实例。

第一种方式：使用`@Configuration`注解标记你的配置类，此处为`UserConfig`

第二种方式：使用`@ConfigurationPropertiesScan()`注解标记你的程序启动类xxxApplication,此处为SpringLearnApplication

第三种方式：使用`@EnableConfigurationProperties({你的配置类.class})` 注解标记你的程序启动类xxxApplication,此处为SpringLearnApplication

三种方式使用任何一种都可，其中第二种方式最为便捷，相当于SpringBoot自动扫描那些使用`@ConfigurationProperties`标记的类。

最后的样子为

```
@Configuration //当使用`@ConfigurationPropertiesScan()`或`@EnableConfigurationProperties({你的配置类.class})` 时，此注解可省略
@ConfigurationProperties(prefix="database")
public class DatabaseConfig {
    ...
}
```

# 如何读取非默认属性配置文件

众所周知，我们可以将程序的配置写在默认的application.properties文件中，但是有时我们有将某些配置写在单独的文件中的需求，这样便于管理，
SpringBoot可以实现吗？可以实现吗？请把那个吗字去掉...

假设我们现在有一个自定义的配置文件info.properties,其内容如下,那我们如何读取其内容呢？

```
test.name= hello
```

# 第一步：将其放在src/main/resources目录下的任何位置。

放在已有目录下，或者自建目录，例如此处我自己建了一个properties文件夹

# 第二步：使用@PropertySource属性注解映射类

```
@PropertySource(value = "classpath:properties/info.properties")
```

注意其value的值

# 第三步：使用@ConfigurationProperties注解映射类

```
@ConfigurationProperties(prefix = "test")
```

# 第四步：使用@Configuration注解映射类

自测这种使用`@PropertySource`的情形，`@EnableConfigurationProperties`与`@ConfigurationPropertiesScan`是不起作用的，必须使用
`@Configuration`

最后的代码样子如下：

```
@Configuration
@PropertySource(value = "classpath:properties/info.properties",ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "test")
public class InfoConfig {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getTest() {
        return name;
    }
}
```





