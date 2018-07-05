package com.weibiaogan.litong.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.weibiaogan.litong.R;


/**
 * Quantity view to add and remove quantities
 */
public class QuantityView extends LinearLayout implements View.OnClickListener {

    private Drawable quantityBackground, addButtonBackground, removeButtonBackground;

    private String addButtonText, removeButtonText;

    private int quantity;
    private boolean quantityDialog;
    private int maxQuantity = Integer.MAX_VALUE, minQuantity = Integer.MAX_VALUE;
    private int quantityPadding;
    private int buttonSize = 15;

    private ColorStateList quantityTextColor, addButtonTextColor, removeButtonTextColor;

    private Button mButtonAdd, mButtonRemove;
    private TextView mTextViewQuantity;

    private String labelDialogTitle = "Change Quantity";
    private String labelPositiveButton = "Change";
    private String labelNegativeButton = "Cancel";
    private Paint paint;

    public interface OnQuantityChangeListener {
        void onQuantityChanged(QuantityView view, int oldQuantity, int newQuantity, boolean programmatically);

        void onLimitReached();

        void onLimitReachedMin();

        void onLimitReachedMax();
    }

    private OnQuantityChangeListener onQuantityChangeListener;
    private OnClickListener mTextViewClickListener;

    public QuantityView(Context context) {
        super(context);
        init(null, 0);
    }

