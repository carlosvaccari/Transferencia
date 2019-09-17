package br.com.cvaccari.moneytransfer.moneytransference

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import br.com.cvaccari.moneytransfer.R
import br.com.cvaccari.moneytransfer.custom.view.AvatarImageView.SHOW_IMAGE
import br.com.cvaccari.moneytransfer.data.remote.vo.ContactVO
import br.com.cvaccari.moneytransfer.data.remote.vo.SendMoneyRequestVO
import br.com.cvaccari.moneytransfer.listeners.OnConfirmedListener
import br.com.cvaccari.moneytransfer.extensions.onlyNumbersToDouble
import br.com.cvaccari.moneytransfer.extensions.phoneNumberFormat
import br.com.cvaccari.moneytransfer.extensions.setCurrencyMask
import br.com.cvaccari.moneytransfer.utils.SharedPrefsStorage
import br.com.cvaccari.moneytransfer.utils.VisualFeedbackUtils
import kotlinx.android.synthetic.main.dialog_send_money.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class AmountDialog : DialogFragment(), KodeinAware {

    override val kodein: Kodein by kodein()

    private val sharedPrefsStorage: SharedPrefsStorage by instance()

    private lateinit var sendMoneyListener: OnConfirmedListener

    private lateinit var contact: ContactVO

    companion object {
        fun getInstance(): AmountDialog {
            return AmountDialog()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater.inflate(R.layout.dialog_send_money, container, false)
        return rootview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        editext_amount_value.setCurrencyMask()
        button_send_money.setOnClickListener {
            sendMoneyListener.onConfirmedClickListener(
                SendMoneyRequestVO(
                    contact.id.toString(),
                    sharedPrefsStorage.getUserToken(),
                    editext_amount_value.onlyNumbersToDouble()
                )
            )
            VisualFeedbackUtils.hideKeyboard(activity)
            dismiss()
        }

        imageview_close.setOnClickListener {
            dismiss()
        }

        textview_user_name.text = contact.name
        textview_user_phone.text = contact.phone.phoneNumberFormat()
        imageview_profile.setText(contact.name)
        contact.photo?.let {
            val id = context!!.resources.getIdentifier(contact.photo, "drawable", context!!.packageName)
            imageview_profile.state = SHOW_IMAGE
            imageview_profile.setImageResource(id)
        }
    }

    override fun onStart() {
        super.onStart()
        WindowManager.LayoutParams().apply {
            copyFrom(dialog?.window?.attributes)
            width = (resources.displayMetrics.widthPixels * 0.90).toInt()
            height = WindowManager.LayoutParams.WRAP_CONTENT
            gravity = Gravity.CENTER
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog?.window?.attributes = this
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
    }


    fun setOnConfirmedListener(sendMoneyListener: OnConfirmedListener) {
        this.sendMoneyListener = sendMoneyListener
    }

    fun setContact(contact: ContactVO) {
        this.contact = contact
    }
}