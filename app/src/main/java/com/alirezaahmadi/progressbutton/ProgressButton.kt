package com.alirezaahmadi.progressbutton

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout

class ProgressButton : RelativeLayout {

    lateinit var button: View
    lateinit var progressbar: View

    var progressbarRecourceId = R.layout.progress_bar_default
    var buttonResourceid = R.layout.button_default

    private var inProgress: Boolean = false

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()
        button = LayoutInflater.from(context).inflate(buttonResourceid, this, false).also {
            addView(it)
        }
        progressbar = LayoutInflater.from(context).inflate(progressbarRecourceId, this, false).also {
            addView(it)
        }
    }

    fun setInProgress(inProgress: Boolean) {
        this.inProgress = inProgress
        progressbar.visibility = if (inProgress) View.VISIBLE else View.INVISIBLE
    }
}
