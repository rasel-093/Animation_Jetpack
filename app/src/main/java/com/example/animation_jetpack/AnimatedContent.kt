package com.example.animation_jetpack

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContent(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var isVisible by rememberSaveable { mutableStateOf(false) }
        androidx.compose.animation.AnimatedContent(
            targetState = isVisible,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            content = { visibility ->
                if (visibility) {
//                                Box(
//                                    modifier = Modifier
//                                        .fillMaxSize()
//                                        .background(Color.Red)
//                                )
                    Image(
                        painter = painterResource(id = R.drawable.bird1),
                        contentDescription = "Bird1"
                    )
                } else {
//                                Box(
//                                    modifier = Modifier
//                                        .fillMaxSize()
//                                        .background(Color.Green)
//                                )
                    Image(
                        painter = painterResource(id = R.drawable.bird2),
                        contentDescription = "Bird2"
                    )
                }
            },
            transitionSpec = {
//                            fadeIn() with fadeOut()
                slideInHorizontally(
                    initialOffsetX = {
                        if (isVisible) it else -it
                    }
                ) with slideOutHorizontally(
                    targetOffsetX = {
                        if (isVisible) -it else it
                    }
                )
            }, label = ""
        )
        Button(
            onClick = { isVisible = !isVisible }
        ) {
            Text(text = "Toggle")
        }
    }
}