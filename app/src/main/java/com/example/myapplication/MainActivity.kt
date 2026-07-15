package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        BusinessCard(
                            name = "Ella Sharmaine N. Abeso",
                            title = "Full Stack Web Developer (pending)",
                            phone = "09544806017",
                            email = "eabeso63122@liceo.edu.ph",
                            address = "Puerto, Cagayan de Oro City",
                            profileImageResId = R.drawable.pfp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BusinessCard(
    name: String,
    title: String,
    phone: String,
    email: String,
    address: String,
    modifier: Modifier = Modifier,
    profileImageResId: Int? = null
) {
    val darkBlue = Color(0xFF00122A)
    val lightBlue = Color(0xFF3DDCFB)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .aspectRatio(1.6f),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = darkBlue)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Top Section: Avatar and Name/Title side by side
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Avatar (Image or Initials)
                    Surface(
                        modifier = Modifier.size(80.dp),
                        shape = CircleShape,
                        color = Color.White,
                        border = androidx.compose.foundation.BorderStroke(2.dp, lightBlue)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            if (profileImageResId != null) {
                                Image(
                                    painter = painterResource(id = profileImageResId),
                                    contentDescription = "Profile Picture",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            } else {
                                val initials = name.split(" ")
                                    .filter { it.isNotEmpty() }
                                    .take(2)
                                    .map { it.first().uppercase() }
                                    .joinToString("")
                                Text(
                                    text = initials,
                                    color = darkBlue,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Name and Title
                    Column {
                        Text(
                            text = name,
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 24.sp,
                            maxLines = 2
                        )
                        Text(
                            text = title,
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .width(30.dp)
                                .height(2.dp)
                                .background(lightBlue)
                        )
                    }
                }

                // Contact Info (Bottom Section)
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(bottom = 4.dp)
                ) {
                    ContactInfoRow(icon = Icons.Default.Phone, text = phone)
                    ContactInfoRow(icon = Icons.Default.Email, text = email)
                    ContactInfoRow(icon = Icons.Default.LocationOn, text = address)
                }
            }
        }
    }
}

@Composable
fun ContactInfoRow(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(24.dp),
            shape = CircleShape,
            color = Color.White
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .size(16.dp),
                tint = Color(0xFF001B3D)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            color = Color.White,
            fontSize = 12.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    MyApplicationTheme {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            BusinessCard(
                name = "Bienedict E. Caballero",
                title = "Data Analytics (pending)",
                phone = "09275117041",
                email = "bcaballero06578@liceo.edu.ph",
                address = "BRGY17 Magsaysay Street CDOC",
                profileImageResId = R.drawable.pfp
            )
        }
    }
}
