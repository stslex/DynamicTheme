package com.stslex.feature_home.ui

import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.stslex.core_ui.AppTheme
import com.stslex.feature_home.ui.components.HomeImagesSelectionBody
import com.stslex.feature_home.ui.components.HomeTitle

@Composable
fun FeatureHomeScreen(
    modifier: Modifier = Modifier
) {
    var result by rememberSaveable { mutableStateOf<Uri?>(null) }

    val intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Intent(MediaStore.ACTION_PICK_IMAGES).apply {
            type = "image/*"
        }
    } else {
        Intent(ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            result = it.data?.data
        }
    )
    Box(
        modifier = modifier,
    ) {
        HomeTitle()
        HomeImagesSelectionBody(
            modifier = Modifier.align(Alignment.Center),
            onChangeImageClick = { isDarkTheme ->
                launcher.launch(intent)
            }
        ) {
            AsyncImage(
                model = result,
                contentDescription = ""
            )
        }
    }
}


@Preview(device = "id:pixel_6_pro", showSystemUi = true, showBackground = true)
@Composable
fun PreviewFeatureHomeScreen() {
    AppTheme {
        FeatureHomeScreen()
    }
}