package com.arm.ipc.rt;

import com.arm.ipc.rt.domain.Cell;
import com.arm.ipc.rt.domain.CellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ApplicationController
{

  @Autowired
  CellRepository cellRepository;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(@RequestParam(value="version", required=false, defaultValue="1.0.0") String version, Model model)
  {
    model.addAttribute("version", version);
    return "index";
  }

  /*
   * REST
   */
  @RequestMapping(value="/cell", method = RequestMethod.PUT)
  public @ResponseBody String save(@RequestBody Cell cell)
  {
    // Check if cell exists
    Cell c = cellRepository.findOne(cell.getId());
    if(c == null)
      c = cell; // Create a new cell

    // Update entity
    cellRepository.save(c);
    return "ok";
  }

  @RequestMapping(value="/cell", method = RequestMethod.DELETE)
  public void remove(@RequestBody Cell cell)
  {
    // Check if cell exists
    Cell c = cellRepository.findOne(cell.getId());
    if(c == null)
     throw new NotFoundException("Cell is not found: " + cell);

    // Update entity
    cellRepository.delete(c);
   // return "ok";
  }

  @RequestMapping(value = "/cell", method = RequestMethod.GET)
  public @ResponseBody List<Cell> all()
  {
    List<Cell> cells = new ArrayList<>();
    for(Cell cell: cellRepository.findAll())
    {
      cells.add(cell);
    }
    return cells;
  }

}
