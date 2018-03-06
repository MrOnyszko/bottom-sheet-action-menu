package pl.gratitude.bottomsheetactionmenu

import android.graphics.drawable.Drawable

fun ActionMenuBottomSheetDialog.withAction(action: ActionMenuItem): ActionMenuBottomSheetDialog {
    this.actions.add(action)
    return this
}

fun ActionMenuBottomSheetDialog.withAction(
    id: Int,
    drawable: Drawable? = null,
    name: String
): ActionMenuBottomSheetDialog {
    this.actions.add(ActionMenuItem(id, drawable, name))
    return this
}

fun ActionMenuBottomSheetDialog.withActions(
    actions: List<ActionMenuItem>
): ActionMenuBottomSheetDialog {
    require(actions.isNotEmpty(), { "Actions list could not be empty" })
    this.actions.addAll(actions)
    return this
}

fun ActionMenuBottomSheetDialog.withListener(listener: ActionMenuItemClickListener): ActionMenuBottomSheetDialog {
    this.listener = listener
    return this
}

fun ActionMenuBottomSheetDialog.getActionMenuItem(position: Int): ActionMenuItem =
    this.adapter.actions[position]

fun ActionMenuBottomSheetDialog.withListener(listener: ActionMenuBottomSheetDialog.(Int) -> Unit): ActionMenuBottomSheetDialog {
    this.listener = object : ActionMenuItemClickListener {
        override fun onActionMenuItemClick(position: Int) {
            listener(position)
        }
    }
    return this
}

fun ActionMenuBottomSheetDialog.withCanceledOnTouchOutside(value: Boolean): ActionMenuBottomSheetDialog {
    this.setCanceledOnTouchOutside(value)
    return this
}