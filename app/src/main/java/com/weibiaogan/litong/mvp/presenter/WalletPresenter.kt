package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.entity.UserCenterBean
import com.weibiaogan.litong.mvp.contract.WalletContract
import com.weibiaogan.litong.mvp.model.WalletModel
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:账户
 */
class WalletPresenter :  WalletContract.Presenter() {

    override fun refreshBalance(userId: String, token: String) {
        getModel().refreshBalance(userId,token,object : XxBaseHttpObserver<UserCenterBean>(){
            override fun onCompleted(msg: String?, entity: UserCenterBean?) {
                Constants.setUserData(entity)
                getView()?.onRefreshBalanceSuccess(entity!!)
            }

            override fun onError(error: String?) {
                getView()?.showToast(error)
            }

            override fun onStart() {
                getView()?.showLoadingDialog()
            }

            override fun onFinish() {
                getView()?.dismissLoadingDialog()
            }

        })
    }


    override fun createModel(): WalletContract.Model {
        return WalletModel()
    }
}
