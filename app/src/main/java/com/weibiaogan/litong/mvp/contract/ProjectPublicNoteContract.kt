package com.weibiaogan.litong.mvp.contract

import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView

/**
 * author: xiaoguagnfei
 * date: 2018/7/9
 * describe:
 */
interface ProjectPublicNoteContract {
    interface View :BaseMvpView{

    }
    interface Model{

    }
    abstract class Prsenter:BaseMvpPresenter<Model,View>(){

    }
}