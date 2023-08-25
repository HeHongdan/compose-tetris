package com.ods.libtoast;

import android.text.TextUtils;
import android.view.Gravity;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.ViewUtils;


/**
 * 类描述：(屏幕中间)吐司。
 *
 * @author HeHongdan
 * @date 2021/1/21
 * @since v2021/1/21
 */
public class Toast {

    /**
     * 显示(通用)吐司。
     *
     * @param resId  需要显示内容的资源Id。
     */
    public static void show(@StringRes int resId) {
        show(resId, false);
    }

    /**
     * 显示(通用)吐司。
     *
     * @param resId  需要显示内容的资源Id。
     * @param isLong 是否显示长(>2秒)吐司。
     */
    public static void show(@StringRes int resId, boolean isLong) {
        show(StringUtils.getString(resId), isLong);
    }

    /**
     * 显示(通用)吐司。
     *
     * @param content 需要显示的内容。
     */
    public static void show(@NonNull String content) {
        show(content, false);
    }
    /**
     * 显示(通用)吐司。
     *
     * @param content 需要显示的内容。
     * @param isLong 是否显示长(>2秒)吐司。
     *
     *
     *  if (!NetworkUtils.isConnected()) {
     *      ToastUtils.showMiddleToast(getString(R.string.net_error), false);
     *  }
     */
    public static void show(@NonNull final String content, final boolean isLong) {
        ThreadUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cancel();

                String _content = content;
                if (TextUtils.isEmpty(_content)) {
//                    LogUtils.d("吐司内容为空");
                    return;
                } else {
//                    LogUtils.d("吐司内容= " + _content);
                }

                //一行12个字符不超两行并最多不超50个字符
                final int maxLength = 50;
                if (_content.length() > maxLength) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(_content.substring(0, maxLength - 1));
                    sb.append("...");
                    _content = sb.toString();
                }

                //自定义文本样式
                TextView textView = (TextView) ViewUtils.layoutId2View(R.layout.toast_custom);
                textView.setText(_content);

                ToastUtils.make()
                        .setGravity(Gravity.CENTER, 0, 0)
                        //.setTextColor(Color.WHITE)
                        //.setBgResource(R.drawable.dialog_toast_bg)
                        //.setBgColor(ColorUtils.getColor(R.color.toast_background))
                        //.setMode(com.blankj.utilcode.util.ToastUtils.MODE.DARK)
                        .setDurationIsLong(isLong)
                        .show(textView);
            }
        });
    }

    public static void cancel() {
        ToastUtils.cancel();
    }

}
