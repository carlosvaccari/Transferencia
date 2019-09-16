package br.com.cvaccari.moneytransfer.flowmanager


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cvaccari.moneytransfer.ContactsAdapter
import br.com.cvaccari.moneytransfer.R
import br.com.cvaccari.moneytransfer.moneytransference.MoneyTransferenceFragment
import kotlinx.android.synthetic.main.content_contacts.*

class FlowManager(fragmentManager: FragmentManager) {

    companion object {
        fun showFragment(fragment: Fragment, fragmentManager: FragmentManager?) {
            fragmentManager?.apply {
                beginTransaction()
                    .setCustomAnimations(
                        R.anim.push_left_in,
                        R.anim.push_left_out,
                        R.anim.close_right_in,
                        R.anim.close_right_out
                    )
                    .replace(R.id.fragment_content, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }

        fun removeFragment(fragmentManager: FragmentManager?) {
            fragmentManager?.apply {
                popBackStackImmediate()
            }
        }

        fun showDialog(dialogFragment: DialogFragment, fragmentManager: FragmentManager?) {
            fragmentManager?.apply {
                val ft = beginTransaction()
                val prev = findFragmentByTag("dialog")
                if (prev != null) {
                    ft.remove(prev)
                }
                ft.addToBackStack(null)
                dialogFragment.show(ft, "dialog")
            }
        }
    }
}