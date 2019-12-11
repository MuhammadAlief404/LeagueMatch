package com.quantumhiggs.footballmatch.ui.match.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.quantumhiggs.footballmatch.getOrAwaitValue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

class SearchMatchViewModelTest {

    private lateinit var viewModel: SearchMatchViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    var name = ""


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = SearchMatchViewModel(name)
    }

    @Test
    fun get_list_match_by_name() {

        name = "United"

        viewModel.getListMatch(name)

        Assert.assertNotNull(viewModel.setListMatch().getOrAwaitValue())
        Assert.assertTrue(viewModel.setListMatch().getOrAwaitValue().event.size > 5)
    }
}