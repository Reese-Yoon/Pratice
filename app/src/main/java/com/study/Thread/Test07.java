// 1) 스레드 사용 전
// 2) 스레드 사용 후 => 패키지 멤버 클래스로 스레드 구현하기
//    Thread를 생성하기 위해 따로 패키지 멤버 클래스로 만듬
// 3) 인스턴스 생성 후 즉시 메서드 호출하기
//   코드 더 짧게 구현하기 위해
// 4) 패키지 멤버 --> 스태틱 중첩 클래스로 만든다.
//    Thread 생성 클래스를 Test 클래스에서만 사용하여서 중첩클래스로 만듬.(아직 Main밖임.)
//    Main()보다 먼저 수행하기위해 static으로 메모리를 먼저 할당해줌.
//    static은 static과 왕래가 가능함.(main()도 Static임!)
// 5) 스태틱 중첩 클래스--> 로컬 클래스로 만든다
//    Thread 생성클래스가 Test클래스에서만 사용. Main() 안으로 들어옴. static이 필요없음.
// 6) 로컬 클래스가 바깥 메서드의 변수를 사용할때 
//    로컬 클래스에서 그 변수의 값을 다룰 수 있도록
//    그와 관련된 인스턴스 필드와 생성자 파라미터를
//    컴파일러가 자동으로 만드는 기법을 활용한다. 
// 7) 로컬 클래스를 익명 클래스로 만든다. 

package com.study.Thread;

public class Test07 {
  public static void main(String[] args) {

    int count = 100;

    Thread t = new Thread() {
      public void run() {
        for(int i=0; i<count; i++) {
          System.out.println("==>"+i);
        }
      }
    };
    t.start();

    for(int i=0;i<count;i++) {
      System.out.println(">>>" + i);
    }

  }

}
