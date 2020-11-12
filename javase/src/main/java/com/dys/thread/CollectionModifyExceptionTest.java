package com.dys.thread;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionModifyExceptionTest {
  public static void main(String[] args) {
     Collection users = new CopyOnWriteArrayList();
       
       //new ArrayList();
     users.add(new User("张三",28)); 
     users.add(new User("李四",25));     
     users.add(new User("王五",31)); 
     Iterator itrUsers = users.iterator();
     while(itrUsers.hasNext()){
       System.out.println("aaaa");
       User user = (User)itrUsers.next();
       if("李四".equals(user.getName())){
          users.remove(user);
          //itrUsers.remove();
       } else {
          System.out.println(user);         
       }
     }
  }

  private static class User {
      private Integer age;
      private String name;

      public User(String name, Integer age) {
          this.age = age;
          this.name = name;
      }

      public Integer getAge() {
          return age;
      }

      public void setAge(Integer age) {
          this.age = age;
      }

      public String getName() {
          return name;
      }

      public void setName(String name) {
          this.name = name;
      }
  }
}
