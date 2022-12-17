package com.stslex.feature_widget.widget

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.Button
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalSize
import androidx.glance.appwidget.CircularProgressIndicator
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.FontStyle
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import com.stslex.feature_widget.AppWidgetBox
import com.stslex.feature_widget.AppWidgetColumn
import com.stslex.feature_widget.AppWidgetRow
import com.stslex.feature_widget.GlanceTheme
import com.stslex.feature_widget.R
import com.stslex.feature_widget.worker.ThemeWidgetInfoStateDefinition
import com.stslex.feature_widget.base.UpdateWidgetAction
import com.stslex.feature_widget.base.ThemeWidgetState

class ThemeWidget : GlanceAppWidget() {

    companion object {
        private val smallMode = DpSize(70.dp, 70.dp)
        private val thinMode = DpSize(70.dp, 100.dp)
        private val widthMode = DpSize(100.dp, 70.dp)
        private val largeMode = DpSize(150.dp, 150.dp)
    }

    override val stateDefinition = ThemeWidgetInfoStateDefinition

    override val sizeMode: SizeMode = SizeMode.Responsive(
        setOf(thinMode, smallMode, widthMode, largeMode)
    )

    @Composable
    override fun Content() {

        val widgetState = currentState<ThemeWidgetState>()
        val size = LocalSize.current

        GlanceTheme {
            when (widgetState) {
                ThemeWidgetState.Loading -> {
                    AppWidgetBox(contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is ThemeWidgetState.Success -> {
                    when (size) {
                        thinMode -> ThemeWidgetSmallThin()
                        smallMode -> ThemeWidgetSmallThin()
                        widthMode -> ThemeWidgetMediumThin()
                        largeMode -> ThemeWidgetMedium()
                    }
                }

                is ThemeWidgetState.Failed -> {
                    AppWidgetColumn(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Data not available")
                        Button("Refresh", actionRunCallback<UpdateWidgetAction>())
                    }
                }
            }
        }
    }

    @Composable
    fun ThemeWidgetMediumThin(
        modifier: GlanceModifier = GlanceModifier
    ) {
        AppWidgetBox(modifier = modifier) {
            AppWidgetRow {
                SingleItemThin(
                    modifier = GlanceModifier.defaultWeight(),
                    imageSource = R.drawable.ic_widget_night_mode,
                )
                Spacer(GlanceModifier.size(8.dp))
                SingleItemThin(
                    modifier = GlanceModifier.defaultWeight(),
                    imageSource = R.drawable.ic_widget_light_mode,
                )
            }
        }

    }

    @Composable
    fun ThemeWidgetMedium(
        modifier: GlanceModifier = GlanceModifier
    ) {
        AppWidgetBox(modifier = modifier) {
            AppWidgetRow {
                SingleItem(
                    modifier = GlanceModifier.defaultWeight(),
                    imageSource = R.drawable.ic_widget_night_mode,
                )
                Spacer(GlanceModifier.size(8.dp))
                SingleItem(
                    modifier = GlanceModifier.defaultWeight(),
                    imageSource = R.drawable.ic_widget_light_mode,
                )
            }
        }
    }

    @Composable
    fun ThemeWidgetSmallThin(
        modifier: GlanceModifier = GlanceModifier
    ) {
        AppWidgetBox(modifier = modifier) {
            SingleItemThin(
                modifier = GlanceModifier.fillMaxSize(),
                imageSource = R.drawable.ic_widget_light_mode,
            )
        }
    }

    @Composable
    fun SingleItemThin(
        modifier: GlanceModifier = GlanceModifier,
        imageSource: Int
    ) {
        AppWidgetColumn(modifier = modifier) {
            Image(
                provider = ImageProvider(imageSource),
                contentDescription = null,
                modifier = GlanceModifier.padding(4.dp)
            )
        }
    }

    @Composable
    fun SingleItem(
        modifier: GlanceModifier = GlanceModifier,
        imageSource: Int
    ) {
        AppWidgetColumn(modifier = modifier) {
            Image(
                provider = ImageProvider(imageSource),
                contentDescription = null,
                modifier = GlanceModifier.padding(4.dp)
            )
            Text(
                modifier = GlanceModifier.padding(4.dp),
                text = "light",
                style = TextStyle(
                    color = GlanceTheme.colors.textColorPrimary,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}