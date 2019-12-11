package com.quantumhiggs.footballmatch.ui.match.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.quantumhiggs.footballmatch.getOrAwaitValue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

class DetailMatchViewModelTest {

    private lateinit var viewModel: DetailMatchViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    var id = ""


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailMatchViewModel()
    }

    @Test
    fun get_detail_match() {

        id = "441613"

        viewModel.getDetailMatch(id)

        print(viewModel.detailMatch.getOrAwaitValue())

        Assert.assertNotNull(viewModel.detailMatch.getOrAwaitValue())
        Assert.assertEquals(viewModel.detailMatch.getOrAwaitValue().events[0].idEvent, id)
        Assert.assertEquals(
            viewModel.detailMatch.getOrAwaitValue().events[0].strEvent,
            "Liverpool vs Swansea"
        )
    }

    @Test
    fun get_detail_home() {
        id = "133605"

        viewModel.getDetailHome(id)

        Assert.assertNotNull(viewModel.homeDetail.getOrAwaitValue())
        Assert.assertEquals(viewModel.homeDetail.getOrAwaitValue().teams[0].strTeam, "QPR")
    }

    @Test
    fun get_detail_away() {
        id = "133604"

        viewModel.getAwayDetail(id)

        Assert.assertNotNull(viewModel.awayDetail.getOrAwaitValue())
        Assert.assertEquals(viewModel.awayDetail.getOrAwaitValue().teams[0].strTeam, "Arsenal")
    }
}