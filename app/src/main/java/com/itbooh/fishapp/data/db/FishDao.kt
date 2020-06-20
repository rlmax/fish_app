package com.itbooh.fishapp.data.db

import androidx.room.*
import com.itbooh.fishapp.data.model.Category
import com.itbooh.fishapp.data.model.Favourite
import com.itbooh.fishapp.data.model.Fish

@Dao
interface FishDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFish(food: Fish): Long

    @Delete
    fun deleteFish(food: Fish): Int

    @Query("SELECT * from Fish")
    fun selectAllFish(): MutableList<Fish>

    @Query("SELECT * FROM Fish WHERE id=:id ")
    fun loadSingle(id: Int?): MutableList<Fish>

    @Query("SELECT * FROM Fish WHERE cat_id=:cat_id ")
    fun loadCatId(cat_id: String): MutableList<Fish>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: Category): Long

    @Query("SELECT * from category")
    fun selectAllCategory(): MutableList<Category>

    @Query("SELECT * FROM Fish WHERE story_title LIKE '%' || :search || '%'")
    fun loadSearch(search: String?): MutableList<Fish>

    @Query("SELECT * FROM Fish WHERE cat_id=:cat_id AND story_title LIKE '%' || :search || '%'")
    fun loadCatIdSearch(cat_id: String,search: String?): MutableList<Fish>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavourite(favourite: Favourite): Long

    @Query("SELECT * from favourite")
    fun selectAllFavourite(): MutableList<Favourite>

    @Query("SELECT * FROM favourite WHERE id=:id ")
    fun loadSingleFav(id: Int?): MutableList<Favourite>

}