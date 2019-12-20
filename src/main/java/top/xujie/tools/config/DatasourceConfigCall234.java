package top.xujie.tools.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * 61.155.215.234数据源配置
 *
 * @author xujie
 */
//@Configuration
//@MapperScan(basePackages = DatasourceConfigCall234.PACKAGE,
//        sqlSessionFactoryRef = DatasourceConfigCall234.SQL_SESSION_FACTORY_NAME,
//        sqlSessionTemplateRef = DatasourceConfigCall234.SQL_SESSION_TEMPLATE_NAME)
public class DatasourceConfigCall234 {

    /**
     * mapper扫描路径
     */
    static final String PACKAGE = "com.huiyu.org.sync.mapper.call234";

    /**
     * xml扫描路径
     */
    private static final String MAPPER_LOCATION = "classpath:mybatis/mapper/call234/*.xml";

    /**
     * 数据源配置路径
     */
    private static final String CONFIG_PATH = "spring.datasource.druid.call234";

    /**
     * 数据源 SqlSessionFactory 名称
     */
    static final String SQL_SESSION_FACTORY_NAME = "call234SqlSessionFactory";

    /**
     * 数据源 SqlSessionTemplate 名称
     */
    static final String SQL_SESSION_TEMPLATE_NAME = "call234SqlSessionTemplate";


    /**
     * 数据源名称
     */
    private static final String BEAN_NAME_DATA_SOURCE = "call234DataSource";

    /**
     * 数据源 TransactionManager 名称
     */
    private static final String BEAN_NAME_TRANSACTION_MANAGER = "call234TransactionManager";


    @Bean(name = BEAN_NAME_DATA_SOURCE)
    @ConfigurationProperties(CONFIG_PATH)
    public DataSource initDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = BEAN_NAME_TRANSACTION_MANAGER)
    public DataSourceTransactionManager initTransactionManager(@Qualifier(BEAN_NAME_DATA_SOURCE) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = SQL_SESSION_FACTORY_NAME)
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier(BEAN_NAME_DATA_SOURCE) DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(DatasourceConfigCall234.MAPPER_LOCATION));

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sessionFactory.setConfiguration(configuration);

        return sessionFactory.getObject();
    }

    @Bean(name = SQL_SESSION_TEMPLATE_NAME)
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier(SQL_SESSION_FACTORY_NAME) SqlSessionFactory gtSqlSessionFactory) throws IOException {
        return new SqlSessionTemplate(gtSqlSessionFactory);
    }
}
