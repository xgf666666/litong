package com.xx.baseuilibrary.mvp.presenter


import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.contract.BaseMvpRecycleViewContract
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver

/**
 * BaseMvpRecycleViewPresenter
 * (。・∀・)ノ
 * Describe：
 * Created by 雷小星🍀 on 2017/8/4 9:48.
 */

class BaseMvpRecycleViewPresenter<E, Model : BaseMvpRecycleViewContract.Model<E>>(private val model: Model)
    : BaseMvpPresenter<Model, BaseMvpRecycleViewContract.View<E>>(), BaseMvpRecycleViewContract.Presenter {
    var startPage = 1//默认页数从1开始
    private var page = startPage
    override fun loadData(refresh: Boolean) {
        page = if (refresh) startPage else ++page
        model.loadData(page, object : XxBaseHttpObserver<List<E>>() {
            override fun onStart() {
                isLoading = true
            }

            override fun onFinish() {
                isLoading = false
            }

            override fun onCompleted(msg: String?, entity: List<E>?) {
                if (refresh) {
                    getView()?.setData(entity)
                } else {
                    getView()?.upDateAdd(entity)
                }
            }

            override fun onError(error: String?) {
                page = if (refresh) startPage else --page
                getView()?.showError(Throwable(error), refresh)
            }
        })
    }

    override fun createModel(): Model {
        return model
    }
}
