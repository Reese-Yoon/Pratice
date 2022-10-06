package com.bitcamp.transaction;

import java.sql.Connection;

// 트랜잭션 제어의 필요한 값을 담을 보관소
public class TransactionStatus {

  Connection con;

  public TransactionStatus(Connection con) {
    this.con = con;
  }



  public Connection get() {
    return this.con;
  }

}
