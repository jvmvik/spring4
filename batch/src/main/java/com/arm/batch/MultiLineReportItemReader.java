package com.arm.batch;

import com.arm.batch.domain.Cell;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.transform.FieldSet;

/import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.sample.domain.trade.Trade;
import org.springframework.util.Assert;

/**
 * @author Dan Garrette
 * @since 2.0
 */
public class MultiLineReportItemReader implements ItemReader<Cell>, ItemStream
{
  private FlatFileItemReader<FieldSet> delegate;

  /**
   * @see org.springframework.batch.item.ItemReader#read()
   */
  public CellItem read() throws Exception
  {
    CellItem t = null;
    int i = -1;

    for (FieldSet line = null; (line = this.delegate.read()) != null; )
    {
      String prefix = line.readString(0);
      if (prefix.startsWith("Directory of:"))
      {
        t = new CellItem(); // Record must start with 'BEGIN'
        t.setDirectoryLine(prefix);
      }
      else if(prefix.indexOf("//") == 3)
      {
        t.setActiveLine(prefix);
        return t;
      }
    }
    //Assert.isNull(t, "No 'END' was found.");
    return null;
  }

  public void setDelegate(FlatFileItemReader<FieldSet> delegate)
  {
    this.delegate = delegate;
  }

  public void close() throws ItemStreamException
  {
    this.delegate.close();
  }

  public void open(ExecutionContext executionContext) throws ItemStreamException
  {
    this.delegate.open(executionContext);
  }

  public void update(ExecutionContext executionContext) throws ItemStreamException
  {
    this.delegate.update(executionContext);
  }
}