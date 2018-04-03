package com.makhovyk.android.shrinkedtext;


import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
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
    private float defaultTextSize;


    public ShrinkedTextView(Context context) {
        super(context);
    }

    public ShrinkedTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        currentTextSize = getTextSize();
        // original text size
        defaultTextSize = getTextSize();
        setMaxLines(1);
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
        // preventing multiline
        setMaxLines(1);


        if (availableWidth != 0 && text != null && text.length() != 0) {
            Rect bounds = new Rect();
            getPaint().getTextBounds(text.toString(), 0, text.length(), bounds);
            float desiredTextSize = (float) Math.ceil(currentTextSize * availableWidth / bounds.width());

            // check if new text size is not bigger, than original
            if (desiredTextSize <= defaultTextSize) {
                this.setTextSize(TypedValue.COMPLEX_UNIT_PX, desiredTextSize);
                currentTextSize = desiredTextSize;

            }
        }

        // while (text != (TextUtils.ellipsize(text, getPaint(), availableWidth, null))) {
        /*if (availableWidth < getPaint().measureText(text.toString())) {
            while (getWidth() < getPaint().measureText(text.toString())) {
                this.setTextSize(currentTextSize -= 0.5f);
                this.setText(text);
            }
        }else {
          /*  while (availableWidth > getPaint().measureText(text.toString()) && currentTextSize < defaultTextSize) {
                Log.d("TAG", "text: " + TextUtils.ellipsize(text, getPaint(), availableWidth, TextUtils.TruncateAt.END).length());
                Log.d("TAG", "ellipsize: " + text.length());
                Log.d("TAG", "measure: " + getPaint().measureText(text.toString()));
                Log.d("TAG", "width: " + getWidth());
                this.setTextSize(currentTextSize += 0.5f);

                this.setText(text);
            }*/
        //}
    }
}
