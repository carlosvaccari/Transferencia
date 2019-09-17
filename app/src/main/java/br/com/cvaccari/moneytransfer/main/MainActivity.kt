package br.com.cvaccari.moneytransfer.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.cvaccari.moneytransfer.R
import br.com.cvaccari.moneytransfer.flowmanager.FlowManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFragment()
    }

    fun showFragment() {
        FlowManager.showFragment(MainFragment.getInstance(), supportFragmentManager)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentById(R.id.fragment_content) is MainContract.View) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}
