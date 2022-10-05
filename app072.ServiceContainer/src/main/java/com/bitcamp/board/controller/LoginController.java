package com.bitcamp.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.bitcamp.board.domain.Member;
import com.bitcamp.board.service.MemberService;

@WebServlet("/auth/login")
public class LoginController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MemberService memberService;

  @Override
  public void init() {
    memberService = (MemberService) this.getServletContext().getAttribute("memberService");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String email = request.getParameter("email");
      String password = request.getParameter("password");

      Member member = memberService.get(email, password);

      if (member != null) {
        HttpSession session = request.getSession(); 
        session.setAttribute("loginMember", member);
      }


      Cookie cookie = new Cookie("email", email); // 클라이언트 쪽에 저장할 쿠키 생성
      if (request.getParameter("saveEmail") == null) {
        cookie.setMaxAge(0); // 클라이언트에게 해당 이름의 쿠키를 지우라고 명령한다.
      } else {
        // 쿠키의 지속시간을 설정하지 않으면 웹브라우저가 실행되는 동안만 유효하다.
        // 만약 웹브라우저를 종료하더라도 쿠키를 유지하고 싶다면,
        // 지속 시간을 설정해야 한다.
        cookie.setMaxAge(60 * 60 * 24 * 7); // 7일
      }
      response.addCookie(cookie); // 응답 헤더에 쿠키를 포함시킨다.

      request.setAttribute("member", member);

      response.setContentType("text/html;charset=UTF-8");
      request.getRequestDispatcher("/auth/loginResult.jsp").include(request, response);

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response); 
    }
  }
}






