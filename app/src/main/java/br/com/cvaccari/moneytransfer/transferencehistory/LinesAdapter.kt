package br.com.cvaccari.moneytransfer.transferencehistory

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cvaccari.moneytransfer.R

class LinesAdapter  : RecyclerView.Adapter<LinesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_graphyc_background, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("CHVG", position.toString())
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}
