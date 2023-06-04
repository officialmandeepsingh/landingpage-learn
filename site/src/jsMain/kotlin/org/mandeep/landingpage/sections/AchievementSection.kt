package org.mandeep.landingpage.sections

import Constants.SECTION_WIDTH
import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.px
import org.mandeep.landingpage.components.AchievementCard
import org.mandeep.landingpage.model.Achievement
import org.mandeep.landingpage.model.Section
import org.mandeep.landingpage.model.Theme
import org.mandeep.landingpage.util.ObserveViewPortEntered
import org.mandeep.landingpage.util.animateNumber


@Composable
fun AchievementSection() {
    Box(
        modifier = Modifier.id(Section.Achievements.id).maxWidth(SECTION_WIDTH.px).padding(topBottom = 50.px)
            .backgroundColor(
                Theme.LighterGray.rgb
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        AchievementsContent()
    }
}

@Composable
fun AchievementsContent() {
    val breakpoint = rememberBreakpoint()
    var viewportEntered by remember { mutableStateOf(false) }
    val animatedNumbers = remember { mutableStateListOf(0, 0, 0, 0) }
    val scope = rememberCoroutineScope()

    ObserveViewPortEntered(Section.Achievements.id, distanceFromTop = 700.0) {
        viewportEntered = true
        Achievement.values().forEach { achievement ->
            scope.launch {
                animateNumber(number = achievement.number) {
                    animatedNumbers[achievement.ordinal] = it
                }
            }
        }
    }


    SimpleGrid(numColumns = numColumns(base = 1, md = 2, lg = 4)) {
        Achievement.values().forEach { achievement ->
            AchievementCard(
                modifier = Modifier.margin(
                    right = if (achievement == Achievement.Team) 0.px
                    else {
                        if (breakpoint > Breakpoint.SM) 40.px else 0.px
                    },
                    bottom = if (breakpoint > Breakpoint.MD) 0.px else 40.px
                ),
                animatedNumber = if (viewportEntered) animatedNumbers[achievement.ordinal] else 0,
                achievement = achievement
            )
        }
    }
}
