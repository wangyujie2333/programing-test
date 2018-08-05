package com.yspay.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-start.xml",
		"classpath:applicationContext-dubbo.xml",
		"classpath:applicationContext-message.xml" })
public class BaseTest extends AbstractJUnit4SpringContextTests {

}
