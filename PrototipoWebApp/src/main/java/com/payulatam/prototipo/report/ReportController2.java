package com.payulatam.prototipo.report;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.payulatam.mybatis.CustomerDAO;

@Controller
public class ReportController2 {

	
	@Autowired
	CustomerDAO customerDAO;
	
	@Autowired
    ApplicationContext applicationContext;

	@RequestMapping("/hello")
    public void printBeans() {
        System.out.println(Arrays.asList(applicationContext.getBeanDefinitionNames()));
    }

}
