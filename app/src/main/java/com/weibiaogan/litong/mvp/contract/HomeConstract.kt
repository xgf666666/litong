package com.weibiaogan.litong.mvp.contract

import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecView

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:首页
 */
interface HomeConstract {

    public interface View : BaseMvpLcecView<Any> {


    }

    public abstract class Presenter : BaseMvpPresenter<Model, View>() {

    }

    public interface Model {



    }
}
