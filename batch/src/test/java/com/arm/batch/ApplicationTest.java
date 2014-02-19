package com.arm.batch;

import org.junit.runner.RunWith;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test batch application
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationTest
{

  @Autowired
  private JobLauncher jobLauncherTestUtils;

}
