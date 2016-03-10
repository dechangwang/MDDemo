package wang.edu.cn.mddemo.application;

import android.animation.Animator;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import wang.edu.cn.mddemo.R;

/**
 * Created by wangdechang on 2016/3/9.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Animator createAnimation(View view,int duration, int style){
        Animator animator = null;
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            switch (style){
                case 1:
                    animator = ViewAnimationUtils.createCircularReveal(view,view.getWidth()/2,view.getHeight()/2,0,view.getWidth());
                    break;
                case 2:
                    animator = ViewAnimationUtils.createCircularReveal(view,view.getWidth()/2,0,0,view.getHeight());
                    break;
            }
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setDuration(duration);
        }
        return  animator;
    }

    public static void startAnimation(View view, int duration, int style) {
        Animator animator = createAnimation(view,  duration,  style);
        if (animator != null) {
            animator.start();
        }
    }

    public static void showAlertDialog(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.mipmap.ic_class)
                .setNegativeButton("取消",null)
                .setPositiveButton("确定",null)
                .setTitle("Material Design")
                .setMessage("Material Design Note ")
                .show();
    }


    public static List<Map<String, Object>> loadOutlineData(Context mContext) {
        List<Map<String,Object>> list = new ArrayList<>();
        // 类型数组Typed-Array资源。以下是类型数组的获取方式。
        TypedArray array_title = mContext.getResources().obtainTypedArray(R.array.arrItemTitle);
        TypedArray array_teacher = mContext.getResources().obtainTypedArray(R.array.arrItemTeacher);
        TypedArray array_desc = mContext.getResources().obtainTypedArray(R.array.arrItemDesc);
        TypedArray array_star = mContext.getResources().obtainTypedArray(R.array.arrItemLevel);
        TypedArray array_image = mContext.getResources().obtainTypedArray(R.array.arrItemImage);
        for (int i = 0; i < array_title.length(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("title", array_title.getString(i));
            map.put("teacher", array_teacher.getString(i));
            map.put("desc", array_desc.getString(i));
            map.put("image", array_image.getDrawable(i));
            map.put("star", array_star.getString(i));
            list.add(map);
        }
        return list;
    }

    public static List<Map<String, Object>> loadTeacherData(Context context) {
        List<Map<String, Object>> list = new ArrayList<>();
        // 类型数组Typed-Array资源。以下是类型数组的获取方式。
        TypedArray arrTeacherName = context.getResources().obtainTypedArray(R.array.arrTeacherName);
        TypedArray arrTeacherPic = context.getResources().obtainTypedArray(R.array.arrTeacherPic);
        TypedArray arrTeacherDesc = context.getResources().obtainTypedArray(R.array.arrTeacherDesc);
        TypedArray arrQuotation = context.getResources().obtainTypedArray(R.array.arrQuotation);
        for (int i = 0; i < arrTeacherName.length(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("teacher", arrTeacherName.getString(i));
            map.put("quotation", arrQuotation.getString(i));
            map.put("desc", arrTeacherDesc.getString(i));
            map.put("image", arrTeacherPic.getDrawable(i));
            list.add(map);
        }
        return list;
    }
    public static List<String> loadCourseData(Context context) {
        List<String> list = new ArrayList<>();
        // 类型数组Typed-Array资源。以下是类型数组的获取方式。
        TypedArray arrCourse = context.getResources().obtainTypedArray(R.array.arrCourseWeekDesc);
        for (int i = 0; i < arrCourse.length(); i++) {
            list.add(arrCourse.getString(i));
        }
        return list;
    }
}
