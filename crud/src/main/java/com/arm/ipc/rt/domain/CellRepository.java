package com.arm.ipc.rt.domain;

import com.arm.ipc.rt.domain.Cell;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Cell repository
 *
 * @author vicben01
 */
public interface CellRepository extends CrudRepository<Cell, Long>
{
  List<Cell> findByName(String name);
}
