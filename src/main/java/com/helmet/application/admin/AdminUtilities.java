package com.helmet.application.admin;

import static com.helmet.application.admin.AdminConstants.ADMINISTRATOR;
import static com.helmet.application.admin.AdminConstants.ADMINISTRATORADMINACCESSES;
import static com.helmet.application.admin.AdminConstants.LEVEL2LOGIN;
import static com.helmet.application.admin.AdminConstants.PWDENTERED;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.helmet.bean.AdminAccess;
import com.helmet.bean.Administrator;

public class AdminUtilities {

	public static Administrator getCurrentAdministrator(HttpServletRequest request) {
		return (Administrator) request.getSession().getAttribute(ADMINISTRATOR);
	}

	public static void setCurrentAdministrator(HttpServletRequest request,
			Administrator administrator) {
		request.getSession().setAttribute(ADMINISTRATOR, administrator);
	}

	public static void removeCurrentAdministrator(HttpServletRequest request) {
		request.getSession().removeAttribute(ADMINISTRATOR);
	}

	public static List<AdminAccess> getCurrentAdministratorAdminAccesses(HttpServletRequest request) {
		return (List<AdminAccess>) request.getSession().getAttribute(ADMINISTRATORADMINACCESSES);
	}

	public static void setCurrentAdministratorAdminAccesses(HttpServletRequest request,
			List<AdminAccess> adminAccesses) {
		request.getSession().setAttribute(ADMINISTRATORADMINACCESSES, adminAccesses);
	}

	public static void removeCurrentAdministratorAdminAccesses(HttpServletRequest request) {
		request.getSession().removeAttribute(ADMINISTRATORADMINACCESSES);
	}
	
	public static boolean hasAccess(HttpServletRequest request, String name) {
		Administrator administrator = AdminUtilities.getCurrentAdministrator(request);

        if (administrator.getUser().getSuperUser()) {
        	return true;
        }
		
		List<AdminAccess> adminAccesses = AdminUtilities.getCurrentAdministratorAdminAccesses(request);
		for (AdminAccess adminAccess: adminAccesses) {
            if ((adminAccess.getStartsWith() && 
            	 name.startsWith(adminAccess.getName()))
             ||	name.equals(adminAccess.getName())) {
            	return true;
            }	
		}
        return false;
	}
    	
	public static Boolean getLevel2Login(HttpServletRequest request) {
		return (Boolean) request.getSession().getAttribute(LEVEL2LOGIN);
	}

	public static void setLevel2Login(HttpServletRequest request) {
		request.getSession().setAttribute(LEVEL2LOGIN, true);
	}

	public static void removeLevel2Login(HttpServletRequest request) {
		request.getSession().removeAttribute(LEVEL2LOGIN);
	}

	public static Boolean getPwdEntered(HttpServletRequest request) {
		return (Boolean) request.getSession().getAttribute(PWDENTERED);
	}

	public static void setPwdEntered(HttpServletRequest request) {
		request.getSession().setAttribute(PWDENTERED, true);
	}

	public static void removePwdEntered(HttpServletRequest request) {
		request.getSession().removeAttribute(PWDENTERED);
	}

	public static void remove(HttpServletRequest request) {
      	request.getSession().invalidate();
//    		removeCurrentAdministrator(request);
//      	removeLevel2Login(request);
//      	removeCurrentAdministratorAdminAccesses(request);
	}
	
	public static boolean needToEnterSecretWord(HttpServletRequest request) {
		return true;
	}
	
	public static boolean needToReEnterPwd(HttpServletRequest request, String path) {
        return false;
	}

	
}
