package com.sheverdyaevartem.hh.feature_sign_in.api.ui.code_entry


import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.sheverdyaevartem.hh.feature_sign_in.R

import com.sheverdyaevartem.hh.feature_sign_in.api.ui.code_entry.view_model.CodeEntryViewModel
import com.sheverdyaevartem.hh.feature_sign_in.api.ui.code_entry.view_model.model.CodeVerifiedState
import com.sheverdyaevartem.hh.feature_sign_in.api.ui.navigation.LoginNavigator
import com.sheverdyaevartem.hh.feature_sign_in.databinding.FragmentCodeEntryBinding
import org.koin.android.ext.android.inject
import java.lang.StringBuilder

class FragmentCodeEntry : Fragment() {

    companion object {

        private const val ENTERED_EMAIL_FLAG = "email flag"

        fun createArgs(email: String): Bundle {
            return bundleOf(ENTERED_EMAIL_FLAG to email)
        }
    }

    private var _binding: FragmentCodeEntryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CodeEntryViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCodeEntryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = arguments?.getString(ENTERED_EMAIL_FLAG)

        binding.tvTitle.text = getString(R.string.code_entry_title, email)

        setAsteriskMask(binding.code1, binding.code2, binding.code3, binding.code4)

        setTextChangeLogic(binding.code1, binding.code2)
        setTextChangeLogic(binding.code2, binding.code3)
        setTextChangeLogic(binding.code3, binding.code4, true)

        setBackspaceBehavior(binding.code4, binding.code3)
        setBackspaceBehavior(binding.code3, binding.code2)
        setBackspaceBehavior(binding.code2, binding.code1)

        binding.bConfirm.setOnClickListener {
            email?.let {
                viewModel.verifySmsCode(it, getCode())
            }
        }

        viewModel.codeVerified.observe(viewLifecycleOwner) { receivedData ->
            when (receivedData) {
                is CodeVerifiedState.Answer -> {
                    if (receivedData.isAccepted) {
                        (requireActivity() as LoginNavigator).navigateToFragmentSearch(receivedData.id)
                    } else {
                        showSnackBar("Неверный код!")
                    }
                }

                CodeVerifiedState.InternetError -> {
                    showSnackBar("Проверьте соединение с интернетом")
                }

                CodeVerifiedState.RequestError -> {
                    showSnackBar("Ошибка приложения\nобратитесь в поддержку")
                }

                CodeVerifiedState.ServerError -> {
                    showSnackBar("На сервере ведутся работы")
                }
            }
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.tvTitle, message, Snackbar.LENGTH_LONG)
            .setAnchorView(binding.tvTitle).show()
    }

    private fun getCode(): String {
        val codeAccumulator = StringBuilder()
        codeAccumulator.append(binding.code1.text)
        codeAccumulator.append(binding.code2.text)
        codeAccumulator.append(binding.code3.text)
        codeAccumulator.append(binding.code4.text)
        return codeAccumulator.toString()
    }

    private fun setButtonConfirmAccess() {
        binding.bConfirm.isEnabled = with(binding) {
            code1.text.isNotEmpty()
                    && code2.text.isNotEmpty()
                    && code3.text.isNotEmpty()
                    && code4.text.isNotEmpty()
        }
    }

    private fun setBackspaceBehavior(currentEditText: EditText, previousEditText: EditText) {
        currentEditText.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                if (currentEditText.text.isEmpty()) {
                    previousEditText.requestFocus()
                    previousEditText.setSelection(previousEditText.text.length)
                }
            }
            false
        }
    }

    private fun setAsteriskMask(vararg editTexts: EditText) {
        editTexts.forEach { editText ->
            editText.transformationMethod = PasswordTransformationMethod.getInstance()

            editText.transformationMethod = object : PasswordTransformationMethod() {
                override fun getTransformation(source: CharSequence, view: View): CharSequence {
                    return object : CharSequence {
                        override val length: Int
                            get() = source.length

                        override fun get(index: Int): Char {
                            return '*'
                        }

                        override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
                            return source.subSequence(startIndex, endIndex)
                        }
                    }
                }
            }
        }
    }

    private fun setTextChangeLogic(current: EditText, next: EditText, nextIsLast: Boolean = false) {
        current.addTextChangedListener(onTextChanged = { charSequence, _, _, _ ->
            if (charSequence?.length == 1) {
                next.requestFocus()
                setButtonConfirmAccess()
            }
        })
        if (nextIsLast) {
            next.addTextChangedListener(onTextChanged = { _, _, _, _ ->
                setButtonConfirmAccess()
            })
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
