package com.helmet.application.admin;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.DisciplineCategory;
import com.helmet.bean.DisciplineCategoryTraining;


public class DisciplineCategoryTrainingNew extends AdminAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    DisciplineCategory disciplineCategory = (DisciplineCategory)dynaForm.get("disciplineCategory");
    String[] selectedItems = (String[])dynaForm.get("selectedItems");
    ActionMessages errors = new ActionMessages();
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    for (String str : selectedItems)
    {
      StringTokenizer st = new StringTokenizer(str, ",");
      DisciplineCategoryTraining ca = new DisciplineCategoryTraining();
      ca.setDisciplineCategoryId(disciplineCategory.getDisciplineCategoryId());
      ca.setTrainingCourseId(new Integer(st.nextToken().trim()));
      int rowCount = adminService.insertDisciplineCategoryTraining(ca, getAdministratorLoggedIn().getAdministratorId());
    }
    ActionForward actionForward = mapping.findForward("success");
    logger.exit("Out going !!!");
    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?disciplineCategory.disciplineCategoryId=" + disciplineCategory.getDisciplineCategoryId(), actionForward.getRedirect());

  }

}
