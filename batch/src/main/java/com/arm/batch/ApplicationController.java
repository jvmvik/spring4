package com.arm.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Application main controller
 *
 * @author vicben01
 */
@RestController
public class ApplicationController
{
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index()
  {
    return "index";
  }

  @Autowired
  JobLauncher launcher;

  @Autowired
  Job job;

  @RequestMapping("/start")
  public @ResponseBody String handle(@RequestParam String repository) throws Exception
  {
    Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
    parameters.put("projectID", new JobParameter("1"));
    parameters.put("repository", new JobParameter(repository));
    parameters.put("type", new JobParameter("archetype")); // root

    try
    {
      launcher.run(job, new JobParameters(parameters));
      return "ok";
    }
    catch (JobInstanceAlreadyCompleteException exception)
    {
      return "Job was already executed today... ";
    }
  }
}
