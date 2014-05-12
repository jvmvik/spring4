package com.arm.batch;

import com.arm.batch.domain.Cell;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Cell item test
 *
 * @author victor@milkyway.io
 * @date 2/18/14
 */
public class CellItemTest
{
  @Test
  public void getLastLayoutCommit() throws Exception
  {
    String line = "05/18/2013 05:06                               -                         layout.sync.cds;";
    CellItem item = new CellItem();
    item.setLayout(line);

    assertEquals("Sat May 18 05:06:00 CDT 2013", item.getLastLayoutCommit().toString());
  }

  @Test
  public void getName() throws Exception
  {
    String line = "Directory of: sync://nadc-sync2.nadc.arm.com:2658/Projects/samsung_ln14lpe/sc/oa/sc9mc_ln14lpe_archetype/A2BISO_X1M_A9TR_C14_ARCH\n";
    CellItem cell = new CellItem();
    cell.setDirectoryLine(line);

    assertEquals("A2BISO_X1M_A9TR_C14_ARCH", cell.getName());
  }
}
