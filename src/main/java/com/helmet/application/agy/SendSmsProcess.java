package com.helmet.application.agy;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.application.SmsHandler;
import com.helmet.application.agy.abztract.AgyAction;


public class SendSmsProcess extends AgyAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
    	
      	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

      	String username = SmsHandler.getInstance().getUsername();
		String password = SmsHandler.getInstance().getPassword();
		String account = SmsHandler.getInstance().getAccount();

		//String username = "kevin@matchmyjob.co.uk";
		//String password = "NDX2054";
		//String account = "EX0037271";
		
		String recipient = (String)dynaForm.getString("mobileNumber");
		String body = (String)dynaForm.getString("message");
		
		System.out.println(username + " " + password + " " + account); 
		System.out.println(recipient);
		System.out.println(body);

//      	try
//      	{
//      		
//			EsendexHeader header = new EsendexHeader(username, password, account);
//			
//			SendServiceLocator locator = new SendServiceLocator();
//			SendServiceSoap_BindingStub service = (SendServiceSoap_BindingStub)locator.getSendServiceSoap();
//			service.setHeader(header);
//			
//			String sendMessageRetVal = service.sendMessage(recipient, body, MessageType.Text);
//
////
////	        System.out.println(sendMessageRetVal);
////	        // dd3593b0-23e3-4f34-921a-4bec51893e54
////	        
//	        System.out.println(sendMessageRetVal + " - " + service.getMessageStatus(sendMessageRetVal));
////	        // Queued
////			// Delivered
////
//
////	        System.out.println(service.getMessageStatus("dd3593b0-23e3-4f34-921a-4bec51893e54"));
//
//      	}
//      	catch(Exception e)
//      	{
//      	  e.printStackTrace();
//      	}

        String referer = (String)dynaForm.getString("referer");
		
		if (referer == null || "".equals(referer)) {
	     	return mapping.findForward("success");
		}

		try {
			response.sendRedirect(referer);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            //
			return mapping.findForward("success");
		}
        
      	return null;

    }

}
