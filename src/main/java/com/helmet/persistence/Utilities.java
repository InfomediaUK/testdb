package com.helmet.persistence;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.StringCharacterIterator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

public final class Utilities
{

  public static final String ifNull(String in, String replacementValue)
  {
    if (in == null || in.equals(StringUtils.EMPTY))
    {
      return replacementValue;
    }
    return in;
  }

  public static final void replaceAndQuoteNullable(StringBuffer line, String value)
  {
    // Default to nullable.
    replaceAndQuote(line, value, true, false);
  }

  public static final void replaceAndQuoteUpperNullable(StringBuffer line, String value)
  {
    // Default to nullable.
    replaceAndQuote(line, value, true, true);
  }

  public static final void replaceAndQuote(StringBuffer line, String value)
  {
    // Default to NOT nullable.
    replaceAndQuote(line, value, false, false);
  }

  public static final void replaceAndQuoteNullable(StringBuffer line, Date value)
  {
    // Default to nullable.
    replaceAndQuote(line, value == null ? null : value.toString(), true, false);
  }

  public static final void replaceAndQuote(StringBuffer line, Date value)
  {
    // Default to NOT nullable.
    replaceAndQuote(line, value == null ? "" : value.toString(), false, false);
  }
  
  public static final void replaceAndQuoteUpper(StringBuffer line, String value)
  {
    // Default to NOT nullable.
    replaceAndQuote(line, value, false, true);
  }

  private static final void replaceAndQuote(StringBuffer line, String value, boolean nullable, boolean forceUpperCase)
  {
    int index = 0;
    while (line.charAt(index) != Constants.sqlParameterChar)
    {
      index++;
    }
    if ((value == null || value.equals(StringUtils.EMPTY)) && nullable)
    {
      // Empty value that is nullable. Set to null.
      line = line.replace(index, index + 1, Constants.sqlNull);
    }
    else
    {
      // Non-empty or not nullable value. Wrap value in single quotes.

      // Add the leading sqlQuote.
      line = line.replace(index, index + 1, String.valueOf(Constants.sqlQuote));

      // Create a new StringCharacterIterator based on the value passed in.
      StringCharacterIterator sci = null;
      if (forceUpperCase)
      {
        // Force Uppercase.
        sci = new StringCharacterIterator(value.toUpperCase());
      }
      else
      {
        // Just leave the case alone.
        sci = new StringCharacterIterator(value);
      }

      // Define a variable to hold the number of additional sqlQuote characters added.
      int i = 0;
      // Loop thru the String value passed in.
      for(char c = sci.first(); c != StringCharacterIterator.DONE; c = sci.next())
      {
        if (c == Constants.sqlQuote)
        {
          // sqlQuote has been found. Insert an addition sqlQuote and increment the additional characters variable.
          line = line.insert(index + 1 + i + sci.getIndex(), Constants.sqlQuote);
          i++;
        }
        // Insert the character into the output StringBuffer.
        line = line.insert(index + 1 + i + sci.getIndex(), c);
      }

      // Add the trailing sqlQuote.
      line = line.insert(index + 1 + i + value.length(), Constants.sqlQuote);
    }
  }

  public static final void replaceZeroWithNull(StringBuffer line, Integer value)
  {
    if (value == null) {
	  replaceZeroWithNull(line, 0);
    }
    else {
      replaceZeroWithNull(line, value.toString());
    }
  }

  public static final void replace(StringBuffer line, Integer value)
  {
    if (value == null) {
  	  replaceZeroWithNull(line, 0);
    }
    else {
	  replace(line, value.toString());
    }
  }

  public static final void replaceZeroWithNull(StringBuffer line, BigDecimal value)
  {
    if (value == null) {
	  replaceZeroWithNull(line, 0);
    }
    else {
  	  if (value.compareTo(new BigDecimal("0")) == 0) {
		replaceZeroWithNull(line, "0");
	  }
  	  else {
	    replaceZeroWithNull(line, value.toString());
  	  }
    }
  }
  
  public static final void replace(StringBuffer line, BigDecimal value)
  {
	if (value == null) {
	  replaceZeroWithNull(line, 0);
    }
	else {
	  replace(line, value.toString());
	}
  }

  public static final void replaceZeroWithNull(StringBuffer line, int value)
  {
    replaceZeroWithNull(line, Integer.toString(value));
  }

