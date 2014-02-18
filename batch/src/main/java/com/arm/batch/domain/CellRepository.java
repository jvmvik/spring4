package com.arm.batch.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 *
 * @author vicben01
 */
public interface CellRepository extends CrudRepository<Cell, Long>
{
    public List<Cell> findArchetype(Cell cell);
}
