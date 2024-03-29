/**
 * Copyright 2019 bejson.com
 */
package com.auto.oneclean.tools;

/**
 * Auto-generated: 2019-09-20 17:14:38
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class SmsBean {

    private String code;
    private String msg;
    private MsgInfo msgInfo;

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

    public void setMsgInfo(MsgInfo msgInfo) {
        this.msgInfo = msgInfo;
    }

    public MsgInfo getMsgInfo() {
        return msgInfo;
    }

    public static class MsgInfo {

        private String phoneno;
        private String msgId;
        private String msgTextContent;
        private String msgVoiceContent;
        private String msgType;

        public void setPhoneno(String phoneno) {
            this.phoneno = phoneno;
        }

        public String getPhoneno() {
            return phoneno;
        }

        public void setMsgId(String msgId) {
            this.msgId = msgId;
        }

        public String getMsgId() {
            return msgId;
        }

        public void setMsgTextContent(String msgTextContent) {
            this.msgTextContent = msgTextContent;
        }

        public String getMsgTextContent() {
            return msgTextContent;
        }

        public void setMsgVoiceContent(String msgVoiceContent) {
            this.msgVoiceContent = msgVoiceContent;
        }

        public String getMsgVoiceContent() {
            return msgVoiceContent;
        }

        public void setMsgType(String msgType) {
            this.msgType = msgType;
        }

        public String getMsgType() {
            return msgType;
        }

    }
}