package com.weibiaogan.litong.mvp.contract

import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import java.io.File

/**
 * author: xiaoguagnfei
 * date: 2018/7/7
 * describe:
 */
interface WorkerIdentyContract {
    interface View :BaseMvpView{
        fun setView(file: String)

    }
    interface Model{

    }
    abstract class Presenter:BaseMvpPresenter<Model,View>(){
        abstract fun fileStore(file: File?)

    }
}