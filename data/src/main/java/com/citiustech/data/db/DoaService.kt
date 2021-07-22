package com.citiustech.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(tableName = "table_name")
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

    @Query("SELECT * FROM table_name order by id desc LIMIT 1")
    fun getData(): LiveData<TableData>

}