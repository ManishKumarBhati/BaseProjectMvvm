package com.citiustech.baseproject.data.db

import androidx.room.*
import com.squareup.moshi.Json
import io.reactivex.Observable

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

    @Query("SELECT * FROM table_name LIMIT 1")
    fun getData(): Observable<TableData>

}