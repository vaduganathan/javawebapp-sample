package de.testarea.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * controller for test application = Controller
 */
@Controller
@RequestMapping("example1")   // Mapping für Controller, so Definition weiterer Controller möglich -> url: /example1
public class LoginController {

  @RequestMapping(value = "/sayhello")  //  -> url: /example1/sayhello
  @ResponseBody
  public String sayHello() {
    return "Hello World dummy";
  }


  @RequestMapping(value = "/login")  //  -> url: /example1/login
  public String callLogin() {
    return "login";
  }
}
