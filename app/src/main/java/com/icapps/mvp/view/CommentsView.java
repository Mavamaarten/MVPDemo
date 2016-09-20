package com.icapps.mvp.view;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.icapps.mvp.model.Comment;
import com.icapps.mvp.model.Post;

import java.util.List;

/**
 * Created by maartenvangiel on 20/09/16.
 */
public interface CommentsView extends MvpView {
    void setComments(List<Comment> comments);
    void setLoading(boolean isLoading);
    void showError(Throwable error);
    void setPost(Post post);
}
