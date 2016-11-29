package com.helmet.application.agy;

import static com.helmet.application.agy.AgyConstants.AGENCY;
import static com.helmet.application.agy.AgyConstants.CONSULTANT;
import static com.helmet.application.agy.AgyConstants.CONSULTANTAGYACCESSES;
import static com.helmet.application.agy.AgyConstants.LEVEL2LOGIN;
import static com.helmet.application.agy.AgyConstants.PWDENTERED;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.helmet.bean.Agency;
import com.helmet.bean.AgyAccess;
import com.helmet.bean.Consultant;

public class AgyUtilities {

	public static Consultant getCurrentConsultant(HttpServletRequest request) {
		return (Consultant) request.getSession().getAttribute(CONSULTANT);
	}

	public static void setCurrentConsultant(HttpServletRequest request,
			Consultant consultant) {
		request.getSession().setAttribute(CONSULTANT, consultant);
	}

	public static void removeCurrentConsultant(HttpServletRequest request) {
		request.getSession().removeAttribute(CONSULTANT);
	}

	public static List<AgyAccess> getCurrentConsultantAgyAccesses(HttpServletRequest request) {
		return (List<AgyAccess>) request.getSession().getAttribute(CONSULTANTAGYACCESSES);
	}

	public static void setCurrentConsultantAgyAccesses(HttpServletRequest request,
			List<AgyAccess> agyAccesses) {
		request.getSession().setAttribute(CONSULTANTAGYACCESSES, agyAccesses);
	}

	public static void removeCurrentConsultantAgyAccesses(HttpServletRequest request) {
		request.getSession().removeAttribute(CONSULTANTAGYACCESSES);
	}
	
	public static Agency getCurrentAgency(HttpServletRequest request) {
		return (Agency) request.getSession().getAttribute(AGENCY);
	}

	public static void setCurrentAgency(HttpServletRequest request,
			Agency agency) {
		request.getSession().setAttribute(AGENCY, agency);
	}

	public static void removeCurrentAgency(HttpServletRequest request) {
		request.getSession().removeAttribute(AGENCY);
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

	public static boolean needToEnterSecretWord(HttpServletRequest request) {
		return true;
	}
	
	public static boolean needToReEnterPwd(HttpServletRequest request, String path) {
        return false;
	}
	
	public static boolean hasAccess(HttpServletRequest request, String name) {

		Consultant consultant = AgyUtilities.getCurrentConsultant(request);

        if (consultant.getUser().getSuperUser()) {
        	return true;
        }
		
		List<AgyAccess> agyAccesses = AgyUtilities.getCurrentConsultantAgyAccesses(request);
		for (AgyAccess agyAccess: agyAccesses) {
            if ((agyAccess.getStartsWith() && 
            	 name.startsWith(agyAccess.getName()))
             ||	name.equals(agyAccess.getName())) {
            	return true;
            }	
		}
        return false;
	}

	public static void remove(HttpServletRequest request) {
      	request.getSession().invalidate();
//	    	removeCurrentConsultant(request);
//      	removeLevel2Login(request);
//      	removeCurrentConsultantAgyAccesses(request);
//      	removeCurrentAgency(request);
	}
	
}
