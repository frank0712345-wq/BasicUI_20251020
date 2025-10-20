package tw.edu.pu.csim.tcyang.basicui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
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
fun Main(modifier: Modifier = Modifier, activity: ComponentActivity) {
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
            text = stringResource(R.string.app_author),
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

        // 將按鈕改為橫向排列
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp), // 調整間距
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp) // 填滿寬度並給予左右邊距
        ) {
            Button(onClick = {
                // 實際功能你可以補上
            }) {
                Text(text = "歡迎修課")
            }

            Button(onClick = {
                // 實際功能你可以補上
            }) {
                Text(text = "展翅飛翔")
            }

            Button(onClick = {
                // 正確關閉 App
                activity.finish()
            }) {
                Text(text = "結束App")
            }
        }

        Spacer(modifier = Modifier.size(10.dp))

        // 顯示測試按鈕
        Button(
            onClick = {
                // 切換 flag 的值
                flag = if (flag == "text") {
                    "abc"
                } else {
                    "text"
                }
            }
        ) {
            Text(text = "按鈕測試")
        }

        Spacer(modifier = Modifier.size(10.dp))

        // 顯示 flag 的值
        Text(text = flag)
    }
}
