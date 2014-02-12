package com.arm.ipc.rt;

import com.arm.ipc.rt.domain.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
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

  @RequestMapping("/cell/all")
  @Transactional(readOnly = true)
  public @ResponseBody List<Cell> all()
  {
    List<Cell> cells = new ArrayList<>();
    for(Cell cell: cellRepository.findAll())
      cells.add(cell);
    return cells;
  }
}
