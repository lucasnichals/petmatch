package br.com.projects.petbite.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.projects.petbite.data.PetDTO
import br.com.projects.petbite.data.repository.MockAPI
import br.com.projects.petbite.data.repository.Network
import br.com.projects.petbite.domain.PetListState.FAILURE
import br.com.projects.petbite.domain.PetListState.LOADING
import br.com.projects.petbite.domain.PetListState.SUCCESS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PetListViewModel : ViewModel() {

    val state: MutableLiveData<PetListState> = MutableLiveData()

    val pets: MutableLiveData<List<PetDTO>> by lazy {

        MutableLiveData<List<PetDTO>>().also {
            loadPets()
        }

    }

    private fun loadPets() {

        val network = Network.getInstance("https://64690bde03bb12ac2084f15c.mockapi.io/")
        val petAPI = network.create(MockAPI::class.java)
        val listPets = petAPI.listPets()
        state.postValue(LOADING)

        listPets.enqueue(object : Callback<List<PetDTO>> {
            override fun onResponse(
                call: Call<List<PetDTO>>, response: Response<List<PetDTO>>
            ) {
                if (response.isSuccessful) {
                    val responsePets = response.body()
                    state.postValue(SUCCESS)
                    responsePets?.let {
                        pets.postValue(responsePets)
                    }
                }
            }

            override fun onFailure(call: Call<List<PetDTO>>, t: Throwable) {
                state.postValue(FAILURE)
            }
        })
    }

}

enum class PetListState {

    LOADING, SUCCESS, FAILURE

}