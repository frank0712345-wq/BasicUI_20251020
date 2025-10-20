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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
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
    // 動物圖片資源
    val animals = listOf(
        R.drawable.animal0, R.drawable.animal1,
        R.drawable.animal2, R.drawable.animal3,
        R.drawable.animal4, R.drawable.animal5,
        R.drawable.animal6, R.drawable.animal7,
        R.drawable.animal8, R.drawable.animal9
    )

    // 動物名稱列表
    val animalsName = listOf("鴨子", "企鵝", "青蛙", "貓頭鷹", "海豚", "牛", "無尾熊", "獅子", "狐狸", "小雞")

    // 使用 remember 和 mutableStateOf 管理按鈕按下的狀態
    var flag by remember { mutableStateOf("text") }

    // 定義 MediaPlayer 變數
    var mper: MediaPlayer? = null

    // 取得當前的 Context
    val context = LocalContext.current

    // 使用 DisposableEffect 來管理 MediaPlayer 的生命週期
    DisposableEffect(Unit) {
        onDispose {
            // 釋放 MediaPlayer 資源，避免記憶體洩漏
            mper?.release()
            mper = null
        }
    }

    // 隨機動物圖片按鈕
    var randomAnimalImage by remember { mutableStateOf(animals[0]) }

    // 主 UI 結構
    Column(
        modifier
            .fillMaxSize()
            .background(Color(0xFFE0BBE4)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 顯示應用程式名稱
        Text(
            text = stringResource(R.string.app_title),
            fontSize = 25.sp,
            color = Color.Blue,
            fontFamily = FontFamily(Font(R.font.kai)) // 確保 kai.ttf 存在
        )

        Spacer(modifier = Modifier.size(10.dp))

        // 顯示作者名稱
        Text(
            text = "游禎友",  // 更新為新作者名稱
            fontSize = 20.sp,
            color = Color(0xFF654321)
        )

        Spacer(modifier = Modifier.size(10.dp))

        // 顯示三個圖示（Android, Compose, Firebase）
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

        // 顯示動物列表
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

        // 按鈕區域
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        ) {
            Button(onClick = {
                flag = if (flag == "text") "hello" else "text"
            }) {
                Text(text = "哈摟")
            }

            Button(onClick = {
                // 實際功能你可以補上
            }) {
                Text(text = "歡迎修課")
            }

            Button(onClick = {
                mper?.release()
                mper = MediaPlayer.create(activity, R.raw.fly)
                mper?.start()
            }) {
                Text(text = "展翅飛翔")
            }

            Button(
                onClick = {
                    activity.finish()
                },
                modifier = Modifier
                    .border(1.dp, Color.Blue, CutCornerShape(10))
                    .padding(1.dp), // 避免邊框被內容覆蓋
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00BFFF)),
                shape = CutCornerShape(10),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
            ) {
                Text(text = "結束App")
            }
        }

        Spacer(modifier = Modifier.size(10.dp))

        // 顯示按鈕變化的文字
        Text(
            text = flag,
            fontSize = 30.sp,
            color = Color(0xFF00BFFF)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BasicUITheme {
        Main(activity = Activity())
    }
}
