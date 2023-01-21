package io.nikolamicic21.highperformancepersistenceapp.entity.util;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.nikolamicic21.highperformancepersistenceapp.HighPerformancePersistenceApp;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceUnitInfo;
import net.ttddyy.dsproxy.ExecutionInfo;
import net.ttddyy.dsproxy.QueryInfo;
import net.ttddyy.dsproxy.listener.ChainListener;
import net.ttddyy.dsproxy.listener.DataSourceQueryCountListener;
import net.ttddyy.dsproxy.listener.logging.DefaultQueryLogEntryCreator;
import net.ttddyy.dsproxy.listener.logging.SLF4JQueryLoggingListener;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.internal.PersistenceUnitInfoDescriptor;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

public class EntityManagerUtil {

    public static EntityManagerFactory newEntityManagerFactory() {
        PersistenceUnitInfo persistenceUnitInfo = persistenceUnitInfo(HighPerformancePersistenceApp.class.getSimpleName());
        EntityManagerFactoryBuilderImpl entityManagerFactoryBuilder = new EntityManagerFactoryBuilderImpl(
                new PersistenceUnitInfoDescriptor(persistenceUnitInfo), Map.of()
        );
        return entityManagerFactoryBuilder.build();
    }

    private static PersistenceUnitInfoImpl persistenceUnitInfo(String name) {
        return new PersistenceUnitInfoImpl(
                name, entityClassNames(), properties()
        );
    }

    private static Properties properties() {
        final var properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", MySQLDialect.class.getName());

        DataSource dataSource = dataSource();
        properties.put("hibernate.connection.datasource", dataSource);
        properties.put("hibernate.generate_statistics", Boolean.TRUE.toString());
        properties.put("hibernate.jdbc.batch_size", 20);
        properties.put("hibernate.order_inserts", true);
        properties.put("hibernate.order_updates", true);

        return properties;
    }

    private static DataSource dataSource() {
        DataSource dataSource = DataSourceProxyType.DATA_SOURCE_PROXY.dataSource(getDataSource());
        HikariConfig hikariConfig = new HikariConfig();
        int cpuCores = Runtime.getRuntime().availableProcessors();
        hikariConfig.setMaximumPoolSize(cpuCores * 4);
        hikariConfig.setDataSource(dataSource);

        return new HikariDataSource(hikariConfig);
    }

    private static DataSource getDataSource() {
        try {
            MysqlDataSource dataSource = new MysqlDataSource();

            String url = "jdbc:mysql://localhost:3306/hibernate_app?useSSL=false";
            dataSource.setURL(url);
            dataSource.setUser("root");
            dataSource.setPassword("hibernate_app");

            dataSource.setRewriteBatchedStatements(true);
            dataSource.setUseCursorFetch(true);
            dataSource.setCachePrepStmts(true);
            dataSource.setUseServerPrepStmts(true);
            dataSource.setPrepStmtCacheSqlLimit(10);

            return dataSource;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    enum DataSourceProxyType {
        DATA_SOURCE_PROXY {
            @Override
            public DataSource dataSource(DataSource dataSource) {
                ChainListener listener = new ChainListener();
                SLF4JQueryLoggingListener loggingListener = new SLF4JQueryLoggingListener();
                loggingListener.setQueryLogEntryCreator(new InlineQueryLogEntryCreator());
                listener.addListener(loggingListener);
                listener.addListener(new DataSourceQueryCountListener());
                return ProxyDataSourceBuilder
                        .create(dataSource)
                        .name(name())
                        .listener(listener)
                        .build();
            }
        };

        public abstract DataSource dataSource(DataSource dataSource);
    }


    private static List<String> entityClassNames() {
        return List.of(
                "io.nikolamicic21.highperformancepersistenceapp.entity.relationships.manytoone.UniManyToOnePostComment",
                "io.nikolamicic21.highperformancepersistenceapp.entity.relationships.manytoone.UniManyToOnePost",
                "io.nikolamicic21.highperformancepersistenceapp.entity.relationships.onetomany.bidirectionalonetomany.BiOneToManyPost",
                "io.nikolamicic21.highperformancepersistenceapp.entity.relationships.onetomany.bidirectionalonetomany.BiOneToManyPostComment"
        );
    }

    private static class InlineQueryLogEntryCreator extends DefaultQueryLogEntryCreator {

        @Override
        protected void writeParamsEntry(StringBuilder sb, ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
            sb.append("Params:[");
            for (QueryInfo queryInfo : queryInfoList) {
                boolean firstArg = true;
                for (Map<String, Object> paramMap : queryInfo.getQueryArgsList()) {

                    if (!firstArg) {
                        sb.append(", ");
                    } else {
                        firstArg = false;
                    }

                    SortedMap<String, Object> sortedParamMap = new TreeMap<>(new CustomStringAsIntegerComparator());
                    sortedParamMap.putAll(paramMap);

                    sb.append("(");
                    boolean firstParam = true;
                    for (Map.Entry<String, Object> paramEntry : sortedParamMap.entrySet()) {
                        if (!firstParam) {
                            sb.append(", ");
                        } else {
                            firstParam = false;
                        }
                        Object parameter = paramEntry.getValue();
                        if (parameter != null && parameter.getClass().isArray()) {
                            sb.append(arrayToString(parameter));
                        } else {
                            sb.append(parameter);
                        }
                    }
                    sb.append(")");
                }
            }
            sb.append("]");
        }

        private String arrayToString(Object object) {
            if (object.getClass().isArray()) {
                if (object instanceof byte[]) {
                    return Arrays.toString((byte[]) object);
                }
                if (object instanceof short[]) {
                    return Arrays.toString((short[]) object);
                }
                if (object instanceof char[]) {
                    return Arrays.toString((char[]) object);
                }
                if (object instanceof int[]) {
                    return Arrays.toString((int[]) object);
                }
                if (object instanceof long[]) {
                    return Arrays.toString((long[]) object);
                }
                if (object instanceof float[]) {
                    return Arrays.toString((float[]) object);
                }
                if (object instanceof double[]) {
                    return Arrays.toString((double[]) object);
                }
                if (object instanceof boolean[]) {
                    return Arrays.toString((boolean[]) object);
                }
                if (object instanceof Object[]) {
                    return Arrays.toString((Object[]) object);
                }
            }
            throw new UnsupportedOperationException("Array type not supported: " + object.getClass());
        }

        private static class CustomStringAsIntegerComparator extends StringAsIntegerComparator {
        }
    }

}