    public QuantityView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public QuantityView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void init(AttributeSet attrs, int defStyle) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(1);
        paint.setColor(Color.parseColor("#B3B3B3"));
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.QuantityView, defStyle, 0);

        addButtonText = getResources().getString(R.string.qv_add);
        if (a.hasValue(R.styleable.QuantityView_qv_addButtonText)) {
            addButtonText = a.getString(R.styleable.QuantityView_qv_addButtonText);
        }
        addButtonBackground = ContextCompat.getDrawable(getContext(), R.drawable.qv_btn_selector);
        if (a.hasValue(R.styleable.QuantityView_qv_addButtonBackground)) {
            addButtonBackground = a.getDrawable(R.styleable.QuantityView_qv_addButtonBackground);
        }
        addButtonTextColor = a.getColorStateList(R.styleable.QuantityView_qv_addButtonTextColor);

        removeButtonText = getResources().getString(R.string.qv_remove);
        if (a.hasValue(R.styleable.QuantityView_qv_removeButtonText)) {
            removeButtonText = a.getString(R.styleable.QuantityView_qv_removeButtonText);
        }
        removeButtonBackground = ContextCompat.getDrawable(getContext(), R.drawable.qv_btn_selector);
        if (a.hasValue(R.styleable.QuantityView_qv_removeButtonBackground)) {
            removeButtonBackground = a.getDrawable(R.styleable.QuantityView_qv_removeButtonBackground);
        }
        removeButtonTextColor = a.getColorStateList(R.styleable.QuantityView_qv_removeButtonTextColor);

        if (a.hasValue(R.styleable.QuantityView_qv_ButtonTextSize)) {
            buttonSize = a.getInteger(R.styleable.QuantityView_qv_ButtonTextSize, buttonSize);
        }
        quantity = a.getInt(R.styleable.QuantityView_qv_quantity, 0);
        maxQuantity = a.getInt(R.styleable.QuantityView_qv_maxQuantity, Integer.MAX_VALUE);
        minQuantity = a.getInt(R.styleable.QuantityView_qv_minQuantity, 0);

        quantityPadding = (int) a.getDimension(R.styleable.QuantityView_qv_quantityPadding, pxFromDp(24));
        quantityTextColor = a.getColorStateList(R.styleable.QuantityView_qv_quantityTextColor);
        quantityBackground = ContextCompat.getDrawable(getContext(), R.drawable.qv_bg_selector);
        if (a.hasValue(R.styleable.QuantityView_qv_quantityBackground)) {
            quantityBackground = a.getDrawable(R.styleable.QuantityView_qv_quantityBackground);
        }

        quantityDialog = a.getBoolean(R.styleable.QuantityView_qv_quantityDialog, true);

        a.recycle();
        int dp8 = pxFromDp(4);

        mButtonAdd = new Button(getContext());
        mButtonAdd.setGravity(Gravity.CENTER);
        mButtonAdd.setPadding(dp8, 0, dp8, 0);
        mButtonAdd.setMinimumHeight(0);
        mButtonAdd.setMinimumWidth(0);
        mButtonAdd.setMinHeight(0);
        mButtonAdd.setMinWidth(0);
        setAddButtonBackground(addButtonBackground);
        setAddButtonText(addButtonText);
        setAddButtonTextColor(addButtonTextColor);


        mButtonRemove = new Button(getContext());
        mButtonRemove.setGravity(Gravity.CENTER);
        mButtonRemove.setPadding(dp8, 0, dp8, 0);
        mButtonRemove.setMinimumHeight(0);
        mButtonRemove.setMinimumWidth(0);
        mButtonRemove.setMinHeight(0);
        mButtonRemove.setMinWidth(0);
        setRemoveButtonBackground(removeButtonBackground);
        setRemoveButtonText(removeButtonText);
        setRemoveButtonTextColor(removeButtonTextColor);
        setButtonTextSize(buttonSize);
        mTextViewQuantity = new TextView(getContext());
        mTextViewQuantity.setGravity(Gravity.CENTER);
        mTextViewQuantity.setTextSize(14);
        setQuantityTextColor(quantityTextColor);
        setQuantity(quantity);
        setQuantityBackground(quantityBackground);
        setQuantityPadding(quantityPadding);

        setOrientation(HORIZONTAL);

        addView(mButtonRemove, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        addView(mTextViewQuantity, LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        addView(mButtonAdd, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        setGravity(Gravity.CENTER);
        mButtonAdd.setOnClickListener(this);
        mButtonRemove.setOnClickListener(this);
        mTextViewQuantity.setOnClickListener(this);
    }

    private void setButtonTextSize(int buttonSize) {
        this.buttonSize = buttonSize;
        mButtonAdd.setTextSize(buttonSize);
        mButtonRemove.setTextSize(buttonSize);
    }


    public void setQuantityClickListener(OnClickListener ocl) {
        mTextViewClickListener = ocl;
    }

    @Override
    public void onClick(View v) {
        if (v == mButtonAdd) {
            if (quantity + 1 > maxQuantity) {
                if (mButtonAdd.isEnabled()) {
                    mButtonAdd.setEnabled(false);
                }

                if (onQuantityChangeListener != null) {
                    onQuantityChangeListener.onLimitReached();
                    onQuantityChangeListener.onLimitReachedMax();
                }
            } else {
                if (!mButtonRemove.isEnabled()) {
                    mButtonRemove.setEnabled(true);
                }
                int oldQty = quantity;
                quantity += 1;
                mTextViewQuantity.setText(String.valueOf(quantity));
                if (onQuantityChangeListener != null) {
                    onQuantityChangeListener.onQuantityChanged(this, oldQty, quantity, false);
                }
            }
        } else if (v == mButtonRemove) {
            if (quantity - 1 < minQuantity) {
                if (mButtonRemove.isEnabled()) {
                    mButtonRemove.setEnabled(false);
                }
                if (onQuantityChangeListener != null) {
                    onQuantityChangeListener.onLimitReached();
                    onQuantityChangeListener.onLimitReachedMin();
                }
            } else {
                if (!mButtonAdd.isEnabled()) {
                    mButtonAdd.setEnabled(true);
                }

                int oldQty = quantity;
                quantity -= 1;
                mTextViewQuantity.setText(String.valueOf(quantity));
                if (onQuantityChangeListener != null) {
                    onQuantityChangeListener.onQuantityChanged(this, oldQty, quantity, false);
                }
            }
        } else if (v == mTextViewQuantity) {
            if (!quantityDialog) {
                return;
            }

            if (mTextViewClickListener != null) {
                mTextViewClickListener.onClick(v);
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(labelDialogTitle);

            final View inflate = LayoutInflater.from(getContext()).inflate(R.layout.qv_dialog_changequantity, null, false);
            final EditText et = (EditText) inflate.findViewById(R.id.qv_et_change_qty);
            et.setText(String.valueOf(quantity));

            builder.setView(inflate);
            builder.setPositiveButton(labelPositiveButton, null);
            final AlertDialog dialog = builder.show();
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newQuantity = et.getText().toString();
                    if (isValidNumber(newQuantity)) {
                        int intNewQuantity = Integer.parseInt(newQuantity);
                        Log.d(VIEW_LOG_TAG, "newQuantity " + intNewQuantity + " max " + maxQuantity);
                        if (intNewQuantity > maxQuantity) {
                            Toast.makeText(getContext(), "Maximum quantity allowed is " + maxQuantity, Toast.LENGTH_LONG).show();
                        } else if (intNewQuantity < minQuantity) {
                            Toast.makeText(getContext(), "Minimum quantity allowed is " + minQuantity, Toast.LENGTH_LONG).show();
                        } else {
                            setQuantity(intNewQuantity);
                            hideKeyboard(et);
                            dialog.dismiss();
                        }

                    } else {
                        Toast.makeText(getContext(), "Enter valid number", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void hideKeyboard(View focus) {
        InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (focus != null) {
            inputManager.hideSoftInputFromWindow(focus.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    public OnQuantityChangeListener getOnQuantityChangeListener() {
        return onQuantityChangeListener;
    }

    public void setOnQuantityChangeListener(OnQuantityChangeListener onQuantityChangeListener) {
        this.onQuantityChangeListener = onQuantityChangeListener;
    }

    public Drawable getQuantityBackground() {
        return quantityBackground;
    }

    public void setQuantityBackground(Drawable quantityBackground) {
        this.quantityBackground = quantityBackground;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mTextViewQuantity.setBackground(quantityBackground);
        } else {
            mTextViewQuantity.setBackgroundDrawable(quantityBackground);
        }
    }

    public Drawable getAddButtonBackground() {
        return addButtonBackground;
    }

    public void setAddButtonBackground(Drawable addButtonBackground) {
        this.addButtonBackground = addButtonBackground;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mButtonAdd.setBackground(addButtonBackground);
        } else {
            mButtonAdd.setBackgroundDrawable(addButtonBackground);
        }
    }

    public Drawable getRemoveButtonBackground() {
        return removeButtonBackground;
    }

    public void setRemoveButtonBackground(Drawable removeButtonBackground) {
        this.removeButtonBackground = removeButtonBackground;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mButtonRemove.setBackground(removeButtonBackground);
        } else {
            mButtonRemove.setBackgroundDrawable(removeButtonBackground);
        }
    }

    public String getAddButtonText() {
        return addButtonText;
    }

    public void setAddButtonText(String addButtonText) {
        this.addButtonText = addButtonText;
        mButtonAdd.setText(addButtonText);
    }

    public String getRemoveButtonText() {
        return removeButtonText;
    }

    public void setRemoveButtonText(String removeButtonText) {
        this.removeButtonText = removeButtonText;
        mButtonRemove.setText(removeButtonText);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int newQuantity) {
        boolean limitReached = false;

        if (newQuantity > maxQuantity) {
//            newQuantity = maxQuantity;
            mButtonAdd.setEnabled(false);
            limitReached = true;
        }
        if (newQuantity < minQuantity) {
            newQuantity = minQuantity;
            limitReached = true;
        }
        if (!limitReached) {
//            if (onQuantityChangeListener != null) {
//                onQuantityChangeListener.onQuantityChanged(quantity, newQuantity, true);
//            }
            this.quantity = newQuantity;
            mTextViewQuantity.setText(String.valueOf(this.quantity));
        } else {
            this.quantity = newQuantity;
            mTextViewQuantity.setText(String.valueOf(this.quantity));
            if (onQuantityChangeListener != null) {
                onQuantityChangeListener.onLimitReached();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float x = mTextViewQuantity.getX();

        super.onDraw(canvas);
        canvas.save();
        canvas.drawLine(x, 0, x + mTextViewQuantity.getWidth(), 0, paint);
        canvas.drawLine(x, mTextViewQuantity.getY(), x + mTextViewQuantity.getWidth(), mTextViewQuantity.getY(), paint);
        canvas.restore();
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        if (quantity >= maxQuantity) {
            if (onQuantityChangeListener != null) {
                mButtonAdd.setEnabled(false);
                onQuantityChangeListener.onLimitReached();
                onQuantityChangeListener.onLimitReachedMax();
            }
        } else {
            mButtonAdd.setEnabled(true);
        }
        this.maxQuantity = maxQuantity;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public int getQuantityPadding() {
        return quantityPadding;
    }

    public void setQuantityPadding(int quantityPadding) {
        this.quantityPadding = quantityPadding;
        mTextViewQuantity.setPadding(quantityPadding, 0, quantityPadding, 0);
    }

    public ColorStateList getQuantityTextColor() {
        return quantityTextColor;
    }

    public void setQuantityTextColor(ColorStateList quantityTextColor) {
        this.quantityTextColor = quantityTextColor;
        mTextViewQuantity.setTextColor(quantityTextColor);
    }

    public void setQuantityTextColorRes(int quantityTextColorRes) {
        this.quantityTextColor = ContextCompat.getColorStateList(getContext(), quantityTextColorRes);
        mTextViewQuantity.setTextColor(quantityTextColor);
    }

    public ColorStateList getAddButtonTextColor() {
        return addButtonTextColor;
    }

    public void setAddButtonTextColor(ColorStateList addButtonTextColor) {
        this.addButtonTextColor = addButtonTextColor;
        mButtonAdd.setTextColor(addButtonTextColor);
    }

    public void setAddButtonTextColorRes(int addButtonTextColorRes) {
        this.addButtonTextColor = ContextCompat.getColorStateList(getContext(), addButtonTextColorRes);
        mButtonAdd.setTextColor(addButtonTextColor);
    }

    public ColorStateList getRemoveButtonTextColor() {
        return removeButtonTextColor;
    }

    public void setRemoveButtonTextColor(ColorStateList removeButtonTextColor) {
        this.removeButtonTextColor = removeButtonTextColor;
        mButtonRemove.setTextColor(removeButtonTextColor);
    }

    public void setRemoveButtonTextColorRes(int removeButtonTextColorRes) {
        this.removeButtonTextColor = ContextCompat.getColorStateList(getContext(), removeButtonTextColorRes);
        mButtonRemove.setTextColor(removeButtonTextColor);
    }

    public String getLabelDialogTitle() {
        return labelDialogTitle;
    }

    public void setLabelDialogTitle(String labelDialogTitle) {
        this.labelDialogTitle = labelDialogTitle;
    }

    public String getLabelPositiveButton() {
        return labelPositiveButton;
    }

    public void setLabelPositiveButton(String labelPositiveButton) {
        this.labelPositiveButton = labelPositiveButton;
    }

    public String getLabelNegativeButton() {
        return labelNegativeButton;
    }

    public void setLabelNegativeButton(String labelNegativeButton) {
        this.labelNegativeButton = labelNegativeButton;
    }

    public void setQuantityDialog(boolean quantityDialog) {
        this.quantityDialog = quantityDialog;
    }

    public boolean isQuantityDialog() {
        return quantityDialog;
    }

    private int dpFromPx(final float px) {
        return (int) (px / getResources().getDisplayMetrics().density);
    }

    private int pxFromDp(final float dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }


    public static boolean isValidNumber(String string) {
        try {
            return Integer.parseInt(string) <= Integer.MAX_VALUE;
        } catch (Exception e) {
            return false;
        }
    }


}
