package com.example.taskmanagermobile.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagermobile.R
import com.example.taskmanagermobile.Task
import com.example.taskmanagermobile.ui.theme.AvatarBackground
import com.example.taskmanagermobile.ui.theme.BorderGray
import com.example.taskmanagermobile.ui.theme.BrandPurple
import com.example.taskmanagermobile.ui.theme.CardBackground
import com.example.taskmanagermobile.ui.theme.DoneBackground
import com.example.taskmanagermobile.ui.theme.DoneContent
import com.example.taskmanagermobile.ui.theme.HighBackground
import com.example.taskmanagermobile.ui.theme.HighContent
import com.example.taskmanagermobile.ui.theme.InProgressBackground
import com.example.taskmanagermobile.ui.theme.InProgressContent
import com.example.taskmanagermobile.ui.theme.LowBackground
import com.example.taskmanagermobile.ui.theme.LowContent
import com.example.taskmanagermobile.ui.theme.MediumBackground
import com.example.taskmanagermobile.ui.theme.MediumContent
import com.example.taskmanagermobile.ui.theme.MutedText
import com.example.taskmanagermobile.ui.theme.PageBackground
import com.example.taskmanagermobile.ui.theme.PendingBackground
import com.example.taskmanagermobile.ui.theme.PendingContent
import com.example.taskmanagermobile.ui.theme.TaskManagerMobileTheme
import com.example.taskmanagermobile.ui.theme.TitleText

@Composable
fun HomePage(modifier: Modifier = Modifier) {
    val tasks = remember { taskList() }
    val pendingTasks = tasks.count { it.status != "Concluída" }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(PageBackground)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        AppHeader()
        SearchRow()
        CreateTaskButton()
        ListHeader(pendingTasks = pendingTasks)

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(
                items = tasks,
                key = { it.id }
            ) { task ->
                TaskCard(task = task)
            }
        }
    }
}

@Composable
private fun AppHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(38.dp),
            shape = RoundedCornerShape(10.dp),
            color = BrandPurple
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    painter = painterResource(R.drawable.ic_check_all),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )
            }
        }

        Text(
            text = "Task Manager",
            modifier = Modifier.padding(start = 12.dp),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = TitleText
        )

        Spacer(modifier = Modifier.weight(1f))

        Surface(
            modifier = Modifier.size(40.dp),
            shape = CircleShape,
            color = AvatarBackground
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "JS",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandPurple
                )
            }
        }
    }
}

@Composable
private fun SearchRow() {
    var search by remember { mutableStateOf("") }

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = CardBackground,
        unfocusedContainerColor = CardBackground,
        disabledContainerColor = CardBackground,
        focusedBorderColor = BrandPurple,
        unfocusedBorderColor = BorderGray,
        disabledBorderColor = BorderGray,
        focusedTextColor = TitleText,
        unfocusedTextColor = TitleText,
        disabledTextColor = TitleText,
        focusedLeadingIconColor = MutedText,
        unfocusedLeadingIconColor = MutedText,
        disabledLeadingIconColor = MutedText,
        focusedTrailingIconColor = MutedText,
        unfocusedTrailingIconColor = MutedText,
        cursorColor = BrandPurple
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            placeholder = { Text(text = "Buscar tarefas...", color = MutedText) },
            singleLine = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            },
            shape = RoundedCornerShape(12.dp),
            colors = textFieldColors,
            modifier = Modifier
                .weight(1f)
                .height(54.dp)
        )

        OutlinedButton(
            onClick = { },
            modifier = Modifier.height(54.dp),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, BorderGray),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = CardBackground,
                contentColor = TitleText
            )
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_filter),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = "Filtros",
                modifier = Modifier.padding(start = 6.dp),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun CreateTaskButton() {
    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = BrandPurple,
            contentColor = Color.White
        )
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_add),
            contentDescription = null,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = "Criar tarefa",
            modifier = Modifier.padding(start = 8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ListHeader(pendingTasks: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Minhas Tarefas",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = TitleText
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "$pendingTasks pendentes",
            fontSize = 13.sp,
            color = MutedText
        )
    }
}

@Composable
private fun TaskCard(task: Task) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TitleText
                )

                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    TaskChip(
                        text = task.status,
                        background = statusBackground(task.status),
                        contentColor = statusContent(task.status)
                    )
                    TaskChip(
                        text = task.priority,
                        background = priorityBackground(task.priority),
                        contentColor = priorityContent(task.priority)
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.ic_calendar),
                            contentDescription = null,
                            modifier = Modifier.size(12.dp),
                            tint = MutedText
                        )
                        Text(
                            text = task.date,
                            modifier = Modifier.padding(start = 4.dp),
                            fontSize = 11.sp,
                            color = MutedText
                        )
                    }
                }
            }

            Surface(
                modifier = Modifier.size(36.dp),
                shape = RoundedCornerShape(10.dp),
                color = HighBackground
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        painter = painterResource(R.drawable.ic_delete),
                        contentDescription = "Excluir tarefa",
                        modifier = Modifier.size(16.dp),
                        tint = HighContent
                    )
                }
            }
        }
    }
}

@Composable
private fun TaskChip(text: String, background: Color, contentColor: Color) {
    Surface(
        shape = RoundedCornerShape(6.dp),
        color = background
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = contentColor
        )
    }
}

private fun statusBackground(status: String): Color = when (status) {
    "Em andamento" -> InProgressBackground
    "Concluída" -> DoneBackground
    else -> PendingBackground
}

private fun statusContent(status: String): Color = when (status) {
    "Em andamento" -> InProgressContent
    "Concluída" -> DoneContent
    else -> PendingContent
}

private fun priorityBackground(priority: String): Color = when (priority) {
    "Alta" -> HighBackground
    "Média" -> MediumBackground
    else -> LowBackground
}

private fun priorityContent(priority: String): Color = when (priority) {
    "Alta" -> HighContent
    "Média" -> MediumContent
    else -> LowContent
}

private fun taskList(): List<Task> {
    return listOf(
        Task(1, "Entregar relatório final", "Em andamento", "Alta", "15/09/2026"),
        Task(2, "Estudar para prova de cálculo", "Pendente", "Média", "10/09/2026"),
        Task(3, "Revisar apresentação do grupo", "Concluída", "Baixa", "05/09/2026"),
        Task(4, "Preparar slides do seminário", "Pendente", "Alta", "20/09/2026"),
        Task(5, "Enviar formulário de matrícula", "Em andamento", "Média", "12/09/2026")
    )
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    TaskManagerMobileTheme {
        HomePage()
    }
}
