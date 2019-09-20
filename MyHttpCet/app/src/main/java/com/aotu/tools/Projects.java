package com.aotu.tools;

import androidx.annotation.NonNull;

public class Projects {

    private String projectid;
    private String projectcode;
    private String projectname;
    private String projecttype;
    private String projectprice;
    private String projectmatchtext;

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype;
    }

    public String getProjecttype() {
        return projecttype;
    }

    public void setProjectprice(String projectprice) {
        this.projectprice = projectprice;
    }

    public String getProjectprice() {
        return projectprice;
    }

    public void setProjectmatchtext(String projectmatchtext) {
        this.projectmatchtext = projectmatchtext;
    }

    public String getProjectmatchtext() {
        return projectmatchtext;
    }

    @NonNull
    @Override
    public String toString() {
        return "Projects [projectId=" + projectid + ", projectCode=" + projectcode + ", projectName="
                + projectname + ", projectType=" + projecttype + ", projectPrice="
                + projectprice + ", projectMatchText=" + projectmatchtext;
    }
}