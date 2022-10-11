
# 079. Spring IoC 컨테이너 도입하기 : 페이지 컨트롤러 생성 자동화

## 작업내용
### 1단계 - Spring IoC 컨테이너 프레임워크를 프로젝트에 추가한다. 
- search.maven.org 에서 spring-context, spring-jdbc 라이브러리 검색한다.
- 빌드 스크립트 파일(build.gradle)에 의존 라이브러리 정보를 추가한다.
  - ```implementation 'org.springframework:spring-context:5.3.23'```
  -``` implementation 'org.springframework:spring-jdbc:5.3.2' ```
- gradle eclipse 실행한다.
- 이클립스IDE에서 프로젝트를 갱신한다.

### 2단계 - ContextLoaderListener에서 스프링 IoC 컨테이너를 준비한다.
- com.bitcamp.board.config.AppConfig 클래스 생성
  - 스프링 IoC 컨테이너의 설정을 수행하는 클래스
- com.bitcamp.board.listener.ContextLoaderListener 클래스 변경
  - Spring이 지원하는 DataSource를 사용하니 기존에 DataSource를 지우고 대신에 IocContainer객체를 만들어준다!
  ``` AnnotationConfigApplicationContext iocContainer = new AnnotationConfigApplicationContext(); ```


## 3단계 - Spring IoC 컨테이너에서 DataSource객체를 생성한다.
- com.bitcamp.board.config.AppConfig 클래스 변경
  - createDataSource(): DataSource 구현체를 준비한다.

## 4단계 - Spring IoC 컨테이너에서 트랜잭션 관리자를 생성한다.
 - com.bitcamp.board.config.AppConfig 클래스 변경
   - createTransactionManager(): PlatformTransactionManager 구현체를 준비한다.
   
## 여기까지가 설정파일 완성!
```java
@ComponentScan(value="com.bitcamp.board")
public class AppConfig {

  public AppConfig() {
    System.out.println("AppConfig() 생성자 호출됨!!");
  }


  @Bean("transactionManager")
  public PlatformTransactionManager createTransactionManager(DataSource ds) {
    System.out.println("TransactionManager() 호출됨!");
    return new DataSourceTransactionManager(ds);
  }


  @Bean("dataSource")
  public DataSource createDataSource() {
    System.out.println("createDataSource() 호출됨!");
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(null);
    ds.setUrl(null);
    ds.setUsername(null);
    ds.setPassword(null);
    return ds;
  }

}

```
 흐름
 1. @ComponentScan으로 범위에 있는 모든 애노테이션이 있는 클래스들을 컨테이너에 생성해놓는다.
 2. 생성자 실행
 3. transactionManager를 생성! -> 매개변수(파라미터)로  DataSource를 필요로함! 
 4. @Bean("dataSource")를 통해 DataSource 실행!
 5. 다시 돌아와서 transactionManager 실행! 
 
## 5단계 - 스프링에서 생성한 DataSource를 사용하도록 DAO를 변경한다.
- com.bitcamp.sql.DataSource 클래스 삭제(내가 만든 DataSource는 지우고 spring이 지원하는 DataSource로 대체한다.)
- com.bitcamp.board.dao.MariaDBBoardDao 클래스 변경
- com.bitcamp.board.dao.MariaDBMemberDao 클래스 변경

## 6단계 - 스프링에서 DAO 객체를 생성한다. 
- com.bitcamp.board.listener.ContextLoaderListener 클래스 생성
  - DataSourece 생성 코드 삭제
  - TransactionManager 생성 코드 삭제
  - DAO 생성 코드 삭제
- com.bitcamp.board.dao.MariaDBBoard 클래스 변경
  - Spring IoC컨테이너가 관리하는 객체임을 표시한다. @Component!
- com.bitcamp.board.dao.MariaDBMember 클래스 변경
  - Spring IoC컨테이너가 관리하는 객체임을 표시한다. @Component!
- com.bitcamp.board.config.AppConfig 클래스 변경
  - @Component가 붙은 클래스를 찾아 객체를 생성하도록 그 클래스가 소속된 패키지를 지정한다. 

