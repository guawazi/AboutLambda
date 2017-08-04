package cn.duozhuan.aboutlambda;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton mFabDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mFabDemo = (FloatingActionButton) findViewById(R.id.fab_demo);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mFabDemo.setOnClickListener(v -> DemoActivity.actionActivity(DetailActivity.this));
    }

    public static void actionActivity(Context context) {
        Intent intent = new Intent(context, DetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
