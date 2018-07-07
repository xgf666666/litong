package com.weibiaogan.litong.mvp.contract

import com.xx.baseuilibrary.mvp.BaseMvpActivity
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView

/**
 * author: xiaoguagnfei
 * date: 2018/7/7
 * describe:
 */
interface WorkerIdentyTwoContrat {
    interface View:BaseMvpView{

    }
    interface Model{

    }
    abstract class Presenter:BaseMvpPresenter<Model,View>(){

    }
}