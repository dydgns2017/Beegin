package com.sungkyul.aa.planFragment;

import android.graphics.drawable.Drawable;

public class ListViewItem {
    private Drawable iconDrawable ;
    private String titleStr ;
    private int progressBar ;

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setProgressBar(int ProgressBar) {
        progressBar = ProgressBar ;
    }

    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public int getProgressBar() {
        return progressBar ;
    }
}