package com.example.socialpet.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : ViewModel() {
    private val _imageUri = MutableStateFlow<Uri?>(null)
    val imageUri: StateFlow<Uri?> = _imageUri

    private val _name = MutableStateFlow("Lucas")
    val name: StateFlow<String> = _name

    private val _age = MutableStateFlow("2 años")
    val age: StateFlow<String> = _age

    private val _weight = MutableStateFlow("9 Kg")
    val weight: StateFlow<String> = _weight

    private val _breed = MutableStateFlow("Golden Retriever")
    val breed: StateFlow<String> = _breed

    private val _owner = MutableStateFlow("Gabriel Torres")
    val owner: StateFlow<String> = _owner

    private val _description = MutableStateFlow("Hola humanos, mi nombre es Lucas. Amo correr, dormir y perseguir mariposas en el parque. ¡Sígueme para más aventuras!")
    val description: StateFlow<String> = _description

    fun setImageUri(uri: Uri) { _imageUri.value = uri }
    fun setName(value: String) { _name.value = value }
    fun setAge(value: String) { _age.value = value }
    fun setWeight(value: String) { _weight.value = value }
    fun setBreed(value: String) { _breed.value = value }
    fun setOwner(value: String) { _owner.value = value }
    fun setDescription(value: String) { _description.value = value }
}
