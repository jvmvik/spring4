package com.arm.batch.domain;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Archetype Cell
 *
 * @author vicben01
 */
@Entity
public class ArchCell extends Cell
{

  public ArchCell()
  {
  }

  public ArchCell(String name, Date lastCommit, Long projectID)
  {
    setName(name);
    setLastLayoutCommit(lastCommit);
    setProjectID(projectID);
  }
}
