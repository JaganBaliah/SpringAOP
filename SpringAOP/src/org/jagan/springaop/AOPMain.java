package org.jagan.springaop;

import org.jagan.springaop.service.ShapeService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPMain {

	public static void main(String[] args) {
		AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
		
		ShapeService shapeService = applicationContext.getBean("shapeService", ShapeService.class);
		//shapeService.getCircle().setName("Dummy name");
		shapeService.getCircle().setNameAndReturn("Dummy name");
		System.out.println(shapeService.getCircle().getName());
		shapeService.getCircle();
				
		applicationContext.close();
	}

}
