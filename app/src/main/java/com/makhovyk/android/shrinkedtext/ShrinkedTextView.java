package com.makhovyk.android.shrinkedtext;


import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;

/**
 * Created by misha on 4/2/18.
 */

public class ShrinkedTextView extends android.support.v7.widget.AppCompatTextView {

    private float currentTextSize;
    private Paint paint;


    public ShrinkedTextView(Context context) {
        super(context);
    }

    public ShrinkedTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        currentTextSize = getTextSize();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        resizeText();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        resizeText();
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        resizeText();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        resizeText();
    }

    private void resizeText() {
        float availableWidth = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
        CharSequence text = getText();

        while (text != (TextUtils.ellipsize(text, getPaint(), availableWidth, TextUtils.TruncateAt.END))) {
            this.setTextSize(currentTextSize -= 1f);
        }
        invalidate();
    }
}
