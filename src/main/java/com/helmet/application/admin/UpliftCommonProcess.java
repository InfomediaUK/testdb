package com.helmet.application.admin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.helmet.api.AdminService;
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.Uplift;
import com.helmet.bean.UpliftMinute;


public abstract class UpliftCommonProcess extends AdminAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  protected Integer insertOrReactivateUpliftMinutes(AdminService adminService, Uplift uplift)
  {
    List<UpliftMinute> listUpliftMinute = adminService.getUpliftMinutesForUplift(uplift.getUpliftId(), false);
    if (listUpliftMinute.size() == 0)
    {
      // No UpliftMinutes exist for this Uplift yet. So insert UpliftMinutes.
      return insertUpliftMinutes(adminService, uplift);
    }
    else
    {
      return reactivateUpliftMinutes(adminService, listUpliftMinute, uplift);
    }
  }
  
  protected Integer insertUpliftMinutes(AdminService adminService, Uplift uplift)
  {
    int rowCount = 0;
    int minute = 0;
    UpliftMinute upliftMinute =  new UpliftMinute();
    upliftMinute.setUpliftId(uplift.getUpliftId());
    upliftMinute.setUpliftFactor(uplift.getUpliftFactor());
    upliftMinute.setUpliftValue(uplift.getUpliftValue().divide(new BigDecimal(60 / uplift.getUpliftMinutePeriod()), 5, RoundingMode.HALF_UP));
    while (minute < 60)
    {
      upliftMinute.setUpliftMinute(minute);
      rowCount += adminService.insertUpliftMinute(upliftMinute, getAdministratorLoggedIn().getAdministratorId());
      minute += uplift.getUpliftMinutePeriod();
    }
    return rowCount;
  }
  
  protected Integer reactivateUpliftMinutes(AdminService adminService, List<UpliftMinute> listUpliftMinute, Uplift uplift)
  {
    int rowCount = 0;
    for (UpliftMinute upliftMinute : listUpliftMinute)
    {
      upliftMinute.setUpliftFactor(uplift.getUpliftFactor());
      upliftMinute.setUpliftValue(uplift.getUpliftValue().divide(new BigDecimal(60 / uplift.getUpliftMinutePeriod()), 5, RoundingMode.HALF_UP));
      // Note. This Update sets the record to ACTIVE.
      rowCount += adminService.updateUpliftMinute(upliftMinute, getAdministratorLoggedIn().getAdministratorId());
    }
    return rowCount;
  }

  protected Integer deleteUpliftMinutes(AdminService adminService, Uplift uplift)
  {
    int rowCount = 0;
    List<UpliftMinute> listUpliftMinute = adminService.getUpliftMinutesForUplift(uplift.getUpliftId());
    for (UpliftMinute upliftMinute : listUpliftMinute)
    {
      rowCount += adminService.deleteUpliftMinute(upliftMinute.getUpliftMinuteId(), upliftMinute.getNoOfChanges(), getAdministratorLoggedIn().getAdministratorId());
    }
    return rowCount;
  }

}
