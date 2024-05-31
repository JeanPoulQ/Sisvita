package com.example.sisvita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sisvita.ui.theme.SisvitaTheme
import data.FuentePreguntas
import model.Pregunta

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SisvitaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PreguntaApp()
                }
            }
        }
    }
}

@Composable
fun PreguntaApp (){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "TEST DE ANSIEDAD 1",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(10.dp)
        )

        Text(
            text = "Califica cada una de las situaciones que te aparecen a continuación según el grado de ansiedad que te producen o proudicirían." +
                    "\nResponda con sinceridad las siguientes preguntas:",
            modifier = Modifier.padding(10.dp),
        )

        ListaPregunta(listaPregunta = FuentePreguntas().cargaPreguntas())

    }
}

@Composable
fun ListaPregunta( listaPregunta: List<Pregunta>, modifier: Modifier = Modifier) {

    LazyColumn(modifier = modifier) {

        items(listaPregunta) { pregunta ->

            ComponentePreguntas(
                pregunta = pregunta,
                modifier = modifier.padding(8.dp)
            )

        }
        item {
            Button(
                onClick = { /* Handle submit action */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("Enviar")
            }

        }

    }
}
@Composable
fun ComponentePreguntas(pregunta: Pregunta, modifier: Modifier = Modifier) {

    var seleccionarOpcion by remember { mutableStateOf(0) }
    val opciones = listOf("1: No, nada", "2: Un poco", "3: Bastante", "4: Sí, mucha")

    Column (modifier = Modifier
        .fillMaxSize()
    ){
        Text(
                text = LocalContext.current.getString(pregunta.stringResourceId),
                modifier = modifier.padding(bottom = 1.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        opciones.forEachIndexed { index, opcion ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
            ) {
                RadioButton(
                    selected = seleccionarOpcion == index + 1,
                    onClick = { seleccionarOpcion = index + 1 }
                )
                Text(text = opcion, modifier = Modifier.padding(start = 8.dp))
            }
        }

        }

}

@Preview(showBackground = true)
@Composable
fun TestPsicologicoPreview() {
    SisvitaTheme {
        PreguntaApp()
    }
}
