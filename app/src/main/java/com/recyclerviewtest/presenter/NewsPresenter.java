package com.recyclerviewtest.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.recyclerviewtest.NewsContract;
import com.recyclerviewtest.bean.News;
import com.recyclerviewtest.model.Model;
import com.recyclerviewtest.util.Api;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by asus on 2017/7/20.
 */

public class NewsPresenter implements NewsContract.Presenter {


    private  NewsContract.View view;
    private Context context;
    private ArrayList<News.result> list = new ArrayList<>();
    private Gson gson = new Gson();
    private Model model;
    private Model m;


    public NewsPresenter(Context context, NewsContract.View view){
        this.context = context;
        this.view = view;

    }
    @Override
    public void start(){
        loadPosts();
    }

    @Override
    public void loadPosts() {
        view.showLoading();
        model = new Model();
        model.get(Api.GUOKR_ARTICLES, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                view.showError();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                Log.d("打印",responseBody);
                News question = gson.fromJson(responseBody, News.class);
                for (News.result re : question.getResult()) {
                    // list.add(re);
                    Log.d("打印标题",re.getTitle());
                    list.add(re);
                }
                view.showResult(list);
            }
        });
        view.stopLoading();
    }


    @Override
    public void refresh() {
        loadPosts();
    }

    /**
     * 点进去阅读呀
     * @param position
     */
    @Override
    public void starReading(int position) {

    }
}