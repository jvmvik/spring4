package com.arm.ipc.rt;

import com.arm.ipc.rt.domain.Cell;
import com.arm.ipc.rt.domain.CellRepository;
import org.springframework.boot.SpringApplication;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories
public class Application
{
  ///   https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-data-jpa/src/main/java/sample/data/jpa/service
  public static void main(String[] args)
  {
    SpringApplication.run(Application.class, args);
    System.out.println("Application started: http://localhost:8080");
    startBrowser();
  }

  private static void startBrowser()
  {
    try
    {
      Thread.sleep(500);
      ProcessBuilder pb = new ProcessBuilder("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe", "http://localhost:8080");
      pb.start();
    } catch (IOException e)
    {
      e.printStackTrace();
    } catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }

  /*
   * Bean
   */
  @Bean
  public DataSource dataSource()
  {
    return new EmbeddedDatabaseBuilder().setType(H2).build();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter)
  {
    LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
    lef.setDataSource(dataSource);
    lef.setJpaVendorAdapter(jpaVendorAdapter);
    lef.setPackagesToScan("com.arm.ipc.rt.domain");
    return lef;
  }

  @Bean
  public JpaVendorAdapter jpaVendorAdapter()
  {
    HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
    hibernateJpaVendorAdapter.setShowSql(false);
    hibernateJpaVendorAdapter.setGenerateDdl(true);
    hibernateJpaVendorAdapter.setDatabase(Database.H2);
    return hibernateJpaVendorAdapter;
  }

  @Bean
  public PlatformTransactionManager transactionManager()
  {
    return new JpaTransactionManager();
  }

}

