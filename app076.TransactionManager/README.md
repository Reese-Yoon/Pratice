
# app076. DataSource+트랜잭션 관리자 도입하기 
- 나중에 진짜 스프링 프레임워크를 사용할 때 잘 이해하기 위해 Spring 프레임워크의 트랜잭션 관리기법을 모방해보자!

---------------------------------------
## 작업내용
### 1단계 
 - 트랜잭션 제어의 필요한 값을 담을 보관소를 만든다. 
 - com.bitcamp.transaction.TransactionStatus 클래스 생성
### 2단계 
 - 트랜잭션 관리자 역할을 수행할 클래스를 정의한다. 
 - com.bitcamp.transaction.TransactionManager 클래스 생성
### 3단계
 - 서비스 객체에 DataSource 대신 트랜잭션 관리자를 주입한다. 
 - com.bitcamp.board.listener.ContextLoaderListener 클래스 변경

### 4단계
 - 트랜잭션 관리자를 이용하여 트랜잭션을 처리한다. 
 - com.bitcamp.board.service.DefaultBoardService 클래스 변경

---------------------------------
## 개념정리
![](https://velog.velcdn.com/images/hyun5no/post/7928357f-1915-414a-8c99-328b7da22b36/image.jpg)

> 코드분석!
TransactionStatus.java
```java
// 트랜잭션 제어의 필요한 값을 담을 보관소
public class TransactionStatus {

  Connection con;

  public TransactionStatus(Connection con) {
    this.con = con;
  }

  public Connection get() {
    return this.con;
  }
}
```
TransactionManager.java
```java
public class TransactionManager {

  DataSource ds;

  public TransactionManager(DataSource ds) {
    this.ds =ds;
  }

  public TransactionStatus getTransaction() throws Exception {
    // 현재 스레드에서 사용할 DB 커넥션 객체를 꺼낸다
    Connection con = ds.getConnection();

    // 트랜잭션을 수행할 수 있도록 수동 커밋 상태로 변경한다.
    con.setAutoCommit(false);

    // 트랜잭션을 수행하는 동안 사용할 DB커넥션 객체를 보관소에 담아서 리턴한다. 
    return new TransactionStatus(con);
  }

  public void commit(TransactionStatus status) {
    try {
      status.get().commit();
    } catch(Exception e) {
      // 커밋하다가 발생하는 예외는 무시한다
    } finally {
      try {
        status.get().setAutoCommit(true);
      } catch (Exception e) {
        // 자동커밋 상태로 설정하는 중에 발생한 오류는 무시한다.
      }
    }
  }
}


```