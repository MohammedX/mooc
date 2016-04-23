package com.mooc.mooc;

/**
 * Created by mx911 on 4/22/2016.
 */
public class CoursesList {

    private String courseName,providerName,date,areaOfCourse,instructorName,url,picture;



    public CoursesList(String courseName, String providerName, String date, String areaOfCourse, String instructorName, String url, String picture) {
        this.courseName = courseName;
        this.providerName = providerName;
        this.date = date;
        this.areaOfCourse = areaOfCourse;
        this.instructorName = instructorName;
        this.url = url;
        this.picture = picture;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getProviderName() {
        return providerName;
    }

    public String getDate() {
        return date;
    }

    public String getAreaOfCourse() {
        return areaOfCourse;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getUrl() {
        return url;
    }

    public String getPicture() {
        return picture;
    }
}
