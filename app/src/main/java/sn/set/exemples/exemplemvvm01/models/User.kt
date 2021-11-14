package sn.set.exemples.exemplemvvm01.models

data class User(
    val blocked: Boolean,
    val confirmed: Boolean,
    val created_at: String,
    val email: String,
    val id: Int,
    val provider: String,
    val role: Role,
    val updated_at: String,
    val username: String
)