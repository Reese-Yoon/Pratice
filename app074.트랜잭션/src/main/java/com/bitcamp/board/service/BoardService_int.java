package com.bitcamp.board.service;

import java.util.List;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.AttachedFile;
import com.bitcamp.board.domain.Board;

// 인터페이스 적용 후
public class BoardService_int implements BoardService{

  BoardDao boardDao;

  public BoardService_int(BoardDao boardDao) {
    this.boardDao = boardDao;
  }


  @Override
  public void add(Board board) throws Exception {

    if (boardDao.insert(board) == 0) {
      throw new Exception("게시글 등록 실패!");
    }
    boardDao.insertFiles(board);
  }

  @Override
  public boolean update(Board board) throws Exception {
    if (boardDao.update(board) == 0) {
      return false;
    }

    boardDao.insertFiles(board);
    return true;
  }

  @Override
  public Board get(int no) throws Exception {

    return boardDao.findByNo(no);
  }


  @Override
  public boolean delete(int no) throws Exception {

    boardDao.deleteFiles(no);

    return boardDao.delete(no) > 0;
  }

  @Override
  public List<Board> list() throws Exception{
    return boardDao.findAll();
  }

  @Override
  public AttachedFile getAttachedFile(int fileNo) throws Exception {
    return boardDao.findFileByNo(fileNo);
  }

  @Override
  public boolean deleteAttachedFile(int fileNo) throws Exception {
    return boardDao.deleteFile(fileNo)>0;
  }



}
