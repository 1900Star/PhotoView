package com.yibao.biggirl.util;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.PublishSubject;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/29 10:35
 */
public class RxBus {
    public RxBus() {

    }

    private PublishSubject<Object> bus = PublishSubject.create();

    public void send(Object o) {
        bus.onNext(o);

    }

    public Observable<Object> toObservable() {
        return bus;
    }

    public boolean hasObservers() {
        return bus.hasObservers();
    }
    public void getk(){
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    }



}
