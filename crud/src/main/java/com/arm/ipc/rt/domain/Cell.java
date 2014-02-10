package com.arm.ipc.rt.domain;

import java.util.UUID;

/**
 * Simple Cell object
 *
 * @date 2/9/14
 */
public class Cell
{

  UUID key;
  String name;
  String status;

  public Cell(String name, String status)
  {
    this.key = UUID.randomUUID();
    this.name = name;
    this.status = status;
  }

  public UUID getKey()
  {
    return key;
  }

  public void setKey(UUID key)
  {
    this.key = key;
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
}
