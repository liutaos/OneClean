package com.aotu.tools;

public class PhoneCodeBean {

    private String code;
    private String msg;
    private PhoneInfo phoneInfo;

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

    public void setPhoneInfo(PhoneInfo phoneInfo) {
        this.phoneInfo = phoneInfo;
    }

    public PhoneInfo getPhoneInfo() {
        return phoneInfo;
    }

    public static class PhoneInfo {

        private String phoneno;
        private String projectId;
        private String projectCode;
        private String projectName;
        private String projectTypeLable;
        private String projectType;
        private String projectPrice;
        private String projectMatchText;

        public void setPhoneno(String phoneno) {
            this.phoneno = phoneno;
        }

        public String getPhoneno() {
            return phoneno;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectCode(String projectCode) {
            this.projectCode = projectCode;
        }

        public String getProjectCode() {
            return projectCode;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectTypeLable(String projectTypeLable) {
            this.projectTypeLable = projectTypeLable;
        }

        public String getProjectTypeLable() {
            return projectTypeLable;
        }

        public void setProjectType(String projectType) {
            this.projectType = projectType;
        }

        public String getProjectType() {
            return projectType;
        }

        public void setProjectPrice(String projectPrice) {
            this.projectPrice = projectPrice;
        }

        public String getProjectPrice() {
            return projectPrice;
        }

        public void setProjectMatchText(String projectMatchText) {
            this.projectMatchText = projectMatchText;
        }

        public String getProjectMatchText() {
            return projectMatchText;
        }

    }
}