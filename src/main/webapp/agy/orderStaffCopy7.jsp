<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<%-- focusControl stuff --%>
<bean:define id="focusControl" value="singleCandidate"/>
<logic:notEqual name="OrderStaffCopyFormAgy" property="location.singleCandidateShow" value="true">
  <bean:define id="focusControl" value="cvRequired"/>
  <logic:notEqual name="OrderStaffCopyFormAgy" property="location.cvRequiredShow" value="true">
	  <bean:define id="focusControl" value="interviewRequired"/>
	  <logic:notEqual name="OrderStaffCopyFormAgy" property="location.interviewRequiredShow" value="true">
		  <bean:define id="focusControl" value="canProvideAccommodation"/>
		  <logic:notEqual name="OrderStaffCopyFormAgy" property="location.canProvideAccommodationShow" value="true">
			  <bean:define id="focusControl" value="carRequired"/>
			  <logic:notEqual name="OrderStaffCopyFormAgy" property="location.carRequiredShow" value="true">
  			  <bean:define id="focusControl" value="dressCode0"/>
			  </logic:notEqual>
		  </logic:notEqual>
	  </logic:notEqual>
  </logic:notEqual>
</logic:notEqual>
<html:form action="/orderStaffCopy8.do" onsubmit="return singleSubmit();" focus="<%= focusControl %>">
	<html:hidden property="page" value="7"/>
	<table>
	  <tr>
	    <td align="left" valign="middle" width="250">
	      <bean:message key="title.orderStaffCopyStep7"/>
	      -&nbsp;<bean:message key="title.orderStaffCopy7"/>
	    </td>
	    <td align="left" valign="top">
				<jsp:include page="orderStaffCopyButtons.jsp" flush="true" >
				  <jsp:param name="backButtonTabIndex" value="1" />
				  <jsp:param name="nextButtonTabIndex" value="2" />
				  <jsp:param name="completeButtonTabIndex" value="3" />
				</jsp:include>
	    </td>
	  </tr>
	</table>
	<hr/>
	<html:errors />
	<table border="0" width="100%">
	  <tr>
	    <td width="50%" valign="top">
	      <table class="simple">
        <logic:equal name="OrderStaffCopyFormAgy" property="location.singleCandidateShow" value="true">
				  <tr>
				    <th class="label">
					    <bean:message key="label.singleCandidate"/>
					  </th>
					  <td>
				      <html:radio name="OrderStaffCopyFormAgy" property="singleCandidate" value="true" styleId="singleCandidateYes" tabindex="4" />
				      <label for="singleCandidateYes">
				        <bean:message key="label.yes" />
				      </label>
				      <html:radio name="OrderStaffCopyFormAgy" property="singleCandidate" value="false" styleId="singleCandidateNo" tabindex="5" />
				      <label for="singleCandidateNo">
				        <bean:message key="label.no" />
				      </label>
					  </td>
					</tr>
				</logic:equal>
				<logic:equal name="OrderStaffCopyFormAgy" property="location.cvRequiredShow" value="true">
				  <tr>
				    <th class="label">
					    <bean:message key="label.cvRequired"/>
					  </th>
					  <td>
              <html:radio name="OrderStaffCopyFormAgy" property="cvRequired" value="true" styleId="cvRequiredYes" tabindex="6" />
              <label for="cvRequiredYes">
                <bean:message key="label.yes" />
              </label>
              <html:radio name="OrderStaffCopyFormAgy" property="cvRequired" value="false" styleId="cvRequiredNo" tabindex="7" />
              <label for="cvRequiredNo">
                <bean:message key="label.no" />
              </label>
					  </td>
					</tr>
				</logic:equal>
				<logic:equal name="OrderStaffCopyFormAgy" property="location.interviewRequiredShow" value="true">
				  <tr>
				    <th class="label">
					    <bean:message key="label.interviewRequired"/>
					  </th>
					  <td>
              <html:radio name="OrderStaffCopyFormAgy" property="interviewRequired" value="true" styleId="interviewRequiredYes" tabindex="8" />
              <label for="interviewRequiredYes">
                <bean:message key="label.yes" />
              </label>
              <html:radio name="OrderStaffCopyFormAgy" property="interviewRequired" value="false" styleId="interviewRequiredNo" tabindex="9" />
              <label for="interviewRequiredNo">
                <bean:message key="label.no" />
              </label>
					  </td>
					</tr>
				</logic:equal>
				<logic:equal name="OrderStaffCopyFormAgy" property="location.canProvideAccommodationShow" value="true">
				  <tr>
				    <th class="label">
					    <bean:message key="label.canProvideAccommodation"/>
					  </th>
					  <td>
              <html:radio name="OrderStaffCopyFormAgy" property="canProvideAccommodation" value="true" styleId="canProvideAccommodationYes" tabindex="10" />
              <label for="canProvideAccommodationYes">
                <bean:message key="label.yes" />
              </label>
              <html:radio name="OrderStaffCopyFormAgy" property="canProvideAccommodation" value="false" styleId="canProvideAccommodationNo" tabindex="11" />
              <label for="canProvideAccommodationNo">
                <bean:message key="label.no" />
              </label>
					  </td>
					</tr>
				</logic:equal>
				<logic:equal name="OrderStaffCopyFormAgy" property="location.carRequiredShow" value="true">
				  <tr>
				    <th class="label">
					    <bean:message key="label.carRequired"/>
					  </th>
					  <td>
				      <html:radio name="OrderStaffCopyFormAgy" property="carRequired" value="true" styleId="carRequiredYes" tabindex="12" />
				      <label for="carRequiredYes">
				        <bean:message key="label.yes" />
				      </label>
				      <html:radio name="OrderStaffCopyFormAgy" property="carRequired" value="false" styleId="carRequiredNo" tabindex="13" />
				      <label for="carRequiredNo">
				        <bean:message key="label.no" />
				      </label>
					  </td>
					</tr>
				</logic:equal>
				  <tr>
				    <th class="label">
				      <bean:message key="label.autoFill"/>
					  </th>
					  <td>
              <html:radio name="OrderStaffCopyFormAgy" property="autoFill" value="true" styleId="autoFillYes" tabindex="14" />
              <label for="autoFillYes">
                <bean:message key="label.yes" />
              </label>
              <html:radio name="OrderStaffCopyFormAgy" property="autoFill" value="false" styleId="autoFillNo" tabindex="15" />
              <label for="autoFillNo">
                <bean:message key="label.no" />
              </label>
					  </td>
					</tr>
				  <tr>
				    <th class="label">
				      <bean:message key="label.autoActivate"/>
					  </th>
					  <td>
              <html:radio name="OrderStaffCopyFormAgy" property="autoActivate" value="true" styleId="autoActivateYes" tabindex="16" />
              <label for="autoActivateYes">
                <bean:message key="label.yes" />
              </label>
              <html:radio name="OrderStaffCopyFormAgy" property="autoActivate" value="false" styleId="autoActivateNo" tabindex="17" />
              <label for="autoActivateNo">
                <bean:message key="label.no" />
              </label>
					  </td>
					</tr>
				</table>
				<logic:notEmpty name="OrderStaffCopyFormAgy" property="list">
				  <br/>
				  <logic:equal name="OrderStaffCopyFormAgy" property="noOfDressCodes" value="1">
				  <table class="simple">
				    <tr>
				      <th class="label">
				        <bean:message key="label.dressCode"/>&nbsp;&nbsp;
				      </th>
				      <td>
				        <bean:write name="OrderStaffCopyFormAgy" property="dressCode.name"/>&nbsp;&nbsp;
				      </td>
				    </tr>
				  </table>
				  </logic:equal>
				  <logic:greaterThan name="OrderStaffCopyFormAgy" property="noOfDressCodes" value="1">
				    <bean:message key="label.dressCode"/>
				    <table class="radio">
					  <logic:iterate id="dressCode" name="OrderStaffCopyFormAgy" property="list" indexId="dressCodeIndex">
					  <tr>
					    <td>
				   	    <html:radio tabindex="18" property="dressCode.dressCodeId" idName="dressCode" value="dressCodeId" styleId='<%= "dressCode" + dressCodeIndex %>' />
				   	  </td>
				   	  <td>
							  <label for="<%= "dressCode" + dressCodeIndex %>">
							  <bean:write name="dressCode" property="name"/>
							  </label>
							</td>
					  </tr>
					  </logic:iterate>
					  </table>
				 </logic:greaterThan>
				</logic:notEmpty>
      </td>
	    <td class="simple" width="50%" valign="top">
	     <jsp:include page="orderStaffCopyFeedback.jsp" flush="true" >
	       <jsp:param name="formName" value="OrderStaffCopyFormAgy" />
	     </jsp:include>
	    </td>
	  </tr>
	</table>    
</html:form>
