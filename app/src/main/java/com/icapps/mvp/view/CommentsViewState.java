package com.icapps.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.viewstate.RestorableViewState;
import com.icapps.mvp.model.Comment;
import com.icapps.mvp.model.Post;

import java.util.ArrayList;

/**
 * Created by maartenvangiel on 20/09/16.
 */
public class CommentsViewState implements RestorableViewState<CommentsView> {
    private final static String KEY_POST = "post";
    private final static String KEY_COMMENTS = "comments";

    private ArrayList<Comment> comments;
    private Post post;

    @Override
    public void apply(CommentsView view, boolean retained) {
        if(post != null){
            view.setPost(post);
        }
        if(comments != null){
            view.setComments(comments);
        }
    }

    @Override
    public void saveInstanceState(@NonNull Bundle out) {
        if(post != null) out.putParcelable(KEY_POST, post);
        if(comments != null) out.putParcelableArrayList(KEY_COMMENTS, comments);
    }

    @Override
    public RestorableViewState<CommentsView> restoreInstanceState(Bundle in) {
        CommentsViewState commentsViewState = new CommentsViewState();
        if(in == null) return commentsViewState;

        commentsViewState.post = in.getParcelable(KEY_POST);
        commentsViewState.comments = in.getParcelableArrayList(KEY_COMMENTS);

        return commentsViewState;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
