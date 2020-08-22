package com.huseyinbulbul.simplerepos.list

import com.huseyinbulbul.simplerepos.R
import com.huseyinbulbul.simplerepos.common.DataManager
import com.huseyinbulbul.simplerepos.common.model.Repository
import com.huseyinbulbul.simplerepos.list.detail.DetailAct
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainPresenterTest {
    private val view= spyk<IMainView>()
    private val manager = mockk<DataManager>(relaxed = true)
    private lateinit var presenter: MainPresenter

    @BeforeAll
    fun setup(){
        presenter = MainPresenter(view, manager)
    }

    @Test
    fun viewReadyTest(){
        presenter.viewReady()

        verify {
            view.showLeftIcon(false)
            view.showRightIcon(false)
            view.setTitle(R.string.app_name)
        }
    }

    @Test
    fun userSubmittedEmptyTextTest(){
        presenter.userSubmitted("")

        verify {
            view.showMessage(R.string.invalid_username)
        }
    }

    @Test
    fun userSubmittedTest(){
        val text = "text"
        presenter.userSubmitted(text)

        verify {
            view.showLoading()
            view.hideKeyboard()
            manager.getListFor(text, any(), any())
        }
    }

    @Test
    fun repoSelected(){
        val repo = mockk<Repository>()
        presenter.repoSelected(repo)

        verify {
            manager.selectRepository(repo)
            view.openActivity(DetailAct::class.java)
        }
    }
}