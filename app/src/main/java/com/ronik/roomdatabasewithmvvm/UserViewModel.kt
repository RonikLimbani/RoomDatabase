package com.ronik.roomdatabasewithmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ronik.roomdatabasewithmvvm.db.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {





    suspend fun inserUser(user : User){
        viewModelScope.async(Dispatchers.IO) {
            userRepository.insertUser(user)
        }.await()
    }

    suspend fun updateUser(user : User){
        viewModelScope.async(Dispatchers.IO) {
            userRepository.updateUser(user)
        }.await()
    }

    fun getAllUsersFlow(): Flow<List<User>> {
        return userRepository.getAllUser()
    }

    suspend fun deleteUser(user : User){
        viewModelScope.async(Dispatchers.IO) {
            userRepository.deleteUser(user)
        }.await()
    }

    suspend fun deleteUserWithId(id : Long){
        viewModelScope.async(Dispatchers.IO) {
            userRepository.deleteUserById(id)
        }.await()
    }





}
