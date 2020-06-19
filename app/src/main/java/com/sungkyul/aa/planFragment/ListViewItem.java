package com.sungkyul.aa.planFragment;

import android.graphics.drawable.Drawable;

public class ListViewItem {
    private Drawable iconDrawable ;
    private String titleStr ;
    private int progressBar ;
    private String currentTime;
    private String GoalTime;

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        this.titleStr = title ;
    }
    public void setProgressBar(int ProgressBar) { this.progressBar = ProgressBar ; }
    public void setCurrentTime(String currentTime) { this.currentTime = currentTime ; }
    public void setGoalTime(String GoalTime) { this.GoalTime = GoalTime ; }

    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public int getProgressBar() {
        return progressBar ;
    }
    public String getCurrentTime() {
        return currentTime ;
    }
    public String getGoalTime() {
        return GoalTime ;
    }
}