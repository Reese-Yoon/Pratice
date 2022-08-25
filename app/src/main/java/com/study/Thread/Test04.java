// 1) 스레드 사용 전
// 2) 스레드 사용 후 => 패키지 멤버 클래스로 스레드 구현하기
//    Thread를 생성하기 위해 따로 패키지 멤버 클래스로 만듬
// 3) 인스턴스 생성 후 즉시 메서드 호출하기
//   코드 더 짧게 구현하기 위해
// 4) 패키지 멤버를 스태틱 중첩 클래스로 만든다.
//    Thread 생성 클래스를 Test 클래스에서만 사용하여서 중첩클래스로 만듬.(아직 Main밖임.)

package com.study.Thread;

public class Test04 {
  public static void main(String[] args) {

    int count = 100;

    //    MyThread t1 = new MyThread(count);
    //    t1.start();
    new MyThread(count).start();

    for(int i=0;i<count;i++) {
      System.out.println(">>>" + i);
    }

  }

  static class MyThread extends Thread {
    int count;

    public MyThread(int count) {
      this.count = count;
    }

    @Override
    public void run() {
      for(int i=0; i<count; i++) {
        System.out.println("==>"+i);
      }
    }
  } //static 중첩 클래스

}
