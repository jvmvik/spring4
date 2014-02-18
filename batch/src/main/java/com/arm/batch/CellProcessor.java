package com.arm.batch;

import com.arm.batch.domain.Cell;
import org.springframework.batch.item.ItemProcessor;

/**
 * TODO describe
 *
 * @author vicben01
 */
public class CellProcessor implements ItemProcessor<Cell, Cell>
{
  @Override
  public Cell process(Cell item) throws Exception
  {
    return null;  //TODO
  }
}
