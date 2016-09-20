package com.icapps.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.viewstate.RestorableViewState;
import com.icapps.mvp.model.Post;

import java.util.ArrayList;

/**
 * Created by maartenvangiel on 20/09/16.
 */
public class PostsViewState implements RestorableViewState<PostsView> {
    private final static String KEY_POSTS = "posts";

    private ArrayList<Post> posts;

    @Override
    public void apply(PostsView view, boolean retained) {
        if(posts != null){
            view.setPosts(posts);
        }
    }

    @Override
    public void saveInstanceState(@NonNull Bundle out) {
        if(posts != null) out.putParcelableArrayList(KEY_POSTS, posts);
    }

    @Override
    public RestorableViewState<PostsView> restoreInstanceState(Bundle in) {
        PostsViewState postsViewState = new PostsViewState();
        if(in == null) return postsViewState;

        postsViewState.posts = in.getParcelableArrayList(KEY_POSTS);

        return postsViewState;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }
}
