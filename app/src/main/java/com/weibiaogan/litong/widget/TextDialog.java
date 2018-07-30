package com.weibiaogan.litong.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.weibiaogan.litong.R;


/**
 * Created by JacobHHH on 2018/4/22.
 */

public class TextDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    private TextView mTv_title;
    private TextView mTv_content;
    private TextView mTv_cancel;
    private TextView mTv_confirm;


    public TextDialog(@NonNull Context context) {
        super(context);
        this.mContext=context;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        this.setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView() {
        View view = View.inflate(mContext, R.layout.view_text_dialig, null);
        view.findViewById(R.id.tv_cancel).setOnClickListener(this);
        view.findViewById(R.id.tv_confirm).setOnClickListener(this);
        mTv_title = view.findViewById(R.id.tv_title);
        mTv_content = view.findViewById(R.id.tv_content);
        mTv_cancel = view.findViewById(R.id.tv_cancel);
        mTv_confirm = view.findViewById(R.id.tv_confirm);
        setContentView(view);
    }

    public void setTitle(String titleText,boolean isVisibility){
        mTv_title.setText(titleText);
        mTv_title.setVisibility(isVisibility ? View.VISIBLE : View.GONE);
    }

    public void setContent(String contentText){
        mTv_content.setText(contentText);
    }

    public void setCancelButtonText(String cancelText){
        mTv_cancel.setText(cancelText);
    }

    public void setConfirmButtonText(String confirmText){
        mTv_confirm.setText(confirmText);
    }

    @Override
    public void show() {
        if (!((Activity)mContext).isFinishing()){
            super.show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_cancel:
                if (mOnBtnClickListener!=null){
                    mOnBtnClickListener.cancel();
                }
                break;
            case R.id.tv_confirm:
                if (mOnBtnClickListener!=null){
                    mOnBtnClickListener.confirm();
                }
                break;
        }
    }

    private OnBtnClickListener mOnBtnClickListener;

    public interface OnBtnClickListener{
        void confirm();
        void cancel();
    }

    public void setOnBtnClickListener(OnBtnClickListener listener){
        this.mOnBtnClickListener=listener;
    }
}
