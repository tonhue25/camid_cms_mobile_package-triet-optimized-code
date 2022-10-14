package co.siten.myvtg.configuration;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import co.siten.myvtg.util.AES;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;

@Configuration
@PropertySource({"classpath:database.properties"})
@EnableJpaRepositories(basePackages = "co.siten.apigw.dao", entityManagerFactoryRef = "apigwEntityManager", transactionManagerRef = "apigwTransactionManager")
@ComponentScan(basePackages = {"co.siten.apigw.dao"})
/**
 * daibq
 */
public class JpaApigwConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(JpaApigwConfiguration.class);
//    @Autowired
//    private Environment env;
//
//    public JpaApigwConfiguration() {
//        super();
//    }
//
//    //
//    @Primary
//    @Bean
//    public LocalContainerEntityManagerFactoryBean apigwEntityManager() {
//        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(apigwDataSource("apigw"));
//        em.setPackagesToScan(new String[]{"co.siten.myvtg.model.apigw"});
//
//        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        final HashMap<String, Object> properties = new HashMap<String, Object>();
//        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
//        properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
//        properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
//        properties.put("hibernate.connection.isolation", env.getProperty("hibernate.connection.isolation"));
//        em.setJpaPropertyMap(properties);
//
//        return em;
//    }
//
//    @Primary
//    @Bean
//    public DataSource apigwDataSource(String schema) {
//
//        final HikariDataSource ds = new HikariDataSource();
//        ds.setMaximumPoolSize(100);
//        ds.setDataSourceClassName("oracle.jdbc.pool.OracleDataSource");
//        ds.addDataSourceProperty("url", AES.decrypt(env.getRequiredProperty(schema + ".jdbc.url")));
//        ds.addDataSourceProperty("user", AES.decrypt(env.getRequiredProperty(schema + ".jdbc.username")));
//        ds.addDataSourceProperty("password", AES.decrypt(env.getRequiredProperty(schema + ".jdbc.password")));
//        ds.addDataSourceProperty("driverType", "thin");
//        return ds;
//    }
//
//    @Primary
//    @Bean
//    public PlatformTransactionManager apigwTransactionManager() {
//        final JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(apigwEntityManager().getObject());
//        return transactionManager;
//    }

    public static final String DB_DRIVER = "jdbc.driverClassName";
    public static final String DB_DIALECT = "hibernate.dialect";
    public static final String PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE = "hibernate.jdbc.batch_size";
    public static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    public static final String PROPERTY_NAME_HIBERNATE_FMT_SQL = "hibernate.format_sql";
    public static final String[] ENTITYMANAGER_PACKAGES_TO_SCAN = {"co.siten.myvtg.model.apigw"};

    public static final String DB_URL = ".jdbc.url";
    public static final String DB_USER = ".jdbc.username";
    public static final String DB_PASSWORD = ".jdbc.password";

    @Autowired
    private Environment env;

    @Bean
    public AnnotationMBeanExporter annotationApigwMBeanExporter() {
        AnnotationMBeanExporter annotationMBeanExporter = new AnnotationMBeanExporter();
        annotationMBeanExporter.addExcludedBean("apigwDataSource");
        annotationMBeanExporter.setRegistrationPolicy(RegistrationPolicy.IGNORE_EXISTING);
        return annotationMBeanExporter;
    }
//, destroyMethod = ""
//name = "apigwDataSource"

    @Bean()
    @Primary
    public DataSource apigwDataSource(String schema) {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        try {
            dataSource.setDriverClass(env.getProperty(DB_DRIVER, "oracle.jdbc.driver.OracleDriver"));

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl(AES.decrypt(env.getProperty(schema + DB_URL)));
        dataSource.setUser(AES.decrypt(env.getProperty(schema + DB_USER)));
        dataSource.setPassword(AES.decrypt(env.getProperty(schema + DB_PASSWORD)));
        dataSource.setAcquireIncrement(Integer.parseInt(env.getProperty("hibernate.c3p0.acquire_increment", "5")));
        dataSource.setMaxStatements(Integer.parseInt(env.getProperty("hibernate.c3p0.max_statements")));
        dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("hibernate.c3p0.max_size", "200")));
        dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("hibernate.c3p0.min_size", "10")));
        dataSource.setCheckoutTimeout(Integer.parseInt(env.getProperty("hibernate.c3p0.timeout", "1000")));
        dataSource.setIdleConnectionTestPeriod(Integer.parseInt(env.getProperty("hibernate.c3p0.idle_test_period", "300")));
        dataSource.setMaxStatementsPerConnection(20);
        dataSource.setNumHelperThreads(10);
        dataSource.setTestConnectionOnCheckout(true);
        dataSource.setMaxIdleTime(200);
        dataSource.setMaxIdleTimeExcessConnections(3600);
