package org.mandeep.landingpage.sections

import Constants.SECTION_WIDTH
import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.mandeep.landingpage.components.SectionTitle
import org.mandeep.landingpage.components.ServiceCard
import org.mandeep.landingpage.model.Section
import org.mandeep.landingpage.model.Service

@Composable
fun ServiceSection() {
    Box(
        modifier = Modifier.maxWidth(SECTION_WIDTH.px),
        contentAlignment = Alignment.TopCenter
    ) {
        ServiceContent()
    }
}

@Composable
fun ServiceContent() {
    val breakpoint = rememberBreakpoint()
    Column(modifier = Modifier.fillMaxWidth(if (breakpoint >= Breakpoint.MD) 100.percent else 90.percent)) {
        SectionTitle(
            section = Section.Service, alignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxWidth()
                .margin(bottom = 20.px)
        )
        SimpleGrid(numColumns = numColumns(base = 1, sm = 2, md = 3)) {
            Service.values().forEach { service ->
                ServiceCard(service = service)
            }
        }
    }
}