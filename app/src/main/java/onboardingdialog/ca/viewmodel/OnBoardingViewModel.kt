package onboardingdialog.ca.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import onboardingdialog.ca.model.Content

class OnBoardingViewModel : ViewModel() {

    private val _content = MutableLiveData<List<Content>>()
    val content: LiveData<List<Content>>
        get() = _content

    fun listContent() {
        _content.value = listOf(
            Content(
                text = "Iron Man is a superhero appearing in American comic books published by Marvel Comics."
            ),
            Content(
                text = "The Avengers, is a 2012 American superhero film based on the Marvel Comics superhero team of the same name."
            ),
            Content(
                text = "Bruce Banner is a fictional character in the Marvel Cinematic Universe (MCU) media franchise."
            ),
            Content(
                text = "Steven Grant Rogers, more commonly known as Steve Rogers, is a fictional character.",
                isLast = true
            )
        )
    }
}
