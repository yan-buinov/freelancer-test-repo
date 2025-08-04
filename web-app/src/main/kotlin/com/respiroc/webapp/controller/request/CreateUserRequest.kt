package com.respiroc.webapp.controller.request

import com.respiroc.util.constant.TenantRoleCode

data class CreateUserRequest (
    val email: String,
    val password: String,
    val tenantRole: TenantRoleCode
) {}