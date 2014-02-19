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
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Batch
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
  public Job job(JobBuilderFactory jobs, Step capture, Step push)
  {
    return jobs.get("report")
        .incrementer(new RunIdIncrementer())
        .start(capture)
        .next(push) // .next(compare)
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
  public ItemProcessor<CellItem, Cell> cellProcessor()
  {
    return new ReportProcessor();
  }

  @Bean
  public ItemReader<CellItem> cellReader()
  {
    return new MultiLineReportItemReader();
  }

  @Bean
  public ItemWriter<Cell> cellWriter()
  {
    HibernateItemWriter writer = new HibernateItemWriter<Cell>();
    writer.setSessionFactory(sessionFactory);
    return writer;
  }

  // Capture DSSC content
  @Bean
  public Step capture(StepBuilderFactory stepBuilderFactory, Tasklet dsscTasklet)
  {
    return stepBuilderFactory.get("capture").tasklet(dsscTasklet).build();
  }

  // Push report in database
  @Bean
  public Step push(StepBuilderFactory stepBuilderFactory, ItemReader<CellItem> cellReader, ItemProcessor<CellItem, Cell> cellProcessor, ItemWriter<Cell> cellWriter)
  {
    return stepBuilderFactory
            .get("push")
            .<CellItem, Cell>chunk(10)
            .reader(cellReader)
            .processor(cellProcessor)
            .writer(cellWriter)
            .build();
  }

  // Compare archetype versus root cell

}
