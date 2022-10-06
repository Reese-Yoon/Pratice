# App074. 트랜잭션 적용하기

## 작업내용

### 1단계
 - 서비스 객체에 Connection 객체를 주입한다. 
 - com.bitcamp.service.DefaultBoardService 클래스 변경
   - 생성자 수정
       ``` java
      public class DefaultBoardService {
          Connection con;   // 트랜잭션으로 다룰 때 사용할 객체
          BoardDao boardDao
          
          public DefaultBoardService(BoardDao boardDao, Connection con) {
              this.con = con;
              this.boardDao = boardDao;
          }
        ```
 - com.bitcamp.board.listener.ContextLoadListener 클래스 변경
   - BoardSericve 객체를 생성할 때 생성자에 Connection 객체 주입한다.  


  
### 2단계
 - 게시글 입력과 변경, 삭제에 트랜젝션을 적용한다. 
 - com.bitcamp.service.DefaultBoardService 클래스 변경
   - insert(), update(), delete() rollback과 commit()추가와 함께 try~catch 문으로 정리
   ``` java
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
   ```
   
------------------
## 개념정리 - 트랜잭션
   ![](https://velog.velcdn.com/images/hyun5no/post/bc94ffe9-4855-48b1-9a00-841375683a52/image.jpg)
  [트랜잭션 정리 (내가정리함^^)](https://velog.io/@hyun5no/%ED%8A%B8%EB%9E%9C%EC%9E%AD%EC%85%98%EC%9D%B4%EB%9E%80)
   
   