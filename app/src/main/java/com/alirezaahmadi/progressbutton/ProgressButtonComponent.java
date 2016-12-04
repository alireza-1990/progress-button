package com.alirezaahmadi.progressbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;


public class ProgressButtonComponent extends RelativeLayout {
    private static final int UNDEFINED = -1;

    private TextView textTV;
    private ProgressWheel progressWheel;

    private String buttonText;
    private int textColor;
    private int textSize;
    private int progressColor;
    private int progressWidth;

    private boolean inProgress;

    public ProgressButtonComponent(Context context) {
        super(context);
        initializeViews(context);
    }

    public ProgressButtonComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        readAttrs(attrs);
        initializeViews(context);
    }

    public ProgressButtonComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readAttrs(attrs);
        initializeViews(context);
    }

    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.compoenent_progress_button, this);
    }

    private void readAttrs(AttributeSet attrs){
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ProgressButtonComponent,
                0, 0);

        try {
            buttonText = a.getString(R.styleable.ProgressButtonComponent_buttonText);
            textColor = a.getColor(R.styleable.ProgressButtonComponent_buttonTextColor, Color.WHITE);
            textSize = a.getDimensionPixelSize(R.styleable.ProgressButtonComponent_buttonTextSize, UNDEFINED);
            progressColor = a.getColor(R.styleable.ProgressButtonComponent_progressColor, Color.WHITE);
            progressWidth = a.getDimensionPixelSize(R.styleable.ProgressButtonComponent_progressWidth, UNDEFINED);

        } finally {
            a.recycle();
        }
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        textTV = (TextView) findViewById(R.id.component_progress_button_text_view);
        progressWheel = (ProgressWheel) findViewById(R.id.component_progress_button_loading);

        textTV.setText(buttonText);

        textTV.setTextColor(textColor);
        progressWheel.setBarColor(progressColor);

        if(textSize != UNDEFINED)
            textTV.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

        if(progressWidth != UNDEFINED)
            progressWheel.setBarWidth(progressWidth);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //set progress wheel width and height based on parent height
        int size = h - getPaddingTop() - getPaddingBottom();
        ViewGroup.LayoutParams params = progressWheel.getLayoutParams();
        params.width = size;
        params.height = size;
        progressWheel.setLayoutParams(params);

    }

    public void setInProgress(boolean inProgress){
        this.inProgress = inProgress;
        setViewValues();
    }

    public boolean isInProgress() {
        return inProgress;
    }

    private void setViewValues(){
        setEnabled(!inProgress);
        progressWheel.setVisibility(inProgress ? VISIBLE : GONE);
    }

    private void setText(String text){
        this.buttonText = text;
        textTV.setText(buttonText);
    }

    private String getText(){
        return buttonText;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        textTV.setTextColor(textColor);
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int pxTextSize) {
        this.textSize = pxTextSize;
        textTV.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    public int getProgressColor() {
        return progressColor;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
        progressWheel.setBarColor(progressColor);
    }

    public int getProgressWidth() {
        return progressWidth;
    }

    public void setProgressWidth(int progressWidth) {
        this.progressWidth = progressWidth;
        progressWheel.setBarWidth(progressWidth);
    }
}
