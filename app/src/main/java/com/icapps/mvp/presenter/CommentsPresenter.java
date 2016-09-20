package com.icapps.mvp.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.icapps.mvp.model.PostRepository;
import com.icapps.mvp.view.CommentsView;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by maartenvangiel on 20/09/16.
 */
public class CommentsPresenter extends MvpBasePresenter<CommentsView> {

    private PostRepository postRepository;
    private Subscription subscription;

    public CommentsPresenter(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void loadComments(int postId){
        if(!isViewAttached()) return;
        getView().setLoading(true);

        subscription = postRepository.getPostComments(postId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(comments -> {
                    if(!isViewAttached()) return;
                    getView().setComments(comments);
                    getView().setLoading(false);
                }, throwable -> {
                    if(!isViewAttached()) return;
                    getView().showError(throwable);
                    getView().setLoading(false);
                });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if(subscription != null) subscription.unsubscribe();
    }
}