## 7단계 - 스프링에서 서비스 객체를 생성한다.
- com.bitcamp.board.listener.ContextLoaderListener 클래스 변경
  - 서비스 객체 생성 코드 삭제
- com.bitcamp.board.service.DefaultBoardService 클래스 변경
  - Spring IoC 컨테이너가 관리하는 객체임을 표시한다.
  - transactionManager를 Spring 에서 제공하는 객체로 교체한다. 
  ```java
      // (Spring의) 트랜잭션 동작 방법을 정의한다! 
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("tx1");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
  ```
- com.bitcamp.board.service.DefaultMemberService 클래스 변경
  - Spring IoC 컨테이너가 관리하는 객체임을 표시한다.
  
## 8단계 - 스프링에서 페이지 컨트롤러를 생성한다.
- com.bitcamp.board.controller.XXController 클래스 변경
  - Spring IoC 컨테이너가 관리하는 객체임을 표시한다.
- com.bitcamp.board.listener.ContextLoaderListener 클래스 변경
  - 페이지 컨트롤러 생성 코드를 삭제한다.
  
## 9단계 - Spring IoC 컨테이너를 프론트 컨트롤러에 주입한다.
- com.bitcamp.servlet.DispatcherServlet 클래스 변경
  - Spring IoC 컨테이너를 주입받는 생성자로 변경한다.
  ```java
  public class DispatcherServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  ApplicationContext iocContainer;

  public DispatcherServlet(ApplicationContext iocContainer) {
    // IoC컨테이너를 주입받음. 
    this.iocContainer = iocContainer;
  }
     ~
    // 페이지 컨트롤러를 찾는다.
    // -Spring IoC 컨테이너를 찾지 못할 때 예외를 발생시킨다. 
    Controller controller = (Controller) iocContainer.getBean(pathInfo);
      ~
  }
  ```
- com.bitcamp.board.listener.ContextLoaderListener 클래스 변경
  - DisapatcherServlet 객체를 생성할 때 생성자 파라미터로 spring IoC 컨테이너를 주입한다.
  ```java
  DispatcherServlet servlet = new DispatcherServlet(iocContainer);
  ```
  
 ## 10단계 - Spring IoC 컨테이너에서 페이지 컨트롤러를 꺼내 실행한다.
 - com.bitcamp.servlet.DispacherServlet 클래스 변경
 

----------------------------------------------------------------------------
## 개념정리
### AppConfig
스프링 IoC컨테이너의 설정을 수행하는 클래스! AppConfig 설정파일로 DataSource, Transaction 클래스를 생성 후에 @Bean애노테이션으로 연결해준다! (->Spring라이브러리 덕분에 가능했다!)
```java
// ContextLoadListener파일에서 스프링 Ioc컨테이너 준비!
AnnotationConfigApplicationContext iocContainer 
= new AnnotationConfigApplicationContext(AppConfig.class);
```

- 1. DB 커넥션 객체 관리자 준비 : DataSource
- 2. 트랜잭션 관리자 준비 : PlateformTransactionManager
- 3. 어떤 패키지의 있는 객체를 자동으로 생성할 것인지 지정한다.

## @ComponentScan() -> @Component,@Bean
: @ComponentScan('패키지 범위') 애노테이션을 걸어 놓으면)
먼저 패키지 및 그 하위 패키지에 소속된 클래스 중에서 @Component, @Controller, @Service, @Repository 등의 애노테이션이 붙은 클래스를 찾아 객체를 생성한다.
->
@bean("XXX") : XXX가 나오면 이 자바빈 클래스를 실행한다.
@Component : 이 애노테이션을 붙이면 Spring IoC 컨테이너가 자동으로 생성할 것이다.
 - 객체의 이름을 명시하지 않으면 -> 클래스 이름(첫 알파벳을 소문자로 예:boardService)을 사용해서 저장해놓는다. 
 - 생성자에 파라미터가 있다면 해당 타입의 객체를 찾아 생성자를 호출할 때 주입할 것이다,.
 - 만약, 생성자가 원하는 파라미터 값이 없다면 생성 예외가 발생한다. 
