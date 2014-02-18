package com.arm.batch;

import com.arm.batch.domain.Cell;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Evaluate if cell is old
 *
 * @author vicben01
 */
public class ReportProcessor implements ItemProcessor<Cell, Cell>
{
  //@Autowired
  //CellRepository cellRepository;

  @Override
  public Cell process(Cell cell) throws Exception
  {
    // TODO Find achetype that are newer than the current cell
    // TODO Update cell status
    return cell;
  }
}
