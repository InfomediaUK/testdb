<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<bean:parameter id="formName" name="formName" value="Form Name Not Set" />
<bean:define id="form" name="<%= formName %>" type="org.apache.struts.validator.DynaValidatorForm"/>
<bean:define id="bookingDates" name="form" property="bookingDates" type="com.helmet.bean.BookingDate[]"/>
<%
com.helmet.bean.BookingDate minDate = bookingDates[0];
com.helmet.bean.BookingDate maxDate = bookingDates[bookingDates.length-1];
pageContext.setAttribute("minDate", minDate);
pageContext.setAttribute("maxDate", maxDate);
%>
			<div class="tabber">
			  <div class="tabbertab">
				  <h2>Main Details</h2>
				  <table class="simple">
				    <tr>
				      <th class="label">
				        <bean:message key="label.client"/>
				      </th>
				      <td>
		       		  <bean:write name="form" property="client.name"/>&nbsp;(<bean:write name="form" property="client.code"/>)
				      </td>
				    </tr>
				    <tr>
				      <th class="label">
				        <bean:message key="label.reason"/>
				      </th>
				      <td>
		       		  <bean:write name="form" property="reasonForRequest.name"/>
		       		  (<bean:write name="form" property="reasonForRequestText"/>)
				      </td>
				    </tr>
				    <tr>
		          <th class="label">
		            <bean:message key="label.location"/>
		          </th>
		          <td>
						    <bean:write name="form" property="location.name"/>,
						    <bean:write name="form" property="location.siteName"/>
					      <logic:notEmpty name="form" property="location.description">
					  	    (<bean:write name="form" property="location.description"/>)
					      </logic:notEmpty>
		          </td>
				    </tr>
		        <tr>
		          <th class="label" valign="top">
		            <bean:message key="label.jobProfile"/>
		          </th>
		          <td>
		            <bean:write name="form" property="jobProfile.name"/>
			          (<bean:write name="form" property="jobProfile.jobFamilyCode"/>.<bean:write name="form" property="jobProfile.jobSubFamilyCode"/>.<bean:write name="form" property="jobProfile.code"/>)
		          </td>
		        </tr>
		        <tr>
		          <th class="label" valign="top">
		            <bean:message key="label.grade"/>
		          </th>
		          <td>
					    <logic:iterate id="clientAgencyJobProfileUser" name="OrderStaffCopyFormAgy" property="clientAgencyJobProfileGradeUserArray">
		            <bean:write name="clientAgencyJobProfileUser" property="agencyName"/>, <bean:write name="clientAgencyJobProfileUser" property="gradeName"/><br />
		          </logic:iterate>
		          </td>
		        </tr>
					  <tr>
					    <th align="left" class="label">
					      <bean:message key="label.start"/>
					    </th>
					    <td align="left">
					      <bean:write name="minDate" property="bookingDate" formatKey="format.longDateFormat"/>
					    </td>
					  </tr>
					  <tr>
					    <th align="left" class="label">
					      <bean:message key="label.end"/>
					    </th>
					    <td align="left">
					      <bean:write name="maxDate" property="bookingDate" formatKey="format.longDateFormat"/>
					    </td>
					  </tr>
		        <tr>
		          <th align="left" class="label">
		            <bean:message key="label.days"/>
		          </th>
		          <td>
		            <bean:write name="form" property="noOfDates"/>
		          </td>
		        </tr>
  <logic:empty name="form" property="shiftName">
		        <tr>
		          <th align="left" class="label">
		            <bean:message key="label.shifts"/>
		          </th>
		          <td>
		            <bean:message key="label.varied"/>
		          </td>
		        </tr>
	</logic:empty>
  <logic:notEmpty name="form" property="shiftName">
		        <tr>
		          <th align="left" class="label">
		            <bean:message key="label.shift"/>
		          </th>
		          <td>
		            <bean:write name="form" property="shiftName"/>
		          </td>
		        </tr>
  </logic:notEmpty>
					  <tr>
					    <th align="left" class="label">
					      <bean:message key="label.totalHours" />
					    </th>
					    <td align="left">
					      <bean:write name="form" property="totalHours" format="#0.00"/>
					    </td>
					  </tr>
					  <tr>
					    <th align="left" class="label">
								<bean:message key="label.chargeRate"/>
					    </th>
					    <td align="left">
								<bean:message key="label.currencySymbol"/><bean:write name="form" property="hourlyRate" format="#,##0.00" />
								(<bean:message key="label.currencySymbol"/><bean:write name="form" property="rrp" format="#,##0.00" />)
					    </td>
					  </tr>
				  </table>
				</div>
			  <div class="tabbertab">
				  <h2>Specifics</h2>
		      <table class="simple">
					<logic:equal name="form" property="location.singleCandidateShow" value="true">
					  <tr>
					    <th class="label">
						    <bean:message key="label.singleCandidate"/>
						  </th>
						  <td>
				    	<logic:equal name="form" property="singleCandidate" value="true">
						    <bean:message key="label.yes"/>
						  </logic:equal>
				    	<logic:notEqual name="form" property="singleCandidate" value="true">
						    <bean:message key="label.no"/>
						  </logic:notEqual>
						  </td>
						</tr>
					</logic:equal>
          <logic:equal name="form" property="location.cvRequiredShow" value="true">
            <tr>
              <th class="label">
                <bean:message key="label.cvRequired"/>
              </th>
              <td>
              <logic:equal name="form" property="cvRequired" value="true">
                <bean:message key="label.yes"/>
              </logic:equal>
              <logic:notEqual name="form" property="cvRequired" value="true">
                <bean:message key="label.no"/>
              </logic:notEqual>
              </td>
            </tr>
          </logic:equal>
          <logic:equal name="form" property="location.interviewRequiredShow" value="true">
            <tr>
              <th class="label">
                <bean:message key="label.interviewRequired"/>
              </th>
              <td>
              <logic:equal name="form" property="interviewRequired" value="true">
                <bean:message key="label.yes"/>
              </logic:equal>
              <logic:notEqual name="form" property="interviewRequired" value="true">
                <bean:message key="label.no"/>
              </logic:notEqual>
              </td>
            </tr>
          </logic:equal>
          <logic:equal name="form" property="location.canProvideAccommodationShow" value="true">
            <tr>
              <th class="label">
                <bean:message key="label.canProvideAccommodation"/>
              </th>
              <td>
              <logic:equal name="form" property="canProvideAccommodation" value="true">
                <bean:message key="label.yes"/>
              </logic:equal>
              <logic:notEqual name="form" property="canProvideAccommodation" value="true">
                <bean:message key="label.no"/>
              </logic:notEqual>
              </td>
            </tr>
          </logic:equal>
          <logic:equal name="form" property="location.carRequiredShow" value="true">
            <tr>
              <th class="label">
                <bean:message key="label.carRequired"/>
              </th>
              <td>
              <logic:equal name="form" property="carRequired" value="true">
                <bean:message key="label.yes"/>
              </logic:equal>
              <logic:notEqual name="form" property="carRequired" value="true">
                <bean:message key="label.no"/>
              </logic:notEqual>
              </td>
            </tr>
          </logic:equal>
            <tr>
              <th class="label">
                <bean:message key="label.autoFill"/>
              </th>
              <td>
              <logic:equal name="form" property="autoFill" value="true">
                <bean:message key="label.yes"/>
              </logic:equal>
              <logic:notEqual name="form" property="autoFill" value="true">
                <bean:message key="label.no"/>
              </logic:notEqual>
              </td>
            </tr>
            <tr>
              <th class="label">
                <bean:message key="label.autoActivate"/>
              </th>
              <td>
              <logic:equal name="form" property="autoActivate" value="true">
                <bean:message key="label.yes"/>
              </logic:equal>
              <logic:notEqual name="form" property="autoActivate" value="true">
                <bean:message key="label.no"/>
              </logic:notEqual>
              </td>
            </tr>
					</table>
				</div>
			  <div class="tabbertab">
				  <h2>Expenses</h2>
            <table class="simple">
              <logic:notEmpty name="form" property="expenseArray">
                <logic:iterate id="expense" name="form" property="expenseArray" indexId="expenseIndex">
              <tr>
                <th align="left" class="label">
                  <bean:write name="expense" property="name"/>
                  <logic:notEmpty name="expense" property="description">(<bean:write name="expense" property="description"/>)</logic:notEmpty>
                </th>
                <td align="left">
                  <bean:message key="label.yes"/>
                </td>
              </tr>
                </logic:iterate>
              </logic:notEmpty>
		          <tr>
                <th align="left" class="label">
                  <bean:message key="label.comment"/>
                </th>
                <td>
    			      <logic:empty name="form" property="expensesText">
    	            &nbsp;
			          </logic:empty>
    			      <logic:notEmpty name="form" property="expensesText">
    	            <bean:write name="form" property="expensesText"/>
			          </logic:notEmpty>
                </td>
              </tr>
           </table>
				</div>
			  <div class="tabbertab">
				  <h2>Additional Information</h2>
            <table class="simple" width="100%">
						  <tr>
						    <th align="left" valign="top" class="label" nowrap="true">
						      <bean:message key="label.comments" />
						    </th>
						    <td align="left">
                  <bean:write name="form" property="comments"/>
						    </td>
						  </tr>
              <tr>
                <th align="left" class="label">
                  <bean:message key="label.duration"/>
                </th>
                <td align="left">
                  <bean:write name="form" property="duration"/>
                </td>
              </tr>
              <tr>
                <th align="left" class="label">
                  <bean:message key="label.bookingReference"/>
                </th>
                <td align="left">
                  <bean:write name="form" property="bookingReference"/>
                </td>
              </tr>
						  <tr>
						    <td align="left">
						      &nbsp;
						    </td>
						    <th align="left" class="label">
						      <bean:message key="label.contact" />
						    </th>
						  </tr>
						  <tr>
						    <th align="left" class="label">
						      <bean:message key="label.contactName" />
						    </th>
						    <td align="left">
                  <bean:write name="form" property="contactName"/>
						    </td>
						  </tr>
						  <tr>
						    <th align="left" class="label">
						      <bean:message key="label.contactJobTitle" />
						    </th>
						    <td align="left">
                  <bean:write name="form" property="contactJobTitle"/>
						    </td>
						  </tr>
						  <tr>
						    <th align="left" class="label">
						      <bean:message key="label.contactEmailAddress" />
						    </th>
						    <td align="left">
                  <bean:write name="form" property="contactEmailAddress"/>
						    </td>
						  </tr>
						  <tr>
						    <th align="left" class="label">
						      <bean:message key="label.contactTelephoneNumber" />
						    </th>
						    <td align="left">
                  <bean:write name="form" property="contactTelephoneNumber"/>
						    </td>
						  </tr>
						  <tr>
						    <td align="left">
						      &nbsp;
						    </td>
						    <th align="left" class="label">
						      <bean:message key="label.accountContact" />
						    </th>
						  </tr>
						  <tr>
						    <th align="left" class="label">
						      <bean:message key="label.accountContactName" />
						    </th>
						    <td align="left">
                  <bean:write name="form" property="accountContactName"/>
						    </td>
						  </tr>
						  <tr>
						    <th align="left" class="label">
						      <bean:message key="label.accountContactAddress" />
						    </th>
						    <td align="left">
                  <bean:write name="form" property="accountContactAddress.address1"/>
						    </td>
						  </tr>
						  <tr>
						    <th align="left" class="label">
						      &nbsp;
						    </th>
						    <td align="left">
                  <bean:write name="form" property="accountContactAddress.address2"/>
						    </td>
						  </tr>
						  <tr>
						    <th align="left" class="label">
						      &nbsp;
						    </th>
						    <td align="left">
                  <bean:write name="form" property="accountContactAddress.address3"/>
						    </td>
						  </tr>
						  <tr>
						    <th align="left" class="label">
						      &nbsp;
						    </th>
						    <td align="left">
                  <bean:write name="form" property="accountContactAddress.address4"/>
						    </td>
						  </tr>
						  <tr>
						    <th align="left" class="label">
						      <bean:message key="label.accountContactPostalCode" />
						    </th>
						    <td align="left">
                  <bean:write name="form" property="accountContactAddress.postalCode"/>
						    </td>
						  </tr>
						  <tr>
						    <th align="left" class="label">
						      <bean:message key="label.accountContactCountry" />
						    </th>
						    <td align="left">
                  <bean:write name="form" property="accountContactCountryName"/>
						    </td>
						  </tr>
						  <tr>
						    <th align="left" class="label">
						      <bean:message key="label.accountContactEmailAddress" />
						    </th>
						    <td align="left">
                  <bean:write name="form" property="accountContactEmailAddress"/>
						    </td>
						  </tr>
            </table>
				</div>
			</div>
    