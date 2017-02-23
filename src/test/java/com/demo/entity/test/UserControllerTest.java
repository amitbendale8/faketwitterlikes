package com.demo.entity.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.config.SpringMongoConfiguration;
import com.demo.entity.Address;
import com.demo.entity.User;
import com.demo.entity.repository.UserRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringMongoConfiguration.class})
@ComponentScan(basePackages = {"de.my.packages"})
public class UserControllerTest {
	 @Autowired
	 private TestEntityManager entityManager;

	 @Autowired
	 private UserRepository repository;

	 @Test
     public void findByUsernameShouldReturnUser() {
		 User user = new User();
		 user.setName("Amit");
		 Address address = new Address();
		 address.setLine1("Address-Line1");
		 address.setLine2("Address-Line2");
		 address.setLine3("Address-Line3");
		 address.setCity("Nagpur");
		 address.setState("MH");
		 address.setPinCode(441212);
		 user.setAddress(address);
		 user.setEmail("amit.bendale@tpt.com");
		
		 user.setPhoneNumber("8600572422");
		 
         this.entityManager.persist(user);
         List<User> mockUserList = this.repository.findByEmail("amit.bendale@tpt.com");
         User mockUser = mockUserList.get(0);
         
         assertThat(mockUser.getName()).isEqualTo("Amit");
         assertThat(mockUser.getPhoneNumber()).isEqualTo("8600572422");
     }
}
