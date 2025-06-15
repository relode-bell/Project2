package com.dvops.maven.eclipse;

import java.sql.Timestamp;

public class Attendance {
    private String email;
    private Timestamp checkIn;
    private Timestamp checkOut;

    public Attendance(String email, Timestamp checkIn, Timestamp checkOut) {
        this.email = email;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getCheckIn() {
        return checkIn;
    }

    public Timestamp getCheckOut() {
        return checkOut;
    }

    public String getCheckInDisplay() {
        return (checkIn != null) ? checkIn.toString() : "";
    }

    public String getCheckOutDisplay() {
        return (checkOut != null) ? checkOut.toString() : "";
    }

    public String getStatus() {
        if (checkIn != null && checkOut == null) {
            return "Checked In";
        } else if (checkIn != null && checkOut != null) {
            return "Checked Out";
        } else {
            return "Not Checked In";
        }
    }
}
