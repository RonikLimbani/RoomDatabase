package com.ronik.roomdatabasewithmvvm.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAllUser(): Flow<List<User>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)
    @Update
    fun updateUser(user: User)
    @Delete
    fun deleteUser(user: User)
    @Query("DELETE FROM user WHERE id = :userId")
    fun deleteUserById(userId: Long)

}
