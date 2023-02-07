package com.realcoders.realcoderlinkedinspring3.db;

import com.realcoders.realcoderlinkedinspring3.user.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class InMemoryDb {

   public HashMap<Integer, User> savedCustomers = new HashMap<>();

}
