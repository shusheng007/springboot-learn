package top.shusheng007.readwritesplit.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.shusheng007.readwritesplit.multids.DataSourceType;
import top.shusheng007.readwritesplit.multids.DefaultRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/2 09:38
 * @description: 多数据源配置文件，用来构建一个多数据源的DataSource
 */

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.ss007-datasource.master")
    public DataSource masterDs(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.ss007-datasource.slave")
    public DataSource slaveDs(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSource targetDs(@Qualifier("masterDs") DataSource masterDs,
                               @Qualifier("slaveDs") DataSource slaveDs){
        Map<Object,Object> targetDs = new HashMap<>();
        targetDs.put(DataSourceType.MASTER,masterDs);
        targetDs.put(DataSourceType.SLAVE,slaveDs);

        DefaultRoutingDataSource routingDs = new DefaultRoutingDataSource();
        //绑定所有的数据源
        routingDs.setTargetDataSources(targetDs);
        //绑定默认数据源
        routingDs.setDefaultTargetDataSource(masterDs);
        return routingDs;
    }

}
