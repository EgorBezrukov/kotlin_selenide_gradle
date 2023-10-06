package ru.egor.kotlin_selenide_gradle.commonInterfaces

import ru.egor.kotlin_selenide_gradle.pageObject.LoginPage

interface PageInterface {

    fun logout() : LoginPage
}