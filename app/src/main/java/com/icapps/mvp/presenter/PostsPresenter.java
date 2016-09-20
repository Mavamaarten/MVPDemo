package com.icapps.mvp.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.icapps.mvp.model.PostRepository;
import com.icapps.mvp.view.PostsView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by maartenvangiel on 20/09/16.
 */
public class PostsPresenter extends MvpBasePresenter<PostsView> {
    private PostRepository postRepository;

    public PostsPresenter(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void loadPosts(){
        if(!isViewAttached()) return;
        getView().setLoading(true);

        postRepository.getPosts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(posts -> {
                    if(!isViewAttached()) return;
                    getView().setPosts(posts);
                    getView().setLoading(false);
                }, throwable -> {
                    if(!isViewAttached()) return;
                    getView().showError(throwable);
                    getView().setLoading(false);
                });
    }

}
