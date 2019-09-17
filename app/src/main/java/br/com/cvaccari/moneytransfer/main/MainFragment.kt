package br.com.cvaccari.moneytransfer.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.cvaccari.moneytransfer.R
import br.com.cvaccari.moneytransfer.base.BaseFragment
import br.com.cvaccari.moneytransfer.extensions.textToString
import br.com.cvaccari.moneytransfer.flowmanager.FlowManager
import br.com.cvaccari.moneytransfer.moneytransference.MoneyTransferenceFragment
import br.com.cvaccari.moneytransfer.transferencehistory.ExtractFragment
import br.com.cvaccari.moneytransfer.utils.VisualFeedbackUtils
import kotlinx.android.synthetic.main.fragment_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class MainFragment : BaseFragment(), KodeinAware, MainContract.View {

    override val kodein: Kodein by kodein()

    private val mPresenter: MainContract.Presenter by instance()

    companion object {
        fun getInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootview = inflater.inflate(R.layout.fragment_main, container, false)
        return rootview
    }

    override fun startFragment() {
        initViews()
        mPresenter.getToken(textview_user_name.textToString(), textview_user_phone.textToString())
    }

    private fun initViews() {
        button_send_money.setOnClickListener {
            FlowManager.showFragment(MoneyTransferenceFragment.getInstance(), fragmentManager)
        }

        button_report.setOnClickListener {
            FlowManager.showFragment(ExtractFragment.getInstance(), fragmentManager)
        }
    }

    override fun showError(message: String?) {
        VisualFeedbackUtils.showSnackbar(message ?: getString(R.string.error_unkown), view!!)
    }

}