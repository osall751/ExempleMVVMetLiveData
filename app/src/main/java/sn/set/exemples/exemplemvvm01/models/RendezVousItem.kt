package sn.set.exemples.exemplemvvm01.models

data class RendezVousItem(
    val date: String,
    val description: String,
    val id: Int = 0,
    val lieu: String,
)