package com.recyclerviewtest.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.recyclerviewtest.NewsContract;
import com.recyclerviewtest.R;
import com.recyclerviewtest.bean.News;
import com.recyclerviewtest.presenter.NewsPresenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewsContract.View{

    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    private ArrayList<String> list = new ArrayList<>();
    private NewsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(this);
        mRecyclerView.setAdapter(myAdapter);

        presenter = new NewsPresenter(this,this);
        presenter.start();
    }



    //---------------------------------分割线---------------
    @Override
    public void showError() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Snackbar.make(mRecyclerView, "加载失败", Snackbar.LENGTH_INDEFINITE)
                        .setAction("重试", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //presenter.refresh();
                            }
                        }).show();
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void stopLoading() {
    }

    @Override
    public void showResult(final ArrayList<News.result> list) {
       runOnUiThread(new Runnable() {
            @Override
            public void run() {
                myAdapter.addData(list);
            }
        });

    }
}
