package com.payulatam.prototipo.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import com.apyulatam.mybatis.service.AccountServiceDAO;
import com.apyulatam.mybatis.service.MovementServiceDAO;
import com.payulatam.dto.Report;
import com.payulatam.dto.TotalTypesMovement;
import com.payulatam.model.Account;
import com.payulatam.model.Customer;
import com.payulatam.prototipo.BaseController;
import com.payulatam.prototipo.gs.CustomerRepository;

/**
 * Controller Report ZK view
 * @author John
 *
 */
public class ReportController extends BaseController<Customer> {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Repository DAO customer
	 */
	private CustomerRepository<Customer> respoCustomer;
	
	/**
	 * Repository DAO account
	 */
	private AccountServiceDAO accountDAO;
	
	/**
	 * Repository DAO movement
	 */
	private MovementServiceDAO movementDAO;
	
	/**
	 * Combobox customer
	 */
	private Combobox comboboxCustomer;
	
	/**
	 * Datebox initial date
	 */
	private Datebox dateboxDateInitial;
	
	/**
	 * Datebox final date
	 */
	private Datebox dateboxDateFinal;
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        respoCustomer = new CustomerRepository<>(gigaSpace);
        accountDAO = (AccountServiceDAO)SpringUtil.getBean("AccountServiceDAO");
        movementDAO = (MovementServiceDAO)SpringUtil.getBean("MovementServiceDAO");
        
        dateboxDateInitial.setValue(new Date());
        dateboxDateFinal.setValue(new Date());
        
        Customer[] customers = respoCustomer.findByCriteria("ORDER BY name");
        for (int i = 0; i < customers.length; i++) {
        	Comboitem comboitem = new Comboitem();
        	comboitem.setValue(customers[i].getId());
        	comboitem.setLabel(customers[i].getName());
        	comboitem.setParent(comboboxCustomer);
		}
        if (customers != null && customers.length > 0) {
        	comboboxCustomer.setSelectedIndex(0);
        }

        gridResults.setRowRenderer(new RowRenderer() {
            public void render(Row row, Object data) throws Exception {
                final Report report = (Report)data;
                new Label(report.getAccountNumber()).setParent(row);
                new Label(report.getBalance().toString()).setParent(row);
                new Label(report.getMovements().stream()
                		.filter(item -> "DEBIT".equals(item.getTypeMovement()))
                		.findFirst().orElse(new TotalTypesMovement()).getTotal()
                		).setParent(row);
                
                new Label(report.getMovements().stream()
                		.filter(item -> "CREDIT".equals(item.getTypeMovement()))
                		.findFirst().orElse(new TotalTypesMovement()).getTotal()
                		).setParent(row);
                
            }
        });
	}
	
	public void onClick$buttonGenerate() {
		String customerId = (String)comboboxCustomer.getSelectedItem().getValue();
		List<Account> accounts = accountDAO.getAllAccountByCustomerIdAndRangeDateOfMovement(customerId, 
				dateboxDateInitial.getValue(), dateboxDateFinal.getValue());
		
		List<Report> result = new ArrayList<Report>();
		for (Account account : accounts) {
			Report report = new Report();
			report.setAccountNumber(account.getNumberAccount());
			report.setBalance(account.getBalance());
			
			List<TotalTypesMovement> movements = movementDAO.getTotalTypeByAccountIdAndRangeDate(account.getId(), 
				dateboxDateInitial.getValue(), dateboxDateFinal.getValue());
			report.setMovements(movements);
			result.add(report);
		}
		
		prodModel = new ListModelList(result);
		gridResults.setModel(prodModel);
	}
	
	@Override
	public void onClick$buttonSearch() {}

	@Override
	public void onClick$btnNew() {}

}
