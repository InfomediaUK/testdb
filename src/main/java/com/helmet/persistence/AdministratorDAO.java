package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.Administrator;
import com.helmet.bean.AdministratorEntity;

public interface AdministratorDAO {

	public List<Administrator> getAdministrators(boolean showOnlyActive);
	public Administrator getAdministratorForLogin(String login);
	public Administrator getAdministrator(Integer administratorId);
	public AdministratorEntity getAdministratorEntity(Integer administratorId);
	public int insertAdministrator(Administrator administrator, Integer auditorId);
	public int updateAdministrator(Administrator administrator, Integer auditorId);
	public int updateAdministratorPwd(Integer administratorId, String newPwd, String pwdHint, Integer noOfChanges, Integer auditorId);
	public int updateAdministratorSecretWord(Integer administratorId, String newSecretWord, Integer noOfChanges, Integer auditorId);
	public int deleteAdministrator(Integer administratorId, Integer noOfChanges, Integer auditorId);

	public List<Administrator> getAdministratorAudits();
	
	
}
