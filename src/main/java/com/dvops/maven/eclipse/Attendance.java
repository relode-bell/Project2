package com.dvops.maven.eclipse;

import java.sql.Timestamp;

public class Attendance {
    private String email;
    private Timestamp checkInTime;
    private Timestamp checkOutTime;

    public Attendance(String email, Timestamp checkIn, Timestamp checkOut) {
        this.email = email;
        this.checkInTime = checkIn;
        this.checkOutTime = checkOut;
    }

    public String getEmail() { return email; }
    public Timestamp getCheckInTime() { return checkInTime; }
    public Timestamp getCheckOutTime() { return checkOutTime; }
}
