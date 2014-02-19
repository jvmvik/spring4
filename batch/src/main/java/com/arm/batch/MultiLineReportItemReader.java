package com.arm.batch;

import com.arm.batch.domain.Cell;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.transform.FieldSet;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;

/**
 *
 */
public class MultiLineReportItemReader implements ItemReader<CellItem>, ItemStream
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
      else if(prefix.indexOf("/") == 3)
      {
        if(prefix.contains(CellItem.LAYOUT))
          t.setLayout(prefix);
        else if(prefix.contains(CellItem.SCHEMATIC))
          t.setSchematic(prefix);
        else if(prefix.contains(CellItem.SYMBOL))
          t.setSymbol(prefix);

        if(t.isCompleted())
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