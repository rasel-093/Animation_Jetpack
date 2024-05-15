package com.example.animation_jetpack

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SurveyTopAppProgress(
    modifier: Modifier = Modifier,
    questionIndex: Int,
    totalQuestions: Int
) {
//    val progress = (questionIndex+1) / totalQuestions.toFloat()
    val progress by animateFloatAsState(
        targetValue = (questionIndex+1) / totalQuestions.toFloat(),
        label = "",
        animationSpec = tween(1000)
    )
    LinearProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(16.dp),
        progress = {
            progress
        },
        trackColor = Color.Gray,
        color = Color.Green,
        strokeCap = StrokeCap.Round
    )
    Text(
        text = "Question ${questionIndex+1} of $totalQuestions",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        fontSize = 20.sp
    )
}

@Composable
fun SurveyScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .padding(top = 20.dp)
    ) {

        var questionIndex by rememberSaveable { mutableIntStateOf(0) }

        SurveyTopAppProgress(
            questionIndex = questionIndex,
            totalQuestions = questions.size
        )


        val selectedOptions = remember {
            mutableStateListOf<Int>()
        }

        val onNextClick = {selectedIndex: Int->
            if (questionIndex == questions.size-1){
                questionIndex = 0
                Toast.makeText(context, "Survey Completed", Toast.LENGTH_SHORT).show()
            }else{
                questionIndex++
                selectedOptions.add(selectedIndex)
            }
        }
        MCQQuestionView(
            question = questions[questionIndex],
            totalQuestion = questions.size,
            onNextClick = { onNextClick(it) },
            currentQuestionIndex = questionIndex
        )
    }
}

@Composable
fun MCQQuestionView(
    question: MCQQuestion,
    onNextClick: (Int) -> Unit,
    totalQuestion: Int,
    currentQuestionIndex: Int
) {
    val context2 = LocalContext.current
    val radioOptions = question.options
    var selectedOption by rememberSaveable { mutableStateOf("") }
    var selectedIndex by rememberSaveable {
        mutableStateOf(-1)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = question.question,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        radioOptions.forEachIndexed {index, type ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = (type == selectedOption),
                    onClick = {
                        selectedOption = type
                        selectedIndex = index
                    }
                )
                Text(
                    text = type,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        Button(onClick = {
            if (selectedOption.isNotEmpty()){
                onNextClick(selectedIndex)
                selectedIndex = 1
                selectedOption = ""
            }else{
                Toast.makeText(context2, "Please select an option", Toast.LENGTH_SHORT).show()
            }
        }) {
            if (currentQuestionIndex == totalQuestion-1){
                Text(text = "Submit")
            }else{
                Text(text = "Next")
            }
        }
    }
}


data class MCQQuestion(
    val question: String,
    val options: List<String>,
    val answerIndex: Int // Index of the correct answer option
)


val questions = listOf(
    MCQQuestion(
        "What is the capital of France?",
        listOf("London", "Paris", "Berlin"),
        1
    ),
    MCQQuestion(
        "What is the largest river in the world by volume?",
        listOf("Nile", "Amazon", "Yangtze"),
        0
    ),
    MCQQuestion(
        "What is the tallest mountain in the world?",
        listOf("Mount Everest", "K2", "Kangchenjunga"),
        0
    ),
    MCQQuestion(
        "What is the currency of Japan?",
        listOf("Euro", "Yen", "Dollar"),
        1
    ),
    MCQQuestion(
        "What is the largest country in the world by land area?",
        listOf("Russia", "Canada", "China"),
        0
    ),
    MCQQuestion(
        "What is the capital of Australia?",
        listOf("Sydney", "Melbourne", "Canberra"),
        2
    )
)



