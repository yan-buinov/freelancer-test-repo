package com.respiroc.webapp.controller.web

import com.respiroc.user.application.UserService
import com.respiroc.util.constant.TenantRoleCode
import com.respiroc.webapp.controller.BaseController
import com.respiroc.webapp.controller.request.CreateUserRequest
import jakarta.validation.Valid
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import kotlin.collections.toTypedArray

@Controller
@RequestMapping(path = ["/users"])
class UserWebController(
    private val userService: UserService
) : BaseController() {

    @GetMapping("/new")
    @PreAuthorize("hasAuthority('all:write')")
    fun createUserForm(model: Model): String {
        model.addAttribute("createUserRequest", CreateUserRequest("", "", TenantRoleCode.EMPLOYEE))
        model.addAttribute("roleOptions", TenantRoleCode.entries.toTypedArray())
        try {
            addCommonAttributesForCurrentTenant(model, "Create User")
            return "user/create"
        } catch (_: Exception) {
            addCommonAttributes(model, "Create User")
            return "user/create"
        }
    }

}

@Controller
@RequestMapping(path = ["/htmx/users"])
class UserHTMXController(
    private val userService: UserService,
) : BaseController() {

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('all:write')")
    fun createUser(
        @Valid @ModelAttribute createUserRequest: CreateUserRequest,
        @CookieValue("token", required = true) token: String,
        bindingResult: BindingResult,
        model: Model,
    ): String {
        if (bindingResult.hasErrors()) {
            model.addAttribute(
                errorMessageAttributeName,
                "Please fill in all required fields correctly."
            )
            return "fragments/error-message"
        }

        val (email, password, role) = createUserRequest

        userService.registerUserWithRole(
            email, password, role, user()
        )

        return "redirect:htmx:/"
    }

}