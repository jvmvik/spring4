package com.arm.ipc.rt;

import com.arm.ipc.rt.domain.Cell;
import com.arm.ipc.rt.domain.CellRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.ws.Response;
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
      return "fail";

    // Update entity
    cellRepository.save(cell);
    return "ok";
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
