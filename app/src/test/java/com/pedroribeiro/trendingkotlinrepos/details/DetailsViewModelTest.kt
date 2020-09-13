package com.pedroribeiro.trendingkotlinrepos.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.pedroribeiro.trendingkotlinrepos.schedulers.TrampolineSchedulerProviderImpl
import com.pedroribeiro.trendingkotlinrepos.utils.LifecycleOwnerUtils
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.internal.schedulers.TrampolineScheduler
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class DetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailsViewModel
    private val lifecycleOwner = mockk<LifecycleOwner>(relaxed = true)

    @Before
    fun setup() {
        viewModel = DetailsViewModel(
            TrampolineSchedulerProviderImpl()
        )
        LifecycleOwnerUtils.setupLifecycleOwner(lifecycleOwner)
    }

    @Test
    fun `GIVEN user is on details screen WHEN user clicks up arrow THEN navigate up`() {
        val observer = mockk<Observer<DetailsViewModel.Navigation>>(relaxed = true)
        viewModel.navigation.observe(lifecycleOwner, observer)

        viewModel.onUpClick()

        verify {
            observer.onChanged(DetailsViewModel.Navigation.Up)
        }
    }
}