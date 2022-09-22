/*
 * 게시글 메뉴 처리 클래스
 */
package com.bitcamp.board.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.dao.MariaDBBoardDao;
import com.bitcamp.board.domain.Board;

@WebServlet(value="/board/update")
public class BoardUpdateServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private BoardDao boardDao;

  public BoardUpdateServlet() throws Exception{

    // 톰캣 서버를 실행할 때는
    // JAR 파일에 들어 있는 JDBC Driver 클래스가 자동으로 로딩되지 않기 때문에
    // 개발자가 직접 로딩해야 한다. 

    Class.forName("org.mariadb.jdbc.Driver");
    Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");  
    boardDao = new MariaDBBoardDao(con);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    resp.setContentType("text/html; charset=UTF-8");
    PrintWriter out = resp.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset=\"UTF-8\">");
    out.println("<title>bitcamp</title>");
    out.println("<meta http-equiv='Refresh' content='1; url=list'>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>게시글 변경</h1>");

    Board board = new Board();
    board.no = Integer.parseInt(req.getParameter("no"));
    board.title = req.getParameter("title");
    board.content = req.getParameter("content");

    try {
      if (boardDao.update(board) == 0) {
        out.println("<p>해당 번호의 게시글이 없습니다.</p>");

      } else {
        out.println("<p>해당 게시글을 변경했습니다.</p>");
      }
    } catch (Exception e) {
      out.println("오류 발생!");
    }

    out.println("</body>");
    out.println("</html>");
  }

}




