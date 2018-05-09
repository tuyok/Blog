package com.tuyongkang.blog.config.ext;

import com.jolbox.bonecp.BoneCPDataSource;
import com.tuyongkang.blog.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:jdbc.properties")
@EnableJpaRepositories(value= AppConstants.DAO_SCAN)
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class JpaConfig {

    @Autowired
    private Environment env;

    /**
     * 数据源
     */
    @Bean
    public DataSource dataSource(){
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(env.getProperty("default.driverClassName"));
        dataSource.setJdbcUrl(env.getProperty("default.url"));
        dataSource.setUsername(env.getProperty("default.username"));
        dataSource.setPassword(env.getProperty("default.password"));
        dataSource.setPartitionCount(env.getProperty("default.partitionCount", Integer.class));
        dataSource.setMaxConnectionsPerPartition(env.getProperty("default.maxConnectionsPerPartition", Integer.class));
        dataSource.setMinConnectionsPerPartition(env.getProperty("default.minConnectionsPerPartition", Integer.class));
        dataSource.setIdleConnectionTestPeriodInMinutes(env.getProperty("default.idleConnectionTestPeriodInMinutes", Integer.class));
        dataSource.setIdleMaxAgeInMinutes(env.getProperty("default.idleMaxAgeInMinutes", Integer.class));
        dataSource.setAcquireIncrement(env.getProperty("default.acquireIncrement", Integer.class));
        dataSource.setDisableConnectionTracking(true);
        return dataSource;
    }

    /**
     * 实体管理器工厂
     * @return
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(AppConstants.ENTITY_SCAN);
        factory.setDataSource(dataSource());
        return factory;
    }

    /**
     * 事务管理器
     * @param emf
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }
}
