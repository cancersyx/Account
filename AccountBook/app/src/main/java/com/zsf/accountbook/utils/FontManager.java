package com.zsf.accountbook.utils;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zsf.accountbook.R;

/**
 * @author EWorld
 * @date 2018/3/30
 * @e-mail 852333743@qq.com
 * 改变字体
 */

public class FontManager {
    //定义自定义字体
    public static Typeface tf;

    //初始化字体，方正卡通
    // TODO: 2018/3/30 把字体添加进来font文件夹
    public static void initTypeFace(Activity activity) {
        if (tf == null) {
            tf = Typeface.createFromAsset(activity.getAssets(), "fonts/newfont.ttf");
        }
    }

    /**
     * 改变字体
     *
     * @param root
     * @param activity
     */
    public static void changeFonts(ViewGroup root, Activity activity) {
        for (int i = 0; i < root.getChildCount(); i++) {
            View view = root.getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTypeface(tf);
            } else if (view instanceof Button) {
                ((Button) view).setTypeface(tf);
            } else if (view instanceof EditText) {
                ((EditText) view).setTypeface(tf);
            } else if (view instanceof ViewGroup) {
                changeFonts((ViewGroup) view, activity);
            }
        }
    }

    /**
     * 获得root contentview
     * @param activity
     * @return
     */
    public static ViewGroup getContentView(Activity activity) {
        ViewGroup systemContent = (ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content);
        ViewGroup content = null;
        if (systemContent.getChildCount() > 0 && systemContent.getChildAt(0) instanceof ViewGroup) {
            content = (ViewGroup) systemContent.getChildAt(0);
        }
        return content;
    }
}
