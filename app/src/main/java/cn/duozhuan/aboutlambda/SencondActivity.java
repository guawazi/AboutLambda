package cn.duozhuan.aboutlambda;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class SencondActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sencond);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        setSupportActionBar(toolbar);
        // 设置默认选中的菜单
        navigationView.setCheckedItem(R.id.item_a);
        // 监听完了，注意关闭侧滑  drawerLayout.closeDrawer(GravityCompat.START);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public static void actionActivity(Context context) {
        Intent intent = new Intent(context, SencondActivity.class);
        context.startActivity(intent);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_a:

                break;
            case R.id.item_b:

                break;
            case R.id.item_c:

                break;
            case R.id.item_d:

                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Snackbar.make(toolbar, "Data delete", Snackbar.LENGTH_LONG)
                        .setAction("Undo",
                                v1 -> Toast.makeText(this, "我真的被删除了", Toast.LENGTH_SHORT).show())
                        .show();
                break;
        }
    }
}
