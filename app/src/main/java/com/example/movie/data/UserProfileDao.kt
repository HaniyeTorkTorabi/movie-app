package com.example.movie.data

import androidx.room.*
import com.example.movie.model.UserProfileEntity

@Dao
interface UserProfileDao {
    @Query("SELECT * FROM user_profile WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(profile: UserProfileEntity)

    @Query("DELETE FROM user_profile")
    suspend fun clear()
}
