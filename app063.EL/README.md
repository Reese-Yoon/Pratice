# app063.EL문법이용
063. OGNL 표기법으로 객체 프로퍼티를 좀 더 쉽게 다루기: EL 문법 사용
----------------------
## 작업 내용
### 1단계 - JSP에서 Expression Element 를 EL로 대체한다.
 - /webapp/board/detail.jsp, list.jsp 파일 변경
 - /webapp/member/detail.jsp, list.jsp 파일 변경
 
-------------------------------------------

## 개념정리
### EL(Expression Language) 사용법
  - JSP파일에 자바형식의 코드를 사용하면 불편한점을 해결할 수 있다!
  - <%= %>, out.println( )과 같은 자바 코드를 더이상 사용하지 않고 좀더 간편하게 출력을 지원하기 위한 도구
  - 기whs <%= %> 대신에  ${표현식}으로 대체!
  - 출력, 반복처리를 태그 기반으로 제공
  - 배열, 컬렉션, JavaBean의 프로퍼티에도 사용된다. 



> Scope 란?
![](https://velog.velcdn.com/images/hyun5no/post/22c5d290-10f9-44c0-aee0-aac9aaf84cd0/image.png)
1. application(ServletContext) : 웹 어플리케이션이 시작되고 종료될 때까지 변수가 유지되는 경우
2. Session(HttpSession) : 웹 브라우저 별로 변수가 관리되는 경우
3. Request(ServletRequest) : http요청을 WAS가 받아서 웹 브라우저에게 응답할 때까지 변수가 유지되는 경우
4. PageScope(JspContext) : 페이지 내에서 지역변수처럼 사용 

* JspContext -> ServletRequest -> HttpSession -> ServletContext 순서대로 검색 후 처음에 발견된 객체를 가져온다.


> Attribute란? 
Attribute란 메서드를 통해 저장되고 관리되는 데이터
![](https://velog.velcdn.com/images/hyun5no/post/9ad815ea-c924-4adb-867e-7646d4229ac0/image.png)
![](https://velog.velcdn.com/images/hyun5no/post/287856dc-f240-4858-aa19-efb78abe47a7/image.png)

