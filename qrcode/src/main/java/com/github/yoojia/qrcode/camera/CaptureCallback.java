package com.github.yoojia.qrcode.camera;

import android.graphics.Bitmap;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.0
 */
public interface CaptureCallback {

    void onCaptured(Bitmap bitmap);
}
