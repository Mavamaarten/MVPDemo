package com.icapps.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.icapps.mvp.MVPApplication;
import com.icapps.mvp.R;
import com.icapps.mvp.model.Post;
import com.icapps.mvp.presenter.PostsPresenter;
import com.icapps.mvp.view.adapter.PostsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsActivity extends MvpActivity<PostsView, PostsPresenter> implements SwipeRefreshLayout.OnRefreshListener, PostsView, PostsAdapter.PostsAdapterListener {
    @BindView(R.id.rcv_posts)
    RecyclerView postsRecycler;
    @BindView(R.id.contentView)
    SwipeRefreshLayout swipeRefresh;

    PostsAdapter postsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        swipeRefresh.setOnRefreshListener(this);

        postsAdapter = new PostsAdapter(this);
        postsRecycler.setAdapter(postsAdapter);
        postsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        presenter.loadPosts();
    }

    @NonNull
    @Override
    public PostsPresenter createPresenter() {
        return new PostsPresenter(((MVPApplication) getApplication()).getPostRepository());
    }

    @Override
    public void setPosts(List<Post> data) {
        postsAdapter.setPosts(data);
    }

    @Override
    public void setLoading(boolean isLoading) {
        swipeRefresh.setRefreshing(isLoading);
    }

    @Override
    public void showError(Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        presenter.loadPosts();
    }

    @Override
    public void onPostClicked(Post post, int position) {
        Intent intent = new Intent(this, CommentsActivity.class);
        intent.putExtra("post", post);
        startActivity(intent);
    }
}
