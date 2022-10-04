# board-app 062. mvc 모델2
062. JSP에 있는 자바코드를  Servlet으로 분리하기 : MVC 모델2

-------------------------------------------------------------------------------

## 작업내용

### 1단계
 - com.bticamp.board.controller.XXXController 클래스 추가
   ```java
   @WebServlet("/board/XXX")
   BoardDao boardDao;
   public init() {
   	  boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
    						throws ServletException, IOException {
      try {
      
        ~~ java 문법 ~~
        
        // delete를 제외하고 모두 있음. 
        // JSP가 사용할 수 있도록 ServletRequest 보관소에 결과를 저장한다.
        req.setAttribute("boards" boards);
        
        // JSP에게 UI 생성을 위임한다.
        // JSP가 출력할 콘텐츠의 MIME 타입 설정
        resp.setContentType("text/html.charset=UTF-8");
        RequestDispatcher 요청배달자 = req.getRequestDispatcher("/board/list.jsp");
        요청배달자.include(req,resp); // JSP를 실행 후 리턴해준다. 
      
      } catch(Exception e) {
        // 예외가 발생하면 예외를 처리하는 JSP에게 UI생성을 위임한다.
        RequestDispatcher 요청배달자 = req.getRequestDispatcher("/error.jsp");
        // JSP를 실행하기 전에 ServletRequest 보관소에 오류 정보를 담는다.
        req.setAttribute("exception",e);
              
        //forward();
        // - 예외가 발생하면 기존의 출력 내용을 모두 버린다.
        // - JSP에게 처음부터 새로 출력하게 전권을 위임한다. 
        요청배달자.forward(req.resp);
      }
  ```
    
   ```java
   	// 짧게 줄이면...
      req.setAttribute("board", board);
      resp.setContentType("text/html;charset=UTF-8");
      req.getRequestDispatcher("/board/detail.jsp").include(req, resp);
   } catch (Exception e) {
   	  req.setAttribute("exception", e);
    	req.getRequestDispatcher("/error.jsp").forward(req, resp);
   }
   
   
 - /webapp/WEB-INF/web.html 변경
   - servlet경로를 정해준 방법은 2가지가 있다.
     - web.html파일에 < servlet-name >, < jsp-file >, < url-pattern >을 이용하여 경로설정
     - 어노테이션을 사용하여 @WebServlet("/board/list")...

 - /webapp/board/ *.jsp 파일 수정
	- 기존에 jsp파일들은 view역할을 하기때문에 EL문을 정리해준다! 
  
 - /webapp/member/ *.jsp 파일 수정
 
----------
## 개념정리
### MVC 모델2
: 기존 MVC 모델1에서는 JSP파일이 View,Controller역할을 함께 해왔지만 유지보수를 위해 역할을 분리하여 JSP는 View역할을 Servlet은 Controller역할을 함.
![](https://velog.velcdn.com/images/hyun5no/post/ec67fe51-58aa-4cc7-8e8c-41777005374d/image.png)
