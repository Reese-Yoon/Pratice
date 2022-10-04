package com.bitcamp.board.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.Board;

@WebServlet("/board/list")
public class BoardListController extends HttpServlet{

  private static final long serialVersionUID = 1L;

  BoardDao boardDao;

  @Override
  public void init() throws ServletException {
    boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    try {
      List<Board> boards = boardDao.findAll();

      req.setAttribute("boards", boards);

      resp.setContentType("text/html;charset=UTF-8");
      RequestDispatcher 요청배달자 = req.getRequestDispatcher("/board/list.jsp");
      요청배달자.include(req, resp);


    } catch(Exception e) {
      RequestDispatcher 요청배달자 = req.getRequestDispatcher("/error.jsp");
      req.setAttribute("exception", e);
      요청배달자.forward(req, resp);
    }
  }

}
