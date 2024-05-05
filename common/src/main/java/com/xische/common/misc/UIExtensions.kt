package com.xische.common.misc

import android.view.View

 fun View.setVisibility(visible: Boolean) {
    if (visible)
        this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}