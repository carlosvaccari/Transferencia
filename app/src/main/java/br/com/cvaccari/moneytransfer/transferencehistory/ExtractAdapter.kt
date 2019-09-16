package br.com.cvaccari.moneytransfer.transferencehistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import br.com.cvaccari.moneytransfer.R
import br.com.cvaccari.moneytransfer.custom.view.AvatarImageView
import br.com.cvaccari.moneytransfer.custom.view.AvatarImageView.SHOW_IMAGE
import br.com.cvaccari.moneytransfer.data.remote.vo.GraphicItemVO
import br.com.cvaccari.moneytransfer.extensions.expand
import br.com.cvaccari.moneytransfer.extensions.setCurrencyMask



class ExtractAdapter(val graphicList: MutableList<GraphicItemVO>) : RecyclerView.Adapter<ExtractAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_graphic, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return graphicList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val height = holder.graphicHeight.height

        val item = graphicList[position]
        val params = holder.graphicHeight.layoutParams as ConstraintLayout.LayoutParams
        params.height = (params.height * item.amountNormalized).toInt()
        holder.graphicHeight.layoutParams = params
        holder.graphicAmount.setCurrencyMask()
        holder.graphicAmount.text = item.amount.toString()
        item.photo?.let {
            holder.profileImage.apply {
                val id = context.resources.getIdentifier(item.photo, "drawable", context.packageName)
                setImageResource(id)
                setText(item.name)
                this.state = SHOW_IMAGE
            }
        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val graphicHeight = view.findViewById<View>(R.id.view_graphich_height)
        val graphicAmount = view.findViewById<TextView>(R.id.textview_value)
        val profileImage = view.findViewById<AvatarImageView>(R.id.imageview_profile)
    }
}
