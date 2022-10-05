## App073. 서비스 컴포넌트 인터페이스 적용
- 서비스 객체에 인터페이스 적용하여 서비스 객체를 교체하기 쉽게 만들기
- DefaultXXXX : 인터페이스를 받는 구현체
  - XXXX : 인터페이스 
--------------------------------------------------------------------
###  작업내용
 * 1단계
  - BoardService 객체를 인터페이스와 구현체로 분리한다.
  - com.bitcamp.board.service.BoardService 인터페이스 생성
  - com.bitcamp.board.service.DefaultBoardService 클래스 생성
    - 기존의 BoardService 클래스를 인터페이스 구현 클래스로 만든다.
  
 * 2단계
  - MemberService 객체를 인터페이스와 구현체로 분리한다.
  - com.bitcamp.Member.service.MemberService 인터페이스 생성
  - com.bitcamp.Member.service.DefaultMemberService 클래스 생성
    - 기존의 MemberService 클래스를 인터페이스 구현 클래스로 만든다.
  
 * 3단계
  - 서비스 객체를 준바할 때 샐호 생성한 구현체를 사용한다.
  - com.bitcamp.board.listener.ContextLoaderListener 클래스 변경
  ```
  ctx.setAttribute("boardService", new DefaultBoardService(boardDao));
  ctx.setAttribute("memberService", new DefaultMemberService(memberDao));
  ```
 --------------------------------------------------------------------
### 개념정리
![](https://velog.velcdn.com/images/hyun5no/post/712d3dfe-83da-47b1-afd8-d04dfaca2bb3/image.jpg)
 
