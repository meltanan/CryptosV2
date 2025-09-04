package com.example.cryptosv2.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchComponent(
    callback: (text: String) -> Unit
) {
    var search by rememberSaveable { mutableStateOf(false) }
    var inputSearch by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp)
            .background(color = Color.Black, RoundedCornerShape(6.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .height(42.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .background(
                    color = Color.DarkGray,
                    shape = RoundedCornerShape(8.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.White,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            BasicTextField(
                value = inputSearch,
                onValueChange = {
                    inputSearch = it
                    search = true
                },
                modifier = Modifier.weight(1f).background(Color.DarkGray),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
                textStyle = TextStyle(
                    //fontFamily = vantacaFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                ),
                decorationBox = { innerTextField ->
                    if (inputSearch.isEmpty()) {
                        Text(
                            text = "Search by name",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                    innerTextField()
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {})
            )

            if (inputSearch.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Clear search content",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable {
                            inputSearch = ""
                            search = true
                        }
                )
            }
        }
    }

    if (search) {
        LaunchedEffect(key1 = true) {
            callback(inputSearch)
            search = false
        }
    }
}