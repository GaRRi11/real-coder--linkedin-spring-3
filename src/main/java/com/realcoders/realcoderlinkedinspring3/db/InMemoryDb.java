package com.realcoders.realcoderlinkedinspring3.db;

import com.realcoders.realcoderlinkedinspring3.user.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

@Component
public class InMemoryDb {

   private HashMap<Integer, User> savedCustomers = new HashMap<>();
   public void insert(User user){
      savedCustomers.put(user.getId(),user);
   }
   public User findById(int id){
      return savedCustomers.get(id);
   }
   public int savedCustomersSize(){
      return savedCustomers.size();
   }

}
