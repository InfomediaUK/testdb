package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.AgencyUser;
import com.helmet.bean.ReEnterPwd;

public interface ReEnterPwdDAO {

	public List<ReEnterPwd> getReEnterPwds(boolean showOnlyActive);
	public List<ReEnterPwd> getReEnterPwdsNotForClient(Integer clientId);
	public ReEnterPwd getReEnterPwd(Integer countryId);
	public ReEnterPwd getReEnterPwdForName(String name);
	public int insertReEnterPwd(ReEnterPwd reEnterPwd, Integer auditorId);
	public int updateReEnterPwd(ReEnterPwd reEnterPwd, Integer auditorId);
	public int deleteReEnterPwd(Integer reEnterPwdId, Integer noOfChanges, Integer auditorId);

}
