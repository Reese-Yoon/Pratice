package com.bitcamp.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.Board;

@WebServlet("/board/detail")
public class BoardDetailController extends HttpServlet{

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
      int boardNo = Integer.parseInt(req.getParameter("no"));

      Board board = boardDao.findByNo(boardNo);

      if(board==null) {
        throw new Exception("해당 번호의 게시글이 없습니다.");
      }

      req.setAttribute("board", board);

      resp.setContentType("text/html;charset=UTF-8");
      req.getRequestDispatcher("/board/detail.jsp").include(req, resp);

    } catch (Exception e) {
      req.setAttribute("exception",e);
      req.getRequestDispatcher("/error.jsp").forward(req, resp);
    }
  }

}
