package ru.egor.kotlin_selenide_gradle.pageObject

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.*
import com.codeborne.selenide.WebDriverRunner
import io.qameta.allure.Step

class LoginPage {

    private val loginField = `$`("#user-name")
    private val passwordField = `$`("#password")
    private val loginButton = `$`("#login-button")
    private val errorMessage = `$`("[data-test=error]")

    init {
        open("https://www.saucedemo.com/")
        check(WebDriverRunner.getWebDriver().title == "Swag Labs") {
            "This is not Login Page, " +
                    "current page is: " + WebDriverRunner.getWebDriver().currentUrl
        }
    }
    @Step("Успешная авторизация")
    fun successAuth(login: String?, password: String?): ProductsPage {
        loginField.sendKeys(login)
        passwordField.sendKeys(password)
        loginButton.click()
        return page(ProductsPage())
    }

    @Step("Неуспешная авторизация")
    fun unSuccessAuth(login: String?, password: String?): LoginPage {
        loginField.highlight().sendKeys(login)
        passwordField.highlight().setValue(password)
        loginButton.click()
        return this
    }

    @Step("Проверить, что отображается сообщение об ошибке")
    fun checkErrorMessage(message: String?): LoginPage {
        errorMessage.shouldHave(text(message!!))
        return this
    }

    @Step("Очистить поле логина и пароля")
    fun cleanField(): LoginPage {
        loginField.clear()
        passwordField.clear()
        return this
    }

}

