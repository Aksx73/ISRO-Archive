package com.absut.isro.archive.data.local.dao

import android.app.Application
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.absut.isro.archive.data.local.ISRODatabase
import com.absut.isro.archive.data.model.Spacecraft
import com.strings.attached.musiclibrary.util.TestUtil
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(AndroidJUnit4::class)
class SpacecraftDaoTest {

    private lateinit var dao:SpacecraftDao  //subject under test
    private lateinit var database: ISRODatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ISRODatabase::class.java)
            .build()
        dao = database.getSpacecraftDao()
    }


    @Test
    fun add_and_get_spacecraft_Test() = runBlocking {
        val spacecraftList = TestUtil.createTestSpacecrafts(4)

        dao.addSpacecrafts(spacecraftList) //added to db

        val spacecraftsFromDB = dao.getSpacecrafts()  //get all the list added

       // Truth.assertThat(spacecraftsFromDB).isEqualTo(spacecraftList)  // check if above created list is equal to list in db
        assertThat(spacecraftsFromDB, equalTo(spacecraftList))

    }

    @After
    fun tearDown() {
        database.close()
    }
}