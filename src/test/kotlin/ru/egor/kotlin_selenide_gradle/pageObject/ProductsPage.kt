package ru.egor.kotlin_selenide_gradle.pageObject

import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.*
import com.codeborne.selenide.WebDriverRunner
import io.qameta.allure.Step
import org.junit.jupiter.api.Assertions
import ru.egor.kotlin_selenide_gradle.commonInterfaces.PageInterface
import java.time.Duration.ofMillis

class ProductsPage : PageInterface {

    private val addToCartButton = `$`("#add-to-cart-sauce-labs-backpack")
    private val cartBadge = `$`(".shopping_cart_badge")
    private val cartButton = `$`(".shopping_cart_link")
    private val productNames = `$$`(".inventory_item_name")
    private val sideBarButton = `$`("#react-burger-menu-btn")
    private val sideBarLogout = `$`("#logout_sidebar_link")

    @Step("Проверить что страница открылась")
    fun checkPageOpen(currentUrl: String?): ProductsPage {
        val url = WebDriverRunner.getWebDriver().currentUrl
        Assertions.assertEquals(url, currentUrl)
        return this
    }

    @Step("Добавить товар корзину и перейти в корзину")
    fun addToCart(): CartPage {
        addToCartButton.click()
        cartButton.click()
        return page(CartPage())
    }

    @Step("Проверить количество элементов на странице")
    fun checkSizePageElements(actual: Int): ProductsPage {
        productNames.should(CollectionCondition.size(actual), ofMillis(5000))
        return this
    }

    @Step("Проверить количество товаров в корзине")
    fun checkSizeCartElements(actual: Int): ProductsPage {
        cartBadge.shouldHave(text(actual.toString()))
        return this
    }

    @Step("Выполнить выход из аккаунта")
    override fun logout(): LoginPage {
        sideBarButton.click()
        sideBarLogout.click()
        return page(LoginPage())
    }
}