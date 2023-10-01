package com.example.app_icon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app_icon.data.DataSource
import com.example.app_icon.model.Affirmation
import com.example.app_icon.ui.theme.App_IconTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App_IconTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {AffirmationApp()
                }
            }
        }
    }
}

@Composable
fun AffirmationApp(modifier: Modifier = Modifier) {
    AffirmationList(
        affirmationList = DataSource().loadAffirmations(),
    )

}

@Composable
fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier) {
        items(affirmationList){
                aMilemMal ->
            AffirmationCard(affirmation = aMilemMal,
                modifier = Modifier.padding(8.dp))

        }
    }
}
@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier){
    Card(modifier = modifier){
        Column(){
                Image(painter = painterResource(id = affirmation.imageResourceId),
                    contentDescription = stringResource(id = affirmation.stringResourceId),
                    modifier= Modifier
                        .fillMaxWidth()
                        .height(194.dp),
                    contentScale = ContentScale.Crop)
                Text(
                    text = LocalContext.current.getString(affirmation.stringResourceId),
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineSmall
                )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AffirmationAppPreview() {
    App_IconTheme {
        AffirmationApp()
    }
}

@Preview(showBackground = true)
@Composable
fun AffirmationCardPreview() {
    App_IconTheme {
        AffirmationCard(Affirmation(R.string.affirmation1, R.drawable.image1))
    }
}