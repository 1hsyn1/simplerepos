package com.huseyinbulbul.simplerepos.common

import com.huseyinbulbul.simplerepos.common.model.Repository
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DataManagerTest {
    private var dataManager: DataManager = DataManager()

    @Test
    fun selectRepositoryTest(){
        val repo = mockk<Repository>()

        dataManager.selectRepository(repo)

        assert(dataManager.getSelectedRepository() == repo)
    }

    @Test
    fun deselectRepositoryTest(){
        val repo = mockk<Repository>()

        dataManager.selectRepository(repo)
        dataManager.deselectRepository()

        assert(dataManager.getSelectedRepository() == null)
    }
}