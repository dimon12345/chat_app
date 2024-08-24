package com.example.presentation.ui.app

interface AppPageStateSelector {
    fun selectState(appStateType: AppPageContentType)
}