package pl.gratitude.bottomsheetactionmenu

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

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

    open lateinit var menuActionRecyclerView: RecyclerView

    open lateinit var menuActionHeaderTitle: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuActionRecyclerView = view.findViewById(R.id.menu_action_recycle_view)
        menuActionHeaderTitle = view.findViewById(R.id.menu_action_header)
        setupRecycler()
        setupTitle()
    }

    open fun setupRecycler() {
        adapter.actions = actions
        adapter.listener = listener
        menuActionRecyclerView.adapter = adapter
        menuActionRecyclerView.layoutManager = layoutManager
    }

    open fun setupTitle() {
        menuActionHeaderTitle.visibility = View.GONE
        title?.let {
            menuActionHeaderTitle.visibility = View.VISIBLE
            menuActionHeaderTitle.text = title
        }
    }

}
