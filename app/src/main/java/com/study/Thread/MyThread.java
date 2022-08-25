package com.study.Thread;

// Thread를 상속으로 구현!
public class MyThread extends Thread {
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
}
