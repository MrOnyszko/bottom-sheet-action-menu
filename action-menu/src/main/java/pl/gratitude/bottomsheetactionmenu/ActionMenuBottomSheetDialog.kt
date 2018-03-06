package pl.gratitude.bottomsheetactionmenu

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.bottom_sheet_dialog_menu_action.*

open class ActionMenuBottomSheetDialog(context: Context) : BottomSheetDialog(context) {

    companion object {
        val EMPTY_LISTENER = object : ActionMenuItemClickListener {
            override fun onActionMenuItemClick(position: Int) {
                // default implementation
            }
        }
    }

    open var listener: ActionMenuItemClickListener = EMPTY_LISTENER

    open var actions: MutableList<ActionMenuItem> = ArrayList()

    open var adapter: ActionMenuRecyclerViewAdapter = ActionMenuRecyclerViewAdapter()

    open var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)

    open var layout: Int = R.layout.bottom_sheet_dialog_menu_action

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        setupRecycler()
    }

    open fun setupRecycler() {
        adapter.actions = actions
        adapter.listener = listener
        menu_action_recycle_view.adapter = adapter
        menu_action_recycle_view.layoutManager = layoutManager
    }

}
