package com.arm.batch.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * TODO describe
 *
 * @author vicben01
 */
@Entity
public class Project
{
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  @NotNull
  private String name;

  //@DateTimeFormat
  @CreatedDate
  private Date creationDate;

  @LastModifiedDate
  private Date lastModifiedDate;

  @NotNull
  private String archetypeVault;

  @NotNull
  private String rootVault;

  public Project(String name, String rootVault, String archetypeVault)
  {
    this.rootVault = rootVault;
    this.archetypeVault = archetypeVault;
    this.name = name;
    this.creationDate = new Date();
  }

  @PreUpdate
  public void preUpdate()
  {
    setLastModifiedDate(new Date());
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

  public Date getCreationDate()
  {
    return creationDate;
  }

  public void setCreationDate(Date creationDate)
  {
    this.creationDate = creationDate;
  }

  public Date getLastModifiedDate()
  {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Date lastModifiedDate)
  {
    this.lastModifiedDate = lastModifiedDate;
  }

  public String getArchetypeVault()
  {
    return archetypeVault;
  }

  public void setArchetypeVault(String archetypeVault)
  {
    this.archetypeVault = archetypeVault;
  }

  public String getRootVault()
  {
    return rootVault;
  }

  public void setRootVault(String rootVault)
  {
    this.rootVault = rootVault;
  }
}
