package com.example.rockscrappinchileanpolitics.c_model.c_pojos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rockscrappinchileanpolitics.d_utilities.static_strings.StaticUtils

@Entity(tableName = StaticUtils.GALLERY_TABLE)
data class HeaderHomePojo(
	@PrimaryKey(autoGenerate = true)
	var idHeaderHome:Int = 0,
	var name:String = "",
	var webPictureSite:String = ""
)
