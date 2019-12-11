package com.chengm.cslayout

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.FrameLayout

class CSLayout : FrameLayout, CSInterface {

    override fun csHelper(): CSHelper {
        return csHelper
    }

    val csHelper = CSHelper()

    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        csHelper.initAttr(this, context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr) {
        csHelper.initAttr(this, context, attrs)
    }

    override fun dispatchDraw(canvas: Canvas?) {
        csHelper.drawBefore(canvas, isInEditMode)
        super.dispatchDraw(canvas)
        csHelper.drawAfter(canvas, isInEditMode)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        csHelper.onSizeChange(this, w, h)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        csHelper.onDetached()
    }
}