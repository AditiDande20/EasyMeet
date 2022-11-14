package com.easy.meet.screens.create.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy.meet.models.Event
import com.easy.meet.service.FirestoreService
import com.easy.meet.utils.Constant
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.firebase.dynamiclinks.ktx.androidParameters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(val context: Context) :
    ViewModel() {

    fun insertEvent(event: Event) {
        viewModelScope.launch {
            FirestoreService.insertDataToFirestore(context, Constant.EVENT_TABLE, event, event.id)
        }
    }

    fun generateSharingLink(
        deepLink: Uri,
        previewImageLink: Uri,
        getShareableLink: (String) -> Unit = {},
    ) {
        FirebaseDynamicLinks.getInstance().createDynamicLink().run {
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
            it.addOnFailureListener {
                // This lambda will be triggered when short link generation failed due to an exception
                // Handle
            }
        }
    }


}