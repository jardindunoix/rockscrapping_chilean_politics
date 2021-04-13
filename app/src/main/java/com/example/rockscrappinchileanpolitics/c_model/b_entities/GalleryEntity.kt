package com.example.rockscrappinchileanpolitics.c_model.b_entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rockscrappinchileanpolitics.d_utilities.static_strings.StaticUtils

@Entity(tableName = StaticUtils.HEADER_HOME_TABLE)
data class GalleryEntity(
	@PrimaryKey(autoGenerate = true)
	var idHeaderHome:Int = 0,
	var name:String = "",
	var webPictureSite:String = ""
)