//     dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
//    dataSource.setUser(env.getProperty("jdbc.username"));
//    dataSource.setPassword(env.getProperty("jdbc.password"));
//    dataSource.setAcquireIncrement(20);
//    dataSource.setAcquireRetryAttempts(30);
//    dataSource.setAcquireRetryDelay(1000);
//    dataSource.setAutoCommitOnClose(false);
//    dataSource.setDebugUnreturnedConnectionStackTraces(true);
//    dataSource.setIdleConnectionTestPeriod(100);
//    dataSource.setInitialPoolSize(10);
//    dataSource.setMaxConnectionAge(1000);
//    dataSource.setMaxIdleTime(200);
//    dataSource.setMaxIdleTimeExcessConnections(3600);
//    dataSource.setMaxPoolSize(10);
//    dataSource.setMinPoolSize(2);
//    dataSource.setPreferredTestQuery("select 1");
//    dataSource.setTestConnectionOnCheckin(false);
//    dataSource.setUnreturnedConnectionTimeout(1000);
        return dataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceApigwInitializer() {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(apigwDataSource("apigw"));
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        dataSourceInitializer.setDatabasePopulator(databasePopulator);
        dataSourceInitializer.setEnabled(Boolean.parseBoolean(env.getProperty("hibernate.dialect.init.db", "false")));
        return dataSourceInitializer;
    }

    @Bean(name = "apigwEntityManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean apigwEntityManager() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorApigwAdaptor());
        entityManagerFactoryBean.setDataSource(apigwDataSource("apigw"));
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPersistenceUnitName("apigw");
        entityManagerFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
        entityManagerFactoryBean.setJpaProperties(jpaHibernateProperties());
        entityManagerFactoryBean.afterPropertiesSet();
        entityManagerFactoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        return entityManagerFactoryBean;
    }

//    @Bean(name = "apigwPersistenceUnitManager")
//    @Primary
//    public DefaultPersistenceUnitManager persistenceUniApigwtManager() {
//        DefaultPersistenceUnitManager persistenceUnitManager = new DefaultPersistenceUnitManager();
//        persistenceUnitManager.setDefaultDataSource(apigwDataSource("apigw"));
//        return persistenceUnitManager;
//    }

    @Bean(name = "apigwTransactionManager")
    @Primary
    public JpaTransactionManager jpaApigwTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        JpaDialect jpaDialect = new HibernateJpaDialect();
        transactionManager.setEntityManagerFactory(apigwEntityManager().getObject());
        transactionManager.setJpaDialect(jpaDialect);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private HibernateJpaVendorAdapter vendorApigwAdaptor() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform(env.getProperty(DB_DIALECT, "org.hibernate.dialect.Oracle10gDialect"));
        vendorAdapter.setShowSql(true);
        return vendorAdapter;
    }

    private Properties jpaHibernateProperties() {
        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_FMT_SQL, env.getProperty(PROPERTY_NAME_HIBERNATE_FMT_SQL));
        properties.put(PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE, env.getProperty(PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE));
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
        properties.put("hibernate.cache.use_second_level_cache", env.getProperty("hibernate.cache.use_second_level_cache", "true"));
        properties.put("hibernate.enable_lazy_load_no_trans", env.getProperty("hibernate.enable_lazy_load_no_trans", "true"));
        properties.put("hibernate.generate_statistics", env.getProperty("hibernate.generate_statistics", "true"));
        properties.put("hibernate.connection.isolation", env.getProperty("hibernate.connection.isolation", "2"));
        return properties;
    }

}
