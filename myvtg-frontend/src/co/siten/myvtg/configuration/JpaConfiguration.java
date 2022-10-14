package co.siten.myvtg.configuration;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;

//@Configuration
//@PropertySource({"classpath:database.properties"})
//@EnableJpaRepositories(basePackages = "co.siten.myvtg.dao", entityManagerFactoryRef = "userEntityManager", transactionManagerRef = "userTransactionManager")
@Configuration
@PropertySource({"classpath:database.properties"})
@EnableJpaRepositories(basePackages = "co.siten.myvtg.dao", entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
@ComponentScan(basePackages = {"co.siten.myvtg.dao"})
/**
 * daibq
 */
public class JpaConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(JpaConfiguration.class);
//	@Autowired
//	private Environment env;
//
//	public JpaConfiguration() {
//		super();
//	}
//
//	//
//
//	@Primary
//	@Bean
//	public LocalContainerEntityManagerFactoryBean userEntityManager() {
//		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//		em.setDataSource(userDataSource("myvtg"));
//		em.setPackagesToScan(new String[] { "co.siten.myvtg.model.myvtg" });
//
//		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		em.setJpaVendorAdapter(vendorAdapter);
//		final HashMap<String, Object> properties = new HashMap<String, Object>();
//		properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
//		properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
//		properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
//		properties.put("hibernate.connection.isolation", env.getProperty("hibernate.connection.isolation"));
//		em.setJpaPropertyMap(properties);
//
//		return em;
//	}
//
//	@Primary
//	@Bean
//	public DataSource userDataSource(String schema) {
//
//		final HikariDataSource ds = new HikariDataSource();
//		ds.setMaximumPoolSize(100);
//		ds.setDataSourceClassName("oracle.jdbc.pool.OracleDataSource");
//		ds.addDataSourceProperty("url", AES.decrypt(env.getRequiredProperty(schema + ".jdbc.url")));
//		ds.addDataSourceProperty("user", AES.decrypt(env.getRequiredProperty(schema + ".jdbc.username")));
//		ds.addDataSourceProperty("password", AES.decrypt(env.getRequiredProperty(schema + ".jdbc.password")));
//		ds.addDataSourceProperty("driverType", "thin");
//		//
//		// ComboPooledDataSource dataSource = new ComboPooledDataSource();
//		//
//		// try {
//		// dataSource.setDriverClass(env.getRequiredProperty("jdbc.driverClassName"));
//		// } catch (IllegalStateException e) {
//		// logger.error("",e);
//		// e.printStackTrace();
//		// } catch (PropertyVetoException e) {
//		// logger.error("",e);
//		// }
//		// dataSource.setJdbcUrl(AES.decrypt(env.getRequiredProperty(schema +
//		// ".jdbc.url")));
//		// dataSource.setUser(AES.decrypt(env.getRequiredProperty(schema +
//		// ".jdbc.username")));
//		// dataSource.setPassword(AES.decrypt(env.getRequiredProperty(schema +
//		// ".jdbc.password")));
//		//
//		// dataSource.setMaxPoolSize(Integer.parseInt(env.getRequiredProperty("hibernate.c3p0.max_size")));
//		// dataSource.setMinPoolSize(Integer.parseInt(env.getRequiredProperty("hibernate.c3p0.min_size")));
//		//// dataSource.setCheckoutTimeout(Integer.parseInt(env.getRequiredProperty("hibernate.c3p0.timeout")));
//		//// dataSource.setMaxStatements(Integer.parseInt(env.getRequiredProperty("hibernate.c3p0.max_statements")));
//		//// dataSource.setIdleConnectionTestPeriod(
//		//// Integer.parseInt(env.getRequiredProperty("hibernate.c3p0.idle_test_period")));
//		//// dataSource.setAcquireIncrement(Integer.parseInt(env.getRequiredProperty("hibernate.c3p0.acquire_increment")));
//		return ds;
//	}
//
//	@Primary
//	@Bean
//	public PlatformTransactionManager userTransactionManager() {
//		final JpaTransactionManager transactionManager = new JpaTransactionManager();
//		transactionManager.setEntityManagerFactory(userEntityManager().getObject());
//		return transactionManager;
//	}
    public static final String DB_DRIVER = "jdbc.driverClassName";
    public static final String DB_DIALECT = "hibernate.dialect";
    public static final String PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE = "hibernate.jdbc.batch_size";
    public static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    public static final String PROPERTY_NAME_HIBERNATE_FMT_SQL = "hibernate.format_sql";
    public static final String[] ENTITYMANAGER_PACKAGES_TO_SCAN = {"co.siten.myvtg.model.myvtg"};

    public static final String DB_URL = ".jdbc.url";
    public static final String DB_USER = ".jdbc.username";
    public static final String DB_PASSWORD = ".jdbc.password";

    @Autowired
    private Environment env;

    @Bean
    public AnnotationMBeanExporter annotationMBeanExporter() {
        AnnotationMBeanExporter annotationMBeanExporter = new AnnotationMBeanExporter();
        annotationMBeanExporter.addExcludedBean("dataSource");
        annotationMBeanExporter.setRegistrationPolicy(RegistrationPolicy.IGNORE_EXISTING);
        return annotationMBeanExporter;
    }
//destroyMethod = ""

    @Bean()
    @Primary
    public DataSource dataSource(String schema) {
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
        return dataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource("myvtg"));
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        dataSourceInitializer.setDatabasePopulator(databasePopulator);
        dataSourceInitializer.setEnabled(Boolean.parseBoolean(env.getProperty("hibernate.dialect.init.db", "false")));
        return dataSourceInitializer;
    }

    @Bean(name = "entityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
        entityManagerFactoryBean.setDataSource(dataSource("myvtg"));
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPersistenceUnitName("myvtg");
        entityManagerFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
        entityManagerFactoryBean.setJpaProperties(jpaHibernateProperties());
        entityManagerFactoryBean.afterPropertiesSet();
        entityManagerFactoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        return entityManagerFactoryBean;
    }

//    @Bean
//    @Primary
//    public DefaultPersistenceUnitManager persistenceUnitManager() {
//        DefaultPersistenceUnitManager persistenceUnitManager = new DefaultPersistenceUnitManager();
//        persistenceUnitManager.setDefaultDataSource(dataSource("myvtg"));
//        return persistenceUnitManager;
//    }

    @Bean(name = "transactionManager")
    @Primary
    public JpaTransactionManager jpaTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        JpaDialect jpaDialect = new HibernateJpaDialect();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        transactionManager.setJpaDialect(jpaDialect);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private HibernateJpaVendorAdapter vendorAdaptor() {
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
