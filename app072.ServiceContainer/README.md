## App072. 서비스 컴포넌트 도입
- why? Controller의 역할이 너무많아 GRASP 패턴에 맞춰 Controller에서 비즈니스 로직 분리하고 서비스 컴포넌트 생성! 
-------------------------------------------------------------------------------------------------
###  작업내용
 * 1단계
   - Contoller에서 비즈니스 로직을 분리하여 Service 클래스로 옮긴다.
   - com.bitcamp.board.service.boardService 서비스컴포넌트 생성
     - add(), update(), get(), delete(), list, getAttachedFile(), deleteAttachedFile() 
   - com.bitcamp.board.controller.BoardXXXController 클래스 변경
   - com.bitcamp.member.service.memberService 서비스 컴포넌트 생성
     - add(), update(), get(), get(email, password), delete(), list()
   - com.bitcamp.member.controller.MemberXXXController 클래스 변경
   - com.bitcamp.board.listener.ContextLoaderListener 클래스 변경
      ```
      BoardDao boardDao = new MariaDBBoardDao(con);
      MemberDao meberDao = new MariaDBMemberDao(con);
     
      ctx.setAttribute("boardService", new BoardService(boardDao);
      ctx.setAttribute("memberService", new MemberService(memberDao);
     ```
        


------------------------------------------------------------------------------------------------
### 개념설명
![](https://velog.velcdn.com/images/hyun5no/post/a27893b4-cd7a-49a5-9917-90e926640e7b/image.jpg)

BoardService라는 Service Component를 만들어서 업무로직를 던져준다. 
