//package web.application.com.configuration.datasource;
//
//import javax.sql.DataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//
///**
// * Created by agui on 2016/11/25.
// */
//@Configuration
//@MapperScan(basePackages = DataSourceDbOneConfig.PACKAGE, sqlSessionFactoryRef = "oneSqlSessionFactory")
//public class DataSourceDbOneConfig {
//
//	
//	// 精确到 cluster 目录，以便跟其他数据源隔离
//    static final String PACKAGE = "web.application.com.mvc.mapper";
//    static final String MAPPER_LOCATION = "classpath:mybatis/mapper/db-one/*.xml";
//	
//	@Bean(name = "oneDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.one") //配对application.properties 配置
//    public DataSource oneDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "oneSqlSessionFactory")
//    public SqlSessionFactory oneSqlSessionFactory(@Qualifier("oneDataSource") DataSource oneDataSource)
//            throws Exception {
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(oneDataSource);
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources(DataSourceDbOneConfig.MAPPER_LOCATION));
//        return sessionFactory.getObject();
//    }
//    
//
//    @Bean(name = "oneTransactionManager")
//    @Primary
//    public DataSourceTransactionManager oneTransactionManager() {
//        return new DataSourceTransactionManager(oneDataSource());
//    }
//
//    @Bean(name = "oneSqlSessionTemplate")
//    @Primary
//    public SqlSessionTemplate oneSqlSessionTemplate(@Qualifier("oneSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//    
//    @Bean(name = "oneTransactionManager")
//    public PlatformTransactionManager oneTransactionManager(@Qualifier("oneDataSource") DataSource oneDataSource) {
//        return new DataSourceTransactionManager(oneDataSource);
//    }
//    
//    
//    
//}