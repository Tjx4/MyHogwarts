package net.sure.myhogwarts.features.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wang.avi.AVLoadingIndicatorView
import net.sure.myhogwarts.R
import net.sure.myhogwarts.constants.CATID
import net.sure.myhogwarts.constants.TITLE
import net.sure.myhogwarts.features.base.fragments.BaseDialogFragment

class LoadingSpinnerFragment : BaseDialogFragment() {

    private var loadingTxt: TextView? = null
    private var loader: AVLoadingIndicatorView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.setDimAmount(1f)

        val parentView = super.onCreateView(inflater, container, savedInstanceState)
        loader = parentView?.findViewById(R.id.progressBarLoading)
        loadingTxt = parentView?.findViewById(R.id.txtLoading)

        val loadingMessage = arguments?.getString(TITLE)
        loadingTxt?.text = loadingMessage

        return parentView
    }

    override fun hideLoaderAndShowEnterMessage() {
        super.hideLoaderAndShowEnterMessage()
        loader!!.visibility = View.INVISIBLE
        loadingTxt!!.text = "Opening app..."
    }

    companion object {
        fun newInstance(catId: String): BaseDialogFragment {
            val catFragment = LoadingSpinnerFragment()
            val bundle = Bundle()
            bundle.putString(CATID, catId)
            catFragment.arguments = bundle
            return catFragment
        }
    }

}