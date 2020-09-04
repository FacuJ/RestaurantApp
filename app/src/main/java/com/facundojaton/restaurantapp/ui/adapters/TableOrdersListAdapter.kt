package com.facundojaton.restaurantapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.facundojaton.restaurantapp.R
import com.facundojaton.restaurantapp.databinding.LayoutTableOrderItemBinding
import com.facundojaton.restaurantapp.models.TableOrder
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TableOrdersListAdapter :
    RecyclerView.Adapter<TableOrdersListAdapter.TableOrderItemViewHolder>() {
    private var items = ArrayList<TableOrder>()
    var onSeeDetailsClick: (tableOrder: TableOrder) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableOrderItemViewHolder {
        /** Set dataBinding true in Gradle **/
        val binding = DataBindingUtil.inflate<LayoutTableOrderItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.layout_table_order_item,
            parent,
            false
        )
        return TableOrderItemViewHolder(binding)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: TableOrderItemViewHolder, position: Int) {

    }

    fun setTableOrders(tableOrders: ArrayList<TableOrder>) {
        this.items = ArrayList()
        this.items.clear()
        this.items.addAll(tableOrders)
        sortList()
    }

    fun addTableOrder(tableOrder: TableOrder) {
        this.items.add(tableOrder)
        sortList()
    }

    fun removeTableOrder(tableOrder: TableOrder) {
        var position = -1
        items.forEachIndexed { index, item ->
            if (item.id == tableOrder.id) {
                position = index
            }
        }
        if (position != -1) {
            items.removeAt(position)
        }
        sortList()
    }

    fun updateTableOrder(tableOrder: TableOrder) {
        var position = -1
        items.forEachIndexed { index, item ->
            if (item.id == tableOrder.id) {
                position = index
            }
        }
        if (position != -1) {
            items[position] = tableOrder
        }
        sortList()
    }

    private fun sortList() {
        val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        Collections.sort(this.items, kotlin.Comparator { a, b ->
            val statePriorityA = when (a.state) {
                TableOrder.TableOrderState.NEW -> 3
                TableOrder.TableOrderState.RECEIVED -> 2
                TableOrder.TableOrderState.READY -> 1
                else -> 1
            }
            val statePriorityB = when (b.state) {
                TableOrder.TableOrderState.NEW -> 3
                TableOrder.TableOrderState.RECEIVED -> 2
                TableOrder.TableOrderState.READY -> 1
                else -> 1
            }
            if (statePriorityA == statePriorityB) {
                a?.updateDate?.let { updateDateA ->
                    b?.updateDate?.let { updateDateB ->
                        val dateA = format.parse(updateDateA)
                        val dateB = format.parse(updateDateB)
                        dateA?.let {
                            return@Comparator dateA.compareTo(dateB)
                        } ?: kotlin.run {
                            return@Comparator 0
                        }
                    } ?: kotlin.run {
                        return@Comparator 0
                    }
                } ?: kotlin.run {
                    return@Comparator 0
                }
            } else {
                if (statePriorityA > statePriorityB) {
                    return@Comparator -1
                } else {
                    return@Comparator 1
                }
            }
        })
        notifyDataSetChanged()
    }

    class TableOrderItemViewHolder(val binding: LayoutTableOrderItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}