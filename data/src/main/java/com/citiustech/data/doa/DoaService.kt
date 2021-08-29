package com.citiustech.data.doa

import androidx.lifecycle.LiveData
import androidx.room.*
import com.citiustech.data.util.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class TableData(
    @PrimaryKey @JvmField val id: Int,
    @JvmField val completed: Boolean,
    @JvmField val title: String,
    @JvmField val userId: Int
)

@Dao
interface DoaService {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: TableData)

    @Query("SELECT * FROM $TABLE_NAME order by id desc LIMIT 1")
    fun getData(): LiveData<TableData>

    @Query("update $TABLE_NAME set title=:ptitle where id =:pid")
    suspend fun update(pid: Int, ptitle: String)

    @Query("delete from $TABLE_NAME where id =:pid")
    suspend fun delete(pid: Int)

}