package com.example.taskmanagermobile.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagermobile.R
import com.example.taskmanagermobile.ui.theme.AvatarBackground
import com.example.taskmanagermobile.ui.theme.BorderGray
import com.example.taskmanagermobile.ui.theme.BrandPurple
import com.example.taskmanagermobile.ui.theme.CardBackground
import com.example.taskmanagermobile.ui.theme.HighContent
import com.example.taskmanagermobile.ui.theme.MutedText
import com.example.taskmanagermobile.ui.theme.PageBackground
import com.example.taskmanagermobile.ui.theme.TaskManagerMobileTheme
import com.example.taskmanagermobile.ui.theme.TitleText

@Composable
fun Profile(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CardBackground)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Header()

        ProfileCard()

        MenuItem(
            icon = R.drawable.ic_settings,
            title = "Configurações",
            subtitle = "Ajustes de conta e notificações"
        )
        MenuItem(
            icon = R.drawable.ic_palette,
            title = "Personalização",
            subtitle = "Temas e preferências visuais"
        )
        MenuItem(
            icon = R.drawable.ic_link,
            title = "Integrações",
            subtitle = "Calendário e plataformas de ensino"
        )

        OutlinedButton(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, HighContent),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = CardBackground,
                contentColor = HighContent
            )
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_logout),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = HighContent
            )
            Text(
                text = "Sair da Conta",
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Surface(
            modifier = Modifier.size(40.dp),
            shape = CircleShape,
            color = PageBackground
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = "Voltar",
                    modifier = Modifier.size(20.dp),
                    tint = TitleText
                )
            }
        }

        Text(
            text = "Perfil",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = TitleText
        )
    }
}

@Composable
private fun ProfileCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        border = BorderStroke(1.dp, BorderGray),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(88.dp)
                    .border(2.dp, BrandPurple, CircleShape)
                    .padding(4.dp)
                    .background(AvatarBackground, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "JS",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandPurple
                )
            }

            Text(
                text = "João Silva",
                modifier = Modifier.padding(top = 14.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = TitleText
            )

            Text(
                text = "joao.silva@email.com",
                modifier = Modifier.padding(top = 4.dp),
                fontSize = 14.sp,
                color = MutedText
            )
        }
    }
}

@Composable
private fun MenuItem(
    icon: Int,
    title: String,
    subtitle: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        border = BorderStroke(1.dp, BorderGray),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(40.dp),
                shape = RoundedCornerShape(10.dp),
                color = AvatarBackground
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = BrandPurple
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TitleText
                )
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = MutedText
                )
            }

            Icon(
                painter = painterResource(R.drawable.ic_chevron_right),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = MutedText
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    TaskManagerMobileTheme {
        Profile()
    }
}
