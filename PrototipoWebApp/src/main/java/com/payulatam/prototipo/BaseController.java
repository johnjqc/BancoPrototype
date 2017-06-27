package com.payulatam.prototipo;

import org.openspaces.core.GigaSpace;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;

import com.payulatam.common.GigaSpaceHelper;
import com.payulatam.common.Utils;
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
	
	public void setModel(J[] array) {
		prodModel = new ListModelList(Utils.arrayToList(array));
		gridResults.setModel(prodModel);
	}
	
	public abstract void onClick$buttonSearch();
	
	public abstract void onClick$btnNew();
	
}
