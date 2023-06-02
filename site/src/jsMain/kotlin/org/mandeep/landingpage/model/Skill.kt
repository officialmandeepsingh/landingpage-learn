package org.mandeep.landingpage.model

import org.jetbrains.compose.web.css.CSSSizeValue
import org.jetbrains.compose.web.css.CSSUnit
import org.jetbrains.compose.web.css.percent

enum class Skill(val tittle: String, val percentage: CSSSizeValue<CSSUnit.percent>) {
    JAVA(tittle = "Java", percentage = 90.percent),
    Python(tittle = "Python", percentage = 80.percent),
    JavaScript(tittle = "Java Script", percentage = 60.percent),
    Kotlin(tittle = "Kotlin", percentage = 85.percent)
}