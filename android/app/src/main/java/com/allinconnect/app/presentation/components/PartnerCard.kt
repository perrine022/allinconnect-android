package com.allinconnect.app.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.allinconnect.app.domain.model.Partner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartnerCard(
    partner: Partner,
    onClick: () -> Unit,
    onFavoriteClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = partner.name,
                        style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Text(
                        text = partner.category,
                        style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                    )
                    
                    if (partner.city.isNotEmpty()) {
                        Text(
                            text = partner.city,
                            style = androidx.compose.material3.MaterialTheme.typography.bodySmall
                        )
                    }
                }
                
                onFavoriteClick?.let {
                    IconButton(onClick = it) {
                        Icon(
                            imageVector = if (partner.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favoris",
                            tint = if (partner.isFavorite) androidx.compose.ui.graphics.Color.Red else androidx.compose.ui.graphics.Color.Gray
                        )
                    }
                }
            }
            
            if (partner.rating > 0) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "⭐ ${String.format("%.1f", partner.rating)} (${partner.reviewCount} avis)",
                    style = androidx.compose.material3.MaterialTheme.typography.bodySmall
                )
            }
            
            if (partner.discount != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "CLUB10: -${partner.discount}%",
                    style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = androidx.compose.ui.graphics.Color(0xFFFFD700)
                )
            }
        }
    }
}
