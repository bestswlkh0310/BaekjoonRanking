package com.bestswlkh0310.presentation.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.bestswlkh0310.presentation.R
import com.bestswlkh0310.presentation.BR
import com.bestswlkh0310.presentation.base.BaseViewModel.Companion.NETWORK_ERROR
import com.bestswlkh0310.presentation.feature.onboard.OnBoardActivity
import com.bestswlkh0310.presentation.util.DgswBJRankApplication
import java.lang.reflect.ParameterizedType
import java.util.Locale
import java.util.Objects

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : Fragment() {
    protected lateinit var mBinding: VB
    protected lateinit var mViewModel: VM

    protected abstract val viewModel: VM

    private var isLoad = false
    private var isLoad2 = false

    protected abstract fun observerViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate (
            inflater,
            layoutRes(),
            container, false
        )
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        performDataBinding()
        observerViewModel()
    }

    private fun performDataBinding() {
        mViewModel = if (::mViewModel.isInitialized) mViewModel else viewModel
        mBinding.setVariable(BR.vm, mViewModel)
        mBinding.lifecycleOwner = this
    }

    protected fun bindingViewEvent(action: (event: Any) -> Unit) {
        viewModel.viewEvent.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { event ->
                action.invoke(event)
                when (event) {
                    NETWORK_ERROR -> {
                        requireActivity().finish()
                        val intent = Intent(context, OnBoardActivity::class.java)
                        startActivity(intent)
                        requireActivity().finishAffinity()
                        showToast("토큰이 만료되었어요")
                        DgswBJRankApplication.prefs.isAuthToken = false
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mBinding.isInitialized) mBinding.unbind()
    }

    @LayoutRes
    private fun layoutRes(): Int {
        val split = (
                (Objects.requireNonNull(javaClass.genericSuperclass) as ParameterizedType)
                    .actualTypeArguments[0] as Class<*>
                )
            .simpleName.replace("Binding$".toRegex(), "")
            .split("(?<=.)(?=\\p{Upper})".toRegex())
            .dropLastWhile { it.isEmpty() }.toTypedArray()

        val name = StringBuilder()

        for (i in split.indices) {
            name.append(split[i].lowercase(Locale.ROOT))
            if (i != split.size - 1)
                name.append("_")
        }

        try {
            return R.layout::class.java.getField(name.toString()).getInt(R.layout::class.java)
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }

        return 0
    }

    fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

}
