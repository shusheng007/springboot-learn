package top.shusheng007.readwritesplit.multids;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/1 10:40
 * @description:
 */
@Slf4j
public class DataSourceSwitcher {
    private static final ThreadLocal<DataSourceType> DB_TYPE_CONTAINER = new ThreadLocal<>();

    private static void switchDb(DataSourceType dbType){
        DB_TYPE_CONTAINER.set(dbType);
        log.info("切换数据源:{}",dbType);
    }

    public static void useMaster(){
        switchDb(DataSourceType.MASTER);
    }

    public static void useSlave(){
        switchDb(DataSourceType.SLAVE);
    }

    public static DataSourceType getCurrentDb(){
        return DB_TYPE_CONTAINER.get();
    }

    public static void removeDb(){
        DB_TYPE_CONTAINER.remove();
    }
}
