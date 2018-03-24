package br.com.devlog.springbootmultipledatabases.config.db;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Teaches Spring where to seek for the configurations to connect in our MySQL database.
 *
 * Repository: <a>https://github.com/heliomf-dev/spring-boot-multiple-databases</a>
 * @author Hélio Márcio Filho <My GitHub: <a>https://github.com/heliomf-dev</a>>
 * @version 1.0
 * @since 1.0
 * */

/* Register this class as a Spring component. */
@Configuration

/* Enable annotation-based transaction management. */
@EnableTransactionManagement

/* Teaches Spring how to create connections and where seek for repositories for this database. */
@EnableJpaRepositories(
        entityManagerFactoryRef = "mysqlEntityManagerFactory", // Specifies which @Bean will provide the EntityManager
        transactionManagerRef   = "mysqlTransactionManager", // Specifies which @Bean will the provide JpaTransactionManager
        basePackages = "br.com.devlog.springbootmultipledatabases.db.h2.repository") // Teaches Spring where to seek for repositories for this database
public class MySQLDataSourceConfig {

    /**
     * Creates a new DataSource which will be used to create a {@link LocalContainerEntityManagerFactoryBean}
     * and teaches Spring how to read the configurations from application.properties.
     * */
    @Bean(name = "mysqDataSource")

    /* All configurations with this prefix in application.properties will be related to this database. */
    @ConfigurationProperties(prefix = "mysql.datasource")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * Provides a {@link EntityManager} for this database.
     *
     * @param builder  will be injected by Spring.
     * @param dataSource  inject the bean provided by mysqlDataSource().
     *
     * @return a factory of EntityManager.
     * */
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("mysqDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                      .packages("br.com.devlog.springbootmultipledatabases.db.mysql.model")
                      .persistenceUnit("mysqlPU")
                      .build();
    }

    /**
     * Provides a transaction manager.
     *
     * @param entityManagerFactory inject the {@link LocalContainerEntityManagerFactoryBean} provided by mysqlEntityManagerFactory()
     *
     * @return a transaction manager.
     * */
    public PlatformTransactionManager mysqlTransactionManager(@Qualifier("mysqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
