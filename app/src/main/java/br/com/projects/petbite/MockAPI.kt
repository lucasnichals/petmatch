package br.com.projects.petbite

import retrofit2.Call
import retrofit2.http.GET

interface MockAPI {

    @GET("pets")
    fun listPets(): Call<List<PetDTO>>

}

