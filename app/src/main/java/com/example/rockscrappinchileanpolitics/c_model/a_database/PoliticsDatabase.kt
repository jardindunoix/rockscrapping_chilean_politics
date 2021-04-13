package com.example.rockscrappinchileanpolitics.c_model.a_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rockscrappinchileanpolitics.c_model.b_entities.*
import com.example.rockscrappinchileanpolitics.d_utilities.static_strings.StaticUtils

@Database(entities = [
	GalleryEntity::class,
	AlcaldeActualEntity::class,
	AlcaldeCandidatoEntity::class,
	ComunaConsejalActualEntity::class,
	ConsejalActualDetalleEntity::class,
	DiputadoActualEntity::class,
	DiputadoCandidatoEntity::class,
	SenadorActualEntity::class,
	SenadorCandidatoEntity::class,
	PartidoPoliticoEntity::class],
	version = 1, exportSchema = false)
abstract class PoliticsDatabase:RoomDatabase() {
	
	abstract fun getDaoFromDatabase():DiputadosActualesDao
	
	companion object {
		
		private var databaseInstance:PoliticsDatabase? = null
		
		fun getDatabase(context:Context):PoliticsDatabase {
			if (databaseInstance == null) {
				synchronized(this) {
					databaseInstance = Room.databaseBuilder(context, PoliticsDatabase::class.java,
						StaticUtils.DATABASE_NAME).build()
					//                        .fallbackToDestructiveMigration()
				}
			}
			return databaseInstance !!
		}
	}
}