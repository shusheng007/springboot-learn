package top.shusheng007.readwritesplit.multids;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/1 10:36
 * @description: 切换数据源
 */
public class DefaultRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceSwitcher.getCurrentDb();
    }
}
