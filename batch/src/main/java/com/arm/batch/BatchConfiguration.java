package com.arm.batch;

import com.arm.batch.domain.Cell;
import org.hibernate.SessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.SystemCommandTasklet;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.*;
import org.springframework.batch.item.database.HibernateItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO describe
 *
 * @author vicben01
 */
@Configuration
public class BatchConfiguration
{
  @Autowired
  private SessionFactory sessionFactory;

  // tag::jobstep[]
  @Bean
  public Job job(JobBuilderFactory jobs, Step report, Step publish)
  {
    return jobs.get("report")
        .incrementer(new RunIdIncrementer())
        .start(report)
        .next(publish)
        .build();
  }

  @Bean
  public Tasklet dsscTasklet()
  {
    SystemCommandTasklet dssc = new SystemCommandTasklet();
    dssc.setTimeout(1000);
    //dssc.setWorkingDirectory(Paths.get("/").toAbsolutePath().toString());
    dssc.setTerminationCheckInterval(100);
    dssc.setCommand("cmd.exe /c echo hello" );
    return dssc;
  }

  @Bean
  public ItemProcessor<Cell, Cell> cellProcessor()
  {
    return new ReportProcessor();
  }

  @Bean
  public ItemReader<Cell> reportReader()
  {
    return new ReportReader();
  }

  @Bean
  public ItemWriter<Cell> cellWriter()
  {
    HibernateItemWriter writer = new HibernateItemWriter<Cell>();
    writer.setSessionFactory(sessionFactory);
    return writer;
  }

  @Bean
  public Step report(StepBuilderFactory stepBuilderFactory, Tasklet dsscTasklet)
  {
    return stepBuilderFactory.get("dssc").tasklet(dsscTasklet).build();
  }

  @Bean
  public Step publish(StepBuilderFactory stepBuilderFactory, ItemReader<Cell> reportReader, ItemProcessor reportProcessor, ItemWriter reportWriter)
  {
    return stepBuilderFactory.get("publish").chunk(10).reader(reportReader).processor(reportProcessor).writer(reportWriter).build();
  }
  // end::jobstep[]

}
