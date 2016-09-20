package com.icapps.mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.icapps.mvp.MVPApplication;
import com.icapps.mvp.R;
import com.icapps.mvp.model.PostRepository;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    PostRepository postRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postRepository = ((MVPApplication) getApplication()).getPostRepository();
        postRepository.getPosts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(posts -> {
                    System.out.println(posts);
                }, throwable -> {
                    System.out.println(throwable);
                });
    }
}
