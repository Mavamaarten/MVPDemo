package com.icapps.mvp.view;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.icapps.mvp.model.Post;

import java.util.List;

/**
 * Created by maartenvangiel on 20/09/16.
 */

public interface PostsView extends MvpView {
    void setPosts(List<Post> posts);
    void setLoading(boolean isLoading);
    void showError(Throwable error);
}
