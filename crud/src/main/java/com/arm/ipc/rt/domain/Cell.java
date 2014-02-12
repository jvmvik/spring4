package com.arm.ipc.rt.domain;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.UUID;

/**
 * Simple Cell Entity
 */
@Entity
public class Cell
{

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private long id;

  String name;
  String status;


  public Cell(String name, String status)
  {
    this.name = name;
    this.status = status;
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
