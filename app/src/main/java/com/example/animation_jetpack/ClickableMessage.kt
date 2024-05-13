package com.example.animation_jetpack

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar

data class Message(
    val id: Int,
    val text: String,
    val time: String
)

@Composable
fun ClickableMessage(message: Message, modifier: Modifier = Modifier) {
    var showDetails by rememberSaveable {
        mutableStateOf(false)
    }
    val annotatedText = buildAnnotatedString {
        append(message.text)
        addStyle(style = SpanStyle(color = Color.Black), start = 0, end = message.text.length)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .border(BorderStroke(1.dp, color = Color.Black), RoundedCornerShape(8.dp))
            .padding(16.dp)

    ) {
        ClickableText(
            text = annotatedText,
            style = TextStyle(color = Color.Black, fontSize = 20.sp),
            onClick = {
               showDetails = !showDetails
            })
        AnimatedVisibility(visible = showDetails) {
            Text(
                text = message.time,
                style = TextStyle(color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold))
        }
    }
}

@Composable
fun ClickableMessageScreen(modifier: Modifier = Modifier) {
    val msgList = generateRandomMessages(20)
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()).fillMaxSize()
    ) {
        msgList.forEach {
            ClickableMessage(message = it)
        }
    }
}

fun generateRandomMessages(count: Int): List<Message> {
    val messages = mutableListOf<Message>()
    val loremIpsum = listOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.",
        "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga.",
        "Ut enim ad minima veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
    )

    repeat(count) {
        val messageText = loremIpsum.random()
        val randomId = (0..Int.MAX_VALUE).random()
        val randomTime = generateRandomTime()
        messages.add(Message(randomId, messageText, randomTime))
    }

    return messages.toList()
}

fun generateRandomTime(): String {
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY).toString().padStart(2, '0')
    val minute = calendar.get(Calendar.MINUTE).toString().padStart(2, '0')
    return "$hour:$minute"
}
