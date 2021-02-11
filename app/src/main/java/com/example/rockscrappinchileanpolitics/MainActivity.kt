package com.example.rockscrappinchileanpolitics

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.example.rockscrappinchileanpolitics.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import java.io.IOException

class MainActivity : AppCompatActivity() {
	
	private lateinit var binding : ActivityMainBinding
	
	// private val URL_ = "https://www.tutorialspoint.com/css_online_training/index.asp"
	private val URL_ = "https://www.camara.cl/diputados/diputados.aspx#mostrarDiputados"
	private val CLASS_NAME = "grid-2"
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		title = "KotlinApp"
		binding.btnView.setOnClickListener {
//            WebScratch().execute()
			webScratch()
		}
	}
	
	private fun webScratch() =
		with(binding) {
			var words : String = ""
			CoroutineScope(Dispatchers.IO).launch {
				try {
					val documents = Jsoup.connect(URL_)
							.get()
							.body()
							.getElementsByClass(CLASS_NAME)
					// .body()
					// .getElementsByTag("article")
					// .toggleClass(CLASS_NAME)
					for (docu in documents) {
						words += docu.child(1)
								         .text()
								         .toString() + "\n"
					}
					
					runOnUiThread {
						textView.text = words
					}
					
				} catch (e : IOException) {
					e.printStackTrace()
					textView.text = "no se pudo acceder"
				} catch (e : NetworkOnMainThreadException) {
					textView.text = "NetworkOnMainThreadException ${
						e.printStackTrace()
								.toString()
					}"
				}
			}
		}
	/* inner class WebScratch : AsyncTask<Void, Void, Void>() {

		 private lateinit var words: String
		 override fun doInBackground(vararg params: Void): Void? = with(binding) {
			 if (Build.VERSION.SDK_INT > 9) {
				 val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
				 StrictMode.setThreadPolicy(policy)
			 }
			 val document = Jsoup.connect(URL_)
				 .get()
			 document.getElementsByClass(CLASS_NAME)
			 words = document.text()
			 textView.text = words
			 return null
		 }

		 override fun onPostExecute(aVoid: Void?) =
			 with(binding) {
				 super.onPostExecute(aVoid)
				 textView.text = words
			 }
	 }*/
}