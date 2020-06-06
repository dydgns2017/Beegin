package com.sungkyul.aa;

import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

public class Utils {

    public enum StatusBarColorType {

        ACCENT_STATUS_BAR( R.color.colorAccent ),
        YELLOW_STATUS_BAR( R.color.colorTest ),
        BLUE_STATUS_BAR( R.color.colorBlue ),
        PURPLE_STAUTS_BAR( R.color.colorPurple),
        PRIMARY_STAUTS_BAR(R.color.colorPrimary);




        private int backgroundColorId;

        StatusBarColorType(int backgroundColorId){
            this.backgroundColorId = backgroundColorId;
        }

        public int getBackgroundColorId() {
            return backgroundColorId;
        }
    }

    public static void setStatusBarColor(Activity activity, StatusBarColorType colorType) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity, colorType.getBackgroundColorId()));
        }

   }
}