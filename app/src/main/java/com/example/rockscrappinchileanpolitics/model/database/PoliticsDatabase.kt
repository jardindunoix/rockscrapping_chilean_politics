package com.example.rockscrappinchileanpolitics.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rockscrappinchileanpolitics.model.database.comunal_dao.ConsejalesDao
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ComunaConsejalActualEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualDetalleEntity
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns

@Database(entities = [ConsejalActualDetalleEntity::class, ComunaConsejalActualEntity::class],
	version = 1, exportSchema = false)
abstract class PoliticsDatabase:RoomDatabase() {
	
	abstract fun getDaoFromDatabase():ConsejalesDao
	
	companion object {
		
		private var databaseInstance:PoliticsDatabase? = null
		
		fun getDatabase(context:Context):PoliticsDatabase {
			if (databaseInstance == null) {
				synchronized(this) {
					databaseInstance = Room.databaseBuilder(context, PoliticsDatabase::class.java,
						StaticStrigns.DATABASE_NAME).build()
					//                        .fallbackToDestructiveMigration()
				}
			}
			return databaseInstance !!
		}
	}
}