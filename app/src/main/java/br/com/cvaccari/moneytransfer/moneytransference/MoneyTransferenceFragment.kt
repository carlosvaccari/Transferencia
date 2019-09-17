package br.com.cvaccari.moneytransfer.moneytransference

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import br.com.cvaccari.moneytransfer.ContactsAdapter
import br.com.cvaccari.moneytransfer.R
import br.com.cvaccari.moneytransfer.base.BaseFragment
import br.com.cvaccari.moneytransfer.data.remote.vo.ContactVO
import br.com.cvaccari.moneytransfer.data.remote.vo.SendMoneyRequestVO
import br.com.cvaccari.moneytransfer.flowmanager.FlowManager
import br.com.cvaccari.moneytransfer.listeners.OnClickListener
import br.com.cvaccari.moneytransfer.listeners.OnConfirmedListener
import br.com.cvaccari.moneytransfer.utils.VisualFeedbackUtils
import kotlinx.android.synthetic.main.content_contacts.*
import kotlinx.android.synthetic.main.fragment_send_money.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class MoneyTransferenceFragment : BaseFragment(), KodeinAware, MoneyTransferenceContract.View {

    override val kodein: Kodein by kodein()

    private var contactsList = mutableListOf<ContactVO>()

    private var adapter = ContactsAdapter(contactsList)

    private val mPresenter: MoneyTransferenceContract.Presenter by instance()

    companion object {
        fun getInstance(): MoneyTransferenceFragment {
            return MoneyTransferenceFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootview = inflater.inflate(R.layout.fragment_send_money, container, false)
        return rootview
    }

    override fun startFragment() {
        if (currentFragment() !is MoneyTransferenceFragment) {
            return
        }
        initViews()
        mPresenter.attachView(this)
        mPresenter.getUserContacts()
    }

    private fun initViews() {
        imageview_statement_back.setOnClickListener {
            FlowManager.removeFragment(fragmentManager)
        }

        initRecyclerview()
    }

    private fun initRecyclerview() {
        val layoutManager = LinearLayoutManager(context, VERTICAL, false)
        adapter.setClickListener(contactClicked)
        recyclerview_contacts.layoutManager = layoutManager
        recyclerview_contacts.setHasFixedSize(true)
        recyclerview_contacts.isNestedScrollingEnabled = false
        recyclerview_contacts.adapter = adapter
        val controller = AnimationUtils.loadLayoutAnimation(
            recyclerview_contacts.context,
            R.anim.layout_animation_slide_from_bottom
        )
        recyclerview_contacts.layoutAnimation = controller
    }

    override fun showLoading(show: Boolean) {
        if (show) {
            animation_loading.visibility = View.VISIBLE
            animation_loading.playAnimation()
        } else {
            animation_loading.visibility = View.GONE
            animation_loading.pauseAnimation()
        }
    }

    override fun showUserContacts(contactsList: List<ContactVO>) {
        this.contactsList.addAll(contactsList)
        recyclerview_contacts.adapter?.notifyDataSetChanged()
        recyclerview_contacts.scheduleLayoutAnimation()
    }

    override fun showOperationInitialized() {
        VisualFeedbackUtils.showSnackbar(getString(R.string.money_transfer_sending), view!!)
    }

    override fun showSucess() {
        //HANDLER APENAS PARA SENSAÇÃO DE HAVER UMA CHAMADA REAL
        Handler().postDelayed({
            view?.apply {
                VisualFeedbackUtils.showSnackbar(getString(R.string.money_transfer_success), view!!)
            }
        }, 2000)
    }

    override fun showError(errorMessage: String) {
    }

    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }

    val contactClicked = object : OnClickListener {
        override fun onItemClicked(contact: ContactVO) {
            val dialog = AmountDialog.getInstance()
            dialog.setContact(contact)
            dialog.setOnConfirmedListener(sendMoneyListener)
            FlowManager.showDialog(dialog, fragmentManager!!)
        }
    }

    val sendMoneyListener = object : OnConfirmedListener {
        override fun onConfirmedClickListener(sendMoneyRequest: SendMoneyRequestVO) {
            mPresenter.sendMoney(sendMoneyRequest)
        }
    }
}