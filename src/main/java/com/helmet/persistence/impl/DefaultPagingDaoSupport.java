package com.helmet.persistence.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;


public class DefaultPagingDaoSupport extends JdbcDaoSupport 
{
  private Integer pagingLimit;
  private Integer pagingGroupSize;

  public Integer getPagingGroupSize()
  {
    return pagingGroupSize;
  }

  public void setPagingGroupSize(Integer pagingGroupSize)
  {
    this.pagingGroupSize = pagingGroupSize;
  }

  public Integer getPagingLimit()
  {
    return pagingLimit;
  }

  public void setPagingLimit(Integer pagingLimit)
  {
    this.pagingLimit = pagingLimit;
  }

}
