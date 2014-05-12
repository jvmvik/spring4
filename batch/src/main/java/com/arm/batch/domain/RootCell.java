package com.arm.batch.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Root Cell
 *
 * @author vicben01
 */
@Entity
public class RootCell extends Cell
{
  @NotNull
  private String status;

  public RootCell()
  {
  }

  public RootCell(String name, Date lastCommit, Long projectID)
  {
    setName(name);
    setLastLayoutCommit(lastCommit);
    setProjectID(projectID);
    setStatus("init");
  }

  public String getStatus()
  {
    return status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }
}
