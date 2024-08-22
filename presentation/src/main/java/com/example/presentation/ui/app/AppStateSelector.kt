package com.example.presentation.ui.app

interface AppStateSelector {
    fun selectState(appStateType: AppContentType)
}