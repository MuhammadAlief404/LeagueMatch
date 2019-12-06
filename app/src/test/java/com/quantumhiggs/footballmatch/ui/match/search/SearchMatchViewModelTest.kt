package com.quantumhiggs.footballmatch.ui.match.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import com.quantumhiggs.footballmatch.model.Sports
import com.quantumhiggs.footballmatch.repository.FootballRepository
import com.quantumhiggs.footballmatch.repository.FootballRepositoryCallback
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SearchMatchViewModelTest {

    private lateinit var viewModel: SearchMatchViewModel

    @Mock
    private lateinit var footballResponse: Sports

    @Mock
    private lateinit var footballRepository: FootballRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    var name = ""


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = SearchMatchViewModel(name, footballRepository)
        viewModel.matchName = name
    }

    @Test
    fun get_detail_league() {

        name = "United"

        viewModel.getListMatch(name)

        argumentCaptor<FootballRepositoryCallback<Sports?>>().apply {
            verify(footballRepository).getListMatch(eq(name), capture())
            firstValue.onDataLoaded(footballResponse)
        }
        verify(viewModel.setListMatch().value)
    }
}