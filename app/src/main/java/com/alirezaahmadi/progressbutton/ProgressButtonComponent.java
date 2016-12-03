package com.alirezaahmadi.progressbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;


public class ProgressButtonComponent extends RelativeLayout {

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
            textColor = a.getColor(R.styleable.ProgressButtonComponent_buttonTextColor, -1);
            textSize = a.getDimensionPixelSize(R.styleable.ProgressButtonComponent_buttonTextSize, -1);
            progressColor = a.getColor(R.styleable.ProgressButtonComponent_progressColor, -1);
            progressWidth = a.getDimensionPixelSize(R.styleable.ProgressButtonComponent_progressWidth, -1);

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

        if(textColor != -1)
            textTV.setTextColor(textColor);

        if(textSize != -1)
            textTV.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

        if(progressColor != -1)
            progressWheel.setBarColor(progressColor);

        if(progressWidth != -1)
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


}
