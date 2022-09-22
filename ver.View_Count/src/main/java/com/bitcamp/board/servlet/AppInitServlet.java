package com.bitcamp.board.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.dao.MariaDBBoardDao;
import com.bitcamp.board.dao.MariaDBMemberDao;
import com.bitcamp.board.dao.MemberDao;

// 이 서블릿은 다른 서블릿이 사용할 객체를 준비하는 일을 한다. 
// 다른 서블릿 : boardDao, MemberDao
@WebServlet(
    value="/init", 
    loadOnStartup = 1)
public class AppInitServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public static BoardDao boardDao;
  public static MemberDao memberDao;

  public AppInitServlet() throws Exception {
    System.out.println("공유 자원을 준비중!");

    Class.forName("org.mariadb.jdbc.Driver");

    Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
    boardDao = new MariaDBBoardDao(con);
    memberDao = new MariaDBMemberDao(con);
  }



}