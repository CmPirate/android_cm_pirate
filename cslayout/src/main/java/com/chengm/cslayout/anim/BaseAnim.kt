package com.chengm.cslayout.anim

import android.view.View
import com.chengm.cslayout.keyparms.KeyParm

interface BaseAnim<V : View, KP : KeyParm> {
    fun view(): V
    fun from(): KP
    fun to(): KP
}