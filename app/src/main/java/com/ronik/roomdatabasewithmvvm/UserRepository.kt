package com.ronik.roomdatabasewithmvvm

import com.ronik.roomdatabasewithmvvm.db.UserDao
import com.ronik.roomdatabasewithmvvm.db.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    fun getAllUser(): Flow<List<User>> {
        return userDao.getAllUser()
    }
    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
    suspend fun deleteUserById(id:Long) {
        userDao.deleteUserById(id)
    }

}
