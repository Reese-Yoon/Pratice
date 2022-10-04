package com.bitcamp.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.Board;

@WebServlet("/board/add")
public class BoardAddController extends HttpServlet {

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
      Board board = new Board();
      board.title = req.getParameter("title");
      board.content= req.getParameter("content");
      board.memberNo = Integer.parseInt(req.getParameter("memberNo"));

      if(boardDao.insert(board) == 0) {
        throw new Exception("게시글 등록 실패!");
      }

      resp.setContentType("text/html;charset=UTF-8)");
      req.getRequestDispatcher("/board/add.jsp").include(req,resp);


    } catch (Exception e) {
      req.setAttribute("exception", e);
      req.getRequestDispatcher("/error.jsp").forward(req, resp);
    }


  }

}
