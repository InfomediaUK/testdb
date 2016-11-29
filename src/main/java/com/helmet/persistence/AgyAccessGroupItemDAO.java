package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.AgyAccessGroupItem;
import com.helmet.bean.AgyAccessGroupItemUser;

public interface AgyAccessGroupItemDAO {

	public List<AgyAccessGroupItemUser> getAgyAccessGroupItemUsersForAgyAccessGroup(Integer agyAccessGroupId);
	public int insertAgyAccessGroupItem(AgyAccessGroupItem agyAccessGroupItem, Integer auditorId);
	public int deleteAgyAccessGroupItem(Integer agyAccessGroupItemId, Integer noOfChanges, Integer auditorId);

}
