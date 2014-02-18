package com.arm.batch.domain;

import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Cell
 *
 * @author vicben01
 */
@MappedSuperclass
public class Cell implements Serializable
{
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  @NotNull
  private String name;

  //@DateTimeFormat
  private Date lastCommit;

  @NotNull
  private Long projectID;

  @LastModifiedDate
  private Date lastModifiedDate;

  @PreUpdate
  public void preUpdate()
  {
    setLastModifiedDate(new Date());
  }

  public Date getLastModifiedDate()
  {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Date lastModifiedDate)
  {
    this.lastModifiedDate = lastModifiedDate;
  }

  public Long getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public Date getLastCommit()
  {
    return lastCommit;
  }

  public void setLastCommit(Date lastCommit)
  {
    this.lastCommit = lastCommit;
  }

  public Long getProjectID()
  {
    return projectID;
  }

  public void setProjectID(Long projectID)
  {
    this.projectID = projectID;
  }
}
