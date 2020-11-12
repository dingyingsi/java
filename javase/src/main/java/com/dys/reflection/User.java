package com.dys.reflection;

public class User {
  private int id;
  private int age;
  private String uname;
  public User(int id, int age, String uname) {
    super();
    this.id = id;
    this.age = age;
    this.uname = uname;
  }
  public User() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getUname() {
    return uname;
  }

  public void setUname(String uname) {
    this.uname = uname;
  }
}
