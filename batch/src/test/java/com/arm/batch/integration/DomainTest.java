package com.arm.batch.integration;

import com.arm.batch.DataConfiguration;
import com.arm.batch.domain.*;
import org.apache.log4j.BasicConfigurator;
import org.aspectj.lang.annotation.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.Date;

import static com.arm.batch.fixtures.JPAAssertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * TODO describe
 *
 * @author vicben01
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataConfiguration.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class DomainTest
{

  @Autowired
  EntityManager manager;

  @Autowired
  ProjectRepository projectRepository;

  @Autowired
  ArchCellRepository archCellRepository;

  @Autowired
  RootCellRepository rootCellRepository;

  @Test
  public void project() throws Exception
  {
    Project project = getProject();
    assertEquals(0, projectRepository.count());
    assertEquals(null, project.getId());
    projectRepository.save(project);
    assertNotNull(project.getId());
    assertEquals(1, projectRepository.findAllByName(project.getName()).size());
    assertEquals(1, projectRepository.count());
  }

  @Test
  public void archCell() throws Exception
  {
    ArchCell cell = getArchCell();
    assertEquals(0, archCellRepository.count());
    assertEquals(null, cell.getId());
    archCellRepository.save(cell);
    assertNotNull(cell.getId());
    assertEquals(1, archCellRepository.count());
  }

  @Test
  public void rootCell() throws Exception
  {
    RootCell cell = getRootCell();
    assertEquals(0, rootCellRepository.count());
    assertEquals(null, cell.getId());
    rootCellRepository.save(cell);
    assertNotNull(cell.getId());
    assertEquals(1, rootCellRepository.count());
  }

  private Project getProject()
  {
    return new Project("samsung/ln14lpe",
        "sync://nadc-sync2.nadc.arm.com:2658/Projects/samsung_ln14lpe/sc/oa/sc9mc_ln14lpe_root",
        "sync://nadc-sync2.nadc.arm.com:2658/Projects/samsung_ln14lpe/sc/oa/sc9mc_ln14lpe_archetype");
  }

  public ArchCell getArchCell()
  {
    return new ArchCell("INV1_ARCH", new Date(), 1L);
  }

  public RootCell getRootCell()
  {
    return new RootCell("INV1", new Date(), 1L);
  }


  /*
   assertTableExists(manager, "ARCH_CELL");
    assertTableExists(manager, "root_cell");

    assertTableHasColumn(manager, "root_cell", "product_id");
    assertTableHasColumn(manager, "arch_cell", "product_id");
   */

}
