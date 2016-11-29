package com.helmet.reporting;

import java.io.File;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.beanutils.ResultSetDynaClass;
import org.apache.commons.beanutils.RowSetDynaClass;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XMLGenerator extends JdbcDaoSupport {
	/**
	 * @label singleton
	 */
	private static XMLGenerator xmlGenerator;

	HashMap queries;
	
	String fileName;
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Get instance of the XMLGenerator object. If it does not yet exist, create
	 * it.
	 */
	public static XMLGenerator getInstance() {
		if (xmlGenerator == null) {
			// NOT instantiated yet so create it.
			synchronized (XMLGenerator.class) {
				// Only ONE thread at a time here!
				if (xmlGenerator == null) {
					xmlGenerator = new XMLGenerator();
				}
			}
		}
		return xmlGenerator;
	}

	/**
	 * Empty constructor.
	 */
	public XMLGenerator() {
		queries = new HashMap();
	}

	public void init() {
    	loadQueries(fileName);
	}

	private void loadQueries(String fileName) {

		try {

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File(fileName));

			// normalize text representation
			doc.getDocumentElement().normalize();
//			System.out.println("Root element of the doc is "
//					+ doc.getDocumentElement().getNodeName());

			NodeList listOfQueries = doc.getElementsByTagName("query");
			int totalQueries = listOfQueries.getLength();
//			System.out.println("Total no of queries : " + totalQueries);

			for (int s = 0; s < listOfQueries.getLength(); s++) {

                QueryData qd = new QueryData();

				Node queryNode = listOfQueries.item(s);
				if (queryNode.getNodeType() == Node.ELEMENT_NODE) {

					Element queryElement = (Element) queryNode;

					String queryId = queryElement.getAttribute("id");
					qd.setId(queryId);
					String queryName = queryElement.getAttribute("name");
					qd.setName(queryName);
					String queryDesc = queryElement.getAttribute("desc");
					qd.setDesc(queryDesc);

					NodeList sqlList = queryElement.getElementsByTagName("sql");
					Element sqlElement = (Element) sqlList.item(0);
					NodeList textSQLList = sqlElement.getChildNodes();
					String sql = ((Node) textSQLList.item(0)).getNodeValue().trim();
					qd.setSql(sql);
					NodeList paramsList = queryElement.getElementsByTagName("params");
					if (paramsList.getLength() > 0) {
						Element paramsElement = (Element) paramsList.item(0);
						NodeList paramList = paramsElement.getElementsByTagName("param");
						qd.setParams(new String[paramList.getLength()]);
						for (int p = 0; p < paramList.getLength(); p++) {
							Element paramElement = (Element) paramList.item(p);
							String paramName = paramElement.getAttribute("name");
							String paramTitle = paramElement.getAttribute("title");
							String paramType = paramElement.getAttribute("type");
//							System.out.println("Param : " + paramName + " " + paramTitle + " " + paramType);
							qd.getParams()[p] = paramName;
						}
					}
					else {
						qd.setParams(new String[0]);
					}

					queries.put(queryId, qd);

				}// end of if clause

			}// end of for loop with s var

		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line "
					+ err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
		}


	}


	public String[] getParamNames(String id) {
		  QueryData qd = (QueryData)queries.get(id);
		  return qd.getParams();
		}

	public String getSQL(String id, String[] paramValues) {
		  QueryData qd = (QueryData)queries.get(id);
		  String sql = qd.getSql();
		  String[] params = qd.getParams();
		  for (int i = 0; i < paramValues.length; i++) {
		  	sql = sql.replaceAll(params[i], paramValues[i]);
		  }
		  return sql;
	}

  /**
   * Defaults to show names of columns.
   * 
   * @param id
   * @param paramValues
   * @return
   */
  public String getDataAsString(String id, String[] paramValues) 
  {
    return getDataAsString(id, paramValues, true);
  }
  
	public String getDataAsString(String id, String[] paramValues, boolean showNames) 
  {
		String sql = getSQL(id, paramValues);
		Document document = getDataAsDocument(sql, showNames);
		return convertDocumentToString(document);
	}

	public String getDataAsString() {
		Document document = getDataAsDocument(null, true);
		return convertDocumentToString(document);
	}

	private String convertDocumentToString(Document document) {

		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = tFactory.newTransformer();
		} catch (TransformerConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		DOMSource source = new DOMSource(document);
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sw.toString();
	}

	private Document getDataAsDocument(String sql, boolean showNames) 
  {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = null;
    try
    {
      builder = factory.newDocumentBuilder();
    }
    catch (ParserConfigurationException e1)
    {
      // TODO Auto-generated catch block
      e1.printStackTrace();
      return null;
    }
    Document document = builder.newDocument();

    Element root = document.createElement("resultset");
    document.appendChild(root);

    Connection conn = getConnection();

    try
    {

      ResultSet rs = getResultSet(conn, sql);

      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      String[] columnNames = new String[columnCount];

      Element names = document.createElement("names");
      for (int i = 0; i < columnCount; i++)
      {
        /* the first column is 1, the second is 2, ... */
        columnNames[i] = rsmd.getColumnName(i + 1);
        Element nextNameNode = document.createElement("name");
        Text nextName = document.createTextNode(columnNames[i]);
        nextNameNode.appendChild(nextName);
        names.appendChild(nextNameNode);
      }

      if (showNames)
      {
        // Add names element to root node.
        root.appendChild(names);
      } 
      
      /* Move the cursor through the data one row at a time. */
      while (rs.next())
      {
        /* Create an Element node for each row of data. */
        Element nextRow = document.createElement("row");
        for (int i = 0; i < columnCount; i++)
        {
          /* Create an Element node for each column value. */
          Element nextNode = document.createElement(columnNames[i]);
          /* the first column is 1, the second is 2, ... */
          /* getString() will retrieve any of the basic SQL types */

          String x = rs.getString(i + 1);
          // Unsure about this bit !!!
          x = (x == null ? "null" : x);
          // System.out.println(x);

          Text text = document.createTextNode(x);
          nextNode.appendChild(text);
          nextRow.appendChild(nextNode);
        }
        root.appendChild(nextRow);
        // rows.appendChild(nextRow);
      }

      // Add rows element to root node.
      // root.appendChild(rows);

    }
    catch (SQLException e)
    {
      System.out.println("SQL Exception: " + e.getMessage());
      showSQLException(e);
      return null;
    }
    finally
    {

    }

    // Before we close the connection, let's rollback any changes we may have
    // made.
    try
    {
      if (conn != null) conn.rollback();
    }
    catch (java.sql.SQLException e)
    {
      showSQLException(e);
    }
    try
    {
      if (conn != null) conn.close();
    }
    catch (java.sql.SQLException e)
    {
      showSQLException(e);
    }

    return document;
  }

	public ResultSetData getResultSetData(String id, String[] paramValues) {
		
		String sql = getSQL(id, paramValues);
		ResultSetData resultSetData = getResultSetData(sql);
		return resultSetData;

	}
	
	private ResultSetData getResultSetData(String sql) {

		Connection conn = getConnection();

		ResultSetData resultSetData = new ResultSetData();
		
		try {

			ResultSet rs = getResultSet(conn, sql);

	    	resultSetData.setDynaBeans(new RowSetDynaClass(rs, false));
	    	resultSetData.setDynaProperties(new ResultSetDynaClass(rs, false).getDynaProperties());

		} catch (SQLException e) {
			System.out.println("SQL Exception: " + e.getMessage());
			showSQLException(e);
			return null;
		}
		finally {

		}

		// Before we close the connection, let's rollback any changes we may have made.
		try {
			if (conn != null)
				conn.rollback();
		} catch (java.sql.SQLException e) {
			showSQLException(e);
		}
		try {
			if (conn != null)
				conn.close();
		} catch (java.sql.SQLException e) {
			showSQLException(e);
		}

		return resultSetData;
	}


