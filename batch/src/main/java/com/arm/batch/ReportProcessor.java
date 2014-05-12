package com.arm.batch;

import com.arm.batch.domain.Cell;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * Create cell from cell item.
 *
 * @author vicben01
 */
public class ReportProcessor implements ItemProcessor<CellItem, Cell>
{

  @Value("#{jobParameters[productID]}")
  Long productID;

  @StepScope
  @Override
  public Cell process(CellItem item) throws Exception
  {
    Cell cell = new Cell();
    cell.setName(item.getName());
    cell.setLastLayoutCommit(item.getLastLayoutCommit());
    return cell;
  }
}

