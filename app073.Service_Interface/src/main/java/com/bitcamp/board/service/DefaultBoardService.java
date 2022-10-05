package com.bitcamp.board.service;

import java.util.List;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.AttachedFile;
import com.bitcamp.board.domain.Board;

// 비즈니스 로직을 수행하는 객체
// - 메서드의 이름은 업무와 관련된 이름을 사용한다.
public class DefaultBoardService {

  BoardDao boardDao;

  public DefaultBoardService(BoardDao boardDao) {
    this.boardDao = boardDao;
  }


  // 게시글 추가하는 기능
  public void add(Board board) throws Exception {

    // 1) 게시글 등록
    if (boardDao.insert(board) == 0) {
      throw new Exception("게시글 등록 실패!");
    }
    // 2) 첨부파일 등록
    boardDao.insertFiles(board);
  }

  public boolean update(Board board) throws Exception {
    // 게시글 변경
    if (boardDao.update(board) == 0) {
      return false;
    }

    // 첨부파일 수정
    boardDao.insertFiles(board);
    return true;
  }

  public Board get(int no) throws Exception {
    // 이 메서드의 경우 하는 일이 없다.
    // 그럼에도 불구하고 이렇게 하는 이유는 일관성을 위해서다.
    // 즉 Controller는 Service 객체를 사용하고 Service 객체는 DAO를 사용하는 형식을
    // 지키기 위함이다. 
    // => 사용 규칙이 동일하면 프로그래밍을 이해하기 쉽다. 
    return boardDao.findByNo(no);
  }


  public boolean delete(int no) throws Exception {

    // 첨부파일 삭제
    boardDao.deleteFiles(no);

    //    // 게시글 삭제
    //    if (boardDao.delete(no) == 0) {
    //      throw new Exception("게시글 삭제 실패!");
    //    }
    //    return true;
    return boardDao.delete(no) > 0;
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
