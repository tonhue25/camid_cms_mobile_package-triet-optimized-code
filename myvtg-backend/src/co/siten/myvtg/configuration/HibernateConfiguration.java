package co.siten.myvtg.configuration;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.dom4j.Element;
import org.hibernate.internal.util.ConfigHelper;
import org.hibernate.internal.util.xml.XMLHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import co.siten.myvtg.util.AES;
import co.siten.myvtg.util.CommonUtil;

/**
 *
 * @author thomc
 *
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"co.siten.myvtg.configuration"})
@PropertySource(value = {"classpath:database.properties"})
public class HibernateConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(HibernateConfiguration.class);
    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean myvtgSessionFactory() {
        String schema = "myvtg";
        return getLocalSessionFactoryBean(schema);
    }

    @Bean
    public LocalSessionFactoryBean dataSessionFactory() {
        String schema = "data";
        // return null;
        return getLocalSessionFactoryBean(schema);
    }

    @Bean
    public LocalSessionFactoryBean cmposSessionFactory() {
        String schema = "cmpos";
        // return null;
        return getLocalSessionFactoryBean(schema);
    }

    @Bean
    public LocalSessionFactoryBean cmpreSessionFactory() {
        String schema = "cmpre";
        // return null;
        return getLocalSessionFactoryBean(schema);
    }

    @Bean
    public LocalSessionFactoryBean precallSessionFactory() {
        String schema = "precall";
        // return null;
        return getLocalSessionFactoryBean(schema);
    }

    @Bean
    public LocalSessionFactoryBean paymentSessionFactory() {
        String schema = "payment";
        // return null;
        return getLocalSessionFactoryBean(schema);
    }

    @Bean
    public LocalSessionFactoryBean billingSessionFactory() {
        String schema = "billing";
        // return null;
        return getLocalSessionFactoryBean(schema);
    }

    @Bean
    public LocalSessionFactoryBean smSessionFactory() {
        String schema = "sm";
        // return null;
        return getLocalSessionFactoryBean(schema);
    }

    @Bean
    public LocalSessionFactoryBean productSessionFactory() {
        String schema = "product";
        // return null;
        return getLocalSessionFactoryBean(schema);
    }

    @Bean
    public LocalSessionFactoryBean loyaltySessionFactory() {
        String schema = "loyalty";
        // return null;
        return getLocalSessionFactoryBean(schema);
    }
//daibq bo sung

    @Bean
    public LocalSessionFactoryBean mkishareSessionFactory() {
        String schema = "mkishare";
        // return null;
        return getLocalSessionFactoryBean(schema);
    }

    @Bean
    public LocalSessionFactoryBean apigwSessionFactory() {
        String schema = "apigw";
        // return null;
        return getLocalSessionFactoryBean(schema);
    }

    @Bean
    public LocalSessionFactoryBean smsSessionFactory() {
        String schema = "sms";
        // return null;
        return getLocalSessionFactoryBean(schema);
    }

    @Bean
    public LocalSessionFactoryBean bankplusSessionFactory() {
        String schema = "bankplus";
        // return null;
        return getLocalSessionFactoryBean(schema);
    }

    @Bean
    public LocalSessionFactoryBean imSessionFactory() {
        String schema = "im";
        // return null;
        return getLocalSessionFactoryBean(schema);
    }

    @Bean
    public LocalSessionFactoryBean promoreportappSessionFactory() {
        String schema = "promoreportapp";
        // return null;
        return getLocalSessionFactoryBean(schema);
    }

    //daibq end
    private LocalSessionFactoryBean getLocalSessionFactoryBean(String schema) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        final DriverManagerDataSource ds = new DriverManagerDataSource();

//		ds.setMaxTotal(30);
//		ds.setMaximumPoolSize(30);
//		ds.setLeakDetectionThreshold(15000);
//		ds.setIdleTimeout(10000);
//		ds.setMinimumIdle(5);
//		ds.setConnectionTimeout(60000);
//		ds.setMaxLifetime(20000);
//		ds.setInitialSize(3);
//		ds.setDefaultQueryTimeout(60000);
//		ds.setMaxConnLifetimeMillis(20000);
//		ds.setMinIdle(5);
        ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        String jdbc = environment.getRequiredProperty(schema + ".jdbc.url");
        String jdbcusername = environment.getRequiredProperty(schema + ".jdbc.username");
        String jdbcpassword = environment.getRequiredProperty(schema + ".jdbc.password");
        ds.setUrl(AES.decrypt(environment.getRequiredProperty(schema + ".jdbc.url")));
        ds.setUsername(AES.decrypt(environment.getRequiredProperty(schema + ".jdbc.username")));
        ds.setPassword(AES.decrypt(environment.getRequiredProperty(schema + ".jdbc.password")));

//		ds.setDataSourceClassName("oracle.jdbc.pool.OracleDataSource");
        // ds.addDataSourceProperty("url",
        // environment.getRequiredProperty(schema + ".jdbc.url"));
//		ds.addDataSourceProperty("url", AES.decrypt(environment.getRequiredProperty(schema + ".jdbc.url")));
//		ds.addDataSourceProperty("user", AES.decrypt(environment.getRequiredProperty(schema + ".jdbc.username")));
//		ds.addDataSourceProperty("password", AES.decrypt(environment.getRequiredProperty(schema + ".jdbc.password")));
//		ds.addDataSourceProperty("driverType", "thin");
        // System.out.println("schemaxx:"+schema);
        logger.info("schemaxx", schema);

        sessionFactory.setDataSource(ds);
        sessionFactory.setPackagesToScan(new String[]{"co.siten.myvtg.model." + schema});
        sessionFactory.setHibernateProperties(hibernateProperties(schema));

        if ("product".equalsIgnoreCase(schema)) {
            try {
                InputStream is = ConfigHelper.getResourceAsStream("/com/viettel/pm/database/config/hibernate.cfg.xml");
                XMLHelper xmlHelper = new XMLHelper();
                EntityResolver entityResolver = XMLHelper.DEFAULT_DTD_RESOLVER;
                org.dom4j.Document doc = xmlHelper.createSAXReader(null, entityResolver).read(new InputSource(is));
                Element sfNode = doc.getRootElement().element("session-factory");
                Iterator elements = sfNode.elementIterator();
                List<String> resources = new Vector<>();
                while (elements.hasNext()) {
                    Element subelement = (Element) elements.next();
                    String subelementName = subelement.getName();
                    if ("mapping".equals(subelementName)) {
                        String resource = subelement.attribute("resource").getValue();
                        resources.add(resource);
                    }

                }

                String[] resourceArr = new String[resources.size()];
                for (int i = 0; i < resources.size(); i++) {
                    resourceArr[i] = resources.get(i);
                }
                sessionFactory.setMappingResources(resourceArr);
            } catch (Exception e) {
                logger.error("eee", e);
            }

        }

        return sessionFactory;
    }

    private Properties hibernateProperties(String schema) {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.connection.release_mode", "on_close");
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        // properties.put("hibernate.query.plan_cache_max_size",
        // environment.getRequiredProperty("hibernate.query.plan_cache_max_size"));
        // properties.put("hibernate.query.plan_parameter_metadata_max_size",
        // environment.getRequiredProperty("hibernate.query.plan_parameter_metadata_max_size"));
        properties.put("hibernate.connection.isolation", environment.getProperty("hibernate.connection.isolation"));
        properties.put("hibernate.current_session_context_class", "org.springframework.orm.hibernate4.SpringSessionContext");

        String defaultSchema = environment.getProperty(schema + ".default_schema");
        if (!CommonUtil.isEmpty(defaultSchema)) {
            properties.put("hibernate.default_schema", defaultSchema);
        }
        return properties;
    }

    // @Bean(name = "fulltransaction")
    // @Autowired
    // public ChainedTransactionManager transactionManager() {
    // return new ChainedTransactionManager(precalltx(), datatx(), cmpostx(),
    // cmpretx(), myvtgtx(), payment(),
    // billing(), sm());
    // }
    // @Bean(name = "myvtgtransaction")
    // @Autowired
    // public ChainedTransactionManager myvtgTransactionManager() {
    // return new ChainedTransactionManager(myvtgtx());
    // }
    // @Bean(name = "cmpretransaction")
    // @Autowired
    // public ChainedTransactionManager cmpreTransactionManager() {
    // return new ChainedTransactionManager(cmpretx());
    // }
    // @Bean(name = "cmpostransaction")
    // @Autowired
    // public ChainedTransactionManager cmposTransactionManager() {
    // return new ChainedTransactionManager(cmpostx());
    // }
    // @Bean(name = "myvtglogtransaction")
    // @Autowired
    // public ChainedTransactionManager myvtgLogTransactionManager() {
    // return new ChainedTransactionManager(myvtglogtx());
    // }
    // @Bean(name = "datatransaction")
    // @Autowired
    // public ChainedTransactionManager dataTransactionManager() {
    // return new ChainedTransactionManager(datatx());
    // }
    //
    // @Bean(name = "precalltx")
    // @Autowired
    // public HibernateTransactionManager precalltx() {
    // HibernateTransactionManager txManager = new
    // HibernateTransactionManager();
    // txManager.setSessionFactory(precallSessionFactory().getObject());
    // return txManager;
    // }
    //
    @Bean(name = "datatransaction")
    @Autowired
    public HibernateTransactionManager datatx() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(dataSessionFactory().getObject());
        return txManager;
    }

    @Bean(name = "cmpostransaction")
    @Autowired
    public HibernateTransactionManager cmpostx() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(cmposSessionFactory().getObject());
        return txManager;
    }

    //
    @Bean(name = "cmpretransaction")
    @Autowired
    public HibernateTransactionManager cmpretx() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(cmpreSessionFactory().getObject());
        return txManager;
    }

    @Bean(name = "myvtgtransaction")
    @Autowired
    public HibernateTransactionManager myvtgtx() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(myvtgSessionFactory().getObject());
        return txManager;
    }

    @Bean(name = "paymenttransaction")
    @Autowired
    public HibernateTransactionManager payment() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(paymentSessionFactory().getObject());
        return txManager;
    }

    @Bean(name = "billingtransaction")
    @Autowired
    public HibernateTransactionManager billing() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(billingSessionFactory().getObject());
        return txManager;
    }

    @Bean(name = "smtransaction")
    @Autowired
    public HibernateTransactionManager sm() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(smSessionFactory().getObject());
        return txManager;
    }

    @Bean(name = "producttransaction")
    @Autowired
    public HibernateTransactionManager product() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(productSessionFactory().getObject());
        return txManager;
    }

    @Bean(name = "myvtglogtransaction")
    @Autowired
    public HibernateTransactionManager myvtglogtx() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(myvtgSessionFactory().getObject());
        return txManager;
    }

    @Bean(name = "loyaltytransaction")
    @Autowired
    public HibernateTransactionManager loyalty() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(loyaltySessionFactory().getObject());
        return txManager;
    }

    @Bean(name = "mkisharetransaction")
    @Autowired
    public HibernateTransactionManager mkishare() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(mkishareSessionFactory().getObject());
        return txManager;
    }

    @Bean(name = "apigwtransaction")
    @Autowired
    public HibernateTransactionManager apigw() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(apigwSessionFactory().getObject());
        return txManager;
    }

    @Bean(name = "smstransaction")
    @Autowired
    public HibernateTransactionManager sms() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(smsSessionFactory().getObject());
        return txManager;
    }

    @Bean(name = "bankplustransaction")
    @Autowired
    public HibernateTransactionManager bankplus() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(bankplusSessionFactory().getObject());
        return txManager;
    }

    @Bean(name = {"precalltransaction"})
    @Autowired
    public HibernateTransactionManager precalltx() {
        final HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(this.precallSessionFactory().getObject());
        return txManager;
    }

    @Bean(name = "imtransaction")
    @Autowired
    public HibernateTransactionManager im() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(imSessionFactory().getObject());
        return txManager;
    }

    @Bean(name = "promoreportapptransaction")
    @Autowired
    public HibernateTransactionManager promoreportapp() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(promoreportappSessionFactory().getObject());
        return txManager;
    }

}
