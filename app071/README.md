## App071. DAO에서 비즈니스 로직 분리, Controller에서 비즈니스 로직처리
-Why? Dao의 재생산성이 떨어져서...

---------------------------------------------------------------------
###  작업내용
 * 1단계
   - 게시글 등록과 관련된 업무 흐름을 DAO에서 분리한다.
   - MariaDBBoardDao에서 첨부파일 관련 insert(), update(), delete() 관련 메서드를 지움.
   - insert() -> BoardAddController가서 첨부파일 추가 코드 작성
   update() -> BoardUpdateController가서 첨부파일 수정 코드 작성
   delete() -> BoardDeleteController가서 첨부파일 삭제 코드 작성

   ```
   // BoardAddController.java
      // 첨부파일 등록
      boardDao.insertFiles(board);
   
   // BoardUpdateController.java
     // 첨부파일 수정
     boardDao.insertFiles(board);
   
   // BoardDeleteController.java
    // 첨부파일 삭제
    boardDao.deleteFiles(no);
   ```
   
Problems) 
Controller에서 너무 업무가 많음(GRASP 패턴- high Conhension)
그래서, App72 서비스 컴포넌트를 도입한다. 
 
------------------------------------------------------------------- 
### 개념설명

![](https://velog.velcdn.com/images/hyun5no/post/601a6dcb-f05b-42bd-bc07-4171401fcbd2/image.jpg)
기존에는 BoardAppController가 Board객체와 의존관계가 있는 Member와 AttachedFile까지 모두 준비하여 insert()의 파라미터에 넣어 BoardDao에 전달해주었다. 그럼 BoardDao가 insert메서드를 app_board 테이블과 app_board_file 테이블에 쿼리를 던져주었지만....
만약, 첨부파일이 없는 게시판이거나, 이 게시글에게는 첨부파일이 없는 경우 BoardDao를 변경해야 되서 BoardAddController에서 파라미터를 던져줄 때 같이 insert()뿐만 아니라 insertFiles()도 함께 던져주기로함!

![](https://velog.velcdn.com/images/hyun5no/post/26237873-db02-4dc9-84e4-44cd299856ae/image.jpg)

BoardAppController가 등록할 Board객체를 준비하여 AttachedFile여부에 맞춰 insert() 혹은 insertFiles()을 실행했다. 
-> Controller에게 너무 일이 모여 있어서 GRASP패턴에 맞춰서 App072에서 리펙토리하기로함!

#### GRASP패턴
: 객체지향에서 책임 할당을 위한 기법으로 General Responibity Assignment Software Pattern이라고 한다.
 - Information expert : 책임을 수행하는 데 필요한 정보를 가지고 있는 객체에게 할당
 - Low coupling : 객체 간의 결합도가 낮은 방향으로 책임을 할당
 - High Cohesion : 높은 응집도를 유지할 수 있는 방향으로 책임을 할당
 - Creator : 객체의 생성 책임을 생성되는 객체와 연결되거나, 관련될 필요가 있는 객체에게 맡기는 것
 - Polymorphism : 객체의 타입에 따라 변하는 로직일 있을 때, if, switch등 논리 연산자를 이용하기 보다는 타입을 명시적으로 정의하고 각 타입에 다형적으로 행동하는 책임을 할당하라
 - Portected Variationis : 변경이 예상되는 지점은 인터페이스를 통해 책임을 할당하도록 한다. 


