package controller

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import viewmodel.Viewmodel

class Controller: KoinComponent {
    val viewmodel: Viewmodel by inject()
}