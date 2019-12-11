package com.chengm.cslayout.anim

import android.animation.ValueAnimator
import android.view.View
import com.chengm.cslayout.CSInterface
import com.chengm.cslayout.keyparms.CSKeyParm

class CSViewAnim(val isStart: Boolean, val v: CSInterface, val from: CSKeyParm, val to: CSKeyParm) : ValueAnimator(), BaseAnim<View, CSKeyParm> {
    val view = v as View

    init {
        val start = if (isStart) 1f else 0f
        setFloatValues(start, 1 - start)
        val clipL = from.rect.left - to.rect.left + from.csParms.mClipL
        val clipT = from.rect.top - to.rect.top + from.csParms.mClipT
        val clipR = to.rect.right - from.rect.right + from.csParms.mClipR
        val clipB = to.rect.bottom - from.rect.bottom + from.csParms.mClipB
        addUpdateListener { anim ->
            val value = anim.animatedValue as Float
            val bais = clamp(value, 0f, 1f)
            val helper = v.csHelper()
            helper.mClipL = clipL * bais
            helper.mClipT = clipT * bais
            helper.mClipR = clipR * bais
            helper.mClipB = clipB * bais

            helper.mCornerLeftTop = from.csParms.mCornerLeftTop * bais
            helper.mCornerTopRight = from.csParms.mCornerTopRight * bais
            helper.mCornerRightBottom = from.csParms.mCornerRightBottom * bais
            helper.mCornerBottomLeft = from.csParms.mCornerBottomLeft * bais
            helper.refresh()
            view.invalidate()
        }
    }

    private fun clamp(value: Float, min: Float, max: Float): Float {
        if (value < min)
            return min
        if (value > max)
            return max
        return value
    }


    override fun view() = view
    override fun from() = from
    override fun to() = to

}