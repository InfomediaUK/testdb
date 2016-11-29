package com.helmet.application;

import java.util.List;
import java.util.TimerTask;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.helmet.api.ServiceFactory;
import com.helmet.bean.BookingGrade;

public class CallOffTask extends TimerTask
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public void run()
  {

    logger.debug("***** start *****");

//    MgrService mgrService = ServiceFactory.getInstance().getMgrService();
//
//    List<BookingGrade> bookingGrades = mgrService.getBookingGradesToSend();
//
//    for (BookingGrade bookingGrade : bookingGrades)
//    {
//      // using superuser administrator id - 0
//      System.out.println("processing booking.bookingGrade - " + bookingGrade.getBookingId() + "." + bookingGrade.getBookingGradeId());
//      logger.debug("processing booking.bookingGrade - " + bookingGrade.getBookingId() + "." + bookingGrade.getBookingGradeId());
//      //
//      // lock the booking whilst opening
//      try
//      {
//        BookingLockHandler.getInstance().addLock(bookingGrade.getBookingId());
//        int rc = mgrService.updateBookingGradeOpen(bookingGrade.getBookingGradeId(), bookingGrade.getNoOfChanges(), 0);
//      }
//      catch (AlreadyLockedRuntimeException e)
//      {
//        logger.warn("***** Booking already locked - " + bookingGrade.getBookingGradeId());
//      }
//      finally
//      {
//        try
//        {
//          BookingLockHandler.getInstance().removeLock(bookingGrade.getBookingId());
//        }
//        catch (NotLockedRuntimeException e)
//        {
//          logger.error("Error trying to remove lock on bookingId - " + bookingGrade.getBookingGradeId());
//        }
//      }
//    }

    logger.debug("***** end *****");

  }

}
