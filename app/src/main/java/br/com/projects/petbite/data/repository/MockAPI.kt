package br.com.projects.petbite.data.repository

import br.com.projects.petbite.data.PetDTO
import retrofit2.Call
import retrofit2.http.GET

interface MockAPI {

    @GET("pets")
    fun listPets(): Call<List<PetDTO>>

}

