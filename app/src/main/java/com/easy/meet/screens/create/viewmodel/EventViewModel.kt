package com.easy.meet.screens.create.viewmodel

import android.content.Context
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy.meet.models.Event
import com.easy.meet.service.FireStoreService
import com.easy.meet.utils.Constant
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.ktx.*
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(val context: Context) :
    ViewModel() {

    private val job = Job()


    fun insertEvent(event: Event, onDone: (String) -> Unit) {
        viewModelScope.launch {
            generateSharingLink(event.id) {
                if (it.isNotBlank() && it.isNotEmpty()) {
                    event.link = it
                }
            }
            job.join()
            FireStoreService.insertDataToFireStore(context, Constant.EVENT_TABLE, event, event.id) {
                onDone(it)
            }
        }
    }

    private fun generateSharingLink(
        id: String,
        getShareableLink: (String) -> Unit = {},
    ) {

        val image =
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9RvF3ix5DRfZmehI-Z4ZabBDJg1xt6eel8w&usqp=CAU"
        val dynamicLink = Firebase.dynamicLinks.dynamicLink {
            link = "${Constant.DYNAMIC_LINK_DOMAIN}/?event/${id}".toUri()
            domainUriPrefix = Constant.DYNAMIC_LINK_DOMAIN
            androidParameters {
                build()
            }
            setSocialMetaTagParameters(
                DynamicLink.SocialMetaTagParameters.Builder()
                    .setImageUrl(image.toUri())
                    .build()
            )
        }

        val dynamicLinkUri = dynamicLink.uri

        //getShareableLink.invoke(dynamicLinkUri.toString())

        Firebase.dynamicLinks.shortLinkAsync {
            longLink = dynamicLinkUri
        }.addOnSuccessListener { (shortLink, _) ->
            getShareableLink.invoke(shortLink.toString())
            job.complete()
        }.addOnFailureListener {
            // Error
            // ...
            job.complete()
        }

        /*FirebaseDynamicLinks.getInstance().createDynamicLink().run {
            link = deepLink

            domainUriPrefix = Constant.DYNAMIC_LINK_DOMAIN

            setSocialMetaTagParameters(
                DynamicLink.SocialMetaTagParameters.Builder()
                    .setImageUrl(previewImageLink)
                    .build()
            )
            // Required
            androidParameters {
                build()
            }

            buildShortDynamicLink()
        }.also {
            it.addOnSuccessListener { dynamicLink ->
                getShareableLink.invoke(dynamicLink.shortLink.toString())
            }
            it.addOnFailureListener {e ->
                Log.e("Aditi===>"," link exception :: $e")

                // This lambda will be triggered when short link generation failed due to an exception
                // Handle
            }
        }*/
    }


}