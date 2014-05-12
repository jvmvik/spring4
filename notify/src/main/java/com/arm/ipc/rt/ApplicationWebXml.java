package com.arm.ipc.rt;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/***
 * Configure application
 *  replace the classic web.xml file
 */
public class ApplicationWebXml extends SpringBootServletInitializer
{
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
  {
    return application.sources(Application.class);
  }
}
