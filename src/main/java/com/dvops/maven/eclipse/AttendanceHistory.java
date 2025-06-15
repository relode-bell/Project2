package com.dvops.maven.eclipse;

public class AttendanceHistory {
    private String email;
    private String status;
    private String checkInTime;
    private String checkOutTime;

    public AttendanceHistory(String email, String checkInTime, String checkOutTime) {
        this.email = email;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;

        if (checkInTime != null && checkOutTime == null) {
            this.status = "Checked In";
        } else if (checkInTime != null && checkOutTime != null) {
            this.status = "Checked Out";
        } else {
            this.status = "Not Checked In";
        }
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }
}
