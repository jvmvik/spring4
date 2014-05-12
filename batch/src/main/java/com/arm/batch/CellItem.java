package com.arm.batch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Cell item is an intermediate POJO representation
 *
 * @author vicben01
 */
public class CellItem
{
  public static final String LAYOUT = "layout";
  public static final String SCHEMATIC = "schematic";
  public static final String SYMBOL = "symbol";

  String directoryLine;
  String layout;
  String schematic;
  String symbol;

  private static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");

  public String getDirectoryLine()
  {
    return directoryLine;
  }

  public void setDirectoryLine(String directoryLine)
  {
    this.directoryLine = directoryLine;
  }

  public String getLayout()
  {
    return layout;
  }

  public void setLayout(String layout)
  {
    this.layout = layout;
  }

  public String getSchematic()
  {
    return schematic;
  }

  public void setSchematic(String schematic)
  {
    this.schematic = schematic;
  }

  public String getSymbol()
  {
    return symbol;
  }

  public void setSymbol(String symbol)
  {
    this.symbol = symbol;
  }

  public boolean isCompleted()
  {
    return schematic != null && symbol != null && layout != null;
  }

  public Date getLastLayoutCommit()
  {
    String s = layout.substring(0, layout.indexOf("-")).trim();
    try
    {
      return sdf.parse(s);
    }
    catch(ParseException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  public String getName()
  {
    return directoryLine.substring(directoryLine.lastIndexOf("/") + 1).trim();
  }
}
