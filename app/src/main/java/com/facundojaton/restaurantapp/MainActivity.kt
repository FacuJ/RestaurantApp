package com.facundojaton.restaurantapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity(), MainView {
    companion object {
        const val SEE_DETAILS_TABLE_ORDER_REQUEST_CODE = 102
        const val NEW_TABLE_ORDER_REQUEST_CODE = 101

        @JvmStatic
        fun start(context: Context) =
            context.startActivity(Intent(context, MainActivity::class.java))
    }

    private lateinit var mainPresenter: MainPresenter<MainView>
    private var user: User? = null
    private var tableOrdersListAdapter: TableOrdersListAdapter? = null
    private var logoutDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setMainData()
        mainPresenter = MainPresenter(this)
        mainPresenter.onAttach(this)
        user = UserController.currentUser
        tableOrdersListAdapter = TableOrdersListAdapter()
        rvTableOrders?.adapter = tableOrdersListAdapter
        showProgressBar()
        user?.id?.let {
            mainPresenter.getTableOrders(it)
        }
        hideProgressBar()

        logoutDialog = AlertDialog.Builder(this)
            .setTitle(R.string.logout_dialog_title)
            .setMessage(R.string.logout_dialog_message)
            .setPositiveButton(R.string.yes) { dialog, _ ->
                user?.id?.let {
                    mainPresenter.firebaseLogout(it)
                }
                LoginActivity.start(this)
                finish()
                dialog.dismiss()
            }
            .setNegativeButton(R.string.no) { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        ivLogout?.setOnClickListener { tvLogout.performClick() }
        tvLogout?.setOnClickListener {
            logoutDialog?.show()
        }

        ivNewTableOrder?.setOnClickListener { tvNewTableOrder.performClick() }
        tvNewTableOrder?.setOnClickListener {
            goToTableOrderActivity()
        }
        tableOrdersListAdapter?.onSeeDetailsClick = { tableOrder ->
            SeeTableOrderActivity.startForResult(
                this,
                tableOrder,
                SEE_DETAILS_TABLE_ORDER_REQUEST_CODE
            )
        }
    }

    override fun setMainData() {
        tvUpdateDateMain?.text = getString(R.string.name_in_brackets, UserController.workingDayConfigs)
    }


    override fun onTableOrdersLoaded(tableOrders: ArrayList<TableOrder>) {
        rvTableOrders?.visibility = View.VISIBLE
        clEmptyTableOrders?.visibility = View.GONE
        tableOrdersListAdapter?.setTableOrders(tableOrders)
    }

    override fun onEmptyTableOrders() {
        rvTableOrders?.visibility = View.GONE
        clEmptyTableOrders?.visibility = View.VISIBLE
    }

    override fun onTableOrderChanged(tableOrder: TableOrder) {
        tableOrdersListAdapter?.updateTableOrder(tableOrder)
    }

    override fun onTableOrderAdded(tableOrder: TableOrder) {
        rvTableOrders?.visibility = View.VISIBLE
        clEmptyTableOrders?.visibility = View.GONE
        tableOrdersListAdapter?.addTableOrder(tableOrder)
    }

    override fun onTableOrderRemoved(tableOrder: TableOrder) {
        tableOrdersListAdapter?.removeTableOrder(tableOrder)
        if (tableOrdersListAdapter?.itemCount == 0) {
            onEmptyTableOrders()
        }
    }

    override fun goToTableOrderActivity() {
        TableOrderActivity.startForResult(
            this, tableOrder = TableOrder(), isNew = true,
            requestCode = NEW_TABLE_ORDER_REQUEST_CODE
        )
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun showProgressBar() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar?.visibility = View.GONE
    }

    override fun onDestroy() {
        mainPresenter.onDetach()
        super.onDestroy()
    }
}