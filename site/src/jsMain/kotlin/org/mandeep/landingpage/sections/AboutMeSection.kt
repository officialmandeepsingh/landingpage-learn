package org.mandeep.landingpage.sections

import Constants.FONT_FAMILY
import Constants.LOREM_IPSUM_SHORT
import Constants.SECTION_WIDTH
import Res
import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.mandeep.landingpage.components.SectionTitle
import org.mandeep.landingpage.components.SkillBar
import org.mandeep.landingpage.model.Section
import org.mandeep.landingpage.model.Skill
import org.mandeep.landingpage.model.Theme
import org.mandeep.landingpage.styles.AboutMeImageStyle
import org.mandeep.landingpage.styles.AboutTextStyle
import org.mandeep.landingpage.util.ObserveViewPortEntered
import org.mandeep.landingpage.util.animateNumber


@Composable
fun AboutMeSection() {
    Box(
        modifier = Modifier.id(Section.About.id).maxWidth(SECTION_WIDTH.px).padding(topBottom = 150.px),
        contentAlignment = Alignment.TopCenter
    ) {
        AboutContent()
    }
}

@Composable
fun AboutContent() {
    val breakpoint = rememberBreakpoint()
    Column(
        modifier = Modifier.fillMaxWidth(
            if (breakpoint >= Breakpoint.MD) 100.percent else 90.percent
        ).maxWidth(1200.px),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SimpleGrid(
            modifier = Modifier.fillMaxWidth(
                if (breakpoint >= Breakpoint.MD) 90.percent
                else 100.percent
            ), numColumns = numColumns(base = 1, md = 2)
        ) {
            if (breakpoint >= Breakpoint.MD) {
                AboutImage()
            }
            AboutMe()

        }

    }
}

@Composable
fun AboutImage() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = AboutMeImageStyle.toModifier()
                .fillMaxWidth(80.percent),
            src = Res.Image.about,
            desc = "About Image"
        )
    }
}

@Composable
fun AboutMe() {
    val scope = rememberCoroutineScope()
    var viewPortEntered by remember { mutableStateOf(false) }
    val animatedPercentage = remember { mutableStateListOf(0, 0, 0, 0, 0) }


    ObserveViewPortEntered(sectionId = Section.About.id, distanceFromTop = 250.0) {
        viewPortEntered = true
        Skill.values().forEach { skill ->
            scope.launch {
                animateNumber(number = skill.percentage.value.toInt(), onUpdate = {
                    animatedPercentage[skill.ordinal] = it
                })
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        SectionTitle(section = Section.About)
        P(
            attrs = AboutTextStyle.toModifier()
                .margin(topBottom = 25.px)
                .maxWidth(500.px)
                .fontFamily(FONT_FAMILY)
                .fontSize(18.px)
                .fontWeight(FontWeight.Normal)
                .fontStyle(FontStyle.Italic)
                .color(Theme.Secondary.rgb)
                .toAttrs()
        ) { Text(LOREM_IPSUM_SHORT) }
        Skill.values().forEach { skill ->
            SkillBar(
                title = skill.tittle,
                index = skill.ordinal,
                percentage = if (viewPortEntered) skill.percentage else 0.percent,
                animatedPercentage = if (viewPortEntered) animatedPercentage[skill.ordinal] else 0
            )
        }
    }


}