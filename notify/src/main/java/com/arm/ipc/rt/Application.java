package com.arm.ipc.rt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application
{
  public static void main(String[] args)
  {
    SpringApplication.run(Application.class, args);
    System.out.println("Application started: http://localhost:8090/");
  }
}

