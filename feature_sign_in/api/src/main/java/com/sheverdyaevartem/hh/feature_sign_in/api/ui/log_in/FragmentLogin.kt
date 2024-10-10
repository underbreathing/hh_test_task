package com.sheverdyaevartem.hh.feature_sign_in.api.ui.log_in

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.sheverdyaevartem.hh.feature_sign_in.api.ui.log_in.view_model.SignInViewModel
import com.sheverdyaevartem.hh.feature_sign_in.api.ui.log_in.view_model.states.CodeSendState
import com.sheverdyaevartem.hh.feature_sign_in.api.ui.navigation.LoginNavigator
import com.sheverdyaevartem.hh.feature_sign_in.databinding.FragmentLoginBinding
import org.koin.android.ext.android.inject

class FragmentLogin : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignInViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun emailIsCorrect(enteredEmail: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(enteredEmail).matches()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etEmailField.addTextChangedListener(onTextChanged = { charSequence, _, _, _ ->
            charSequence?.isEmpty()?.let { isEmpty ->
                onEditTextEmailChanged(isEmpty)
            }
        })

        binding.bNext.setOnClickListener {
            val enteredEmail = binding.etEmailField.text.toString()
            context?.let {
                if (emailIsCorrect(enteredEmail)) {
                    viewModel.emailLogin(enteredEmail)
                } else {
                    binding.etEmailField.error = "Неверный формат email\nПример: user@example.com"
                }
            }

        }

        viewModel.codeSend.observe(viewLifecycleOwner) { codeSendState ->
            when (codeSendState) {
                CodeSendState.InternetError -> {
                    showSnackBar("Проверьте соединение с интернетом")
                }

                is CodeSendState.NotFound -> {
                    showSnackBar("Пользователь ${codeSendState.email}\n не зарегистрирован")
                }

                CodeSendState.RequestError -> {
                    showSnackBar("Ошибка приложения\nобратитесь в поддержку")
                }

                CodeSendState.ServerError -> {
                    showSnackBar("На сервере ведутся работы")
                }

                is CodeSendState.Success -> {
                    (requireActivity() as LoginNavigator).navigateToCodeEntry(codeSendState.email)
                }
            }
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.etEmailField, message, Snackbar.LENGTH_LONG)
            .setAnchorView(binding.tvSearchJobTitle).show()
    }

    private fun onEditTextEmailChanged(isEmpty: Boolean) {
        binding.bNext.isEnabled = !isEmpty
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}