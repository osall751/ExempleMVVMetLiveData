package sn.set.exemples.testretrofit_2.models


import java.time.LocalDateTime

data class RendezVousItem(
//    var id: Int = 10000,
    var description: String = "",
    var lieu: String = "",
    var date: String = LocalDateTime.now().toString()
)