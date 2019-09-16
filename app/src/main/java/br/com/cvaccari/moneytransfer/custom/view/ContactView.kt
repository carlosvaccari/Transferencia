package br.com.cvaccari.moneytransfer.custom.view

import android.content.Context
import android.text.Editable
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.cvaccari.moneytransfer.R
import br.com.cvaccari.moneytransfer.custom.view.AvatarImageView.SHOW_IMAGE
import br.com.cvaccari.moneytransfer.extensions.handleVisibility
import br.com.cvaccari.moneytransfer.utils.SimpleTextWatcher
import kotlinx.android.synthetic.main.view_contact.view.*

class ContactView(private val viewContext: Context, private val attrs: AttributeSet) :
    ConstraintLayout(viewContext, attrs) {

    private lateinit var firstText: TextView
    private lateinit var secondText: TextView
    private lateinit var thirdText: TextView
    private lateinit var image: AvatarImageView

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        View.inflate(context, R.layout.view_contact, this)

        initViews()

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ContactView)
        firstText.text = typedArray.getString(R.styleable.ContactView_firstText)
        secondText.text = typedArray.getString(R.styleable.ContactView_secondText)
        thirdText.text = typedArray.getString(R.styleable.ContactView_thirdText)

        imageview_profile.setImageDrawable(typedArray.getDrawable(R.styleable.ContactView_imageSrc))
        typedArray.recycle()
        handleTextviewsVisibilities()
    }

    private fun handleTextviewsVisibilities() {
        firstText.addTextChangedListener(object : SimpleTextWatcher() {
            override fun afterTextChanged(p0: Editable?) {
                firstText.handleVisibility()
            }
        })

        secondText.addTextChangedListener(object : SimpleTextWatcher() {
            override fun afterTextChanged(p0: Editable?) {
                secondText.handleVisibility()
            }
        })

        thirdText.addTextChangedListener(object : SimpleTextWatcher() {
            override fun afterTextChanged(p0: Editable?) {
                thirdText.handleVisibility()
            }
        })
    }

    private fun initViews() {
        firstText = findViewById(R.id.textview_first_text)
        secondText = findViewById(R.id.textview_second_text)
        thirdText = findViewById(R.id.textview_third_text)
        image = findViewById(R.id.imageview_profile)
    }

    fun setFirstText(firstText: String) {
        this.firstText.text = firstText
    }

    fun setSecondText(secondText: String) {
        this.secondText.text = secondText
    }

    fun setThirdText(thirdText: String) {
        this.thirdText.text = thirdText
    }

    fun setImageSrc(image: Int) {
        this.image.setImageResource(image)
        this.image.state = SHOW_IMAGE
    }

    fun setImageText(text: String) {
        this.image.setText(text)
    }

}