package com.example.wanandroid.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.wanandroid.R
import com.example.wanandroid.ui.theme.Purple500
import com.example.wanandroid.ui.theme.Purple700

@Preview
@Composable
fun Logo() {
    Card(shape = RoundedCornerShape(8.dp)) {
//        Box(
//            Modifier
//                .background(color = Purple500)
//                .padding(15.dp), contentAlignment = Alignment.Center) {
//            Icon( painter = painterResource(id = R.drawable.ic_baseline_menu_book_24),"", tint = Color.White)
//            Icon( painter = painterResource(id = R.drawable.ic_baseline_accessible_forward_24),"", tint = Color.White)
//        }

        ConstraintLayout(
            Modifier
                .background(Purple500)
                .size(68.dp)
                .padding(10.dp)
        ) {
            val (people, book,text) = createRefs()
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_menu_book_24),
                "",
                tint = Color.White,
                modifier = Modifier
                    .constrainAs(book) {
                        bottom.linkTo(parent.bottom,)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end,)
                    }
                    .scale(0.7f)
                    .scale(scaleX = 0.8f, scaleY = 1f)
                    .rotate(20f)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_accessible_forward_24),
                "",
                tint = Color.White,
                modifier = Modifier
                    .constrainAs(people) {
                        top.linkTo(parent.top,)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start,)
                    }
                    .scale(1.2f)
            )
            Text(text = "学不动了 - -", style = TextStyle(color = Color.White, fontSize = 8.sp), modifier = Modifier.constrainAs(text){
                top.linkTo(people.bottom,5.dp)
                start.linkTo(people.start)
            })
        }
    }
}