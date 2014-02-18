package com.arm.ipc.rt;

/**
 * TODO describe
 *
 * @author vicben01
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Not found")
public class NotFoundException extends RuntimeException
{
  public NotFoundException(String s)
  {
    super(s);
  }
}
