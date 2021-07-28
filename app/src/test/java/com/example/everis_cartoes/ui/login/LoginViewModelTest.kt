package com.example.everis_cartoes.ui.login

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.everis_cartoes.data.model.login.LoginFireBaseModel
import com.example.everis_cartoes.repository.login.LoginRepository
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock

@RunWith(JUnit4::class)
class LoginViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var loginActionViewObserver: Observer<LoginActionView>

    private lateinit var viewMdel: LoginViewModel
}

class MockRepository(private val result: LoginActionView
): LoginRepository {
    override suspend fun loginFireBase(
        dataLogin: LoginFireBaseModel,
        callbackSuccess: () -> Unit,
        callbackError: (error: String) -> Unit
    ) {
        callbackSuccess.invoke()
    }

    override suspend fun verifySectionFireBase(
        callbackSuccessVerifySection: () -> Unit,
        callbackErrorVerifySection: () -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun logoutFireBase() {
        TODO("Not yet implemented")
    }

}