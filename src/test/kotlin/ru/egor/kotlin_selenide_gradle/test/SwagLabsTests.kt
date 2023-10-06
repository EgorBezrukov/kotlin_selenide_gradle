package ru.egor.kotlin_selenide_gradle.test

import io.qameta.allure.Description
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import ru.egor.kotlin_selenide_gradle.baseTest.BaseTest
import ru.egor.kotlin_selenide_gradle.pageObject.LoginPage

@DisplayName("Тесты для формы регистрации")
class SwagLabsTests : BaseTest() {
    private var loginPage: LoginPage? = null

    @BeforeEach
    @DisplayName("Инициализация страницы авторизации")
    fun init() {
        loginPage = LoginPage()
    }

    @Test
    @DisplayName("Успешный вход")
    fun successfullyLogged() {
        loginPage
            ?.successAuth("standard_user", "secret_sauce")
            ?.checkPageOpen("https://www.saucedemo.com/inventory.html")
            ?.logout()
    }
    @Test
    @DisplayName("Тест падает с ошибкой")
    fun failedLoginTest() {
        loginPage
            ?.successAuth("standard", "secret")
            ?.checkPageOpen("https://www.saucedemo.com/inventory.html")
            ?.logout()
    }

    @Test
    @DisplayName("Вход с пустыми полями")
    @Description("Вход с пустыми полями логина и пароля, проверка сообщения об ошибке")
    fun emptiesField() {
        loginPage
            ?.unSuccessAuth("", "")
            ?.checkErrorMessage("Epic sadface: Username is required")
            ?.cleanField()
    }

    @Test
    @DisplayName("Вход с невалидным логином")
    @Description("Проверить, что при вводе неправильного логина или пароля отображается сообщение об ошибке")
    fun incorrectLogin() {
        loginPage
            ?.unSuccessAuth("HELPDESK_LOGIN", "SWAGLAB_PASSWORD")
            ?.checkErrorMessage("Epic sadface: Username and password do not match any user in this service")
            ?.cleanField()
    }

    @Test
    @DisplayName("Проверка количество элементов на странице")
    @Description("Проверить, что при входе на главную страницу, количество элементов равно 6")
    fun checkSizeElement() {
        loginPage
            ?.successAuth("standard_user", "secret_sauce")
            ?.checkSizePageElements(6)
            ?.logout()
    }

    @Test
    @DisplayName("Пользовательский сценарий, выполнение покупки")
    fun swagShopUserCase() {
        loginPage
            ?.successAuth("standard_user", "secret_sauce")
            ?.addToCart()
            ?.checkoutBtnClick()
            ?.fillField("Lol", "Balabol", "1231")
            ?.checkAmount("$29.99")
            ?.logout()
    }
}