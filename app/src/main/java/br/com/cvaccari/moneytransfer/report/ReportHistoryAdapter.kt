package br.com.cvaccari.moneytransfer.report

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cvaccari.moneytransfer.R
import br.com.cvaccari.moneytransfer.custom.view.ContactView
import br.com.cvaccari.moneytransfer.data.remote.vo.TransferItemVO
import br.com.cvaccari.moneytransfer.extensions.formatToCurrency
import br.com.cvaccari.moneytransfer.extensions.phoneNumberFormat
import kotlinx.android.synthetic.main.view_contact.view.*

class ReportHistoryAdapter(private val transferenceList: List<TransferItemVO>) :
    RecyclerView.Adapter<ReportHistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return transferenceList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.contactview.apply {
            val item = transferenceList[position]
            setFirstText(item.name)
            setSecondText(item.phone.phoneNumberFormat())
            setThirdText(item.amount.toString())
            textview_third_text.formatToCurrency()
            setImageText(item.name)
            item.photo?.let {
                val id = context.resources.getIdentifier(item.photo, "drawable", context.packageName)
                setImageSrc(id)
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var contactview = view.findViewById<ContactView>(R.id.contactview_item)
    }
}