//	private Connection getConnection() {
//    	Connection conn = null;
//        try {
//			conn = DatabaseServer.getDatabaseConnection();
//		} catch (DatabaseAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return conn;
//	}
//
//	private void putConnection(Connection conn) {
//        DatabaseServer.dropDatabaseConnection(conn);
//	}

    private Connection getConnectionHardCoded() {

    	Connection conn = null;

//		String databaseURL = "jdbc:firebirdsql://127.0.0.1/c:/Database/commerce.gdb";
//		String user = "ADMINDBA";
//		String password = "admindba";
//		String driverName = "org.firebirdsql.jdbc.FBDriver";

		String databaseURL = "jdbc:postgresql://puri.gointernet.co.uk:5432/sbdev";
		String user = "gopg";
		String password = "N0m1nat3";
		String driverName = "org.postgresql.Driver";

		try {
//			System.out.println("Try to get a Connection");

			Class.forName(driverName);
			try {
				conn = java.sql.DriverManager.getConnection(databaseURL, user,
						password);
//				System.out.println("Connection established.");
			} catch (java.sql.SQLException e) {
//				System.out
//						.println("Unable to establish a connection through the driver manager.");
				showSQLException(e);
				return null;
			}

			// Let's disable the default autocommit so we can undo our changes later...
			try {
				conn.setAutoCommit(false);
//				System.out.println("Auto-commit is disabled.");
			} catch (java.sql.SQLException e) {
//				System.out.println("Unable to disable autocommit.");
				showSQLException(e);
				return null;
			}
		} catch (java.lang.ClassNotFoundException e) {
			// A call to Class.forName() forces us to consider this exception :-)...
//			System.out.println("InterClient not found in class path");
			System.out.println(e.getMessage());
		}
		return conn;
	}

	private ResultSet getResultSet(Connection conn, String sql) throws SQLException {

		// Create a Statement object.
		Statement stmt = conn.createStatement();
		// Execute an SQL query and get a Result Set object.
        if (sql == null) {

			StringBuffer query = new StringBuffer();

			query.append("SELECT ");
			query.append("COMPANYNAME, ");
			query.append("COMPANYTRADINGNAME AS tradingName, ");
			query.append("COMPANYINARCADE, ");
			query.append("COMPANYLASTORDACKTIME, ");
			query.append("COUNT(companyid) as c, ");
			query.append("sum(companyid) as s ");
			query.append("FROM company ");
			query.append("GROUP BY 1,2,3,4");

			//		query.append("SELECT ");
			//        query.append("trim(tuu.username) AS promoter, ");
			//        query.append("cs.name AS clicksource, ");
			//        query.append("c.timestamp AS timestamp, ");
			//        query.append("c.value AS value, ");
			//        query.append("'sb'||c.visibleelmt_id AS uniqueid, ");
			//        query.append("ccp.authcode AS authcode, ");
			//        query.append("trim(u.username) AS username ");
			//        query.append("FROM ");
			//        query.append("  gwccredit c ");
			//        query.append("    LEFT JOIN gwcccpayment ccp ON ccp.visibleelmt_id = c.visibleelmt_id, ");
			//        query.append("  gwcaccount a, ");
			//        query.append("  gwcuser u  ");
			//        query.append("    LEFT JOIN gwcclicksource cs ON cs.clicksource_id = u.clicksource_id ");
			//        query.append("    LEFT JOIN gwctrafficuser tu ON tu.trafficuser_id = cs.trafficuser_pro_id ");
			//        query.append("    LEFT JOIN gwcuser tuu       ON tuu.user_id = tu.user_id ");
			//        query.append("WHERE  ");
			//        query.append("c.timestamp BETWEEN ");
			//        query.append("'2005-05-01 00:00:00.000' AND CURRENT_TIMESTAMP ");
			//        query.append("AND a.account_id = c.account_id ");
			//        query.append("AND u.user_id = a.user_id ");
			//        query.append("ORDER BY promoter, clicksource, timestamp, value, authcode, username ");

			//		query += "SELECT ";
			//		query += "    cs.name as click, count(u.user_id) as count ";
			//		query += "    from gwcusg usg, gwcusgentry usge, gwcuser u ";
			//		query += "        LEFT JOIN gwcclicksource cs ON cs.clicksource_id = u.clicksource_id ";
			//		query += "    where usg.name = 'Member' ";
			//		query += "    and usge.usg_id = usg.usg_id ";
			//		query += "    and u.user_id = usge.user_id ";
			//		query += "    and u.user_id in ";
			//		query += "    ( ";
			//		query += "     select a.user_id ";
			//		query += "     from gwcaccount a, gwccredit c ";
			//		query += "     where c.account_id = a.account_id ";
			//		query += "    ) ";
			//		query += "    group by 1 ";

			//		query += "select username, count(user_id) from gwcuser group by 1";
			//
			//query += "select u.title, count(*) ";
			//query += "from gwcusg usg, gwcusgentry usge, gwcuser u, gwcclicksource cs ";
			//query += "where usg.name = 'Member' ";
			//query += "and usge.usg_id = usg.usg_id ";
			//query += "and u.user_id = usge.user_id ";
			//query += "and cs.clicksource_id = u.clicksource_id ";
			//query += "and cs.name = 'cliterati' ";
			//query += "group by 1 ";
			//query += "order by 2 desc ";
			//
			//		query += "select 1 as sort, 'credits' as type, sum(value) as value ";
			//		query += "from gwccredit ";
			//		query += "union ";
			//		query += "select 2 as sort, 'debits' as type, sum(value) as value ";
			//		query += "from gwcdebit ";
			//		query += "union ";
			//		query += "select 3 as sort, 'unspent vouchers' as type, sum(value) as value ";
			//		query += "from gwcvoucher ";
			//		query += "where debit_id is null ";
			//		query += "union ";
			//		query += "select 4 as sort, 'account balances' as type, sum(balance) as value ";
			//		query += "from gwcaccount ";
			//		query += "order by 1 ";
			//		query += "; ";

			sql = query.toString();
        }

		ResultSet rs = stmt.executeQuery(sql);

		return rs;
	}

	// Display an SQLException which has occured in this application.
	private void showSQLException(java.sql.SQLException e) {
		// Notice that a SQLException is actually a chain of SQLExceptions,
		// let's not forget to print all of them...
		java.sql.SQLException next = e;
		while (next != null) {
			System.out.println(next.getMessage());
			System.out.println("Error Code: " + next.getErrorCode());
			System.out.println("SQL State: " + next.getSQLState());
			next = next.getNextException();
		}
	}

}