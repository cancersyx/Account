package com.zsf.accountbook.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsf on 2017/6/27.
 * Activity管理器
 */

public class ActivityCollectorUtil {

    public static List<Activity> activityList = new ArrayList<>();

    /**
     * 向List中添加一个活动
     * @param activity
     */
    public static void addActivity(Activity activity){
        activityList.add(activity);
    }

    /**
     * 移除一个Activity
     * @param activity
     */
    public static void removeActivity(Activity activity){
        activityList.remove(activity);
    }

    /**
     * 销毁全部Activity
     */
    public static void finishAll(){
        for (Activity activity : activityList){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
