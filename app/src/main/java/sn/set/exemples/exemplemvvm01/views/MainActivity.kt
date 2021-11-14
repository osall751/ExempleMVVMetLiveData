package sn.set.exemples.exemplemvvm01.views

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import sn.set.exemples.exemplemvvm01.R
import sn.set.exemples.exemplemvvm01.api.RetrofitClient
import sn.set.exemples.exemplemvvm01.api.RetrofitService
import sn.set.exemples.exemplemvvm01.models.MonAdaptateur
import sn.set.exemples.exemplemvvm01.models.RendezVous

class MainActivity : AppCompatActivity() {
    private lateinit var jwt: String
    private lateinit var service: RetrofitService
    private lateinit var loadProgress: ProgressBar
    private lateinit var recyclerview: RecyclerView
    private var data = RendezVous()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialisation du Service
        service = RetrofitClient.getClient()

//        jwt = intent.getStringExtra("jwt")!!
//        txtV = findViewById<TextView>(R.id.txtHello)
//        txtV.text = " Le Token est " + jwt

        // getting the recyclerview
        recyclerview = findViewById(R.id.recycleview)
        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // Lancement du Progress Bar et Chargement des RV
        loadProgress = findViewById(R.id.progressBar)
        loadProgress.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.Main) {
            getAllRendezVous()
        }

    }

    private suspend fun getAllRendezVous() {
        supervisorScope {
            launch {
                val response = service.getAllRendezVous()
                if (response.isSuccessful) {
                    loadProgress.visibility = View.INVISIBLE
                    val rvsResponse = response.body()!!
                    data.addAll(rvsResponse)
                    // This will pass the ArrayList to our Adapter
                    val adapter = MonAdaptateur(data, service)
                    // Setting the Adapter with the recyclerview
                    recyclerview.adapter = adapter
                }
            }
        }
    }
}