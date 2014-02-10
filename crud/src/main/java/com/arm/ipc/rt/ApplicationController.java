package com.arm.ipc.rt;

import com.arm.ipc.rt.domain.Cell;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ApplicationController
{

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(@RequestParam(value="version", required=false, defaultValue="1.0.0") String version, Model model)
  {
    model.addAttribute("version", version);
    return "index";
  }

  @RequestMapping("/cell/all")
  public @ResponseBody List<Cell> all()
  {
    List<Cell> cells = new ArrayList<>();
    cells.add(new Cell("INV", "requested"));
    cells.add(new Cell("NAND", "pending"));
    cells.add(new Cell("XOR", "done"));
    cells.add(new Cell("NOR", "waiting"));
    return cells;
  }
}
