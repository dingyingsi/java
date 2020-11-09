package com.dys.thread;

class MyThreadScopeData{
  private MyThreadScopeData(){}
  public static /*synchronized*/ MyThreadScopeData getThreadInstance(){
     MyThreadScopeData instance = map.get();
     if(instance == null){
       instance = new MyThreadScopeData();
       map.set(instance);
     }
     return instance;
  }
  //private static MyThreadScopeData instance = null;//new MyThreadScopeData();
  private static ThreadLocal<MyThreadScopeData> map = new ThreadLocal<MyThreadScopeData>();
  
  private String name;
  private int age;
  public String getName() {
     return name;
  }
  public void setName(String name) {
     this.name = name;
  }
  public int getAge() {
     return age;
  }
  public void setAge(int age) {
     this.age = age;
  }
}
 
