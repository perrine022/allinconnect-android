package com.allinconnect.app.data.mapper

import com.allinconnect.app.data.dto.rating.RatingResponse
import com.allinconnect.app.domain.model.Review
import java.time.Instant
import java.time.format.DateTimeFormatter

object RatingMapper {
    fun toDomain(dto: RatingResponse): Review {
        val date = try {
            Instant.parse(dto.createdAt)
        } catch (e: Exception) {
            Instant.now()
        }
        
        val userName = dto.rater?.let { "${it.firstName} ${it.lastName}" } ?: "Utilisateur"
        
        return Review(
            id = dto.id.toString(),
            userName = userName,
            rating = dto.score.toDouble(),
            comment = dto.comment ?: "",
            date = date
        )
    }
}
