package witcher.cirilla.jdbc.dao;

import java.util.ArrayList;
import java.util.List;

public class DynamicDataSourceContextHolder {

    private static List<String> dataSourceIds = new ArrayList<>();

    private static final ThreadLocal<String> HOLDER = new ThreadLocal<>();

    public static String getDataSourceRouterKey() {
        return HOLDER.get();
    }

    public static void setDataSourceRouterKey(String dataSourceRouterKey) {
        HOLDER.set(dataSourceRouterKey);
    }

    public static void removeDataSourceRouterKey() {
        HOLDER.remove();
    }

    public static boolean containsDataSource(String dataSourceId){
        return dataSourceIds.contains(dataSourceId);
    }

}
