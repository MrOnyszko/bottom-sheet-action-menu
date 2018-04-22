package pl.gratitude.bottomsheetactionmenu

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.bottom_sheet_dialog_menu_action.*

open class ActionMenuBottomSheetDialog<ActionItemId> :
    BottomSheetDialogFragment() {

    companion object {
        val EMPTY_LISTENER = object : ActionMenuItemClickListener {
            override fun onActionMenuItemClick(position: Int) {
                // default implementation
            }
        }
    }

    open var listener: ActionMenuItemClickListener = EMPTY_LISTENER

    open var actions: MutableList<ActionMenuItem<ActionItemId>> = ArrayList()

    open var adapter: ActionMenuRecyclerViewAdapter<ActionItemId> = ActionMenuRecyclerViewAdapter()

    open var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)

    open var layout: Int = R.layout.bottom_sheet_dialog_menu_action

    open var title: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        setupTitle()
    }

    open fun setupRecycler() {
        adapter.actions = actions
        adapter.listener = listener
        menu_action_recycle_view.adapter = adapter
        menu_action_recycle_view.layoutManager = layoutManager
    }

    open fun setupTitle() {
        menu_action_header.visibility = View.GONE
        title?.let {
            menu_action_header.visibility = View.VISIBLE
            menu_action_header.text = title
        }
    }

}
