package pl.gratitude.bottomsheetactionmenuexample

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_bottom_sheet_action_menu_example.*
import kotlinx.android.synthetic.main.content_bottom_sheet_action_menu_example.*
import pl.gratitude.bottomsheetactionmenu.*

class BottomSheetActionMenuExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet_action_menu_example)
        setSupportActionBar(toolbar)

        val actions = (4..10).map { ActionMenuItem(it, null, "Action $it") }

        val actionMenu = ActionMenuBottomSheetDialog(this)
            .withCanceledOnTouchOutside(true)
            .withAction(
                1,
                ContextCompat.getDrawable(this, R.drawable.ic_cloud_black_24dp),
                "Cloud"
            )
            .withAction(
                2,
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_black_24dp),
                "Favorite"
            )
            .withAction(
                3,
                ContextCompat.getDrawable(this, R.drawable.ic_delete_black_24dp),
                "Delete"
            )
            .withActions(actions)
            .withListener {
                val action = getActionMenuItem(it)
                Toast.makeText(context, action.name, Toast.LENGTH_SHORT).show()
            }

        hello_world_button.setOnClickListener { actionMenu.show() }

    }
}
