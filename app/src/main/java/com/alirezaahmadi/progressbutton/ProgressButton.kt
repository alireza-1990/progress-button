package com.alirezaahmadi.progressbutton

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast

/**
 * This is a Button and a View on top of it. It shows and hides the progress based on the progress state
 * This view is useful for any async operation that takes a while to perform.
 */
class ProgressButton : RelativeLayout {
    lateinit var button: Button
    lateinit var progressView: View

    var progressbarResourceId = R.layout.progress_bar_default
    var buttonResourceId = R.layout.button_default
    private var buttonText: String? = null
    private var buttonTextColor = -1
    private var buttonTextSize: Int = -1

    private var inProgress: Boolean = false
    private var hideTextWhileInProgress = true

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        readAttrs(attrs)
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        readAttrs(attrs)
    }

    /**
     * reads the two important configs [buttonResourceId] and [progressbarResourceId]
     * It also reads some commonly used values such as [buttonText], [buttonTextColor] and [buttonTextSize]
     * to have full control over how view should look like  user have two options, configure all the
     * attributes in an xml file and pass [buttonResourceId] and [progressbarResourceId] or directly
     * access the [button] and [progressView] and call methods on them.
     */
    private fun readAttrs(attrs: AttributeSet?) {
        val attributes = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.ProgressButton,
                0, 0)
        try {
            buttonResourceId = attributes.getResourceId(R.styleable.ProgressButton_alzButtonLayoutId, R.layout.button_default)
            progressbarResourceId = attributes.getResourceId(R.styleable.ProgressButton_alzProgressLayoutId, R.layout.progress_bar_default)

            buttonText = attributes.getString(R.styleable.ProgressButton_alzButtonText)
            buttonTextColor = attributes.getColor(R.styleable.ProgressButton_alzButtonTextColor, -1)
            buttonTextSize = attributes.getDimensionPixelSize(R.styleable.ProgressButton_alzButtonTextSize, -1)

        } finally {
            attributes.recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        button = (LayoutInflater.from(context).inflate(buttonResourceId, this, false) as Button).also {
            addView(it)
        }
        progressView = LayoutInflater.from(context).inflate(progressbarResourceId, this, false).also {
            addView(it)
        }

        if(buttonText != null)
            button.text = buttonText

        if(buttonTextColor != -1)
            button.setTextColor(buttonTextColor)

        if(buttonTextSize != -1)
            button.setTextSize(TypedValue.COMPLEX_UNIT_PX, buttonTextSize.toFloat())

        setInProgress(false)
    }

    override fun setOnClickListener(clickListener: View.OnClickListener){
        button.setOnClickListener(clickListener)
    }

    /**
     * This function show and hides the [progressView] as well as button text visibility. Text visibility
     * is changed by making the color transparent instead  of other options such as changing the text
     * or the text size so the [button] don't change size while showing and hiding the text.
     * @param inProgress determines whether the view should be in inProgress state or a normal one.
     */
    fun setInProgress(inProgress: Boolean) {
        this.inProgress = inProgress
        progressView.visibility = if (inProgress) View.VISIBLE else View.INVISIBLE

        if(hideTextWhileInProgress) {
            //make the color transparent or convert back  the color from transparent one according to
            //progress state.
            val textColor = adjustAlpha(button.textColors.defaultColor, if (inProgress) 0f else 1f)
            button.setTextColor(textColor)
        }

        button.isClickable = !inProgress
    }

    /**
     * This function simply changes the alpha in the color.
     */
    private fun adjustAlpha(color: Int, factor: Float): Int {
        val alpha = Math.round(Color.alpha(color) * factor)
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        return Color.argb(alpha, red, green, blue)
    }
}
