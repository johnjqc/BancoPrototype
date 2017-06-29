package com.payulatam.prototipo.report;

import javax.annotation.PostConstruct;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.spring.SpringUtil;

import com.apyulatam.mybatis.service.CustomerServiceDAO;
import com.payulatam.model.Customer;
import com.payulatam.prototipo.BaseController;

public class ReportController extends BaseController<Customer> {

	private static final long serialVersionUID = 1L;
	
	
	
	CustomerServiceDAO customerDAO;
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
//        System.out.println(Arrays.asList(SpringUtil.getApplicationContext().getBeanDefinitionNames()));
        customerDAO = (CustomerServiceDAO)SpringUtil.getBean("CustomerServiceDAO");
        
	}
	
	@PostConstruct
	public void init() {
		Customer rs = gigaSpace.read(new Customer());
		System.out.println("rs1: " + rs);
	}

	@Override
	public void onClick$buttonSearch() {
		Customer rs = gigaSpace.read(new Customer());
		System.out.println("rs: " + rs);
		System.out.println("size: " + customerDAO.getAllCustomer().size());
	}

	@Override
	public void onClick$btnNew() {}

}
