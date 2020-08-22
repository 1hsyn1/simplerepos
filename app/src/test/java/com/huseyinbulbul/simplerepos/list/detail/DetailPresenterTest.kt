package com.huseyinbulbul.simplerepos.list.detail

import com.huseyinbulbul.simplerepos.R
import com.huseyinbulbul.simplerepos.common.DataManager
import com.huseyinbulbul.simplerepos.common.FavouriteManager
import com.huseyinbulbul.simplerepos.common.model.Repository
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DetailPresenterTest {
    private val view = spyk<IDetailView>()
    private val manager = mockk<DataManager>(relaxed = true)
    private val favouriteManager = mockk<FavouriteManager>(relaxed = true)
    private lateinit var presenter: DetailPresenter

    @BeforeAll
    fun setup(){
        presenter = DetailPresenter(view, manager, favouriteManager)
    }

    @Test
    fun viewReadyTest(){
        val repo = mockk<Repository>()
        every { manager.getSelectedRepository() } returns repo
        every { repo.name } returns "test"

        presenter.viewReady()

        verify {
            manager.getSelectedRepository()
            view.setLeftIcon(R.drawable.ic_back)
            view.setRightIcon(R.drawable.ic_star)
            view.showRepo(repo)
        }
    }

    @Test
    fun viewReadyEmptySelectionTest(){
        every { manager.getSelectedRepository() } returns null

        presenter.viewReady()

        verify {
            view.close()
        }
    }

    @Test
    fun addFavouriteTest(){
        val repo = mockk<Repository>()
        every { manager.getSelectedRepository() } returns repo
        every { repo.id } returns 12
        every { favouriteManager.isFavourite("12") } returns false

        presenter.favouriteSelected()

        verify {
            favouriteManager.addFavourite("12")
            view.showRepo(repo)
        }
    }

    @Test
    fun removeFavourite(){
        val repo = mockk<Repository>()
        every { manager.getSelectedRepository() } returns repo
        every { repo.id } returns 12
        every { favouriteManager.isFavourite("12") } returns true

        presenter.favouriteSelected()

        verify {
            favouriteManager.addFavourite("12")
            view.showRepo(repo)
        }
    }
}