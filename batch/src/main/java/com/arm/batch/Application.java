package com.arm.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Application
 *
 * @author vicben01
 */

@ComponentScan
@EnableAutoConfiguration
@EnableBatchProcessing
public class Application
{
  public static void main(String[] args)
  {
    SpringApplication.run(Application.class, args);
  }

}
