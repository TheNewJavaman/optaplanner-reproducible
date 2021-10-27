package net.javaman.optaplanner_reproducible

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty
import org.optaplanner.core.api.domain.solution.PlanningScore
import org.optaplanner.core.api.domain.solution.PlanningSolution
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore

@PlanningSolution
class ReforgePlanningSolution @JvmOverloads constructor(
    @PlanningEntityCollectionProperty
    val availableItems: List<ItemPlanningEntity>? = null,
    @PlanningScore
    val score: HardSoftScore? = null,
)