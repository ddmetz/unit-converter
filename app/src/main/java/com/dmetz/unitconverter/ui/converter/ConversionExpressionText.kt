package com.dmetz.unitconverter.ui.converter

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ConversionExpressionText(
    expression: String,
    appendPartial: String = "",
    unit: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier
) {
    val scrollTop = rememberScrollState()
    val scrollBottom = rememberScrollState()
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = color)) {
                    append(expression)
                }
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.outline)) {
                    append(appendPartial)
                }
            },
            textAlign = TextAlign.End,
            modifier = Modifier
                .padding(bottom = 0.dp)
                .horizontalScroll(scrollTop, reverseScrolling = true),
            style = MaterialTheme.typography.displayLarge,
            fontSize = 68.sp,
            fontWeight = FontWeight(600),
            color = color,
            maxLines = 1
        )
        Text(
            text = unit,
            textAlign = TextAlign.End,
            modifier = Modifier
                .padding(bottom = 0.dp)
                .horizontalScroll(scrollBottom, reverseScrolling = true),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1
        )
    }
}
