package pl.gratitude.bottomsheetactionmenuexample

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_bottom_sheet_action_menu_example.*
import kotlinx.android.synthetic.main.content_bottom_sheet_action_menu_example.*
import pl.gratitude.bottomsheetactionmenu.ActionMenuBottomSheetDialog
import pl.gratitude.bottomsheetactionmenu.ActionMenuItem
import pl.gratitude.bottomsheetactionmenu.addAction
import pl.gratitude.bottomsheetactionmenu.addActions
import pl.gratitude.bottomsheetactionmenu.doIf
import pl.gratitude.bottomsheetactionmenu.getActionMenuItem
import pl.gratitude.bottomsheetactionmenu.withCanceledOnTouchOutside
import pl.gratitude.bottomsheetactionmenu.withListener
import pl.gratitude.bottomsheetactionmenu.withTitle
import java.util.*

class BottomSheetActionMenuExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet_action_menu_example)
        setSupportActionBar(toolbar)

        hello_world_button.setOnClickListener {
            showActionMenuBottomSheetDialog()
        }
    }

    fun showActionMenuBottomSheetDialog() {
        val actions = (4..10).map { ActionMenuItem(ActionMenuId.OTHER, null, "Action $it") }

        val isCloudActionAvailable = Random().nextBoolean()

        ActionMenuBottomSheetDialog<ActionMenuId>()
            .withCanceledOnTouchOutside(true)
            .withTitle("ActionMenuBottomSheetDialog")
            .doIf(isCloudActionAvailable) {
                addAction(
                    ActionMenuId.CLOUD,
                    ContextCompat.getDrawable(
                        this@BottomSheetActionMenuExampleActivity,
                        R.drawable.ic_cloud_black_24dp
                    ),
                    "Cloud"
                )
            }
            .addAction(
                ActionMenuId.HEART,
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_black_24dp),
                "Favorite"
            )
            .addAction(
                ActionMenuId.BIN,
                ContextCompat.getDrawable(this, R.drawable.ic_delete_black_24dp),
                "Delete"
            )
            .addActions(actions)
            .withListener {
                val action = getActionMenuItem(it)
                Toast.makeText(context, "${action.name} ${action.id.name}", Toast.LENGTH_SHORT).show()
                dismissAllowingStateLoss()
            }
            .show(supportFragmentManager, "TAG")
    }

}

enum class ActionMenuId {
    CLOUD, HEART, BIN, OTHER
}
