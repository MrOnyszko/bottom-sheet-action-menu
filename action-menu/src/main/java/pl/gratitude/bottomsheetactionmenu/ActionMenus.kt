package pl.gratitude.bottomsheetactionmenu

import android.graphics.drawable.Drawable

fun <ActionItemId> ActionMenuBottomSheetDialog<ActionItemId>.doIf(
    enabled: Boolean,
    block: ActionMenuBottomSheetDialog<ActionItemId>.() -> ActionMenuBottomSheetDialog<ActionItemId>
) = if (enabled) block() else this

fun <ActionItemId> ActionMenuBottomSheetDialog<ActionItemId>.addAction(action: ActionMenuItem<ActionItemId>) =
    this.apply {
        this.actions.add(action)
    }

fun <ActionItemId> ActionMenuBottomSheetDialog<ActionItemId>.addAction(
    id: ActionItemId,
    drawable: Drawable? = null,
    name: String
) = this.apply {
    this.actions.add(ActionMenuItem(id, drawable, name))
}

fun <ActionItemId> ActionMenuBottomSheetDialog<ActionItemId>.addActions(
    actions: List<ActionMenuItem<ActionItemId>>
) = this.apply {
    kotlin.require(actions.isNotEmpty(), { "Actions list could not be empty" })
    this.actions.addAll(actions)
}

fun <ActionItemId> ActionMenuBottomSheetDialog<ActionItemId>.withListener(listener: ActionMenuItemClickListener) =
    this.apply {
        this.listener = listener
    }

fun <ActionItemId> ActionMenuBottomSheetDialog<ActionItemId>.getActionMenuItem(position: Int): ActionMenuItem<ActionItemId> =
    this.adapter.actions[position]

fun <ActionItemId> ActionMenuBottomSheetDialog<ActionItemId>.withListener(listener: ActionMenuBottomSheetDialog<ActionItemId>.(Int) -> Unit) =
    this.apply {
        this.listener = object : ActionMenuItemClickListener {
            override fun onActionMenuItemClick(position: Int) {
                listener(position)
            }
        }
    }

fun <ActionItemId> ActionMenuBottomSheetDialog<ActionItemId>.withCanceledOnTouchOutside(value: Boolean) = this.apply {
    this.dialog?.setCanceledOnTouchOutside(value)
}

fun <ActionItemId> ActionMenuBottomSheetDialog<ActionItemId>.withTitle(title: String) = this.apply {
    this.title = title
}
