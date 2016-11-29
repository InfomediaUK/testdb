package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.ClientReEnterPwd;
import com.helmet.bean.ClientReEnterPwdUser;

public interface ClientReEnterPwdDAO {

	public List<ClientReEnterPwdUser> getClientReEnterPwdUsersForClient(Integer clientId);
	public int insertClientReEnterPwd(ClientReEnterPwd clientReEnterPwd, Integer auditorId);
	public int deleteClientReEnterPwd(Integer clientReEnterPwdId, Integer noOfChanges, Integer auditorId);

}
