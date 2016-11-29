package com.helmet.bean.nhs;

import java.math.BigDecimal;

import com.helmet.bean.Client;

public class ClientPayment
{
  private Client client;
  private BigDecimal payment;

  public ClientPayment(Client client, BigDecimal payment)
  {
    super();
    this.client = client;
    this.payment = payment;
  }

  public Client getClient()
  {
    return client;
  }

  public void setClient(Client client)
  {
    this.client = client;
  }

  public BigDecimal getPayment()
  {
    return payment;
  }

  public void setPayment(BigDecimal payment)
  {
    this.payment = payment;
  }

  public String toString()
  {
    StringBuffer result = new StringBuffer();
    result.append("ClientPayment:");
    result.append(" ");
    result.append("client.name=");
    result.append(client.getName());
    result.append(" ");
    result.append("payment=");
    result.append(payment);
    result.append("\n");
    return result.toString();
  }
}
