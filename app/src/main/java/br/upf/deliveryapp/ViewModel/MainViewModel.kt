package br.upf.deliveryapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.upf.deliveryapp.Domain.BannerModel
import br.upf.deliveryapp.Domain.CategoryModel
import br.upf.deliveryapp.Domain.FoodModel
import br.upf.deliveryapp.Repository.MainRepository

class MainViewModel : ViewModel() {
    private val repository = MainRepository()

    fun loadBanner(): LiveData<MutableList<BannerModel>> {
        return repository.loadBanner()
    }

    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        return repository.loadCategory()
    }

    fun loadFiltered(id: String): LiveData<MutableList<FoodModel>> {
        return repository.loadFiltered(id)
    }
}