package com.respiroc.webapp.security

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class SecurityFacade {
    fun hasAuthority(authority: String): Boolean {
        val auth: Authentication = SecurityContextHolder
            .getContext()
            .authentication
        return auth.authorities
            .map(GrantedAuthority::getAuthority)
            .contains(authority)
    }
}