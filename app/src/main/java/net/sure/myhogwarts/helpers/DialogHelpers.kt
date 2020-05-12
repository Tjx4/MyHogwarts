package net.sure.myhogwarts.helpers

import net.sure.myhogwarts.R
import net.sure.myhogwarts.constants.LAYOUT
import net.sure.myhogwarts.constants.TITLE
import net.sure.myhogwarts.features.base.activity.BaseActivity
import net.sure.myhogwarts.features.base.fragments.BaseDialogFragment
import net.sure.myhogwarts.features.fragments.LoadingSpinnerFragment

fun showLoadingDialog(loadingMessage: String, activity: BaseActivity) {
    var loadingSpinnerFragment = LoadingSpinnerFragment.newInstance("")
    showDialogFragment(loadingMessage, R.layout.fragment_loading_spinner, loadingSpinnerFragment, activity)
    loadingSpinnerFragment.isCancelable = false
}

fun hideCurrentLoadingDialog(activity: BaseActivity) {
    activity.activeDialogFragment?.dismiss()
}

fun showDialogFragment(title: String, Layout: Int, newFragmentBaseBase: BaseDialogFragment, activity: BaseActivity) {
    activity?.activeDialogFragment?.dismiss()
    val ft = activity.supportFragmentManager.beginTransaction()
    var newFragment = getFragmentDialog(title, Layout, newFragmentBaseBase)
    newFragment.show(ft, "dialog")
    activity.activeDialogFragment = newFragment
}

private fun getFragmentDialog(title: String, Layout: Int, newFragmentBaseBase: BaseDialogFragment) : BaseDialogFragment {
    val payload = newFragmentBaseBase.arguments
    payload?.putString(TITLE, title)
    payload?.putInt(LAYOUT, Layout)

    newFragmentBaseBase.arguments = payload
    return newFragmentBaseBase
}

