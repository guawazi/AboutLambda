package cn.duozhuan.aboutlambda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class SaveDataActivity extends AppCompatActivity {

    private EditText mEtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);
        initView();
    }

    private void initView() {
        mEtData = (EditText) findViewById(R.id.et_data);
    }
}
