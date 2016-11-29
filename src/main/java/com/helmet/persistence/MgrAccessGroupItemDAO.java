package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.MgrAccessGroupItem;
import com.helmet.bean.MgrAccessGroupItemUser;

public interface MgrAccessGroupItemDAO {

	public List<MgrAccessGroupItemUser> getMgrAccessGroupItemUsersForMgrAccessGroup(Integer mgrAccessGroupId);
	public int insertMgrAccessGroupItem(MgrAccessGroupItem mgrAccessGroupItem, Integer auditorId);
	public int deleteMgrAccessGroupItem(Integer mgrAccessGroupItemId, Integer noOfChanges, Integer auditorId);

}
