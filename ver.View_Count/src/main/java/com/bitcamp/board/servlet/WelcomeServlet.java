package com.bitcamp.board.servlet;

import java.io.IOException;

// 기존의 소스 : 우리가 정의한 규칙과 애노테이션 적용 -> 우리 앱을 다운 받은 사람만 이용
// 이제는 자바에서 적용하는 규칙과 애노테이션 이용 -> 전세계 웹서비스 이용자가 이용가능
// 자바에서 적용하는 ~~~ Tomcat

import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(value="/welcome")
public class WelcomeServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private ServletConfig config;


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {

    res.setContentType("text/html; charset=UTF-8");
    PrintWriter out = res.getWriter();


    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset=\"UTF-8\">");
    out.println("<title>bitcamp</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>환영합니다!3</h1>");
    out.println("<p>비트캠프 게시판 관리 시스템 프로젝트입니다.</p>");
    out.println("<ul>");
    out.println("  <li><a href='board/list'>게시글</a></li>");
    out.println("  <li><a href='member/list'>회원</a></li>");
    out.println("</ul>");
    out.println("</body>");
    out.println("</html>");
  }

  //
  //  // 관심은 없지만 인터페이스 안에 메서드는 무조건 실행해야되기에 적어놓음.
  //  @Override
  //  public void init(ServletConfig config) throws ServletException {
  //    // init() : 서블릿 객체가 작업할 때 사용할 자원을 준비 시키는 코드 작성
  //
  //    System.out.println("WelcomeServlet.init()");
  //    this.config = config;
  //  }
  //
  //  @Override
  //  public void destroy() {
  //    // destroy() : init()에서 준비했던 자원을 해체시는 코드 둔다.
  //    // TODO Auto-generated method stub
  //
  //    System.out.println("WelcomeServlet.destroy()");
  //
  //  }
  //
  //
  //  /* 
  //   * service() : 요청처리하는 코드를 둔다
  //   * 
  //   * 서블리 생명 주기(life-cycle) : init() -> service() -> destory()
  //   */
  //
  //  @Override
  //  public String getServletInfo() {
  //    // getServletInfo() :  서버 관리 화면을 실행할때 호출된다. 
  //    // TODO Auto-generated method stub
  //
  //    System.out.println("WelcomeServlet.getServletInfo()");
  //
  //    return "환영인사를 하는 서블릿";
  //  }
  //
  //  @Override
  //  public ServletConfig getServletConfig() {
  //    // serveltConfig() : 서블릿과 관련된 배치 정보를 조회할 때 사용
  //
  //    System.out.println("WelcomeServlet.servletConfig()");
  //    return this.config;
  //  }
}
