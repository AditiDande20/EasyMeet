package com.easy.meet.screens.create.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy.meet.models.Event
import com.easy.meet.screens.create.repository.EventRepository
import com.easy.meet.utils.Constant
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.firebase.dynamiclinks.ktx.androidParameters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(private val eventRepository: EventRepository): ViewModel() {

    fun insertEvent(event: Event) : Long{
        var id = 12L
        viewModelScope.launch {
            id = eventRepository.insertEvent(event)
            Log.e("Aditi===>", "eventRepository event id after insert :: $id")
        }
        return id
    }

    fun generateSharingLink(
        deepLink: Uri,
        previewImageLink: Uri,
        getShareableLink: (String) -> Unit = {},
    ) {
        val dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink().run {
            // What is this link parameter? You will get to know when we will actually use this function.
            link = deepLink

            // [domainUriPrefix] will be the domain name you added when setting up Dynamic Links at Firebase Console.
            // You can find it in the Dynamic Links dashboard.
            domainUriPrefix = Constant.DYNAMIC_LINK_DOMAIN

            // Pass your preview Image Link here;
            setSocialMetaTagParameters(
                DynamicLink.SocialMetaTagParameters.Builder()
                    .setImageUrl(previewImageLink)
                    .build()
            )

            // Required
            androidParameters {
                build()
            }

            // Finally
            buildDynamicLink()
        }

        // Retrieve the newly created dynamic link so that we can use it further for sharing via Intent.
        getShareableLink.invoke(dynamicLink.uri.toString())
    }


}