package ru.egor.kotlin_selenide_gradle.pageObject

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.page
import io.qameta.allure.Step
import ru.egor.kotlin_selenide_gradle.commonInterfaces.PageInterface

class YourInformation: PageInterface {
    private val sideBarButton = `$`("#react-burger-menu-btn")
    private val sideBarLogout = `$`("#logout_sidebar_link")

    @Step("Заполняем информацию о себе и переходим на следующую страницу")
    fun fillField(firstName: String?, lastName: String?, postalCode: String?): OverviewPage {
        `$`("[data-test=firstName]").sendKeys(firstName)
        `$`("[data-test=lastName]").sendKeys(lastName)
        `$`("[data-test=postalCode]").sendKeys(postalCode)
        `$`("#continue").click()
        return page(OverviewPage())
    }

    @Step("Выполнить выход из аккаунта")
   override fun logout(): LoginPage {
        sideBarButton.click()
        sideBarLogout.click()
        return page(LoginPage())
    }
}