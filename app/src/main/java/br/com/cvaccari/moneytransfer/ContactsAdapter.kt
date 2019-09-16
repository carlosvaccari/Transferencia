package br.com.cvaccari.moneytransfer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cvaccari.moneytransfer.custom.view.ContactView
import br.com.cvaccari.moneytransfer.data.remote.vo.ContactVO
import br.com.cvaccari.moneytransfer.extensions.phoneNumberFormat
import br.com.cvaccari.moneytransfer.listeners.OnClickListener

class ContactsAdapter(private val contactsList: List<ContactVO>) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    private var listener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.contactview.apply {
            val item = contactsList[position]
            setFirstText(item.name)
            setSecondText(item.phone.phoneNumberFormat())
            setImageText(item.name)
            item.photo?.let {
                val id = context.resources.getIdentifier(item.photo, "drawable", context.packageName)
                setImageSrc(id)
            }
            setOnClickListener {
                listener?.apply {
                    onItemClicked(item)
                }
            }
        }
    }

    fun setClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var contactview = view.findViewById<ContactView>(R.id.contactview_item)
    }
}
