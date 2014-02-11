package com.arm.ipc.rt.domain;

/**
 * Forecast controller
 *
 * @author victor@milkyway.io
 * @date 2/10/14
 */
public class Twitt
{

  String message;

  public Twitt(String message)
  {
    this.message = message;
  }

  public String getMessage()
  {
    return message;
  }
}
