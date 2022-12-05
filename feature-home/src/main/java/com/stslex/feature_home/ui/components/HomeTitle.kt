package com.stslex.feature_home.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stslex.core_ui.AppTheme
import com.stslex.feature_home.R

@Composable
fun HomeTitle(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        text = LocalContext.current.getString(R.string.feature_home_main_title),
        style = MaterialTheme.typography.displayMedium,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun PreviewHomeTitle(
    modifier: Modifier = Modifier
) {
    AppTheme {
        HomeTitle()
    }
}