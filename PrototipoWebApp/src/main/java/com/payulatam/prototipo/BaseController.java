package com.payulatam.prototipo;

import org.openspaces.core.GigaSpace;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;

import com.payulatam.common.GigaSpaceHelper;
import com.payulatam.model.BaseEntity;

/**
 * Base class with generic objects for Controllers
 * @author John
 *
 * @param <J> Class that define Controller
 */
public abstract class BaseController<J extends BaseEntity> extends GenericForwardComposer {
	
	private static final long serialVersionUID = 1L;
	
	protected GigaSpace gigaSpace = GigaSpaceHelper.getGigaSpace();
	protected Grid gridResults;
	protected ListModelList prodModel;
	protected J actualObject;
	
	/**
	 * Set Model of results grid for Controller
	 * @param array
	 */
	public void setModel(J[] array) {
		prodModel = new ListModelList(ControllerHelper.arrayToList(array));
		gridResults.setModel(prodModel);
	}
	
	/**
	 * Action of Button Search for Controller
	 */
	public abstract void onClick$buttonSearch();
	
	/**
	 * Action of Button New for Controller
	 */
	public abstract void onClick$btnNew();
	
}
