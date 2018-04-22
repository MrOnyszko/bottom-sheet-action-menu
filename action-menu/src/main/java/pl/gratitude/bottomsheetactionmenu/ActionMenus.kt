package pl.gratitude.bottomsheetactionmenu

import android.graphics.drawable.Drawable
import android.view.View
import kotlinx.android.synthetic.main.bottom_sheet_dialog_menu_action.*

fun <ActionItemId> ActionMenuBottomSheetDialog<ActionItemId>.doIf(
    enabled: Boolean,
    block: ActionMenuBottomSheetDialog<ActionItemId>.() -> ActionMenuBottomSheetDialog<ActionItemId>
): ActionMenuBottomSheetDialog<ActionItemId> {
    return if (enabled) block() else this
}

fun <ActionItemId> ActionMenuBottomSheetDialog<ActionItemId>.addAction(action: ActionMenuItem<ActionItemId>): ActionMenuBottomSheetDialog<ActionItemId> {
    this.actions.add(action)
    return this
}

fun <ActionItemId> ActionMenuBottomSheetDialog<ActionItemId>.addAction(
    id: ActionItemId,
    drawable: Drawable? = null,
    name: String
): ActionMenuBottomSheetDialog<ActionItemId> {
    this.actions.add(ActionMenuItem(id, drawable, name))
    return this
}

fun <ActionItemId> ActionMenuBottomSheetDialog<ActionItemId>.addActions(
    actions: List<ActionMenuItem<ActionItemId>>
): ActionMenuBottomSheetDialog<ActionItemId> {
    kotlin.require(actions.isNotEmpty(), { "Actions list could not be empty" })
    this.actions.addAll(actions)
    return this
}

fun <ActionItemId> ActionMenuBottomSheetDialog<ActionItemId>.withListener(listener: ActionMenuItemClickListener): ActionMenuBottomSheetDialog<ActionItemId> {
    this.listener = listener
    return this
}

fun <ActionItemId> ActionMenuBottomSheetDialog<ActionItemId>.getActionMenuItem(position: Int): ActionMenuItem<ActionItemId> =
    this.adapter.actions[position]

fun <ActionItemId> ActionMenuBottomSheetDialog<ActionItemId>.withListener(listener: ActionMenuBottomSheetDialog<ActionItemId>.(Int) -> Unit): ActionMenuBottomSheetDialog<ActionItemId> {
    this.listener = object : ActionMenuItemClickListener {
        override fun onActionMenuItemClick(position: Int) {
            listener(position)
        }
    }
    return this
}

fun <ActionItemId> ActionMenuBottomSheetDialog<ActionItemId>.withCanceledOnTouchOutside(value: Boolean): ActionMenuBottomSheetDialog<ActionItemId> {
    this.dialog?.setCanceledOnTouchOutside(value)
    return this
}

fun <ActionItemId> ActionMenuBottomSheetDialog<ActionItemId>.withTitle(title: String): ActionMenuBottomSheetDialog<ActionItemId> {
    this.title = title
    return this
}
