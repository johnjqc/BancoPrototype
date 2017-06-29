package com.payulatam.prototipo.report;

import java.text.SimpleDateFormat;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;

import com.apyulatam.mybatis.service.AccountServiceDAO;
import com.apyulatam.mybatis.service.CustomerServiceDAO;
import com.apyulatam.mybatis.service.MovementServiceDAO;
import com.payulatam.model.Account;
import com.payulatam.model.Customer;
import com.payulatam.prototipo.BaseController;
import com.payulatam.prototipo.ControllerHelper;
import com.payulatam.prototipo.gs.CustomerRepository;

public class ReportController extends BaseController<Customer> {

	private static final long serialVersionUID = 1L;
	
	private CustomerRepository<Customer> respoCustomer;
	private CustomerServiceDAO customerDAO;
	private AccountServiceDAO accountDAO;
	private MovementServiceDAO movementDAO;
	
	private Combobox comboboxCustomer;
	private Datebox dateboxDateInitial;
	private Datebox dateboxDateFinal;
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        respoCustomer = new CustomerRepository<>(gigaSpace);
        customerDAO = (CustomerServiceDAO)SpringUtil.getBean("CustomerServiceDAO");
        accountDAO = (AccountServiceDAO)SpringUtil.getBean("AccountServiceDAO");
        movementDAO = (MovementServiceDAO)SpringUtil.getBean("MovementServiceDAO");
        
        
        ControllerHelper.setItemDefault(comboboxCustomer);
        Customer[] customers = respoCustomer.findByCriteria("ORDER BY name");
        for (int i = 0; i < customers.length; i++) {
        	Comboitem comboitem = new Comboitem();
        	comboitem.setValue(customers[i].getId());
        	comboitem.setLabel(customers[i].getName());
        	comboitem.setParent(comboboxCustomer);
		}
        
	}
	
	public void onClick$buttonGenerate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String customerId = (String)comboboxCustomer.getSelectedItem().getValue();
		String initialDate = sdf.format(dateboxDateInitial.getValue());
		String finalDate = sdf.format(dateboxDateFinal.getValue());
		List<Account> accounts = accountDAO.getAllAccountByCustomerIdAndRangeDateOfMovement(customerId, 
				dateboxDateInitial.getValue(), dateboxDateFinal.getValue());
		System.out.println("size: " + accounts.size());
	}
	
	@Override
	public void onClick$buttonSearch() {}

	@Override
	public void onClick$btnNew() {}

}
