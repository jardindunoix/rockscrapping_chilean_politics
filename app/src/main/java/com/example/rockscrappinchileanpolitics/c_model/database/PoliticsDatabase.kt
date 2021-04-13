package com.example.rockscrappinchileanpolitics.c_model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rockscrappinchileanpolitics.c_model.database.legislaitivo_dao.DiputadosActualesDao
import com.example.rockscrappinchileanpolitics.c_model.entities.comunal.alcaldes.AlcaldeActualEntity
import com.example.rockscrappinchileanpolitics.c_model.entities.comunal.alcaldes.AlcaldeCandidatoEntity
import com.example.rockscrappinchileanpolitics.c_model.entities.comunal.consejales.ComunaConsejalActualEntity
import com.example.rockscrappinchileanpolitics.c_model.entities.comunal.consejales.ConsejalActualDetalleEntity
import com.example.rockscrappinchileanpolitics.c_model.entities.header_home.HeaderHomeEntity
import com.example.rockscrappinchileanpolitics.c_model.entities.legislativo.diputados.DiputadoActualEntity
import com.example.rockscrappinchileanpolitics.c_model.entities.legislativo.diputados.DiputadoCandidatoEntity
import com.example.rockscrappinchileanpolitics.c_model.entities.legislativo.senadores.SenadorActualEntity
import com.example.rockscrappinchileanpolitics.c_model.entities.legislativo.senadores.SenadorCandidatoEntity
import com.example.rockscrappinchileanpolitics.c_model.entities.partidos_politicos.PartidoPoliticoEntity
import com.example.rockscrappinchileanpolitics.d_utilities.static_strings.StaticUtils

@Database(entities = [
	HeaderHomeEntity::class,
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