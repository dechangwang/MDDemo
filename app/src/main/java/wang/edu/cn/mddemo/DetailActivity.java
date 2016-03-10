package wang.edu.cn.mddemo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.*;

import wang.edu.cn.mddemo.application.MyApp;

public class DetailActivity extends AppCompatActivity {
    private Context mContext = this;
    private CollapsingToolbarLayout collapsingToolbarLayout_detail;
    private int position;
    private ImageView imageView_detail_banner;
    private Map<String, Object> map_item = null;
    private List<Map<String, Object>> list_teacher = null;
    private List<String> list_course = null;

    //定义讲师索引下标
    private int index_teacher = 0;

    private TextView textView_title;
    private TextView textView_teacher;
    private TextView textView_quotation;
    private TextView textView_teacherdesc;
    private ImageView imageView_teacherpic;

    private TextView textView_w1;
    private TextView textView_w2;
    private TextView textView_w3;
    private TextView textView_w4;
    private TextView textView_w5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initData();

        initView();

        loadData2View();
    }

    private void loadData2View() {
        textView_title.setText(map_item.get("title") + "");
        textView_teacher.setText("讲师：" + map_item.get("teacher") + "");
        textView_teacherdesc.setText(list_teacher.get(index_teacher).get("desc") + "");
        textView_quotation.setText(list_teacher.get(index_teacher).get("quotation") + "");
        imageView_teacherpic.setImageDrawable((Drawable) list_teacher.get(index_teacher).get("image"));

        textView_w1.setText(list_course.get(0));
        textView_w2.setText(list_course.get(1));
        textView_w3.setText(list_course.get(2));
        textView_w4.setText(list_course.get(3));
        textView_w5.setText(list_course.get(4));
    }

    private void initView() {

        imageView_detail_banner = (ImageView) findViewById(R.id.imageView_detail_banner);
        imageView_detail_banner.setImageDrawable((Drawable) map_item.get("image"));

        textView_teacher = (TextView) findViewById(R.id.textView_teacher);
        textView_teacherdesc = (TextView) findViewById(R.id.textView_teacherdesc);
        textView_quotation = (TextView) findViewById(R.id.textView_quotation);
        textView_title = (TextView) findViewById(R.id.textView_title);
        imageView_teacherpic = (ImageView) findViewById(R.id.imageView_teacherpic);

        textView_w1 = (TextView) findViewById(R.id.textView_w1);
        textView_w2 = (TextView) findViewById(R.id.textView_w2);
        textView_w3 = (TextView) findViewById(R.id.textView_w3);
        textView_w4 = (TextView) findViewById(R.id.textView_w4);
        textView_w5 = (TextView) findViewById(R.id.textView_w5);

        //标题内容
        collapsingToolbarLayout_detail =
                (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout_detail);
        collapsingToolbarLayout_detail.setTitle(map_item.get("title") + "");

        Toolbar toolbar_detail = (Toolbar) findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar_detail);
        toolbar_detail.setNavigationIcon(R.mipmap.ic_menu);
    }

    private void initData() {
        //接收页面传值
        position = getIntent().getIntExtra("position", 0);
        //获取单条信息
        map_item = MyApp.loadOutlineData(mContext).get(position);

        //获取讲师信息
        list_teacher = MyApp.loadTeacherData(mContext);
        for (int i = 0; i < list_teacher.size(); i++) {
            if (map_item.get("teacher").equals(list_teacher.get(i).get("teacher"))) {
                index_teacher = i;
            }
        }

        //获取課程信息
        list_course = MyApp.loadCourseData(mContext);
    }

    public void clickButton(View view) {
        // 启动动画
        MyApp.startAnimation(view, 1000, 1);

        switch (view.getId()) {
            case R.id.fab_detail:
                Snackbar.make(view, "您已点击收藏该条信息！", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.button_detail:
                MyApp.showAlertDialog(mContext);
                break;
            case R.id.cardView_1:
            case R.id.imageView_detail_banner:
            case R.id.textView_w1:
            case R.id.textView_w2:
            case R.id.textView_w3:
            case R.id.textView_w4:
            case R.id.textView_w5:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }
}
