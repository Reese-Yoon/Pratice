package com.bitcamp.board.service;

import java.sql.Connection;
import java.util.List;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.AttachedFile;
import com.bitcamp.board.domain.Board;


public class DefaultBoardService {

  Connection con;   // 트랜잭션으로 다룰 때 사용할 객체
  BoardDao boardDao;

  public DefaultBoardService(BoardDao boardDao, Connection con) {
    this.con = con;
    this.boardDao = boardDao;
  }


  // 게시글 추가하는 기능
  public void add(Board board) throws Exception {

    con.setAutoCommit(false);
    try {

      if (boardDao.insert(board) == 0) {
        throw new Exception("게시글 등록 실패!");
      }

      boardDao.insertFiles(board);
      con.commit();

    } catch (Exception e) {
      con.rollback();
      throw e;
    } finally { //오류 발생 여부 상관없이 작동
      con.setAutoCommit(true); 
    }
  }

  public boolean update(Board board) throws Exception {

    con.setAutoCommit(false);
    try {    
      // 게시글 변경
      if (boardDao.update(board) == 0) {
        return false;
      }

      // 첨부파일 수정
      boardDao.insertFiles(board);
      con.commit();
      return true;

    } catch (Exception e) {
      con.rollback();
      throw e;
    } finally {
      con.setAutoCommit(true);
    }
  }

  public Board get(int no) throws Exception {

    // 조회에서는 트랜잭션 작용하지 않음. 
    // DB변동 사항이 없어서! 

    return boardDao.findByNo(no);
  }


  public boolean delete(int no) throws Exception {
    con.setAutoCommit(false);
    try {
      boardDao.deleteFiles(no);

      boolean result = boardDao.delete(no) > 0;
      con.commit();
      return result;

    } catch (Exception e) {
      con.rollback();
      throw e;
    } finally {
      con.setAutoCommit(true);
    }
  }

  public List<Board> list() throws Exception{
    return boardDao.findAll();
  }

  public AttachedFile getAttachedFile(int fileNo) throws Exception {
    return boardDao.findFileByNo(fileNo);
  }

  public boolean deleteAttachedFile(int fileNo) throws Exception {
    return boardDao.deleteFile(fileNo)>0;
  }



}
