package com.example.rockscrappinchileanpolitics.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.alcaldes.AlcaldeActualEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.alcaldes.AlcaldeCandidatoEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalCandidatoEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.legislativo.diputados.DiputadoActualEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.legislativo.diputados.DiputadoCandidatoEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.legislativo.senadores.SenadorActualEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.legislativo.senadores.SenadorCandidatoEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.partidos_politicos.PartidoPoliticoEntity
import com.example.rockscrappinchileanpolitics.utilities.services.StaticStrigns

@Database(
	entities = [AlcaldeActualEntity::class, AlcaldeCandidatoEntity::class, ConsejalActualEntity::class, ConsejalCandidatoEntity::class, DiputadoActualEntity::class, DiputadoCandidatoEntity::class, SenadorActualEntity::class, SenadorCandidatoEntity::class, PartidoPoliticoEntity::class],
	version = 1, exportSchema = false)
abstract class PoliticsDatabase:RoomDatabase() {
	
	abstract fun getDao():PoliticsDao
	
	companion object {
		
		private var database:PoliticsDatabase? = null
		
		fun getDatabase(contex:Context):PoliticsDatabase {
			if (database == null) {
				synchronized(this) {
					database = Room.databaseBuilder(contex, PoliticsDatabase::class.java,
						StaticStrigns.DATABASE_NAME).build()
					// .fallbackToDestructiveMigration().
				}
			}
			return database !!
		}
	}
}