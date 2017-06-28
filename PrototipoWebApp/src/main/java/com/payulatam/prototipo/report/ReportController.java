package com.payulatam.prototipo.report;

import org.springframework.beans.factory.annotation.Autowired;

import com.apyulatam.mybatis.service.CustomerServiceDAO;
import com.payulatam.model.Movement;
import com.payulatam.prototipo.BaseController;

public class ReportController extends BaseController<Movement> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	CustomerServiceDAO customerDAO;

	@Override
	public void onClick$buttonSearch() {
		System.out.println("size: " + customerDAO.getAllCustomer().size());
	}

	@Override
	public void onClick$btnNew() {}

}
