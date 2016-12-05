package com.helmet.application.admin.abztract;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.helmet.api.exceptions.IllegalAccessException;
import com.helmet.application.admin.AdminUtilities;
import com.helmet.bean.Administrator;

public abstract class AdminAction extends Action {

    private Administrator administratorLoggedIn;
    private boolean showOnlyActive;
    
	public boolean getShowOnlyActive() {
		return showOnlyActive;
	}
	public void setShowOnlyActive(boolean showOnlyActive) {
		this.showOnlyActive = showOnlyActive;
	}
	public Administrator getAdministratorLoggedIn() {
		return administratorLoggedIn;
	}
	public void setAdministratorLoggedIn(Administrator administratorLoggedIn) {
		this.administratorLoggedIn = administratorLoggedIn;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Administrator administrator = AdminUtilities.getCurrentAdministrator(request);

		if (administrator == null) {
			throw new IllegalAccessException();
		}

		
		
//		mapping.getModuleConfig().findActionConfigs()		
//		mapping.getModuleConfig().findActionConfig(mapping.getPath())
//		mapping.getModuleConfig().getPrefix()
		
		
		
		setAdministratorLoggedIn(administrator);
		setShowOnlyActive(administrator.getShowOnlyActive()); 

		// all is well, so call the actual execute method
		
		return doExecute(mapping, form, request, response);
	}

    protected abstract ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response);

}
