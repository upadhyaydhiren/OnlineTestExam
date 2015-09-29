package com.crossover.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.crossover.utility.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class UnitTestController {
	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext applicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
				.build();
	}

}
