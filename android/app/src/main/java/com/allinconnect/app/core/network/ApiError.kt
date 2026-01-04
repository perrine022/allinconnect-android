package com.allinconnect.app.core.network

sealed class ApiError : Exception() {
    data class HttpError(val statusCode: Int, val message: String?) : ApiError()
    data class NetworkError(val throwable: Throwable) : ApiError()
    data class Unauthorized(val reason: String?) : ApiError()
    object NotFound : ApiError()
    data class DecodingError(val throwable: Throwable) : ApiError()
    object InvalidUrl : ApiError()
    object InvalidResponse : ApiError()
    
    val shouldForceLogout: Boolean
        get() = when (this) {
            is Unauthorized -> reason in listOf("Token expired", "User not found", "Invalid token")
            else -> false
        }
    
    val userMessage: String
        get() = when (this) {
            is HttpError -> message ?: "Erreur HTTP $statusCode"
            is NetworkError -> "Problème de connexion. Vérifiez votre connexion internet."
            is Unauthorized -> when (reason) {
                "Token expired" -> "Votre session a expiré. Veuillez vous reconnecter."
                "User not found" -> "Votre compte n'existe plus. Veuillez vous reconnecter."
                "Invalid token" -> "Token invalide. Veuillez vous reconnecter."
                else -> reason?.let { "Non autorisé: $it. Veuillez vous reconnecter." }
                    ?: "Non autorisé. Veuillez vous reconnecter."
            }
            is NotFound -> "Ressource non trouvée"
            is DecodingError -> "Erreur lors du traitement de la réponse"
            is InvalidUrl -> "URL invalide"
            is InvalidResponse -> "Réponse invalide du serveur"
        }
}

