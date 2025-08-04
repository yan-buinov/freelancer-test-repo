package com.respiroc.webapp.config.navigation

import com.respiroc.webapp.security.SecurityFacade
import org.springframework.stereotype.Component

@Component
class NavigationProvider(
    private val securityFacade: SecurityFacade
) {

    fun getNavigationSections(): List<NavigationSection> = buildList {
        addDashboardSection()
        addVouchersSection()
        addAccountsSection()
        addReportsSection()
        addContactSection()
        addBankSection()
        if (securityFacade.hasAuthority("all:write")) {
            addUsersSection()
        }
    }

    private fun MutableList<NavigationSection>.addDashboardSection() {
        add(
            NavigationSection(
                title = "Dashboard",
                icon = "dashboard",
                items = listOf(
<<<<<<< Updated upstream
                    NavigationSectionItem(label = "General Ledger", url = "/ledger/general"),
                    NavigationSectionItem(label = "Chart of Accounts", url = "/ledger/chart-of-accounts"),
                    NavigationSectionItem(label = "Supplier", url = "/ledger/suppliers"),
                )
            ),

            NavigationSection(
                title = "Reports", icon = "chart-simple",
                items = listOf(
                    NavigationSectionItem(label = "Trial Balance", url = "/report/trial-balance"),
                    NavigationSectionItem(label = "Profit & Loss", url = "/report/profit-loss"),
                    NavigationSectionItem(label = "Balance Sheet", url = "/report/balance-sheet")
                )
            ),

            NavigationSection(
                title = "Contact", icon = "users",
                items = listOf(
                    NavigationSectionItem(label = "Customers", url = "/contact/customer"),
                    NavigationSectionItem(label = "Suppliers", url = "/contact/supplier"),
                    NavigationSectionItem(label = "New Customer", url = "/contact/customer/new"),
                    NavigationSectionItem(label = "New Supplier", url = "/contact/supplier/new")
                )
            ),

            NavigationSection(
                title = "Bank", icon = "bank",
                items = listOf(
                    NavigationSectionItem(label = "Bank Accounts Overview", url = "/bank/account"),
=======
                    NavigationSectionItem("Home", "/dashboard")
>>>>>>> Stashed changes
                )
            ),

            NavigationSection(
                title = "Invoice", icon = "file-invoice",
                items = listOf(
                    NavigationSectionItem(label = "Invoices", url = "/invoice"),
                    NavigationSectionItem(label = "New Invoice", url = "/invoice/new"),
                )
            )
        )
    }

    private fun MutableList<NavigationSection>.addVouchersSection() {
        add(
            NavigationSection(
                title = "Vouchers",
                icon = "file-text",
                items = listOf(
                    "Overview" to "/voucher/overview",
                    "Advanced Voucher" to "/voucher/new-advanced-voucher",
                    "Reception" to "/voucher-reception"
                ).map(::toItem)
            )
        )
    }

    private fun MutableList<NavigationSection>.addAccountsSection() {
        add(
            NavigationSection(
                title = "Accounts",
                icon = "receipt",
                items = listOf(
                    "General Ledger" to "/ledger/general",
                    "Chart of Accounts" to "/ledger/chart-of-accounts"
                ).map(::toItem)
            )
        )
    }

    private fun MutableList<NavigationSection>.addReportsSection() {
        add(
            NavigationSection(
                title = "Reports",
                icon = "chart-simple",
                items = listOf(
                    "Trial Balance" to "/report/trial-balance",
                    "Profit & Loss" to "/report/profit-loss",
                    "Balance Sheet" to "/report/balance-sheet"
                ).map(::toItem)
            )
        )
    }

    private fun MutableList<NavigationSection>.addContactSection() {
        add(
            NavigationSection(
                title = "Contact",
                icon = "users",
                items = listOf(
                    "Customers" to "/contact/customer",
                    "Suppliers" to "/contact/supplier",
                    "New Customer" to "/contact/customer/new",
                    "New Supplier" to "/contact/supplier/new"
                ).map(::toItem)
            )
        )
    }

    private fun MutableList<NavigationSection>.addBankSection() {
        add(
            NavigationSection(
                title = "Bank",
                icon = "bank",
                items = listOf(
                    "Bank Accounts Overview" to "/bank/account"
                ).map(::toItem)
            )
        )
    }

    private fun MutableList<NavigationSection>.addUsersSection() {
        add(
            NavigationSection(
                title = "Users",
                icon = "user-plus",
                items = listOf(
                    "Create new User in tenant" to "/users/new"
                ).map(::toItem)
            )
        )
    }

    private fun toItem(pair: Pair<String, String>) =
        NavigationSectionItem(label = pair.first, url = pair.second)
}