  public static final void replaceZeroWithNull(StringBuffer line, float value)
  {
    replaceZeroWithNull(line, Float.toString(value));
  }

  public static final void replaceZeroWithNull(StringBuffer line, long value)
  {
    replaceZeroWithNull(line, Long.toString(value));
  }

  public static final void replaceZeroWithNull(StringBuffer line, String value)
  {
    // If value zero replace with blank which will be replaced with null in replace method.
    if (value.equals("0") || value.equals("0.0"))
    {
      value = StringUtils.EMPTY;
    }
    replace(line, value);
  }

  public static final void replace(StringBuffer line, Boolean value) {
	  if (value == null) {
		  replaceZeroWithNull(line, 0);
	  }
	  else {
		  replace(line, value.booleanValue());
	  }
  }

  public static final void replace(StringBuffer line, boolean value)
  {
    String s = Constants.sqlFalse;
    if (value)
    {
      s = Constants.sqlTrue;
    }
    // Default to NOT nullable and unchanged case.
    replaceAndQuote(line, s, false, false);
  }

  public static final void replace(StringBuffer line, int value)
  {
    replace(line, Integer.toString(value));
  }

  public static final void replace(StringBuffer line, long value)
  {
    replace(line, Long.toString(value));
  }

  public static final void replace(StringBuffer line, double value)
  {
    replace(line, Double.toString(value));
  }

  public static final void replace(StringBuffer line, String value)
  {
    // Call with nullable parameter set as true so that "" will be replaced with null.
    replace(line, value, true);
  }

  public static final void replace(StringBuffer line, String value, boolean nullable)
  {
    int index = 0;
    while (line.charAt(index) != Constants.sqlParameterChar)
    {
      index++;
    }
    if (value.equals(StringUtils.EMPTY) && nullable)
    {
      // Empty value that is nullable. Set to null.
      line = line.replace(index, index + 1, Constants.sqlNull);
    }
    else
    {
      // Non-empty value.
      line = line.replace(index, index + 1, value);
    }
  }

  public static final boolean replaceSubString(StringBuffer sb, String toReplace, String replaceWith)
  {
    int sbIndex            = 0;
    int toReplaceIndex     = 0;
    boolean toReplaceFound = false;
    while ((toReplaceFound == false) && (sbIndex < (sb.length() - toReplace.length() + 1)))
    {
      while ((toReplaceIndex < toReplace.length()) && sb.charAt(sbIndex + toReplaceIndex) == toReplace.charAt(toReplaceIndex))
      {
        if (toReplaceIndex == toReplace.length() - 1)
        {
          toReplaceFound = true;
          break;
        }
        toReplaceIndex++;
      }
      if (toReplaceFound == false)
      {
        sbIndex++;
        toReplaceIndex = 0;
      }
    }
    if (toReplaceFound)
    {
      sb.replace(sbIndex, (sbIndex + toReplaceIndex + 1), replaceWith);
    }
    return toReplaceFound;
  }
  /**
   *  countCharsInString. Returns the count of the specified Character found in the specified String.
   */
  public static final int countCharsInString(String stringToSearch, char charToFind)
  {
    int count = 0;
    for (int i = 0; i < stringToSearch.length(); i++)
    {
      if (stringToSearch.charAt(i) == charToFind)
      {
        count++;
      }
    }
    return count;
  }
  /**
   *  replaceCharsInString. Returns the stringToSearch with the specified Character replaced with a specified Character.
   */
  public static final String replaceCharsInString(String stringToSearch, char charToFind, char charForReplace)
  {
    return replaceCharsInString(stringToSearch, charToFind, charForReplace, -1);
  }
  /**
   *  replaceCharsInString. Returns the stringToSearch with the specified Character replaced with a specified Character.
   */
  public static final String replaceCharsInString(String stringToSearch, char charToFind, char charForReplace, int maxTokens)
  {
    if (stringToSearch == null)
    {
      return stringToSearch;
    }
    else
    {
      int tokenCount = 0;
      StringBuffer newString = new StringBuffer(stringToSearch);
      for (int i = 0; i < newString.length(); i++)
      {
        if (newString.charAt(i) == charToFind)
        {
          newString.setCharAt(i, charForReplace);
          if (maxTokens > 0)
          {
            tokenCount++;
            if (tokenCount >= maxTokens)
            {
              if (i < newString.length() - 1)
              {
                newString.replace(i, newString.length(), "...");
                break;
              }
            }
          }
        }
      }
      return newString.toString();
    }
  }
  /**
   * getNextDateTime. Calculates Date Today set to the specified Time, if before Date Now
   * then it increments the Time by the specified, otherwise it returns Date Today
   * set to the specified Time
   *
   * @param time The time in 'hh:mm:ss' format.
   */

/*

  public static final Date getNextDateTime(String time, int period)
  {
//    FastDateFormat dateFormat    = FastDateFormat.getInstance("dd MM yyyy hh:mm:ss");
    Calendar calendarTime = TimeZoneConverter.getCalendar();
    calendarTime.setTime(getDateTime(time));
    Calendar calendarNow = TimeZoneConverter.getCalendar();
    calendarNow.setTime(new Timestamp(TimeZoneConverter.getNow()));
//    System.out.println(dateFormat.format(calendarNow.getTime()));
    while (calendarTime.before(calendarNow) && period > 0)
    {
      // Time is before Now. Add period to it.
      calendarTime.add(Calendar.MILLISECOND, period);
//      System.out.println(dateFormat.format(calendarTime.getTime()));
    }
    return calendarTime.getTime();
  }

*/
  
