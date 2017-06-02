package cn.duozhuan.aboutlambda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private transient Button btn_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                Observable.just("hello,world")
                        .map(s -> s + "  wangliang")
                        .subscribe(s -> Log.i(TAG, s));
                break;
            case R.id.btn_2:
                query("hello world") // 查询出集合
                        .flatMap(Observable::from) // 将查询出来的list 发射成一个个的string
                        .debounce(100, TimeUnit.MILLISECONDS)  // 可以利用 debounce 做 search 用简单的话讲就是当N个结点发生的时间太靠近（即发生的时间差小于设定的值T），debounce就会自动过滤掉前N-1个结点。
                        .map(this::getTitle) // 根据查询的值取得标题
                        .filter(title -> title != null) // 过滤掉标题为null 的
                        .take(3)  // 只取三个
                        .doOnNext(this::saveTitle)  // 先保存一下
                        .subscribe(s -> Log.i(TAG, s)); // 打印
                break;

        }
    }

    public Observable<List<String>> query(String key) {
        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");
        stringList.add("5");
        return Observable.just(stringList);
    }

    public String getTitle(String url) {
        return "这是标题";
    }

    public String saveTitle(String title) {
        Log.i(TAG, "保存" + title);
        return title;
    }
}
