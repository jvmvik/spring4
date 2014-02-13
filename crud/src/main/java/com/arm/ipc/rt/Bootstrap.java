package com.arm.ipc.rt;

import com.arm.ipc.rt.domain.Cell;
import com.arm.ipc.rt.domain.CellRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * BootStrap
 */
@Component
public class Bootstrap implements InitializingBean
{

  @Autowired
  CellRepository cellRepository;

  @Override
  @Transactional
  public void afterPropertiesSet() throws Exception
  {
    // save a couple of customers
    cellRepository.save(new Cell("INV", "New"));
    cellRepository.save(new Cell("XOR", "New"));
    System.out.println("2 cells added..");
  }
}
