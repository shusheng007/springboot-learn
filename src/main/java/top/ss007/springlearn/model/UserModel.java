package top.ss007.springlearn.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import top.ss007.springlearn.config.*;
import top.ss007.springlearn.config.configuration_annotaion.Father;
import top.ss007.springlearn.config.configuration_annotaion.MyConfiguration;

/**
 * Created by Ben.Wang
 * <p>
 * author     : Ben.Wang
 * date       : 2021/5/12 14:13
 * description:
 */
@Repository
public class UserModel {

    @Value("${demo.user.name}")
    private String userName;
    private Environment environment;

    private final DatabaseConfig databaseConfig;
    private final InfoConfig infoConfig;
    private final ComplexConfig complexConfig;
    private final ConversionConfig conversionConfig;
    private final ImmutablePresident immutablePresident;

    @Autowired
    public MyConfiguration myConfiguration;


    @Autowired
    public UserModel(DatabaseConfig databaseConfig, InfoConfig infoConfig, ComplexConfig complexConfig, ConversionConfig conversionConfig, ImmutablePresident immutablePresident) {
        this.databaseConfig = databaseConfig;
        this.infoConfig = infoConfig;
        this.complexConfig = complexConfig;
        this.conversionConfig = conversionConfig;
        this.immutablePresident = immutablePresident;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getUserName(){
        return userName + ":"+ infoConfig.getTest();
    }

    public String getUserNameByEnv(){
        return environment.getProperty("demo.user.name");
    }

    public String getDatabaseInfo(){
        return databaseConfig.toString();
    }

    public String getComplexConfig(){
        return complexConfig.toString();
    }

    public String getConversionConfig(){
        return conversionConfig.toString();
    }

    public String getImmutablePresident(){
        return immutablePresident.toString();
    }


    public String isSameObj() {
        Father father1 = myConfiguration.father();
        Father father2 = myConfiguration.father();

        return String.format("father1:%s | father2:%s 是否为同一对象:%s",father1.getClass().getClassLoader(),
                father2.getClass().getClassLoader(),father1 == father2);
    }

}
