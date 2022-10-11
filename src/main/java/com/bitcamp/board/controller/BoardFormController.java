package com.bitcamp.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import com.bitcamp.servlet.Controller;

@Component("/board/form")
public class BoardFormController implements Controller {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return "/board/form.jsp";
  }
}






