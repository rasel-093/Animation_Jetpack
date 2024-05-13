package com.example.animation_jetpack

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableText(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        var isExpanded by remember { mutableStateOf(false) }
        Text(
            text = if (isExpanded) androidExpandableText else androidExpandableText.take(115) + "\t\t\t ...Read more" ,
            maxLines = if (isExpanded) Int.MAX_VALUE else 4,
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                .clickable { isExpanded = !isExpanded }
                .padding(8.dp)
        )
    }
}

@Composable
fun ExpandableTextScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = R.drawable.android),
            contentDescription = "Android"
        )
        ExpandableText()
        Image(
            painter = painterResource(id = R.drawable.android13),
            modifier = Modifier
                .padding(top = 16.dp)
                .size(400.dp),
            contentDescription = "Android13"
        )
    }
}

val androidExpandableText = "Android 13 helps ensure your devices feel unique to you – on your terms. It comes jam-packed with new capabilities for your phone and tablet, like extending app color theming to even more apps, language settings that can be set on an app level, improved privacy controls and even the ability to copy text and media from one Android device and paste it to another with just a click.\n" +
        "\n" +
        "There are many reasons to love Android 13, but here are our top 13:\n" +
        "1. Android 13 comes with an evolved look and style that builds on Material You. You can customize non-Google apps to match your phone’s wallpaper theme and colors, making your home screen more cohesive and unique to your style.\n" +
        "\n" +
        "2. For the many Android users who speak more than one language, we’ve added a top feature request. You can assign specific languages to individual apps so you can keep your phone’s system in one language, and each of your apps in a different language.\n" +
        "\n" +
        "3. Android 13 features an updated media player that tailors its look and feel based on the music or podcast you’re listening to. For example, when you’re listening to music, the media player spotlights album artwork and has a playback bar that dances as you progress through a song. It even works for media played through Chrome.\n" +
        "\n" +
        "4. Your wellbeing has been an important theme for Android – and getting enough sleep is key! Android 13 allows you to further customize Bedtime mode with wallpaper dimming and dark theme. These screen options help your eyes adjust to the dark when you're about to go to bed – and get back to sleep if you wake up and check your phone in the middle of the night."