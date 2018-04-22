package pl.gratitude.bottomsheetactionmenu

import android.graphics.drawable.Drawable

open class ActionMenuItem<out T>(
    open val id: T,
    open val drawable: Drawable? = null,
    open val name: String
)
