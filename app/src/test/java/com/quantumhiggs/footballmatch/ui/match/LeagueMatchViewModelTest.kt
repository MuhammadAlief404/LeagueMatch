package com.quantumhiggs.footballmatch.ui.match

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.quantumhiggs.footballmatch.getOrAwaitValue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations


class LeagueMatchViewModelTest {
    private lateinit var viewModel: LeagueMatchViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    var id = ""


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = LeagueMatchViewModel()
    }

    @Test
    fun get_list_league_previous() {

        id = "4387"

        viewModel.getListPrevMatch(id)

        Assert.assertNotNull(viewModel.listPrevMatch.getOrAwaitValue())
        Assert.assertTrue(viewModel.listPrevMatch.getOrAwaitValue().events.size > 2)
    }

    @Test
    fun get_list_league_next() {
        id = "4387"

        viewModel.getListNextMatch(id)

        Assert.assertNotNull(viewModel.listNextMatch.getOrAwaitValue())
        Assert.assertTrue(viewModel.listNextMatch.getOrAwaitValue().events.size > 2)
    }

}