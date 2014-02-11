package com.arm.ipc.rt;

import com.arm.ipc.rt.domain.Cell;
import com.arm.ipc.rt.domain.Twitt;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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

  /*
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(@RequestParam(value="version", required=false, defaultValue="1.0.0") String version, Model model)
  {
    model.addAttribute("version", version);
    return "index";
  }*/

  @MessageMapping("/update")
  @SendTo("/topic/cell")
  public Twitt update(Cell cell) throws Exception
  {
    Thread.sleep(500); // simulated delay
    return new Twitt("Cell updated: " + cell.getName() + "!");
  }
}
