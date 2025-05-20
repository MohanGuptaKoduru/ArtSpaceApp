package com.example.artspace

import android.graphics.Color.parseColor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme
import androidx.compose.runtime.*
import androidx.compose.ui.draw.shadow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var result by remember { mutableStateOf(1) }
    var imgdes by remember { mutableStateOf("Wish you happy Bhogi to you and your Family") }
    var images by remember { mutableStateOf(R.drawable.happy_bhogi_3) }
    images = when(result) {
        1->R.drawable.happy_bhogi_3
        2->R.drawable.wmremove_transformed
        else->R.drawable.dewatermark_ai_1736733275970
    }
    imgdes = when(result) {
        1->"Wish you happy Bhogi to you and your Family"
        2->"Wish you happy Sankranti to you and your Family"
        else->"Wish you happy Kanuma to you and your Family"
    }
    ArtLayout(
        image=images,
        description = imgdes
    )
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(20.dp)

    ) {
        Button(onClick = {
            if (result>1) {
                result--
            } else {
                result =3
            }
        },
            modifier = Modifier.weight(1f)) {
            Text(
                text="Previous",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center


            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Button(onClick ={
            if (result<3) {
                result++
            } else {
                result=1
            }
        } ,
            modifier = Modifier.weight(1f)) {
            Text(
                text="Next",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

    }


}

@Composable
fun ArtLayout(
    @DrawableRes image:Int,
    description:String,
    modifier: Modifier = Modifier) {
    Column (modifier = Modifier
        .fillMaxSize()
        .safeDrawingPadding()
        .verticalScroll(rememberScrollState())
        .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Box(
            modifier = Modifier

                .padding(top=20.dp,bottom = 45.dp)
                .shadow(
                    elevation = 8.dp, // Controls the intensity of the shadow
                    shape = RoundedCornerShape(16.dp), // Match the shape of the background
                    clip = false // Ensure shadow extends outside the content
                )
                .background(Color.White,shape= RoundedCornerShape(16.dp))
                .padding(16.dp)
                .background(
                    color = Color(parseColor("#ecebf4")),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp)
        ) {
            Image(
                painter =  painterResource(image),
                contentDescription=description,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))

            )

        }


        Spacer(modifier= modifier.height(35.dp))
        Column(modifier = Modifier
            .padding(horizontal = 20.dp)
            .background(
                color = Color(parseColor("#ecebf4"))

            ))    {
            Text(
                text= description,
                fontStyle = FontStyle.Italic,
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(top=20.dp, start = 20.dp)

                )
            Text(
                text="From MohanGuptaKoduru (2025)",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(20.dp)
            )
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
       ArtSpaceApp()
    }
}