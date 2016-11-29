package com.helmet.bean;

import java.util.List;

public class BookingGradeAgyEntity extends BookingGradeAgy {

	private List<BookingDateUser> bookingDateUsers;
	private List<BookingExpense> bookingExpenses;
	private List<BookingGradeApplicantUser> bookingGradeApplicantUsers;

	public List<BookingDateUser> getBookingDateUsers() {
		return bookingDateUsers;
	}

	public void setBookingDateUsers(List<BookingDateUser> bookingDateUsers) {
		this.bookingDateUsers = bookingDateUsers;
	}

	public List<BookingExpense> getBookingExpenses() {
		return bookingExpenses;
	}

	public void setBookingExpenses(List<BookingExpense> bookingExpenses) {
		this.bookingExpenses = bookingExpenses;
	}

	public List<BookingGradeApplicantUser> getBookingGradeApplicantUsers() {
		return bookingGradeApplicantUsers;
	}

	public void setBookingGradeApplicantUsers(List<BookingGradeApplicantUser> bookingGradeApplicantUsers) {
		this.bookingGradeApplicantUsers = bookingGradeApplicantUsers;
	}

	public String getBookingDatesAsString() {
		String result = "";
		for (BookingDate bookingDate: bookingDateUsers) {
			String theDate = bookingDate.getBookingDate().toString();
			if (!"".equals(result)) {
				result += ",";
			}
			result += theDate;
		}
		return result;
	}

  public Boolean getHasSubcontractedApplicants()
  {
    if (bookingGradeApplicantUsers.size() == 0)
    {
      // List is Empty, so no subcontracted Applicants.
      return false;
    }
    else
    {
      for (BookingGradeApplicantUser bookingGradeApplicantUser : bookingGradeApplicantUsers)
      {
        // For each Applicant...
        if (!bookingGradeApplicantUser.getApplicantOriginalAgencyId().equals(0))
        {
          // Subcontracted Applicant found.
          return true;
        }
      }
    }
    return false;
  }
}
