package wang.edu.cn.mddemo;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.ChangeTransform;
import android.transition.Transition;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wang.edu.cn.mddemo.adapter.MyPagerAdapter;
import wang.edu.cn.mddemo.application.MyApp;
import wang.edu.cn.mddemo.fragment.DummyFragment;


public class MainActivity extends AppCompatActivity {
    private Context mContext = this;
    private DrawerLayout drawerLayout_main;
    private NavigationView navigationView_main;
    private ViewPager viewPager_main;
    private TabLayout tabLayout_main;
    private Toolbar toolbar_main;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<Drawable> imageList = new ArrayList<Drawable>();

    //定义用户名与密码是否正确
    boolean flag_username = true;
    boolean flag_pwd = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initView();
        initTabsAndViewPager();

    }

    private void initTabsAndViewPager() {
        tabLayout_main = (TabLayout) findViewById(R.id.tabLayout_main);
        String[] arrTabTitles = getResources().getStringArray(R.array.arrTabTitles);
        viewPager_main = (ViewPager) findViewById(R.id.viewPager_main);
        for (int i = 0; i < arrTabTitles.length; i++) {
            Fragment fragment = DummyFragment.getInstance(i + 1);
            fragmentList.add(fragment);
        }
        PagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentList, arrTabTitles);
        viewPager_main.setAdapter(adapter);
        tabLayout_main.setupWithViewPager(viewPager_main);
        tabLayout_main.setTabsFromPagerAdapter(adapter);
    }

    private void initView() {
        drawerLayout_main = (DrawerLayout) findViewById(R.id.drawerLayout_main);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout_main, toolbar_main, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout_main.setDrawerListener(toggle);
        toggle.syncState();

        //初始化导航视图的
        navigationView_main = (NavigationView) findViewById(R.id.navigationView_main);
        navigationView_main.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                int tabIndex = 0;
                switch (item.getItemId()) {
                    case R.id.action_android:
                        tabIndex = 0;
                        break;
                    case R.id.action_h5:
                        tabIndex = 1;
                        break;
                    case R.id.action_ios:
                        tabIndex = 2;
                        break;
                    case R.id.action_ui:
                        tabIndex = 3;
                        break;
                }
                //滑动viewpage 实现tab 切换
                viewPager_main.setCurrentItem(tabIndex);
                drawerLayout_main.closeDrawers();
                return true;
            }
        });
    }

    private void initToolbar() {
        toolbar_main = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar_main);
        //title默认为APP的Label名称，也可以重新设置：例如：setTitle("材料设计综合案例一");
        //如果不设置子标题，那么title在垂直居中的位置显示。如果设置则上下各一行显示。可以分别设置文字颜色。
        toolbar_main.setSubtitle("知识创造教育课程");
        toolbar_main.setTitleTextColor(Color.WHITE);
        toolbar_main.setSubtitleTextColor(Color.YELLOW);
    }

    public void clickButton(View view) {
        MyApp.startAnimation(view, 500, 1);
        switch (view.getId()){
            case R.id.fab_main:
                Snackbar.make(view,"注册 专享更多特权",Snackbar.LENGTH_LONG)
                        .setAction("注册", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Toast.makeText(MainActivity.this,"onclick ",Toast.LENGTH_LONG).show();
                                View registerView = getLayoutInflater().inflate(R.layout.dialog_login, null);
                                final TextInputLayout textInputLayout_username = (TextInputLayout) registerView.findViewById(R.id.textInputLayout_username);
                                final TextInputLayout textInputLayout_pwd = (TextInputLayout) registerView.findViewById(R.id.textInputLayout_pwd);
                                final EditText editText_dialog_username = textInputLayout_username.getEditText();
                                final EditText editText_dialog_pwd = textInputLayout_pwd.getEditText();

                                editText_dialog_pwd.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {
                                        if (!isPwd(s.toString())) {
                                            textInputLayout_pwd.setErrorEnabled(true);
                                            textInputLayout_pwd.setError("密码必须是6位数字、字母！");
                                            flag_pwd = false;
                                        } else {
                                            textInputLayout_pwd.setErrorEnabled(false);
                                            flag_pwd = true;
                                        }
                                    }
                                });

                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                builder.setIcon(R.mipmap.ic_class)
                                        .setTitle("用户注册")
                                        .setView(registerView)
                                        .setNegativeButton("取消", null)
                                        .setPositiveButton("注册", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                String username = editText_dialog_username.getText() + "";
                                                String pwd = editText_dialog_pwd.getText() + "";
                                                if (username.equals("") || "".equals(pwd)) {
                                                    Toast.makeText(mContext, "信息不可以为空！", Toast.LENGTH_LONG).show();
                                                }
                                                if (flag_username && flag_pwd) {
                                                    Toast.makeText(mContext, username + ":" + pwd, Toast.LENGTH_LONG).show();
                                                } else {
                                                    Toast.makeText(mContext, "信息输入有误，不可以注册！", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                                builder.show();
                            }
                        }).show();
                break;
        }
    }


    public void startActivity(View view, int position) {
        // 启动页面跳转
        Intent intent = new Intent();
        intent.setClass(mContext, DetailActivity.class);
        intent.putExtra("position", position);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            View imageView_item_thumbnail = view.findViewById(R.id.imageView_item_icon);
            View textView_item_title = view.findViewById(R.id.textView_item_title);
            View textView_item_teacher = view.findViewById(R.id.textView_item_teacher);
            // 为当前Activity设置共享元素的场景切换动画
            Transition transition = new ChangeTransform();
            transition.setDuration(6000);
            getWindow().setExitTransition(transition);

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                    (Activity) mContext,
                    Pair.create(textView_item_title, "text_detail"),
                    Pair.create(textView_item_teacher, "text_teacher"),
                    Pair.create(imageView_item_thumbnail, "banner"));
            Bundle bundle = options.toBundle();
            startActivity(intent, bundle);
        } else {
            startActivity(intent);
        }
    }

    private boolean isChinese(String str) {
        String regexp = "^[\u4e00-\u9fa5]+";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    private boolean isPwd(String str) {
        String regexp = "^[0-9a-zA-Z]{6}$";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout_main.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
