package com.example.assignment3;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.assignment3.Controller.UserController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Assignment3ApplicationTests {

	@Autowired
  	private  UserController userController;


	@Test
	public void contextLoads() throws Exception {
		assertThat(userController).isNotNull();
	}

	
}
