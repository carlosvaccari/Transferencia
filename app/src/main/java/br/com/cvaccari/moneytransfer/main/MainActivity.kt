package br.com.cvaccari.moneytransfer.main

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import br.com.cvaccari.moneytransfer.R
import br.com.cvaccari.moneytransfer.flowmanager.FlowManager
import br.com.cvaccari.moneytransfer.moneytransference.MoneyTransferenceFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFragment(MainFragment.getInstance())
    }

    fun showFragment(fragment: Fragment) {
        FlowManager.showFragment(MainFragment.getInstance(), supportFragmentManager)
    }

    override fun onBackPressed() {
        if(supportFragmentManager.findFragmentById(R.id.fragment_content) is MainContract.View) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}
