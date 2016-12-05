package com.helmet.xml.jaxb.model.tradeshift;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ConnectionList")
public class ConnectionList
{
  private List<Connection> connections;
  
  @XmlElement(name = "Connection")
  public List<Connection> getConnections()
  {
    return connections;
  }

  public void setConnections(List<Connection> connections)
  {
    this.connections = connections;
  }

  public String toString()
  {
    StringBuffer result = new StringBuffer();
    result.append("connections:\n");
    for (Connection connection : connections)
    {
      result.append("[");
      result.append(connection.toString());
      result.append("]\n");
    }
    return  result.toString();
  }
}
