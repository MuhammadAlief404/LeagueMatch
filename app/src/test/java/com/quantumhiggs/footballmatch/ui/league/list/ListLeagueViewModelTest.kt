package com.quantumhiggs.footballmatch.ui.league.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.quantumhiggs.footballmatch.getOrAwaitValue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations


class ListLeagueViewModelTest {

    private lateinit var viewModel: ListLeagueViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ListLeagueViewModel()
    }

    @Test
    fun get_list_league() {
        viewModel.getListLeague()

        Assert.assertNotNull(viewModel.listLeague.getOrAwaitValue())

        Assert.assertTrue(viewModel.listLeague.getOrAwaitValue().leagues.size > 5)
    }
}

