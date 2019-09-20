package com.aotu.tools;

import java.util.List;

public class ProjectRoot {

    private String code;
    private String msg;
    private String root;
    public List<Projects> projectsList;

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public void setProjectsList(List<Projects> projectsList) {
        this.projectsList = projectsList;
    }

    public List<Projects> getProjectsList() {
        return projectsList;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

}
