package com.dvops.maven.eclipse;

public class AttendanceHistory {
    private String email;
    private String status;
    private String date;

    public AttendanceHistory() {}

    public AttendanceHistory(String email, String status, String date) {
        this.email = email;
        this.status = status;
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
