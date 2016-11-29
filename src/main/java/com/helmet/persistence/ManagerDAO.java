package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.Manager;
import com.helmet.bean.ManagerEntity;

public interface ManagerDAO {

	public List<Manager> getManagersForClient(Integer clientId, boolean showOnlyActive);
	public Manager getManagerForLogin(Integer clientId, String login);
	public Manager getManager(Integer managerId);
	public ManagerEntity getManagerEntity(Integer managerId);
	public int insertManager(Manager manager, Integer auditorId);
	public int updateManager(Manager manager, Integer auditorId);
	public int deleteManager(Integer managerId, Integer noOfChanges, Integer auditorId);
	public int updateManagerPwd(Integer managerId, String newPwd, String pwdHint, Integer noOfChanges, Integer auditorId);
	public int updateManagerSecretWord(Integer managerId, String newSecretWord, Integer noOfChanges, Integer auditorId);
	public int updateManagerShowPageHelp(Manager manager, Integer auditorId);

}
