package com.alirezaahmadi.progressbutton

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout

class ProgressButton : RelativeLayout {
    lateinit var button: Button
    lateinit var progressbar: View

    var progressbarRecourceId = R.layout.progress_bar_default
    var buttonResourceid = R.layout.button_default

    private var inProgress: Boolean = false
    private var hideTextWhileInProgress = true

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()
        button = (LayoutInflater.from(context).inflate(buttonResourceid, this, false) as Button).also {
            addView(it)
        }
        progressbar = LayoutInflater.from(context).inflate(progressbarRecourceId, this, false).also {
            addView(it)
        }

        setInProgress(false)
    }

    fun setInProgress(inProgress: Boolean) {
        this.inProgress = inProgress
        progressbar.visibility = if (inProgress) View.VISIBLE else View.INVISIBLE

        if(hideTextWhileInProgress) {
            //make the color transparent or convert back  the color from transparent one according to
            //progress state.
            val textColor = adjustAlpha(button.textColors.defaultColor, if (inProgress) 0f else 1f)
            button.setTextColor(textColor)
        }
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
