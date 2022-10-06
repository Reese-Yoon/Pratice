
# app075. DataSource

"직접 DataSource를 만들어서 처리과정을 직접 겪어보자"

-----------------
## 작업내용
### 1단계 
 - thread 전용 DB connection를 제공해주는 일을 하는 객체를 만든다. 
 - com.bitcamp.sql.DataSource 클래스 생성
 
### 2단계 
 - DAO에 DataSource 객체를 주입한다.
 - com.bitcamp.baord.listener.ContextLoaderListener 클래스 변경
   - DataSource 객체를 주입하면 문장이 더 짧아진다. 
   ``` java
         Class.forName("org.mariadb.jdbc.Driver");
      Connection con = DriverManager.getConnection(
          "jdbc:mariadb://localhost:3306/studydb","study","1111");
      ServletContext ctx = sce.getServletContext();
   ```
   
   ``` java
         ServletContext ctx = sce.getServletContext();

      DataSource ds = new DataSource("org.mariadb.jdbc.Driver",
          "jdbc:mariadb://localhost:3306/studydb","study","1111");
   ```
 
### 3단계
 - DataSource에서 제공하는 Connection를 사용하여 데이터를 처리한다.
 - com.bitcamp.board.dao.MariaDBBoardDao 클래스 변경
 - com.bitcamp.board.dao.MariaDBMemberDao 클래스 변경
 - com.bitcamp.board.domain.defaultBoardService 클래스 변경

> 만약 게시글을 추가한다고 하면 
> /board/list
>http-nio-8888-exec-5 => getConnection() 호출 
>http-nio-8888-exec-5 => Connection 객체생성 
> /board/add 
>http-nio-8888-exec-2 => getConnection() 호출 
>http-nio-8888-exec-2 => Connection 객체생성 
>http-nio-8888-exec-2 => getConnection() 호출 // setAutoCommit(true);
>http-nio-8888-exec-2 => getConnection() 호출 // 게시글 등록
>http-nio-8888-exec-2 => getConnection() 호출 // 첨부파일 등록
>http-nio-8888-exec-2 => getConnection() 호출 // setAutoCommit(fasle);




# app075. DataSource

"직접 DataSource를 만들어서 처리과정을 직접 겪어보자"

-----------------
## 작업내용
### 1단계 
 - thread 전용 DB connection를 제공해주는 일을 하는 객체를 만든다. 
 - com.bitcamp.sql.DataSource 클래스 생성
 
### 2단계 
 - DAO에 DataSource 객체를 주입한다.
 - com.bitcamp.baord.listener.ContextLoaderListener 클래스 변경
   - DataSource 객체를 주입하면 문장이 더 짧아진다. 
   ``` java
         Class.forName("org.mariadb.jdbc.Driver");
      Connection con = DriverManager.getConnection(
          "jdbc:mariadb://localhost:3306/studydb","study","1111");
      ServletContext ctx = sce.getServletContext();
   ```
   
   ``` java
         ServletContext ctx = sce.getServletContext();

      DataSource ds = new DataSource("org.mariadb.jdbc.Driver",
          "jdbc:mariadb://localhost:3306/studydb","study","1111");
   ```
 
### 3단계
 - DataSource에서 제공하는 Connection를 사용하여 데이터를 처리한다.
 - com.bitcamp.board.dao.MariaDBBoardDao 클래스 변경
 - com.bitcamp.board.dao.MariaDBMemberDao 클래스 변경
 - com.bitcamp.board.domain.defaultBoardService 클래스 변경





---------------------
## 개념정리
![](https://velog.velcdn.com/images/hyun5no/post/379ef312-f0c0-470a-91fc-f04609f0bbd9/image.jpg)
![](https://velog.velcdn.com/images/hyun5no/post/d651b3b9-7797-4f6a-892a-d7aa241e12bf/image.jpg)


![](https://velog.velcdn.com/images/hyun5no/post/28939b7b-8d06-451f-97da-7b71ffa844ad/image.jpg)

```java
  // 스레드 전용 DB 커넥션 보관소 = Connection Pool?
  ThreadLocal<Connection> conStore = new ThreadLocal<>();
  
  public Connection getConnection() throws Exception {
    Thread currThread = Thread.currentThread();
    System.out.printf("%s => getConnection() 호출 \n", currThread.getName());

    // 현재 스레드의 보관소에서 DB 커넥션 객체를 꺼낸다.
    Connection con = conStore.get();

    if(con == null) { // 현재 스레드 보관소에 커넥션 객체가 없다면
      con = DriverManager.getConnection(jdbcUrl, username, password); // 새로 생성
      conStore.set(con); // 새로 만든 DB 커넥션 객체를 다음에 다시 사용할 수 있도록 보관한다. 
      System.out.printf("%s => Connection 객체생성 \n",currThread.getName());
    }
    return con;
  }
```
> 실행 과정
1. URI에 '/board/add'요청이 오면 BoardAddController를 실행
2. doPost() 메서드 실행하여 Board 객체를 입력받아 BoardService에 파라미터에 담아 전달
   boardService.add(board);
3. model인 BoardService()가 파라미터를 받아 데이터 처리 로직을 수행(add)
   1) MariaDBBoardDao.insert()가 DBMS에 던질 쿼리문을 만듬
   2) Connection 객체를 관리하는 DataSource에 getConnection() 실행!
   3) { 현재 스레드를 출력 -> 현재 스레드 안에 Connection을 꺼냄(새로 생성) -> Connection을 리턴해줌.(새로 만든 Connection은 다음에 사용할 수 있도록 보관한다.) 
4. DataSource에서 꺼낸 Connection으로 DBServer와 연결하여 쿼리문을 던져준다. 


> 여기서 ThreadLocal<Connection>의 역할은?
![](https://velog.velcdn.com/images/hyun5no/post/014028ae-6b5d-4054-81e9-ab08c93cd65c/image.jpg)
스레드로부터 getConnection()요청이 들어오면 스레드에 맞는 Connection을 찾아 리턴해준다. 없으면 DataSource에서 새로 생성해주고 사용한 후에 저장해야한다. (다음에 사용할 수 있어서!)
  
  
