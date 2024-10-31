package com.example.app_loto.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_loto.viewModels.LoteriaViewModel

@Composable
fun LoteriaView(viewModel: LoteriaViewModel) {
    val lotoNumbers by viewModel.lotoNumbers
    val isLoading by viewModel.isLoading

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Título "Lotería" en negrita
            Text(
                text = "LOTERIA",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                // Muestra el loader mientras se están generando los números
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(16.dp))
            } else if (lotoNumbers.isNotEmpty()) {
                Text(text = "Números generados:", fontSize = 20.sp, fontWeight = FontWeight.Medium,color = Color.Black)
            }

            LotteryNumbers(lotoNumbers = lotoNumbers)

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.generateLotoNumbers() },
                enabled = !isLoading
            ) {
                Text(text = if (isLoading) "Generando..." else "Generar números")
            }
        }
    }
}
@Composable
fun LotteryNumbers(lotoNumbers: List<Int>) {
    LazyRow(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(lotoNumbers) { number ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(48.dp)
                    .background(Color(0xFF179AED), CircleShape)
            ) {
                Text(text = number.toString(), color = Color.White, fontSize = 24.sp)
            }
        }
    }
}
