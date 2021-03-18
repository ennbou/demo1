package com.ennbou.mvvm.database

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ennbou.mvvm.dao.UserDao
import com.ennbou.mvvm.entities.User
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.io.IOException

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class UserDBTest {

    private lateinit var db: UserDB
    private lateinit var userDao: UserDao

    @Before
    fun `create user db`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, UserDB::class.java
        ).build()
        userDao = db.userDao()
    }

    @Test
    fun `insert user with (id=1) and get 1`() = runBlocking {
        val user = User(1, "name 1", "username 1", "email1@ennbou.com", "0623343243", "ennbou.com")
        val userId = userDao.insert(user)
        assertThat(userId).isAtMost(1)
    }

    @Test
    fun `delete user who has id = 1 will return 1`() = runBlocking {
        val id = 1
        val expected = 1
        val user = User(
            id.toLong(),
            "name 1",
            "username 1",
            "email1@ennbou.com",
            "0623343243",
            "ennbou.com"
        )
        val userId = userDao.insert(user)
        assertThat(userId).isAtMost(expected)
        val result = userDao.delete(userId)
        assertThat(result).isAtMost(expected)
    }

    @Test
    fun `find user who has id=1 will return user (id=1, name= name1)`() = runBlocking {
        val id = 1
        val user = User(1, "name 1", "username 1", "email1@ennbou.com", "0623343243", "ennbou.com")
        val userId = userDao.insert(user)
        assertThat(userId).isAtMost(1)
        val userResult = userDao.get(id.toLong())
        assertThat(userResult).isEqualTo(user)
    }

    @After
    @Throws(IOException::class)
    fun `close user db`() {
        db.close()
    }


}