package com.example.rockscrappinchileanpolitics.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rockscrappinchileanpolitics.model.database.comunal_dao.ConsejalesDao
import com.example.rockscrappinchileanpolitics.model.database.partidos_politicos_dao.PartidosPoliticosDao
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.alcaldes.AlcaldeActualEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.alcaldes.AlcaldeCandidatoEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ComunaConsejalActualEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.comunal.consejales.ConsejalActualDetalleEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.header_home.HeaderHomeEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.legislativo.diputados.DiputadoActualEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.legislativo.diputados.DiputadoCandidatoEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.legislativo.senadores.SenadorActualEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.legislativo.senadores.SenadorCandidatoEntity
import com.example.rockscrappinchileanpolitics.utilities.objects.entities.partidos_politicos.PartidoPoliticoEntity
import com.example.rockscrappinchileanpolitics.utilities.services.static_strings.StaticUtils

@Database(
	entities = [
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
	
	abstract fun getDaoFromDatabase():PartidosPoliticosDao
	
	companion object {
		
		private var databaseInstance:PoliticsDatabase? = null
		
		fun getDatabase(context:Context):PoliticsDatabase {
			if (databaseInstance == null) {
				synchronized(this) {
					databaseInstance = Room.databaseBuilder(
						context, PoliticsDatabase::class.java,
						StaticUtils.DATABASE_NAME
					).build()
					//                        .fallbackToDestructiveMigration()
				}
			}
			return databaseInstance !!
		}
	}
}