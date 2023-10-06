package ru.egor.kotlin_selenide_gradle.pageObject

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.page
import io.qameta.allure.Step
import ru.egor.kotlin_selenide_gradle.commonInterfaces.PageInterface

class OverviewPage : PageInterface {
    private val sideBarButton = `$`("#react-burger-menu-btn")
    private val sideBarLogout = `$`("#logout_sidebar_link")

    @Step("Проверяем что указанная сумма совпадает с ценой товара")
    fun checkAmount(expectedResult: String?): OverviewPage {
        if (expectedResult != null) {
            `$`(".summary_subtotal_label").text.contains(expectedResult)
        }
        `$`("#finish").click()
        return this
    }

    @Step("Выполнить выход из аккаунта")
    override fun logout(): LoginPage {
        sideBarButton.click()
        sideBarLogout.click()
        return page(LoginPage())
    }
}
