package pl.gratitude.bottomsheetactionmenu

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

open class ActionMenuRecyclerViewAdapter<ActionItemId>(
    open var layout: Int = R.layout.recycler_holder_avatar_one_line_item,
    open var actions: List<ActionMenuItem<ActionItemId>> = emptyList(),
    open var listener: ActionMenuItemClickListener = ActionMenuBottomSheetDialog.EMPTY_LISTENER
) :
    RecyclerView.Adapter<ActionMenuRecyclerViewAdapter<ActionItemId>.ActionMenuItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActionMenuItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(layout, parent, false)
        return ActionMenuItemViewHolder(view)
    }

    override fun getItemCount(): Int = actions.size

    override fun onBindViewHolder(holder: ActionMenuItemViewHolder, position: Int) {
        val action = actions[position]
        action.drawable?.let { holder.avatar.setImageDrawable(it) }
        holder.title.text = action.name
    }

    open inner class ActionMenuItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        open val avatar: ImageView = view.findViewById(R.id.recycler_holder_avatar_one_line_item_avatar)
        open val title: TextView = view.findViewById(R.id.recycler_holder_avatar_one_line_item_title)

        init {
            view.setOnClickListener { listener.onActionMenuItemClick(adapterPosition) }
        }

    }

}
