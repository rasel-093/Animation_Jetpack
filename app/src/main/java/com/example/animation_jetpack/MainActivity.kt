package com.example.animation_jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import com.example.animation_jetpack.ui.theme.Animation_JetpackTheme

@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Animation_JetpackTheme {
                //BasicAnimaton()
                //AnimatedContent()
                //LottieAnimationImpl()
                //ClickableMessageScreen()
                ExpandableTextScreen()
            }
        }
    }
}


