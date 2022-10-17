package com.sihabudin.seehubartspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sihabudin.seehubartspace.ui.theme.SeehubArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SeehubArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpace(modifier = Modifier)
                }
            }
        }
    }
}

fun previousCount(current: Int): Int {
    var prevCount = 0
    if (current > 1) {
        prevCount = current - 1
    }
    else{
        prevCount =4
    }
    return prevCount
}

fun nextCount(current: Int): Int {
    var nCount = 0
    if (current < 4) {
        nCount = current +1
    }
    else{
        nCount = 0
    }
    return nCount
}

@Composable
fun ArtSpace(modifier: Modifier) {
    var count by remember { mutableStateOf(1) }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageSpace(modifier = modifier, count = count)
        Spacer(modifier.height(20.dp))
        ImageDesc(modifier = modifier, count = count)
        Spacer(modifier.height(20.dp))
        Row(modifier = modifier) {
            Button(onClick = { count = previousCount(count)}, modifier = modifier.padding(12.dp)) {
                Text(text = "Previous")
            }

            Button(onClick = { count = nextCount(count) }, modifier = modifier.padding(12.dp)) {
                Text(text = "Next")
            }
        }
    }

}

@Composable
fun ImageSpace(modifier: Modifier, count: Int) {

    val imageResource = when (count) {
        1 -> R.drawable.buffon
        2 -> R.drawable.laptop_phone
        3 -> R.drawable.salah
        else -> R.drawable.searching
    }
    Box(modifier = modifier) {
        Image(
            modifier = Modifier
                .padding(10.dp),
            painter = painterResource(id = imageResource),
            contentDescription = null
        )
    }
}

@Composable
fun ImageDesc(modifier: Modifier, count: Int) {
    var headerText by remember { mutableStateOf("") }
    var authorText by remember { mutableStateOf("") }

    headerText = when (count) {
        1 -> "Buffon in World Cup Final"
        2 -> "Minimalism Notebook"
        3 -> "Salah was never wrong"
        else -> "Seaching........"
    }

    authorText = when (count) {
        1 -> "Juventini"
        2 -> "Steve Work"
        3 -> "Benar True"
        else -> "Cari Find"
    }

    Text(text = headerText, style = MaterialTheme.typography.body1)
    Text(text = authorText, style = MaterialTheme.typography.subtitle2)


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SeehubArtSpaceTheme {
        ArtSpace(modifier = Modifier)
    }
}