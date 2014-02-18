package com.arm.batch.fixtures;


import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.hibernate.jdbc.Work;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static junit.framework.TestCase.fail;

public class JPAAssertions
{

  public static void assertTableHasColumn(EntityManager manager, final String tableName, final String columnName)
  {
    SessionImpl session = (SessionImpl) manager.unwrap(Session.class);

    final ResultCollector rc = new ResultCollector();

    session.doWork(new Work()
    {
      @Override
      public void execute(Connection connection) throws SQLException
      {
        ResultSet columns = connection.getMetaData().getColumns(null, null, tableName.toUpperCase(), null);
        while (columns.next())
        {
          if (columns.getString(4).toUpperCase().equals(columnName.toUpperCase()))
          {
            rc.found = true;
          }
        }
      }
    });

    if (!rc.found)
    {
      fail("Column [" + columnName + "] not found on table : " + tableName);
    }
  }

  public static void assertTableExists(EntityManager manager, final String name)
  {
    SessionImpl session = (SessionImpl) manager.unwrap(Session.class);

    final ResultCollector rc = new ResultCollector();

    session.doWork(new Work()
    {
      @Override
      public void execute(Connection connection) throws SQLException
      {
        ResultSet tables = connection.getMetaData().getTables(null, null, "%", null);
        while (tables.next())
        {
          if (tables.getString(3).toUpperCase().equals(name.toUpperCase()))
          {
            rc.found = true;
          }
        }
      }
    });

    if (!rc.found)
    {
      fail("Table not found in schema : " + name);
    }
  }

  static class ResultCollector
  {
    public boolean found = false;
  }

}