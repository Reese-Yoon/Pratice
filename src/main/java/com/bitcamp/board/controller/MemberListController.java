package com.bitcamp.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import com.bitcamp.board.service.MemberService;
import com.bitcamp.servlet.Controller;

@Component("/member/list")
public class MemberListController implements Controller {

  MemberService memberService;
  public MemberListController(MemberService memberService) {
    this.memberService = memberService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.setAttribute("members", memberService.list());
    return "/member/list.jsp";
  }
}






