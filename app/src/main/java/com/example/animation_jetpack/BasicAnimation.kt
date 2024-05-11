package com.example.animation_jetpack

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BasicAnimaton(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //Simple appearance with animation
        var isVisible by rememberSaveable {
            mutableStateOf(false)
        }
//                    AnimatedVisibility(
//                        visible = isVisible,
//                        enter = slideInHorizontally() + fadeIn(),
//                        modifier = Modifier
//                            .size(200.dp)
//                    ) {
//                        Box(modifier = Modifier.background(Color.Red))
//                    }


        //Shape changing animation
//                    var isRound by rememberSaveable {
//                        mutableStateOf(false)
//                    }
//                    val borderRadius by animateIntAsState(
//                        targetValue = if (isRound) 100 else 0,
//                        animationSpec =
        //keyframes
//                        keyframes {
//                            durationMillis = 500
//                            0 at 0 with LinearEasing
//                            10 at 200 with LinearEasing
//                            15 at 500 with LinearEasing
//                            20 at 1000 with LinearEasing
//                            50 at 4000 with LinearEasing
//                          //  100 at 100 with LinearEasing
//                        }
        //Bouncy
//                        spring(
//                            dampingRatio = Spring.DampingRatioHighBouncy,
//                            stiffness = Spring.StiffnessVeryLow
//                        )
        //Simple
//                        tween(
//                            durationMillis = 4000,
//                            delayMillis = 500,
//                            easing = LinearEasing
//                        ), label = ""
//                    )
//                    Box(modifier = Modifier
//                        .size(200.dp)
//                        .clip(RoundedCornerShape(borderRadius))
//                        .background(Color.Red)
//                    )

        //Shape and color changing animation, multiple value animation
        var isRound by rememberSaveable {
            mutableStateOf(false)
        }
//                    val transition = updateTransition(
//                        targetState = isRound,
//                        label = "Green-Circle->Red-Square"
//                    )
        //if want to make it infinite
        val transition = rememberInfiniteTransition(label = "")

//                    val borderRadius by transition.animateInt(
//                        transitionSpec = {
//                            tween(
//                                durationMillis = 4000,
//                                delayMillis = 500,
//                                easing = LinearEasing
//                            )
//                        },
//                        label = "Green-Circle->Red-Square",
//                        targetValueByState = {
//                            if (it) 100 else 0
//                        }
//                    )
        //If want to make it infinite
        val borderRadius by transition.animateFloat(
            initialValue = 0f,
            targetValue = 100f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 4000,
                    delayMillis = 500,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Reverse
            ),
            label = "",
        )


//                    val color by transition.animateColor(
//                        transitionSpec = {
//                            tween(
//                                durationMillis = 4000,
//                                delayMillis = 500,
//                                easing = LinearEasing
//                            )
//                        },
//                        label = "Green-Circle->Red-Square",
//                        targetValueByState = {
//                            if (it) Color.Green else Color.Red
//                        }
//                    )
        //If want to make it infinite
        val color by transition.animateColor(
            initialValue = Color.Red,
            targetValue = Color.Green,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 4000,
                    delayMillis = 500,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )
        Box(
            modifier = Modifier
                .size(200.dp)
                .padding(10.dp)
                .clip(RoundedCornerShape(borderRadius))
                .background(color)
        )


        Box(
            modifier = Modifier
                .size(200.dp)
                .padding(10.dp)
                .clip(RoundedCornerShape(borderRadius))
                .background(color)
        )

        // Trigger button
        Button(
            onClick = {
                isVisible = !isVisible
                isRound = !isRound
            }
        ) {
            Text(text = "Toggle")
        }
    }
}