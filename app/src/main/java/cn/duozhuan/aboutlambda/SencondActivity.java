package cn.duozhuan.aboutlambda;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import cn.duozhuan.aboutlambda.adapter.QuickAdapter;
import cn.duozhuan.aboutlambda.bean.MeiZiBean;

public class SencondActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private FloatingActionButton fab;
    private RecyclerView recycler;
    private String imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSDrn1lMzA9OtjtcYuJCv-cljjAERlzo_3aolxX_xxnzew6uPiFDQ";
    private QuickAdapter<MeiZiBean> adapter;
    private ArrayList<MeiZiBean> list;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sencond);
        initView();
        initData();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        recycler = (RecyclerView) findViewById(R.id.recyler);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        fab.setOnClickListener(this);
        setSupportActionBar(toolbar);
        // 设置默认选中的菜单
        navigationView.setCheckedItem(R.id.item_a);
        // 监听完了，注意关闭侧滑  drawerLayout.closeDrawer(GravityCompat.START);
        navigationView.setNavigationItemSelectedListener(this);
        refreshLayout.setColorSchemeColors(Color.BLUE);
        refreshLayout.setOnRefreshListener(() -> new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runOnUiThread(() -> {
                List<MeiZiBean> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    MeiZiBean meiZiBean = new MeiZiBean(imageUrl, "新增--" + i);
                    list.add(meiZiBean);
                }
                adapter.addFirst(list);
                refreshLayout.setRefreshing(false);
            });
        }).start());
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            MeiZiBean meiZiBean = new MeiZiBean(imageUrl, "妹子--" + i);
            list.add(meiZiBean);
        }
        adapter = new QuickAdapter<MeiZiBean>() {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_layout;
            }

            @Override
            public void convert(VH holder, MeiZiBean data, int position) {
                holder.setImage(R.id.iv_meizi, data.getImageUrl());
                holder.setText(R.id.tv_meizi, data.getDescribtion());
            }

            @Override
            protected LinearLayoutManager getLayoutManager(RecyclerView recyclerView) {
                return new GridLayoutManager(SencondActivity.this, 2);
            }
        };
        recycler.setAdapter(adapter);
        adapter.addOnItemClickListener((View view, int position) -> {
            DetailActivity.actionActivity(SencondActivity.this);
        });
    }

    public static void actionActivity(Context context) {
        Intent intent = new Intent(context, SencondActivity.class);
        context.startActivity(intent);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_a:
                Intent intent = new Intent("com.github");
                sendOrderedBroadcast(intent, null);
                break;
            case R.id.item_b:
                LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(SencondActivity.this);
                break;
            case R.id.item_c:

                break;
            case R.id.item_d:
                recycler.getLayoutManager().scrollToPosition(0);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Snackbar.make(toolbar, "这是一个提示", Snackbar.LENGTH_LONG)
                        .setAction("加载数据", v1 -> adapter.addAll(list))
//                        .setAction("清除数据", v1 -> adapter.clean())
                        .show();
                break;
        }
    }

    public class MyBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // 截断广播
            abortBroadcast();
        }
    }

    public void save() {
        String data = "save data";
        FileOutputStream outputStream = null;
        BufferedWriter writer = null;
        try {
            outputStream = openFileOutput("data", Context.MODE_APPEND);
            writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String loadData() {
        FileInputStream inputStream = null;
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;
        try {
            inputStream = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String lines = "";
            if ((lines = reader.readLine()) != null) {
                builder.append(lines);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }


    public void getSharePreferences(){
        SharedPreferences sp = getSharedPreferences("sp", MODE_PRIVATE);
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SencondActivity.this);

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("key","value");
        editor.apply();
        String key = sp.getString("key","default");
    }

}
