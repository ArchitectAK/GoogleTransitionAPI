package com.cogitator.googletransitionapi.view.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.cogitator.googletransitionapi.R
import com.cogitator.googletransitionapi.helpers.utils.*
import com.cogitator.googletransitionapi.model.models.TransitionEvent
import kotlinx.android.synthetic.main.transition_item.view.*

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 28/06/2018 (MM/DD/YYYY)
 */

class TransitionsAdapter : RecyclerView.Adapter<TransitionsAdapter.ViewHolder>() {

    var transitionItems: List<TransitionEvent> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.transition_item)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind items
        holder.bindItem(transitionItems[position])
    }

    override fun getItemCount() = transitionItems.size

    class ViewHolder(view: View?) :
            RecyclerView.ViewHolder(view) {

        fun bindItem(item: TransitionEvent?) {
            // Check if items are dummy
            if (item != null) {
                with(itemView) {
                    activity_txt.text = item.stringify()
                    activity_txt.setCompoundDrawablesWithIntrinsicBounds(getDrawable(context,
                            item.toDrawable()), null, null, null)
                    time_txt.text = convertToDateTime(item.eventTimeMillis)
                }
            }
        }
    }
}