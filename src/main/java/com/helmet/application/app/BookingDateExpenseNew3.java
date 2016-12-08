package com.helmet.application.app;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.application.Utilities;
import com.helmet.application.app.abztract.AppAction;
import com.helmet.bean.BookingExpense;


public class BookingDateExpenseNew3 extends AppAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	if (isCancelled(request)) {
    		dynaForm.set("page", (Integer)dynaForm.get("page") - 1);
         	return mapping.findForward("back");
    	}

    	BookingExpense bookingExpense = (BookingExpense)dynaForm.get("bookingExpense");
    	
    	String qtyStr = (String)dynaForm.get("qtyStr");

    	BigDecimal qty = new BigDecimal(qtyStr);

        if (qty.compareTo(new BigDecimal(0)) == 0) {
			ActionMessages errors = new ActionMessages();
			MessageResources messageResources = getResources(request);
			String label = bookingExpense.getIsMultiplier() ? "label.qty" : "label.value";
			errors.add("bookingDateExpenseNew", new ActionMessage("errors.required", messageResources.getMessage(label)));
			saveErrors(request, errors);
			return mapping.getInputForward();
		}

    	// Value is rounded and then the vat is calculated on the rounded value and then the vat value is also rounded.
    	BigDecimal value = Utilities.round(qty.multiply(bookingExpense.getExpenseMultiplier()));
        BigDecimal vatValue = Utilities.round(value.multiply(bookingExpense.getExpenseVatRate().divide(new BigDecimal(100), 5, RoundingMode.HALF_UP)));

		dynaForm.set("qty", qty);
		dynaForm.set("value", value);
		dynaForm.set("vatValue", vatValue);

    	logger.exit("Out going !!!");

     	return mapping.findForward("success");
    }

}
