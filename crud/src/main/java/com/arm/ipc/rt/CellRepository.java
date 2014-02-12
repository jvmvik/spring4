package com.arm.ipc.rt;

import com.arm.ipc.rt.domain.Cell;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * TODO describe
 *
 * @author vicben01
 */
public interface CellRepository extends CrudRepository<Cell, Long>
{
  List<Cell> listByName(String name);
}
