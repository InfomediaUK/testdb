package com.helmet.application.admin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.application.FileHandler;
import com.helmet.application.admin.abztract.AdminAction;


public class ClientUploadProcess extends AdminAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());


	String[] recordTypes = {
		"client", 
		"reasonforrequest", 
		"publicholiday",
		"uplift",
		"grade",
		"clientagency",
		"jobfamily",
		"jobsubfamily",
		"jobprofile",
		"jobprofilegrade",
		"clientagencyjobprofile",
		"clientagencyjobprofilegrade",
		"site",
		"location",
		"shift",
		"dresscode",
		"expense",
		"locationjobprofile"
	};

	int[] recordTypeNoOfTokens = {
		8, 
		3, 
		2, 
		4, 
		2, 
		2, 
		3, 
		3, 
		5, 
		1, 
		3, 
		10, 
		9,
		15,
		10,
		2,
		5,
		6
	};

	String[] recordTypeSQLs = {
		"INSERT INTO client (clientid, name, code, address1, address2, address3, address4, postalcode, countryid, creationTimestamp, auditorId, auditTimestamp) SELECT nextval('getclientid'), '^', '^', '^', '^', '^', '^', '^', countryid, CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP FROM country WHERE active = true AND name = '^'", 
		"INSERT INTO reasonforrequest (reasonforrequestid, clientid, name, code, displayorder, creationTimestamp, auditorId, auditTimestamp) SELECT nextval('getreasonforrequestid'), clientid, '^', '^', ^, CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP FROM client WHERE active = true AND name = 'clientName'", 
		"INSERT INTO publicholiday (publicholidayid, clientid, name, phdate, creationTimestamp, auditorId, auditTimestamp) SELECT nextval('getpublicholidayid'), clientid, '^', '^', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP FROM client WHERE active = true AND name = 'clientName'",
		"INSERT INTO uplift (upliftid, clientid, upliftday, uplifthour, upliftfactor, upliftvalue, creationTimestamp, auditorId, auditTimestamp) SELECT nextval('getupliftid'), clientid, ^, ^, ^, ^, CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP FROM client WHERE active = true AND name = 'clientName'",
		"INSERT INTO grade (gradeid, clientid, name, displayorder, creationTimestamp, auditorId, auditTimestamp) SELECT nextval('getgradeid'), clientid, '^', ^, CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP FROM client WHERE active = true AND name = 'clientName'",
		"INSERT INTO clientagency (clientagencyid, clientid, agencyid, displayorder, creationTimestamp, auditorId, auditTimestamp) SELECT nextval('getclientagencyid'), c.clientid, a.agencyid, ^, CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP FROM client c, agency a WHERE c.active = true AND c.name = 'clientName' AND a.active = true AND a.code = '^'",
		"INSERT INTO jobfamily (jobfamilyid, clientid, name, code, displayorder, creationTimestamp, auditorId, auditTimestamp) SELECT nextval('getjobfamilyid'), clientid, '^', '^', ^, CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP FROM client WHERE active = true AND name = 'clientName'", 
		"INSERT INTO jobsubfamily (jobsubfamilyid, jobfamilyid, name, code, displayorder, creationTimestamp, auditorId, auditTimestamp) SELECT nextval('getjobsubfamilyid'), jf.jobfamilyid, '^', '^', ^, CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP FROM jobfamily jf, client c WHERE jf.active = true AND jf.name = 'jobFamilyName' AND jf.clientid = c.clientid AND c.active = true AND c.name = 'clientName'",
		"INSERT INTO jobprofile (jobprofileid, jobsubfamilyid, name, code, autofill, documenturl, displayorder, creationTimestamp, auditorId, auditTimestamp) SELECT nextval('getjobprofileid'), jsf.jobsubfamilyid, '^', '^', ^, '^', ^, CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP FROM jobsubfamily jsf, jobfamily jf, client c WHERE jsf.active = true AND jsf.name = 'jobSubFamilyName' AND jsf.jobfamilyid = jf.jobfamilyid AND jf.active = true AND jf.name = 'jobFamilyName' AND jf.clientid = c.clientid AND c.active = true AND c.name = 'clientName'",
		"INSERT INTO jobprofilegrade (jobprofilegradeid, jobprofileid, gradeid, creationTimestamp, auditorId, auditTimestamp) SELECT nextval('getjobprofilegradeid'), jp.jobprofileid, g.gradeid, CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP FROM jobprofile jp, jobsubfamily jsf, jobfamily jf, client c, grade g WHERE jp.active = true AND jp.name = 'jobProfileName' AND jp.jobsubfamilyid = jsf.jobsubfamilyid AND jsf.active = true AND jsf.name = 'jobSubFamilyName' AND jsf.jobfamilyid = jf.jobfamilyid AND jf.active = true AND jf.name = 'jobFamilyName' AND jf.clientid = c.clientid AND c.active = true AND c.name = 'clientName' AND g.clientid = c.clientid AND g.active = true AND g.name = '^'",
		"INSERT INTO clientagencyjobprofile (clientagencyjobprofileid, clientagencyid, jobprofileid, percentage, sendemailaddress, creationTimestamp, auditorId, auditTimestamp) SELECT nextval('getclientagencyjobprofileid'), ca.clientagencyid, jp.jobprofileid, ^, '^', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP FROM jobprofile jp, jobsubfamily jsf, jobfamily jf, client c, clientagency ca, agency a WHERE jp.active = true AND jp.name = 'jobProfileName' AND jp.jobsubfamilyid = jsf.jobsubfamilyid AND jsf.active = true AND jsf.name = 'jobSubFamilyName' AND jsf.jobfamilyid = jf.jobfamilyid AND jf.active = true AND jf.name = 'jobFamilyName' AND jf.clientid = c.clientid AND c.active = true AND c.name = 'clientName' AND ca.clientid = c.clientid AND ca.active = true AND a.agencyid = ca.agencyid and a.active = true AND a.code = 'agencyCode'",
		"INSERT INTO clientagencyjobprofilegrade (clientagencyjobprofilegradeid, clientagencyjobprofileid, gradeid, rate, payrate, wtdpercentage, nipercentage, chargeratevatrate, commissionvatrate, payratevatrate, wtdvatrate, nivatrate, creationTimestamp, auditorId, auditTimestamp) SELECT nextval('getclientagencyjobprofilegradeid'), cajp.clientagencyjobprofileid, g.gradeid, ^, ^, ^, ^, ^, ^, ^, ^, ^, CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP FROM clientagencyjobprofile cajp, jobprofile jp, jobsubfamily jsf, jobfamily jf, client c, clientagency ca, agency a, grade g WHERE jp.active = true AND jp.name = 'jobProfileName' AND jp.jobsubfamilyid = jsf.jobsubfamilyid AND jsf.active = true AND jsf.name = 'jobSubFamilyName' AND jsf.jobfamilyid = jf.jobfamilyid AND jf.active = true AND jf.name = 'jobFamilyName' AND jf.clientid = c.clientid AND c.active = true AND c.name = 'clientName' AND ca.clientid = c.clientid AND ca.active = true AND a.agencyid = ca.agencyid and a.active = true AND a.code = 'agencyCode' AND cajp.clientagencyid = ca.clientagencyid and cajp.jobprofileid = jp.jobprofileid and cajp.active = true AND g.clientid = c.clientid AND g.active = true AND g.name = 'gradeName'",
		"INSERT INTO site (siteid, clientid, name, code, address1, address2, address3, address4, postalcode, countryid, displayorder, creationTimestamp, auditorId, auditTimestamp) SELECT nextval('getsiteid'), cl.clientid, '^', '^', '^', '^', '^', '^', '^', c.countryid, ^, CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP FROM client cl, country c WHERE cl.active = true AND cl.name = 'clientName' AND c.active = true AND c.name = '^'", 
		"INSERT INTO location (locationid, siteid, name, description, code, singlecandidateshow, singlecandidatedefault, cvrequiredshow, cvrequireddefault, interviewrequiredshow, interviewrequireddefault, canprovideaccommodationshow, canprovideaccommodationdefault, carrequiredshow, carrequireddefault, commentsdefault, displayorder, creationTimestamp, auditorId, auditTimestamp) SELECT nextval('getlocationid'), s.siteid, '^', '^', '^', ^, ^, ^, ^, ^, ^, ^, ^, ^, ^, '^', ^, CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP FROM site s, client c WHERE s.active = true AND s.name = 'siteName' AND s.clientid = c.clientid AND c.active = true AND c.name = 'clientName'",
		"INSERT INTO shift (shiftid, locationid, name, description, code, starttime, endtime, breakstarttime, breakendtime, noofhours, breaknoofhours, displayorder, creationTimestamp, auditorId, auditTimestamp) SELECT nextval('getshiftid'), l.locationid, '^', '^', '^', '^', '^', '^', '^', ^, ^, ^, CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP FROM location l, site s, client c WHERE l.active = true AND l.name = 'locationName' AND l.siteid = s.siteid AND  s.active = true AND s.name = 'siteName' AND s.clientid = c.clientid AND c.active = true AND c.name = 'clientName'",
		"INSERT INTO dresscode (dresscodeid, locationid, name, displayorder, creationTimestamp, auditorId, auditTimestamp) SELECT nextval('getdresscodeid'), l.locationid, '^', ^, CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP FROM location l, site s, client c WHERE l.active = true AND l.name = 'locationName' AND l.siteid = s.siteid AND  s.active = true AND s.name = 'siteName' AND s.clientid = c.clientid AND c.active = true AND c.name = 'clientName'",
		"INSERT INTO expense (expenseid, locationid, name, code, multiplier, vatrate, displayorder, creationTimestamp, auditorId, auditTimestamp) SELECT nextval('getexpenseid'), l.locationid, '^', '^', ^, ^, ^, CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP FROM location l, site s, client c WHERE l.active = true AND l.name = 'locationName' AND l.siteid = s.siteid AND  s.active = true AND s.name = 'siteName' AND s.clientid = c.clientid AND c.active = true AND c.name = 'clientName'",
		"INSERT INTO locationjobprofile (locationjobprofileid, locationid, jobprofileid, rate, budget, creationTimestamp, auditorId, auditTimestamp) SELECT nextval('getlocationjobprofileid'), l.locationid, jp.jobprofileid, ^, budgetValue, CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP FROM location l, site s, client c, jobfamily jf, jobsubfamily jsf, jobprofile jp WHERE l.active = true AND l.name = 'locationName' AND l.siteid = s.siteid and s.active = true AND s.name = 'siteName' AND s.clientid = c.clientid AND c.active = true AND c.name = 'clientName' AND jf.clientid = c.clientid AND jf.active = true AND jf.code = 'jobFamilyCode' AND jsf.jobfamilyid = jf.jobfamilyid AND jsf.active = true AND jsf.code = 'jobSubFamilyCode' AND jp.jobsubfamilyid = jsf.jobsubfamilyid AND jp.active = true AND jp.code = 'jobProfileCode'"
	};

	String[] mgrAccessSQLs = {
        "INSERT INTO mgraccessgroup (mgraccessgroupid, clientid, name, creationTimestamp, auditorId, auditTimestamp) select nextval('getmgraccessgroupid'), clientid, 'All', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP FROM client WHERE active = true AND name = 'clientName'",
        "INSERT INTO mgraccessgroupitem (mgraccessgroupitemid, mgraccessgroupid, mgraccessid, creationTimestamp, auditorId, auditTimestamp) " +
         "select nextval('getmgraccessgroupitemid'), mag.mgraccessgroupid, ma.mgraccessid, CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP " +
         "from mgraccess ma, mgraccessgroup mag, client c " +
         "where ma.active = true and ma.global = false and mag.name = 'All' " +
         "and mag.clientid = c.clientid and c.active = true AND c.name = 'clientName'"
	};
	
	String budgetTransactionSQL = "INSERT INTO budgettransaction (budgettransactionid, locationjobprofileid, value, comment, creationTimestamp, auditorId, auditTimestamp) " +
			"select nextval('getbudgettransactionid'), ljp.locationjobprofileid, budgetValue, 'budgetComment', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP " +
			"FROM locationjobprofile ljp, location l, site s, client c, jobfamily jf, jobsubfamily jsf, jobprofile jp " + 
			"WHERE l.active = true " +
			"AND l.name = 'locationName' " + 
			"AND l.siteid = s.siteid " + 
			"and s.active = true " + 
			"AND s.name = 'siteName' " + 
			"AND s.clientid = c.clientid  " +
			"AND c.active = true " + 
			"AND c.name = 'clientName' " + 
			"AND jf.clientid = c.clientid " + 
			"AND jf.active = true " + 
			"AND jf.code = 'jobFamilyCode' " + 
			"AND jsf.jobfamilyid = jf.jobfamilyid " + 
			"AND jsf.active = true " + 
			"AND jsf.code = 'jobSubFamilyCode' " + 
			"AND jp.jobsubfamilyid = jsf.jobsubfamilyid " + 
			"AND jp.active = true " + 
			"AND jp.code = 'jobProfileCode' " +
			"AND ljp.locationid = l.locationid " +
			"AND ljp.jobprofileid = jp.jobprofileid " +
			"AND ljp.active = true ";
    
    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	FormFile myFile = (FormFile)dynaForm.get("uploadFile");
        String contentType = myFile.getContentType();
        String filename    = myFile.getFileName();
        int fileSize       = myFile.getFileSize();
    
        if (filename == null || "".equals(filename)) { 
			ActionMessages errors = new ActionMessages();
			MessageResources messageResources = getResources(request);
	        errors.add("clientUpload", new ActionMessage("errors.required", messageResources.getMessage("label.uploadFile")));
	        saveErrors(request, errors);
	   		return mapping.getInputForward();
        }
	
        String clientName = null;
		String jobFamilyName = null;
		String jobSubFamilyName = null;
		String jobProfileName = null;
		String siteName = null;
		String locationName = null;
		String agencyCode = null;
		String gradeName = null;
		
		String jobFamilyCode = null;
		String jobSubFamilyCode = null;
		String jobProfileCode = null;
		String budgetValue = null;
		String budgetComment = null;

        try {
        	BufferedReader in = new BufferedReader(new InputStreamReader(myFile.getInputStream()));
	        StringBuffer out = new StringBuffer();
	        String str;
	        int lineNo = 0;
	        while ((str = in.readLine()) != null) {

				lineNo++;
				
//				System.out.println(lineNo);
				
				StringTokenizer st = new StringTokenizer(str, "|");
				if (st.countTokens() > 1) {
					// line has more that one token
					String recordType = st.nextToken();
					if (recordType.startsWith("#")) {
						// comment - so ignore
						continue;
					}
					int recordTypeId = getRecordTypeId(recordType);
					if (recordTypeId == -1) {
						doError(lineNo, "Invalid record type", out);
						break;
					}
					if (st.countTokens() != recordTypeNoOfTokens[recordTypeId]) {
						doError(lineNo, "Invalid number of tokens. Expecting "
								+ recordTypeNoOfTokens[recordTypeId], out);
						break;
					}

					// need to store parent info like client ???

					// get the SQL
					String sql = recordTypeSQLs[recordTypeId];

					// >>>> below - should be a switch or a clever enum
					if (recordType.equals("client")) {
						clientName = st.nextToken();
						sql = sql.replaceFirst("\\^", clientName);
					}
					if (recordType.equals("jobfamily")) {
						jobFamilyName = st.nextToken();
						sql = sql.replaceFirst("\\^", jobFamilyName);
					}
					if (recordType.equals("jobsubfamily")) {
						jobSubFamilyName = st.nextToken();
						sql = sql.replaceFirst("\\^", jobSubFamilyName);
					}
					if (recordType.equals("jobprofile")) {
						jobProfileName = st.nextToken();
						sql = sql.replaceFirst("\\^", jobProfileName);
					}
					if (recordType.equals("site")) {
						siteName = st.nextToken();
						sql = sql.replaceFirst("\\^", siteName);
					}
					if (recordType.equals("location")) {
						locationName = st.nextToken();
						sql = sql.replaceFirst("\\^", locationName);
					}
					if (recordType.equals("clientagencyjobprofile")) {
						agencyCode = st.nextToken();
						//
						// now replaced with replaceFirst below - this means that the agencyCode can be first in the list of values
						//
						//sql = sql.replaceFirst("\\^", agencyCode);
					}
					if (recordType.equals("clientagencyjobprofilegrade")) {
						gradeName = st.nextToken();
						//
						// now replaced with replaceFirst below - this means that the gradeName can be first in the list of values
						//
					}
					if (recordType.equals("locationjobprofile")) {
						//
						sql = sql.replaceFirst("\\^", st.nextToken());
						//
						jobFamilyCode = st.nextToken();
						jobSubFamilyCode = st.nextToken();
						jobProfileCode = st.nextToken();
						budgetValue = st.nextToken();
						budgetComment = st.nextToken();
					}
					// <<<< above - should be a switch or a clever enum

					while (st.hasMoreTokens()) {
						String token = st.nextToken();
						if ("NULL".equals(token)) {
							// Caters for quoted and non-quoted values by looking for a quote before the carat to be replaced ...
							int indexOfCarat = sql.indexOf("^");
							if (sql.charAt(indexOfCarat - 1) == '\'') {
								// carat is quoted so replace '^' with NULL not just ^ 
								sql = sql.replaceFirst("'\\^'", token);
							} else {
								sql = sql.replaceFirst("\\^", token);
							}
						} else {
							sql = sql.replaceFirst("\\^", token);
						}
					}

					sql = sql.replaceFirst("clientName",
							clientName == null ? "" : clientName);
					sql = sql.replaceFirst("jobFamilyName",
							jobFamilyName == null ? "" : jobFamilyName);
					sql = sql.replaceFirst("jobSubFamilyName",
							jobSubFamilyName == null ? "" : jobSubFamilyName);
					sql = sql.replaceFirst("jobProfileName",
							jobProfileName == null ? "" : jobProfileName);
					sql = sql.replaceFirst("siteName", siteName == null ? ""
							: siteName);
					sql = sql.replaceFirst("locationName",
							locationName == null ? "" : locationName);
					sql = sql.replaceFirst("agencyCode",
							agencyCode == null ? "" : agencyCode);
					sql = sql.replaceFirst("gradeName", gradeName == null ? ""
							: gradeName);
					
					sql = sql.replaceFirst("jobFamilyCode",
							jobFamilyCode == null ? "" : jobFamilyCode);
					sql = sql.replaceFirst("jobSubFamilyCode",
							jobSubFamilyCode == null ? "" : jobSubFamilyCode);
					sql = sql.replaceFirst("jobProfileCode",
							jobProfileCode == null ? "" : jobProfileCode);
					sql = sql.replaceFirst("budgetValue",
							budgetValue == null ? "" : budgetValue);
					sql = sql.replaceFirst("budgetComment",
							budgetComment == null ? "" : budgetComment);

					if (!sql.equals("")) {
						out.append(sql + ";");
						out.append("\n");
//						System.out.println(sql + ";");
					}

					// manageraccess stuff for client
					if (recordType.equals("client")) {
						for (String mgrAccessSQL : mgrAccessSQLs) {
							mgrAccessSQL = mgrAccessSQL.replaceFirst(
									"clientName", clientName);
							out.append(mgrAccessSQL + ";");
							out.append("\n");
//							System.out.println(mgrAccessSQL + ";");
						}
					}
					// budgettransaction stuff for locationjobprofile
					if (recordType.equals("locationjobprofile")) {
						if (!budgetValue.equals("0") && !budgetComment.equals("NULL")) {
							sql = budgetTransactionSQL;

							sql = sql.replaceFirst("clientName",
									clientName == null ? "" : clientName);
							sql = sql.replaceFirst("siteName", siteName == null ? ""
									: siteName);
							sql = sql.replaceFirst("locationName",
									locationName == null ? "" : locationName);
							sql = sql.replaceFirst("jobFamilyCode",
									jobFamilyCode == null ? "" : jobFamilyCode);
							sql = sql.replaceFirst("jobSubFamilyCode",
									jobSubFamilyCode == null ? "" : jobSubFamilyCode);
							sql = sql.replaceFirst("jobProfileCode",
									jobProfileCode == null ? "" : jobProfileCode);
							sql = sql.replaceFirst("budgetValue",
									budgetValue == null ? "" : budgetValue);
							sql = sql.replaceFirst("budgetComment",
									budgetComment == null ? "" : budgetComment);
							out.append(sql + ";");
							out.append("\n");
						}
					}
				}

			}

	        String outputFilePath = FileHandler.getInstance().getTempFileLocation() + FileHandler.getInstance().getTempFileFolder() + File.separator + "helmet.txt";
        	
            File parentFolder = new File(outputFilePath).getParentFile();
            if (!parentFolder.exists())
            {
            	// Create any required directories.
            	parentFolder.mkdirs();
            }
	        
	        File outputFile = new File(outputFilePath);

	        FileWriter writer = new FileWriter(outputFile);
	        writer.write(out.toString());
	        writer.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

	private void doError(int lineNo, String msg, StringBuffer out) {

		String theMsg = msg + " - line " + lineNo;
		System.out.println(theMsg);
		out.append(theMsg);
	}
	
	private int getRecordTypeId(String recordTypeParam) {
		int recordTypeId = -1;
		for (int i = 0; i < recordTypes.length; i++) {
  		    if (recordTypes[i].equals(recordTypeParam)) {
                recordTypeId = i;
                break;
		    }
		}
		return recordTypeId;
	}
    
}
