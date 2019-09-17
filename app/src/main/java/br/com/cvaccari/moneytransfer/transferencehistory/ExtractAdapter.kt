package br.com.cvaccari.moneytransfer.transferencehistory

import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.cvaccari.moneytransfer.R
import br.com.cvaccari.moneytransfer.custom.view.AvatarImageView
import br.com.cvaccari.moneytransfer.custom.view.AvatarImageView.SHOW_IMAGE
import br.com.cvaccari.moneytransfer.data.remote.vo.GraphicItemVO
import br.com.cvaccari.moneytransfer.extensions.setCurrencyMask
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.ViewPropertyAnimator


class ExtractAdapter(val graphicList: MutableList<GraphicItemVO>) :
    RecyclerView.Adapter<ExtractAdapter.ViewHolder>() {

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
        val item = graphicList[position]
        holder.graphicAmount.setCurrencyMask()

        //ANIM
        val valueAnim = getValueAnimator(item.amount)
        valueAnim.addUpdateListener {
            it.animatedValue
            holder.graphicAmount.text = it.animatedValue.toString()
        }
        valueAnim.start()
        getScaleAnim(holder.graphicHeight, item.amountNormalized, position).start()
        getTranslateAnim(holder.dotImage, item.amountNormalized, position).start()
        getTranslateAnim(holder.graphicAmount, item.amountNormalized, position).start()

        //VIEW VALUES
        holder.graphicAmount.text = item.amount.toString()
        holder.profileImage.setText(item.name)
        item.photo?.let {
            holder.profileImage.apply {
                val id = context.resources.getIdentifier(item.photo, "drawable", context.packageName)
                setImageResource(id)
                setText(item.name)
                this.state = SHOW_IMAGE
            }
        }
    }

    private fun getScaleAnim(view: View, amountNormalized: Float, position: Int): ViewPropertyAnimator {
        return view.animate().scaleY(200f * amountNormalized)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration((2000 - (amountNormalized * 200) - (position * 100)).toLong())
    }

    private fun getValueAnimator(endValue: Int): ValueAnimator {
        val anim = ValueAnimator.ofInt(0, endValue)
        anim.duration = 2000
        return anim
    }

    private fun getTranslateAnim(view: View, amountNormalized: Float, position: Int): ViewPropertyAnimator {
        return view.animate().translationY(-300f * amountNormalized)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration((2000 - (amountNormalized * 200) - (position * 100)).toLong())
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val graphicHeight = view.findViewById<View>(R.id.view_graphich_height)
        val graphicAmount = view.findViewById<TextView>(R.id.textview_value)
        val profileImage = view.findViewById<AvatarImageView>(R.id.imageview_profile)
        val dotImage = view.findViewById<View>(R.id.view_dot)
    }
}
