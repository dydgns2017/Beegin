package com.sungkyul.aa.Board;

public class BoardItem {
    //게시판에 들어가기 위한 객체 코드
    String title;
    int commentCount;
    String userid;
    String date;

    public BoardItem(String title, int commentCount, String userid, String date) {
        this.title = title;
        this.commentCount = commentCount;
        this.userid = userid;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
