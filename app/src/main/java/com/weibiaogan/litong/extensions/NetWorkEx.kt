package com.weibiaogan.litong.extensions

import com.weibiaogan.litong.common.ExceptionEngine
import com.xx.baseuilibrary.mvp.BaseMvpView
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * Created by Gubr on 2018/2/11.
 */
val defintStr: (s: String?) -> Unit = {}


fun <T> Observable<T>.ui(action: (t: T) -> Unit, error: (t: String) -> Unit = defintStr): Disposable = subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Consumer {
            action.invoke(it)
        }, object : ExceptionEngine() {
            override fun handle(msg: String) {
                error.invoke(msg)
            }
        })


inline fun <T> Observable<T>.loadDefulat(view: BaseMvpView) =
        compose(loadDefulatInternal(view))



public inline fun <T> T.applyv2(block: T.() -> T): T {
   return  block()

}

fun <T> Observable<T>.loadDefulatInternal(view: BaseMvpView) =
        ObservableTransformer<T, T> {
            doOnSubscribe { view.showLoadingDialog() }
                    .doOnComplete { view.dismissLoadingDialog() }
                    .doOnError { view.dismissLoadingDialog() }
        }
