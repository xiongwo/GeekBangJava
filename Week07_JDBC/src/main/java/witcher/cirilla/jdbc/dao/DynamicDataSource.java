package witcher.cirilla.jdbc.dao;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dsKey = DynamicDataSourceContextHolder.getDataSourceRouterKey();
        return (dsKey == null) ? "master" : dsKey;
    }

}
