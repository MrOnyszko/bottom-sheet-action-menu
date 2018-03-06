package pl.gratitude.bottomsheetactionmenu

import android.graphics.drawable.Drawable

open class ActionMenuItem(
    open val id: Int,
    open val drawable: Drawable? = null,
    open val name: String
)