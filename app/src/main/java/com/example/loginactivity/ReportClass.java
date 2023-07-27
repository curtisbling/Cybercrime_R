package com.example.loginactivity;

public class ReportClass {
    private String dataImage;
    private String dataEmail;
    private String dataIncident;
    private String dataFname;
    private String dataLname;

    public String getDataImage() {
        return dataImage;
    }

    public String getDataEmail() {
        return dataEmail;
    }

    public String getDataIncident() {
        return dataIncident;
    }

    public String getDataFname() {
        return dataFname;
    }

    public String getDataLname() {
        return dataLname;
    }

    public ReportClass(String dataImage, String dataEmail, String dataIncident, String dataFname, String dataLname) {
        this.dataImage = dataImage;
        this.dataEmail = dataEmail;
        this.dataIncident = dataIncident;
        this.dataFname = dataFname;
        this.dataLname = dataLname;
    }
}
