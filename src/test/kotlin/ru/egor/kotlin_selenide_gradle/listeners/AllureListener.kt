package ru.egor.kotlin_selenide_gradle.listeners

import com.codeborne.selenide.Selenide
import io.qameta.allure.Attachment
import org.junit.jupiter.api.extension.AfterTestExecutionCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.openqa.selenium.OutputType
import java.lang.reflect.Method

class AllureListener: AfterTestExecutionCallback {

    @Attachment(value = "Page screenshot", type = "image/png")
    fun saveScreenshot(screenshot: ByteArray): ByteArray {
        return screenshot
    }

    override fun afterTestExecution(context: ExtensionContext?) {
        val testMethod : Method = context!!.requiredTestMethod
        val testName : String = testMethod.name
        val testFailed = if (context.executionException.isPresent) {
            true
        } else {
            false
        }
        if (testFailed){
            if (!testName.contains("Screenshot")){
                saveScreenshot(Selenide.screenshot(OutputType.BYTES)!!)
            }
        }
    }
}