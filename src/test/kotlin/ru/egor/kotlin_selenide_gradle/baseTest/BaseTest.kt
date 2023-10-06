package ru.egor.kotlin_selenide_gradle.baseTest

import com.codeborne.selenide.Configuration
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import ru.egor.kotlin_selenide_gradle.listeners.AllureListener

@ExtendWith(AllureListener::class)
abstract class BaseTest {

    companion object {
        @JvmStatic
        @BeforeAll
        @DisplayName("Инициализация настроек браузера")
        fun setup() {
            Configuration.browser = "chrome"
            Configuration.browserSize = "1920x1080"
            Configuration.browserPosition = "0x0"
            Configuration.timeout = 10000
        }
    }
}