  /**
   * getDateTime. Returns Date Today set to the specified Time.
   *
   * @param time The time in 'hh:mm:ss' format.
   */

/*

  public static final Date getDateTime(String time)
  {
    return getDateTime(time, new Date());
  }

*/
  
  /**
   * getDateTime. Returns the specified Date set to the specified Time.
   *
   * @param time The time in 'hh:mm:ss' format.
   * @param date The date.
   */
  
/*

  public static final Date getDateTime(String time, Date date)
  {
    FastDateFormat dateFormat    = FastDateFormat.getInstance("dd MM yyyy hh:mm:ss");
    int hour          = Integer.parseInt(time.substring(0,2));
    int minute        = Integer.parseInt(time.substring(3,5));
    int second        = Integer.parseInt(time.substring(6));
    Calendar calendar = TimeZoneConverter.getCalendar();
    calendar.setTime(date);
//    System.out.println(dateFormat.format(calendar.getTime()));
    calendar.set(Calendar.HOUR_OF_DAY, hour);
    calendar.set(Calendar.MINUTE, minute);
    calendar.set(Calendar.SECOND, second);
//    System.out.println(dateFormat.format(calendar.getTime()));
    return calendar.getTime();
  }

*/
  
  /**
   *  Strip Class Name from full class name. Eg package.class.
   */
  public static String extractClassName(String className)
  {
    return className.substring((className.lastIndexOf('.') + 1));
  }
  /**
   *  Validate the supplied phone number.
   */
  public static boolean validatePhoneNumber(String phoneNumber)
  {
    byte[] buffer = new byte[phoneNumber.length()];
    buffer = phoneNumber.getBytes();
    int goodChar  = 0;
    int byteValue = 0;
    for (int i = 0; i < buffer.length; i++)
    {
      // For each byte...
      // Make sure its value resolves to a suitable ascii character.
      byteValue = buffer[i];
      if (    (byteValue > 47 && byteValue < 58)   // Zero to nine.
           || (byteValue == 43)                    // Plus (+).
           || (byteValue == 45)                    // Hyphen (-).
           || (byteValue == 32)                    // Space.
         )
      {
        // Zero to nine. OK.
        goodChar++;
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  /**
   *  Validate the supplied mobile phone number's length. Must be at least 11 numeric characters.
   */
  public static boolean validateMobilePhoneNumberLength(String phoneNumber)
  {
    byte[] buffer = new byte[phoneNumber.length()];
    buffer = phoneNumber.getBytes();
    int numericChar = 0;
    int byteValue   = 0;
    for (int i = 0; i < buffer.length; i++)
    {
      // For each byte... Count the numeric characters.
      byteValue = buffer[i];
      if (byteValue > 47 && byteValue < 58)
      {
        // Zero to nine. OK.
        numericChar++;
      }
    }
    if (numericChar < 11)
    {
        return false;
    }
    return true;
  }
  /**
   *  Validate the supplied credit card number is numeric. Can have embedded spaces.
   */
  public static boolean validateCreditCardNumberNumeric(String creditCardNumber)
  {
    byte[] buffer = new byte[creditCardNumber.length()];
    buffer = creditCardNumber.getBytes();
    int numericChar = 0;
    int invalidChar = 0;
    int byteValue   = 0;
    for (int i = 0; i < buffer.length; i++)
    {
      // For each byte... Count the numeric characters.
      byteValue = buffer[i];
      if (byteValue > 47 && byteValue < 58)
      {
        // Zero to nine. OK.
        numericChar++;
      }
      else
      {
        // NOT zero to nine. Only Space allowed here.
        if (byteValue != 32)
        {
          // NOT space.
          invalidChar++;
        }
      }
    }
    if ((numericChar == 0) || (invalidChar > 0))
    {
        return false;
    }
    return true;
  }
  /**
   *  Validate the supplied text to be alphabetic only.
   */
  public static boolean validateAlphabetic(String text)
  {
    char c;
    for (int i = 0; i < text.length(); i++)
    {
      c = text.charAt(i) ;
      if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
      {
        // Character is alphabetic.
      }
      else
      {
        // Character NOT alphabetic.
        return false;
      }
    }
    return true;
  }
  /**
   *  Validate the supplied text to be alphabetic or numeric only.
   */
  public static boolean validateAlphaNumeric(String text)
  {
    char c;
    for (int i = 0; i < text.length(); i++)
    {
      c = text.charAt(i) ;
      if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'))
      {
        // Character is alphanumeric.
      }
      else
      {
        // Character NOT alphanumeric.
        return false;
      }
    }
    return true;
  }
  /**
   *  Validate the supplied value to be numeric only.
   */
  public static boolean validateNumeric(String value)
  {
    char c;
    for (int i = 0; i < value.length(); i++)
    {
      c = value.charAt(i) ;
      if ((c >= '0' && c <= '9'))
      {
        // Character is numeric.
      }
      else
      {
        // Character NOT numeric.
        return false;
      }
    }
    return true;
  }
  /**
   *  Validate the supplied value to be numeric, a thousands seperator or a decimal seperator only.
   */
  public static boolean validateCurrency(String value, char thousandsSeperator, char decimalSeperator)
  {
    char c;
    for (int i = 0; i < value.length(); i++)
    {
      c = value.charAt(i) ;
      if ((c == thousandsSeperator) || (c == decimalSeperator) || (c >= '0' && c <= '9'))
      {
        // Character is numeric, a thousands seperator or a decimal seperator.
      }
      else
      {
        // Character NOT numeric, a thousands seperator or a decimal seperator.
        return false;
      }
    }
    if (countCharsInString(value, decimalSeperator) > 1)
    {
      // Too many decimal seperator characters.
      return false;
    }
    return true;
  }
  /**
   *  Validate the supplied email address.
   */
  public static boolean validateEmail(String email)
  {
    int firstDotPos   = email.indexOf('.');
    int atPos         = email.indexOf('@');
    int lastAtPos     = email.lastIndexOf('@');
    int dotAfterAtPos = email.indexOf('.', atPos + 1);
    int lastDotPos    = email.lastIndexOf('.');
    if (    (firstDotPos    <  1)               // Canonot start with '.'.
         || (atPos          <  1)               // Canonot start with '@'.
         || (atPos          != lastAtPos)       // Only one @ allowed.
         || (dotAfterAtPos  - atPos      < 2)   // @ cannot be followed by '.'.
         || (email.length() - lastDotPos < 3)   // Last '.' must be followed by at least two characters.
         || (email.length() - lastDotPos > 5)   // Last '.' must NOT be followed by more than four characters.
       )
    {
      return false;
    }
    // OK so far. Now check for illegal characters.
    char c;
    for (int i = 0; i < email.length(); i++)
    {
      c = email.charAt(i) ;
      if (    (c >= 'A' && c <= 'Z')
           || (c >= 'a' && c <= 'z')
           || (c >= '0' && c <= '9')
           || (c == '-')
           || (c == '_')
           || (c == '.')
           || (c == '@')
           || (c == '\'')
          )
      {
        // Character is valid.
      }
      else
      {
        // Character NOT valid. IE. Not Alphabetic, Numeric, minus, underscore, fullstop or @.
        return false;
      }
    }
    return true;
  }
  /**
   *  Validate the supplied protocol.
   */
  public static boolean validateProtocol(String externalLink)
  {
    if (    (externalLink.startsWith(Constants.mailToProtocol))
         || (externalLink.startsWith(Constants.httpProtocol))
         || (externalLink.startsWith(Constants.httpsProtocol))
        )
    {
      // External link is a mailto. It must have a valid email address.
      return true;
    }
    return false;
  }
  /**
   *  Validate the supplied external link.
   */
  public static boolean validateExternalLink(String externalLink)
  {
    String strippedExternalLink = null;
    if (externalLink.startsWith(Constants.mailToProtocol))
    {
      // External link is a mailto. It must have a valid email address.
      return validateEmail(externalLink.substring(Constants.mailToProtocol.length()));
    }
    // External link is NOT a mailto.
    if (externalLink.startsWith(Constants.httpProtocol))
    {
      // External link specifies http.
      strippedExternalLink = externalLink.substring(Constants.httpProtocol.length());
    }
    if (externalLink.startsWith(Constants.httpsProtocol))
    {
      // External link specifies https.
      strippedExternalLink = externalLink.substring(Constants.httpsProtocol.length());
    }
    if (strippedExternalLink == null)
    {
      // External link does NOT specify http or https.
      return false;
    }
    int firstDotPos   = strippedExternalLink.indexOf('.');
    if (firstDotPos <  1)               // Canonot start with '.'.
    {
      return false;
    }
    return true;
  }

  public static String encryptPassword(String password) {

	String encryptedPassword = password;
	
	try {
		encryptedPassword = encryptToAscii(password);
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return encryptedPassword;
  
  }

  /**
   *  Encrypt the string to an ascii character in range 045 to 122
   */
  public static String encryptToAscii(String toBeEncrypted)
    throws UnsupportedEncodingException, NoSuchAlgorithmException
  {
    byte[] buffer = new byte[toBeEncrypted.length()];
    buffer = toBeEncrypted.getBytes();
    MessageDigest algorithm = MessageDigest.getInstance("MD5");
    algorithm.reset();
    algorithm.update(buffer);
    buffer = algorithm.digest();
    int byteValue = 0;
    for (int i = 0; i < buffer.length; i++)
    {
      // For each byte...
      // Make sure its value resolves to a suitable ascii character 045 to 122.
      byteValue = buffer[i];
      if (byteValue < 0)
      {
        // Negative -128 to -1. Make it positive.
        byteValue = byteValue * -1;
      }
      // Now in range 0 to 128.
      if (byteValue < 45)
      {
        // Less than -.
        byteValue += 45;
      }
      if (byteValue == 94)
      {
        // As ^ is used as a parameter place holder in SQL it can NOT be used here.
        byteValue = 93;
      }
      if (byteValue > 122)
      {
        // Less than z.
        byteValue -= 6;
      }
      buffer[i] = new Integer(byteValue).byteValue();
    }
    // replace '\' as this is saved in the DB as nuffink as it is considered an escape character
    return new String(buffer).replace('\\', '-');
  }

  /**
   * Test whether two hashMap.keySet have the same values.
   */
  public static boolean equalKeys(HashMap sourceKeyMap, HashMap targetKeyMap)
  {
    // Get an iterator of the keys from the sourceKeyMap parameter.
    Iterator keys      = sourceKeyMap.keySet().iterator();
    String key         = null;
    Object sourceValue = null;
    Object targetValue = null;
    boolean match      = true;
    while (keys.hasNext())
    {
      // Store the current key and sourceValue from the keys of the keyMap parameter.
      key = (String)keys.next();
      sourceValue = sourceKeyMap.get(key);
      // Check if the key value of the recordMap is equal to the value of the keyMap parameter.
      targetValue = targetKeyMap.get(key);
      if (
              (targetValue == null)
           || (targetValue != null && !targetValue.equals(sourceValue))
         )
      {
        // Keys do not match so set the boolean property to false and break out.
        match = false;
        break;
      }
    }
    return match;
  }

/*

  // Open named file and return the contents.
  public static String openFile(String fileName)
    throws LoggedException
  {
    try
    {
      // Open a file of the given name.
      File file = new File(fileName);

      // Get the size of the opened file.
      int size = (int)file.length();

      // Set to zero a counter for counting the number of
      // characters that have been read from the file.
      int chars_read = 0;

      // Create an input reader based on the file, so we can read its data.
      // FileReader handles international character encoding conversions.
      FileReader in = new FileReader(file);
      // Create a character array of the size of the file,
      // to use as a data buffer, into which we will read
      // the text data.
      char[] data = new char[size];
      try
      {
        // Read all available characters into the buffer.
        while(in.ready())
        {
          // Increment the count for each character read,
          // and accumulate them in the data buffer.
          chars_read += in.read(data, chars_read, size - chars_read);
        }
      }
      finally
      {
        in.close();
      }
      return new String(data, 0, chars_read);
    }
    catch (IOException e)
    {
      throw ExceptionHandler.getInstance().logException(e, "Utilities", "openFile() - " + fileName);
    }
  }

*/
  
/*  
  
  // Save named file with the contents.
  public static void saveFile(String fileName, String contents)
    throws LoggedException
  {
    try
    {
      // Open a file of the current name.
      File file = new File (fileName);

      // Create an output writer that will write to that file.
      // FileWriter handles international characters encoding conversions.
      FileWriter out = new FileWriter(file);
      try
      {
        out.write(contents);
      }
      finally
      {
        out.close();
      }
    }
    catch (IOException e)
    {
      throw ExceptionHandler.getInstance().logException(e, "Utilities", "saveFile() - " + fileName);
    }
  }

*/
    
  /**
   * Get the Start Time of the supplied Year/Month.
   */
  
/*

  public static Date getStartTimeForYearMonth(int year, int month)
  {
    Calendar calendar = TimeZoneConverter.getCalendar();
    calendar.setLenient(false);
    // Set to start of month.
    calendar.set(Calendar.DATE, 1);
    calendar.set(Calendar.MONTH, (month - 1));
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
  }

*/
  
  /**
   * Get the End Time of the supplied Year/Month.
   */
  
/*

  public static Date getEndTimeForYearMonth(int year, int month)
  {
    Calendar calendar = TimeZoneConverter.getCalendar();
    calendar.setLenient(false);
    // Set to end of first day of next month.
    calendar.set(Calendar.DATE, 1);
    if (month == 12)
    {
      month = 0;
      year++;
    }
    calendar.set(Calendar.MONTH, month);
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    calendar.set(Calendar.MILLISECOND, 999);
    // Back up one day.
    calendar.add(Calendar.DATE, -1);
    return calendar.getTime();
  }

*/
  
  /**
   * Uses the supplied int values and current Timestamp to create from/to Timestamp objects holding the start and end times
   * of the range.
   */
  
/*  
  
  public static void getTimeRange(int period, int interval, int offsetPeriod, int offsetInterval, Timestamp currentTimestamp, Timestamp fromTimestamp, Timestamp toTimestamp)
  {
    // An period or offset has been set.
    Calendar calendar = TimeZoneConverter.getCalendar();
    calendar.setLenient(false);
    // Set the calendar time to the 'current' time.
    calendar.setTime(currentTimestamp);
    // Set the Time elements to zero so we are at the start of the day.
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    if (offsetPeriod != 0 && offsetInterval != 0)
    {
      // Shift the from time by the offset.
      calendar.add(offsetPeriod, offsetInterval);

    }
    fromTimestamp.setTime(calendar.getTime().getTime());
    // Add the interval.
    calendar.add(period, interval);
    // Subtract 1 second from the calendar date so we are at the end of the previous day.
    calendar.add(Calendar.SECOND, -1);
    toTimestamp.setTime(calendar.getTime().getTime());
  }
  
*/
  
  /**
   * Validate time range.
   */
  public static boolean validateTimeRange(Timestamp startTime, Timestamp endTime)
  {
    if (startTime == null)
    {
      // Start Time NOT supplied.
      if (endTime == null)
      {
        // End Time NOT supplied. Valid time range.
        return true;
      }
      else
      {
        // End Time supplied. Invalid time range.
        return false;
      }
    }
    else
    {
      // Start Time supplied. End Time must be supplied too.
      if (endTime == null)
      {
        // End Time NOT supplied. Invalid time range.
        return false;
      }
      else
      {
        // End Time supplied.
        if (endTime.equals(startTime) || endTime.after(startTime))
        {
          return true;
        }
      }
    }
    return false;
  }
  /**
   * Convert int value to double by specified factor.
   */
  public static double convertIntToDouble(int value, int factor)
  {
    double doubleValue;
    double doubleFactor;
    doubleValue = value;
    doubleFactor = factor;
    return doubleValue / doubleFactor;
  }
  /**
   * Convert second value to a nice string.
   */
  public static String convertSecondsToMinutesAndSeconds(int value)
  {
    String minutesAndSeconds = null;
    String minuteText        = " minutes ";
    String secondText        = " seconds";
    int minutes = value / 60;
    int seconds = value % 60;
    if (minutes == 1)
    {
      minuteText        = " minute ";
    }
    if (seconds == 1)
    {
      secondText        = " second";
    }
    if (minutes == 0)
    {
      minutesAndSeconds = seconds + secondText;
    }
    else
    {
      if (minutes == 1)
      {
        minutesAndSeconds  = minutes + minuteText;
        minutesAndSeconds += seconds + secondText;
      }
      else
      {
        minutesAndSeconds  = minutes + minuteText;
        minutesAndSeconds += seconds + secondText;
      }
    }
    return minutesAndSeconds;
  }
//  /**
//   * Convert millisecond value to a nice string.
//   */
//  public static String convertMillisecondsToString(int value)
//  {
//    double doubleValue = convertIntToDouble(value, 1);
//    int intValue       = 0;
//    // Convert to seconds.
//    doubleValue = doubleValue / 1000.00;
//    intValue    = doubleValue;
//    if (doubleValue - intValue == 0)
//    {
//      // Seconds.
//      doubleValue = doubleValue / 60.00;
//      intValue    = doubleValue;
//      if (doubleValue - intValue == 0)
//      {
//        // Minutes.
//      }
//      else
//      {
//      }
//    }
//    else
//    {
//      // Milliseconds.
//      return Integer.toString(value) + "ms";
//    }
//    return "ee";
//  }
  /**
   * Load a HashMap from a comma separated name=vale String.
   */
  public static int loadHashMapWithNamesValues(HashMap map, String namesValues)
  {
    return loadHashMapWithNamesValues(map, namesValues, Constants.comma);
  }
  /**
   * Load a HashMap from a delimeter separated name=vale String.
   */
  public static int loadHashMapWithNamesValues(HashMap map, String namesValues, String delimiter)
  {
    map.clear();

    if (namesValues == null || namesValues.length() == 0)
    {
      return 0;
    }
    else
    {
      StringTokenizer stringTokenizer = new StringTokenizer(namesValues, delimiter);
      String token         = null;
      String tokenName     = null;
      String tokenValue    = null;
      int indexOfEqualSign = 0;

      int count            = stringTokenizer.countTokens();

      while (stringTokenizer.hasMoreTokens())
      {
        token = stringTokenizer.nextToken();

        indexOfEqualSign = token.indexOf("=");

        tokenName  = token.substring(0, indexOfEqualSign);
        tokenValue = token.substring(indexOfEqualSign + 1);

        map.put(tokenName, tokenValue);
      }
      return count;
    }
  }

  /**
   * Load a Vector from a delimeter separated values String.
   */
  public static int loadVectorWithValues(Vector vector, String values, String delimiter)
  {
    vector.clear();

    if (values == null || values.length() == 0)
    {
      return 0;
    }
    else
    {
      StringTokenizer stringTokenizer = new StringTokenizer(values, delimiter);
      String token = null;

      int count = stringTokenizer.countTokens();

      while (stringTokenizer.hasMoreTokens())
      {
        // Get the next token.
        token = stringTokenizer.nextToken();
        // Add the token to the vector.
        vector.add(token);
      }
      return count;
    }
  }
  /**
   *  Load a Vector from specified File.
   */
  
/*  
  
  public static Vector loadVectorFromFile(String fileName)
    throws LoggedException
  {
    Vector vector = new Vector();
    try
    {
      BufferedReader in = new BufferedReader(new FileReader(fileName));
      try
      {
        String line;
        while ((line = in.readLine()) != null)
        {
          vector.addElement(line);
        }
      }
      finally
      {
        // MUST always be closed.
        in.close();
      }
    }
    catch (IOException e)
    {
      throw ExceptionHandler.getInstance().logException(e, "Utilities", "LoadVectorFromFile(vector, " + fileName + ")");
    }
    return vector;
  }
  
*/  
  
  public static String escapeQuotes(String value)
  {
    int quoteCount = countCharsInString(value, Constants.sqlQuote);

    if (quoteCount > 0)
    {
      StringBuffer sbValue = new StringBuffer();

      String toReplace   = String.valueOf(Constants.sqlQuote);
      String replaceWith = toReplace + toReplace;

      // Replace them.
      for (int index = 0; index < value.length(); index++)
      {
        if (value.charAt(index) == Constants.sqlQuote)
        {
          sbValue.append(replaceWith);
        }
        else
        {
          sbValue.append(value.charAt(index));
        }
      }
      //
      return sbValue.toString();
    }
    else
    {
      // NO quotes exist so just return the String.
      return value;
    }
  }
  /**
   *  Remove Carriage Returns and Line Feeds from Text.
   */
  public static String removeCrLf(String text)
  {
    StringBuffer textWithoutCrLf = new StringBuffer();
      for (int i = 0; i < text.length(); i++)
      {
        if ((text.charAt(i) != '\r') && (text.charAt(i) != '\n'))
        {
          // NOT a Carriage Return or a Line Feed, add it to the StringBuffer.
          textWithoutCrLf.append(text.charAt(i));
        }
      }
    return textWithoutCrLf.toString();
  }
  /**
   *  Return a zero based randomIndex i.e. pass in 10 return value between 0 and 9.
   */
  public static int randomIndex(int rangeValue)
  {
      Random r = new Random();
      int i = r.nextInt();
      i = Math.abs(i);
      return i % rangeValue;
  }
  public static String getDateAsFormattedString(Timestamp time, String format)
  {
    return FastDateFormat.getInstance(format).format(time);
  }

  public static String stripQuotes(String input)
  {
    // Remove the first and last character from a string - used for removing quotes.
    return input.substring(1, input.length() - 1);
  }

  /**
   *  Return a string with no padded spaces based on a string with padded spaces.
   */
  public static String removePadding(String in)
  {
    StringBuffer out = new StringBuffer();

    for (int i = 0; i < in.length(); i++)
    {
      if (in.charAt(i) != ' ')
      {
        out.append(in.charAt(i));
      }
    }
    return out.toString();
  }

  /**
   *  Validate char is alphabetic.
   */
  public static boolean charIsAlphabetic(char c)
  {
    return ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'));
  }

  /**
   *  Validate char is numeric.
   */
  public static boolean charIsNumeric(char c)
  {
    return (c >= '0' && c <= '9');
  }

  /**
   *  Validate char is space.
   */
  public static boolean charIsSpace(char c)
  {
    return (c == ' ');
  }

  /**
   *  Validate the supplied UK postal code.
   */
  public static int validateUKPostalCode(String postalCode)
  {
    String postCode = postalCode.trim();
    String inboundCode = null;
    String outboundCode = null;
    int length = postalCode.length();

    if (length < 6 || length > 8)
    {
      // Postal Code MUST be 6,7 or 8 characters long.
      return 1;
    }

    // Get first character
    char character = postCode.charAt(0);

    if (!charIsAlphabetic(character))
    {
      // First character MUST be alphabetic.
      return 2;
    }

    if (StringUtils.countMatches(postCode, Constants.space) == 0)
    {
      // There MUST 1 space.
      return 3;
    }
    if (StringUtils.countMatches(postCode, Constants.space) > 1)
    {
      // There MUST NOT be more than 1 space.
      return 8;
    }
    // There is a space in the postCode.
    inboundCode  = postCode.substring(postCode.indexOf(Constants.space) + 1);
    outboundCode = postCode.substring(0, postCode.indexOf(Constants.space));

    // Get last character of outbound code.
    character = outboundCode.charAt(outboundCode.length() - 1);

    if (!charIsNumeric(character))
    {
      // Last character of outbound code MUST be numeric.
      return 9;
    }

    if (inboundCode.length() != 3)
    {
      // Inbound code MUST be 3 characters in length.
      return 10;
    }

    // Get first character of inbound code.
    character = inboundCode.charAt(0);

    if (!charIsNumeric(character))
    {
      // First character of inbound code MUST be numeric.
      return 4;
    }

    // Get second character of inbound code.
    character = inboundCode.charAt(1);

    if (!charIsAlphabetic(character))
    {
      // Second character of inbound code MUST be alphabetic.
      return 5;
    }

    // Get third character of inbound code.
    character = inboundCode.charAt(2);

    if (!charIsAlphabetic(character))
    {
      // Third character of inbound code MUST be alphabetic.
      return 6;
    }

    return 0;
  }

}
