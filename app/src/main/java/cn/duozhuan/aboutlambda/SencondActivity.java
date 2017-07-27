package cn.duozhuan.aboutlambda;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SencondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sencond);
    }

    public static void actionActivity(Context context){
        Intent intent = new Intent(context, SencondActivity.class);
        context.startActivity(intent);
    }
}
