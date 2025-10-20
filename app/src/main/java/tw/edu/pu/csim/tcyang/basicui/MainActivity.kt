package tw.edu.pu.csim.tcyang.basicui

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tw.edu.pu.csim.tcyang.basicui.ui.theme.BasicUITheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicUITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(modifier = Modifier.padding(innerPadding), activity = this)
                }
            }
        }
    }
}

@Composable
fun Main(modifier: Modifier = Modifier, activity: Activity) {
    val animals = listOf(
        R.drawable.animal0, R.drawable.animal1,
        R.drawable.animal2, R.drawable.animal3,
        R.drawable.animal4, R.drawable.animal5,
        R.drawable.animal6, R.drawable.animal7,
        R.drawable.animal8, R.drawable.animal9
    )

    val animalsName = listOf("鴨子", "企鵝", "青蛙", "貓頭鷹", "海豚", "牛", "無尾熊", "獅子", "狐狸", "小雞")

    var flag by remember { mutableStateOf("text") }
    var mper: MediaPlayer? by remember { mutableStateOf(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFE0BBE4)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.app_title),
            fontSize = 25.sp,
            color = Color.Blue,
            fontFamily = FontFamily(Font(R.font.kai))
        )

        Spacer(modifier = Modifier.size(10.dp))

        Text(
            text = stringResource(R.string.app_author),
            fontSize = 20.sp,
            color = Color(0xFF654321)
        )

        Spacer(modifier = Modifier.size(10.dp))

        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Image(
                painter = painterResource(id = R.drawable.android),
                contentDescription = "Android 圖示",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Yellow),
                alpha = 0.6f,
            )

            Image(
                painter = painterResource(id = R.drawable.compose),
                contentDescription = "Compose icon",
                modifier = Modifier.size(100.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.firebase),
                contentDescription = "Firebase icon",
                modifier = Modifier.size(100.dp)
            )
        }

        Spacer(modifier = Modifier.size(10.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            items(51) { index ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "$index: ${animalsName[index % 10]}")
                    Image(
                        painter = painterResource(id = animals[index % 10]),
                        contentDescription = "可愛動物",
                        modifier = Modifier.size(60.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(20.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Button(
                onClick = { /* 歡迎修課功能 */ },
                modifier = Modifier
                    .border(1.dp, Color.Blue, CutCornerShape(10.dp))
                    .padding(1.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = CutCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
            ) {
                Text(text = "歡迎修課", color = Color.White)
            }

            Button(
                onClick = { /* 其他功能 */ },
                modifier = Modifier
                    .border(1.dp, Color.Blue, CutCornerShape(10.dp))
                    .padding(1.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = CutCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
            ) {
                Text(text = "展翅飛翔", color = Color.White)
            }

            Button(
                onClick = {
                    mper?.release()
                    mper = null
                    mper = MediaPlayer.create(activity, R.raw.fly)
                    mper?.start()
                },
                modifier = Modifier
                    .border(1.dp, Color.Blue, CutCornerShape(10.dp))
                    .padding(1.dp)
                    .fillMaxWidth(0.5f),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = CutCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
            ) {
                Text(text = "展翅飛翔", color = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.fly),
                    contentDescription = "fly icon",
                    modifier = Modifier.size(24.dp)
                )
            }

            Button(
                onClick = {
                    activity.finish()
                },
                modifier = Modifier
                    .border(1.dp, Color.Blue, CutCornerShape(10.dp))
                    .padding(1.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00BFFF)),
                shape = CutCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
            ) {
                Text(text = "結束App")
            }
        }

        Spacer(modifier = Modifier.size(10.dp))

        Button(
            onClick = {
                flag = if (flag == "text") "abc" else "text"
            },
            modifier = Modifier
                .border(1.dp, Color.Blue, CutCornerShape(10.dp))
                .padding(1.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
            shape = CutCornerShape(10.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
        ) {
            Text(text = "按鈕測試", color = Color.White)
        }

        Spacer(modifier = Modifier.size(10.dp))

        Text(
            text = flag,
            fontSize = 30.sp,
            color = Color(0xFF00BFFF)
        )
    }
}


// 播放音樂並顯示圖示

