package br.com.cvaccari.moneytransfer.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cvaccari.moneytransfer.R
import br.com.cvaccari.moneytransfer.base.BaseFragment
import br.com.cvaccari.moneytransfer.data.remote.vo.GraphicItemVO
import br.com.cvaccari.moneytransfer.data.remote.vo.TransferItemVO
import br.com.cvaccari.moneytransfer.flowmanager.FlowManager
import br.com.cvaccari.moneytransfer.utils.VisualFeedbackUtils
import kotlinx.android.synthetic.main.content_contacts.*
import kotlinx.android.synthetic.main.content_graphic_extract.*
import kotlinx.android.synthetic.main.fragment_report.*
import kotlinx.android.synthetic.main.fragment_report.imageview_statement_back
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ReportFragment : BaseFragment(), KodeinAware, ReportContract.View {

    override val kodein: Kodein by kodein()

    private val mPresenter: ReportContract.Presenter by instance()

    private var graphicList = mutableListOf<GraphicItemVO>()

    private var transferList = mutableListOf<TransferItemVO>()

    override fun currentFragment(): Fragment {
        return this@ReportFragment
    }

    companion object {
        fun getInstance(): ReportFragment {
            return ReportFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater.inflate(R.layout.fragment_report, container, false)
        return rootview
    }

    override fun startFragment() {
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

    private fun initViews() {
        imageview_statement_back.setOnClickListener {
            FlowManager.removeFragment(fragmentManager)
        }

        initRecyclerVies()
    }

    private fun initRecyclerVies() {
        //Graphic Data
        recyclerview_graphic.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerview_graphic.setHasFixedSize(true)
        recyclerview_graphic.isNestedScrollingEnabled = false
        recyclerview_graphic.adapter = GraphicReportAdapter(graphicList)
        recyclerview_graphic.layoutAnimation = getAnimationController()

        //Graphic BG
        recyclerview_graphic_background.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerview_graphic_background.isNestedScrollingEnabled = false
        recyclerview_graphic_background.adapter = GraphicBackgroundAdapter()
        recyclerview_graphic_background.layoutAnimation = getAnimationController()

        //Money Transfer Data
        var adapter = ReportHistoryAdapter(transferList)
        recyclerview_contacts.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerview_contacts.setHasFixedSize(true)
        recyclerview_contacts.isNestedScrollingEnabled = false
        recyclerview_contacts.adapter = adapter
        recyclerview_contacts.layoutAnimation = AnimationUtils.loadLayoutAnimation(
            context,
            R.anim.layout_animation_slide_from_right
        )
    }

    fun getAnimationController() : LayoutAnimationController {
        return AnimationUtils.loadLayoutAnimation(
            context,
            R.anim.layout_animation_slide_from_bottom
        )
    }

    override fun showGraphicData(graphicItens: List<GraphicItemVO>) {
        this.graphicList.addAll(graphicItens)
        recyclerview_graphic.adapter?.notifyDataSetChanged()
        recyclerview_graphic.scheduleLayoutAnimation()

        recyclerview_graphic_background.visibility = View.VISIBLE
        recyclerview_graphic_background.adapter?.notifyDataSetChanged()
        recyclerview_graphic_background.scheduleLayoutAnimation()
    }

    override fun showTransferHistory(transferList: List<TransferItemVO>) {
        this.transferList.addAll(transferList)
        recyclerview_contacts.adapter?.notifyDataSetChanged()
        recyclerview_contacts.scheduleLayoutAnimation()
    }

    override fun showError(message: String?) {
        VisualFeedbackUtils.showSnackbar(message ?: getString(R.string.error_unkown), view)
    }

}