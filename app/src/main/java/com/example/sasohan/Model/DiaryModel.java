package com.example.sasohan.Model;

public class DiaryModel {
    String token;
    String title;
    String content;
    long writeDate;
    String receiver;
    boolean isSended;
    boolean isPrivate;
    long receivedate;

    public DiaryModel(String token, String title, String content,
                      long writeDate, String receiver, boolean isSended, boolean isPrivate, long receivedate) {
        this.token = token;
        this.title = title;
        this.content = content;
        this.writeDate = writeDate;
        this.receiver = receiver;
        this.isSended = isSended;
        this.isPrivate = isPrivate;
        this.receivedate = receivedate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(long writeDate) {
        this.writeDate = writeDate;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public boolean isSended() {
        return isSended;
    }

    public void setSended(boolean sended) {
        isSended = sended;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public long getReceivedate() {
        return receivedate;
    }

    public void setReceivedate(long receivedate) {
        this.receivedate = receivedate;
    }
}
