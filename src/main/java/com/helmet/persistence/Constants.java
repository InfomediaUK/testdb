package com.helmet.persistence;

public final class Constants
{
  public static final int ASCENDING                            = 0;
  public static final int DESCENDING                           = 1;
  /**
   * Email definitions.
   */
  public static final int emailTypeHTML                             = 0;
  public static final int emailTypeText                             = 1;
  public static final String emailSQLFileExtension                  = "sql";
  public static final String emailHTMLFileExtension                 = "htm";
  public static final String emailTextFileExtension                 = "txt";
  public static final String emailHTMLMergeColumnsFileExtension     = "mrghtm";
  public static final String emailTextMergeColumnsFileExtension     = "mrgtxt";
  public static final String emailContentTypeTextHtml               = "text/html";
  public static final String emailContentTypeTextPlain              = "text/plain";
  public static final int    emailToColumnNumber                    = 1;
  public static final int    emailTypeColumnNumber                  = 2;
  /**
   * Id definitions.
   */
  public static final String action                                 = "action";
  public static final String add                                    = "add";
  public static final String administrator                          = "administrator";
  public static final String cancel                                 = "cancel";
  public static final String delete                                 = "delete";
  public static final String edit                                   = "edit";
  public static final String doneURL                                = "doneURL";
  public static final String help                                   = "help";
  public static final String historyTableSuffix                     = "HISTORY";
  public static final String id                                     = "id";
  public static final String insert                                 = "insert";
  public static final String interceptedAction                      = "interceptedAction";
  public static final String mode                                   = "mode";
  public static final String newRecord                              = "new";
  public static final String noneOption                             = "-----";
  public static final String noOfChanges                            = "noOfChanges";
  public static final String now                                    = "now";
  public static final String today                                  = "today";
  public static final String oldRecord                              = "old";
  public static final String off                                    = "off";
  public static final String on                                     = "on";
  public static final String pageNo                                 = "pageno";
//  public static final String actionMappingURLPattern                = "/action/";
  public static final String remove                                 = "remove";
  public static final String servletMappingURLPattern               = "/servlet/";
  public static final String sessionIdURLPattern                    = ";jsessionid=";
  public static final String superUser                              = "superUser";
  public static final String start                                  = "start";
  public static final String startNow                               = "startnow";
  public static final String unique                                 = "unique";
  public static final String updateAction                           = "updateAction";

  public static final String sqlFalse                               = "F";
  public static final String sqlFemale                              = "F";
  public static final String sqlMale                                = "M";
  public static final String sqlMarried                             = "M";
  public static final String sqlNull                                = "null";
  public static final char   sqlParameterChar                       = '^';
  public static final char   sqlQuote                               = '\'';
  public static final String sqlSingle                              = "S";
  public static final String sqlTrue                                = "T";
  public static final String sqlOrderByASC                          = "ASC";
  public static final String sqlOrderByDESC                         = "DESC";
  public static final String sqlWildCard                            = "%";

  /**
   * Timer Task Name definitions.
   */
  public static final String appFilesPath                            = "appFilesPath";
  public static final String backupTimerTaskName                     = "Backup";
  public static final String backupStartTime                         = "backupStartTime";
  public static final String backupPeriodMinutes                     = "backupPeriodMinutes";
  public static final String connectionPoolName                      = "connectionPoolName";
  public static final String adminEmailAddress                       = "adminEmailAddress";
  /**
   * Misc definitions.
   */
  public static final String logFileDateTimeFormat                  = "dd MMM yyyy HH:mm:ss.SSS";
  public static final String cr                                     = "\r";
  public static final String crLf                                   = "\r\n";
  public static final String htmlBreak                              = "<br>";


  public static final String backupDirectory                        = "backup/";
  public static final String emailDirectory                         = "email/";
  public static final String logDirectory                           = "log/";

  public static final String nameValueClassName                     = "net.infomediauk.common.NameValue";
  public static final String intValueClassName                      = "net.infomediauk.common.IntValue";
  public static final String longValueClassName                     = "net.infomediauk.common.LongValue";

  public static final String httpProtocol                           = "http://";
  public static final String httpsProtocol                          = "https://";
  public static final String mailToProtocol                         = "mailto:";

  public static final String equalsSign                             = "=";
  public static final String ampersand                              = "&";
  public static final String doubleQuote                            = "\"";
  public static final String percentSign                            = "%";
  public static final String questionMark                           = "?";
  public static final String slash                                  = "/";
  public static final String space                                  = " ";
  public static final String colon                                  = ":";
  public static final String comma                                  = ",";
  public static final String commaSpace                             = ", ";
  public static final String dot                                    = ".";
  public static final String hyphen                                 = "-";
  public static final String spaceHyphenSpace                       = " - ";
  public static final String leftBracket                            = "(";
  public static final String pipe                                   = "|";
  public static final String rightBracket                           = ")";
  public static final String semiColon                              = ";";
  public static final String tilde                                 = "~";
  public static final String emptyString                            = "";
  public static final String mm                                     = "mm";
  public static final String n                                      = "n";
  public static final String y                                      = "y";
  public static final String yyyy                                   = "yyyy";
  public static final String TRUE                                   = "true";
  public static final String FALSE                                  = "false";
  public static final String timeStartOfDay                         = "00:00:00";
  public static final String timeEndOfDay                           = "23:59:59";
  public static final String startTime                              = "startTime";
  public static final String endTime                                = "endTime";
  public static final String prospectLogin                          = "prospect";
}
