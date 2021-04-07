package com.example.rockscrappinchileanpolitics.utilities.objects.entities.header_home

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rockscrappinchileanpolitics.utilities.services.static_strings.StaticUtils

@Entity(tableName = StaticUtils.HEADER_HOME_TABLE)
data class HeaderHomeEntity(
	@PrimaryKey(autoGenerate = true)
	var idHeaderHome:Int = 0,
	var name:String = "",
	var webPictureSite:String = ""
)
