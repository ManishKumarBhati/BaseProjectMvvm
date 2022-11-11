package com.bmk.data.doa

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bmk.data.util.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class TableData(
    @PrimaryKey @JvmField val id: Int,
    @JvmField val email: String,
    @JvmField val firstName: String,
    @JvmField val lastName: String,
    @JvmField val avatar: String
)

@Dao
interface DoaService {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<TableData>)

    @Query("SELECT * FROM $TABLE_NAME order by id desc LIMIT 1")
    fun getUserDetails(): LiveData<TableData>

 @Query("SELECT * FROM $TABLE_NAME")
    fun getAllUserDetails(): LiveData<List<TableData>>

    @Query("update $TABLE_NAME set firstName=:name where id =:id")
    suspend fun update(id: Int, name: String)

    @Query("delete from $TABLE_NAME where id =:id")
    suspend fun delete(id: Int)

}