package web.application.com.configuration.datasource;
/*package web.application.com.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

*//**
 * Created by agui on 2016/11/25.
 *//*
@Configuration
@MapperScan(basePackages = DataSourceDbTwoConfig.PACKAGE, sqlSessionFactoryRef = "opsSqlSessionFactory")
public class DataSourceDbTwoConfig {

	
	// 精确到 cluster 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.rpc.service.v1.user.mapper.ops";
    static final String MAPPER_LOCATION = "classpath:mybatis/mapper/ops/*.xml";
	
	@Bean(name = "opsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ops") //配对application.properties 配置
	@Primary
    public DataSource opsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "opsSqlSessionFactory")
    public SqlSessionFactory opsSqlSessionFactory(@Qualifier("opsDataSource") DataSource opsDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(opsDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DataSourceDbTwoConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
    

    @Bean(name = "opsTransactionManager")
    @Primary
    public DataSourceTransactionManager opsTransactionManager() {
        return new DataSourceTransactionManager(opsDataSource());
    }

    @Bean(name = "opsSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate opsSqlSessionTemplate(@Qualifier("opsSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    @Bean(name = "opsTransactionManager")
    public PlatformTransactionManager opsTransactionManager(@Qualifier("opsDataSource") DataSource opsDataSource) {
        return new DataSourceTransactionManager(opsDataSource);
    }

}
*/