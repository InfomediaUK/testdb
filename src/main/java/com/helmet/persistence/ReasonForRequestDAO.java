package com.helmet.persistence;

import java.sql.Timestamp;
import java.util.List;

import com.helmet.bean.ReasonForRequest;

public interface ReasonForRequestDAO {

	public List<ReasonForRequest> getReasonForRequestsForClient(Integer clientId, boolean showOnlyActive);
	public ReasonForRequest getReasonForRequest(Integer reasonForRequestId);
	public ReasonForRequest getReasonForRequestForName(Integer clientId, String name);
	public ReasonForRequest getReasonForRequestForCode(Integer clientId, String code);
	public int insertReasonForRequest(ReasonForRequest reasonForRequest, Integer auditorId);
	public int updateReasonForRequest(ReasonForRequest reasonForRequest, Integer auditorId);
	public int deleteReasonForRequest(Integer reasonForRequestId, Integer noOfChanges, Integer auditorId);
	public int updateReasonForRequestDisplayOrder(Integer reasonForRequestId, Integer displayOrder, Integer noOfChanges, Integer auditorId);

}
