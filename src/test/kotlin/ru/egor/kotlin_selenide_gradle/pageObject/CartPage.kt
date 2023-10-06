package ru.egor.kotlin_selenide_gradle.pageObject

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.page
import io.qameta.allure.Step
import ru.egor.kotlin_selenide_gradle.commonInterfaces.PageInterface

class CartPage : PageInterface {

    private val sideBarButton = `$`("#react-burger-menu-btn")
    private val sideBarLogout = `$`("#logout_sidebar_link")

    @Step("Перейти на страницу заполнения информации о себе")
    fun checkoutBtnClick(): YourInformation {
        `$`("#checkout").click()
        return page(YourInformation())
    }

    @Step("Выполнить выход из аккаунта")
    override fun logout(): LoginPage {
        sideBarButton.click()
        sideBarLogout.click()
        return page(LoginPage())
    }
}