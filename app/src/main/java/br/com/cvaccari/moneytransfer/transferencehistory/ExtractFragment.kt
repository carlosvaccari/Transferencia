package br.com.cvaccari.moneytransfer.transferencehistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cvaccari.moneytransfer.R
import br.com.cvaccari.moneytransfer.base.BaseFragment
import br.com.cvaccari.moneytransfer.data.remote.vo.GraphicItemVO
import br.com.cvaccari.moneytransfer.data.remote.vo.TransferItemVO
import br.com.cvaccari.moneytransfer.flowmanager.FlowManager
import kotlinx.android.synthetic.main.content_contacts.*
import kotlinx.android.synthetic.main.content_graphic_extract.*
import kotlinx.android.synthetic.main.fragment_extract.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

public class ExtractFragment : BaseFragment(), KodeinAware, ExtractContract.View {

    override val kodein: Kodein by kodein()

    private val mPresenter: ExtractContract.Presenter by instance()

    companion object {
        fun getInstance(): ExtractFragment {
            return ExtractFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater.inflate(R.layout.fragment_extract, container, false)
        return rootview
    }

    override fun startFragment() {
        if(currentFragment() !is ExtractFragment) {
            return
        }
        initViews()
        mPresenter.attachView(this)
        mPresenter.getTransfers()
    }

    override fun showLoading(show: Boolean) {
        if(show) {
            animation_loading.visibility = View.VISIBLE
            animation_loading.playAnimation()
        } else {
            animation_loading.visibility = View.GONE
            animation_loading.pauseAnimation()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initViews() {
        imageview_statement_back.setOnClickListener {
            FlowManager.removeFragment(fragmentManager)
        }
    }

    override fun showGraphicData(graphicItens: MutableList<GraphicItemVO>) {
        val layoutManager1 = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        var adapter1 = ExtractAdapter(graphicItens)
        recyclerview_graphic.layoutManager = layoutManager1
        recyclerview_graphic.setHasFixedSize(true)
        recyclerview_graphic.isNestedScrollingEnabled = false
        recyclerview_graphic.adapter = adapter1


        recyclerview_graphic_background.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerview_graphic_background.isNestedScrollingEnabled = false
        recyclerview_graphic_background.adapter = LinesAdapter()
    }

    override fun showTransferHistory(transferList: List<TransferItemVO>) {
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        var adapter = TransferenceHistoryAdapter(transferList)
        recyclerview_contacts.layoutManager = layoutManager
        recyclerview_contacts.setHasFixedSize(true)
        recyclerview_contacts.isNestedScrollingEnabled = false
        recyclerview_contacts.adapter = adapter
    }

    override fun showError(message: String?) {

    }

}