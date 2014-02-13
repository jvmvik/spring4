package com.arm.ipc.rt.domain;
;

import javax.persistence.*;
import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Simple Cell Entity
 */
@Entity
public class Cell implements Serializable
{
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private long id;

  @Size(min=2, max=30)
  private String name;

  @NotNull
  private String status;


  public Cell()
  {
  }

  public Cell(String name, String status)
  {
    this.name = name;
    this.status = status;
  }

  public void setId(long id)
  {
    this.id = id;
  }

  public long getId()
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

  public String getStatus()
  {
    return status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  @Override
  public String toString()
  {
    return "Cell{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", status='" + status + '\'' +
        '}';
  }
}
