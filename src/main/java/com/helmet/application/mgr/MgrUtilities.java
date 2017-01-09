package com.helmet.application.mgr;

import static com.helmet.application.mgr.MgrConstants.CLIENT;
import static com.helmet.application.mgr.MgrConstants.LEVEL2LOGIN;
import static com.helmet.application.mgr.MgrConstants.MANAGER;
import static com.helmet.application.mgr.MgrConstants.MANAGERMGRACCESSES;
import static com.helmet.application.mgr.MgrConstants.PWDENTERED;
import static com.helmet.application.mgr.MgrConstants.CLIENTREENTERPWDS;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.helmet.bean.Client;
import com.helmet.bean.ClientReEnterPwdUser;
import com.helmet.bean.Manager;
import com.helmet.bean.MgrAccess;

public class MgrUtilities {

	public static Manager getCurrentManager(HttpServletRequest request) {
		return (Manager) request.getSession().getAttribute(MANAGER);
	}

	public static void setCurrentManager(HttpServletRequest request,
			Manager manager) {
		request.getSession().setAttribute(MANAGER, manager);
	}

	public static void removeCurrentManager(HttpServletRequest request) {
		request.getSession().removeAttribute(MANAGER);
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

	public static void remove(HttpServletRequest request) {
      	request.getSession().invalidate();
//	    	removeCurrentManager(request);
//      	removeLevel2Login(request);
//      	removeCurrentManagerMgrAccesses(request);
//      	removeCurrentClient(request);
//      	removeCurrentManagerClientReEnterPwdUsers(request);
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

	public static boolean needToEnterSecretWord(HttpServletRequest request) {
		return getCurrentClient(request).getSecretWordAtLogin();
	}
	
	public static List<MgrAccess> getCurrentManagerMgrAccesses(HttpServletRequest request) {
		return (List<MgrAccess>) request.getSession().getAttribute(MANAGERMGRACCESSES);
	}

	public static void setCurrentManagerMgrAccesses(HttpServletRequest request,
			List<MgrAccess> mgrAccesses) {
		request.getSession().setAttribute(MANAGERMGRACCESSES, mgrAccesses);
	}

	public static void removeCurrentManagerMgrAccesses(HttpServletRequest request) {
		request.getSession().removeAttribute(MANAGERMGRACCESSES);
	}
	
	public static List<ClientReEnterPwdUser> getCurrentManagerClientReEnterPwdUsers(HttpServletRequest request) {
		return (List<ClientReEnterPwdUser>) request.getSession().getAttribute(CLIENTREENTERPWDS);
	}

	public static void setCurrentManagerClientReEnterPwdUsers(HttpServletRequest request,
			List<ClientReEnterPwdUser> clientReEnterPwdUsers) {
		request.getSession().setAttribute(CLIENTREENTERPWDS, clientReEnterPwdUsers);
	}

	public static void removeCurrentManagerClientReEnterPwdUsers(HttpServletRequest request) {
		request.getSession().removeAttribute(CLIENTREENTERPWDS);
	}
	
	public static boolean needToReEnterPwd(HttpServletRequest request, String path) {

		List<ClientReEnterPwdUser> clientReEnterPwdUsers = getCurrentManagerClientReEnterPwdUsers(request);
		for (ClientReEnterPwdUser clientReEnterPwdUser: clientReEnterPwdUsers) {
            if (path.equals(clientReEnterPwdUser.getReEnterPwdName())) {
            	return true;
            }	
		}
        return false;

	}
	
	public static Client getCurrentClient(HttpServletRequest request) {
		return (Client) request.getSession().getAttribute(CLIENT);
	}

	public static void setCurrentClient(HttpServletRequest request,
			Client client) {
		request.getSession().setAttribute(CLIENT, client);
	}

	public static void removeCurrentClient(HttpServletRequest request) {
		request.getSession().removeAttribute(CLIENT);
	}

	public static boolean hasAccess(HttpServletRequest request, String name) {
		Manager manager = MgrUtilities.getCurrentManager(request);

        if (manager.getUser().getSuperUser()) {
        	return true;
        }
		
		List<MgrAccess> mgrAccesses = MgrUtilities.getCurrentManagerMgrAccesses(request);
		for (MgrAccess mgrAccess: mgrAccesses) {
            if ((mgrAccess.getStartsWith() && 
            	 name.startsWith(mgrAccess.getName()))
             ||	name.equals(mgrAccess.getName())) {
            	return true;
            }	
		}
        return false;
	}

}
