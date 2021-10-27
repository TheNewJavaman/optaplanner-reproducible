package net.javaman.optaplanner_reproducible

import org.optaplanner.core.api.domain.entity.PlanningEntity
import org.optaplanner.core.api.domain.lookup.PlanningId
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider
import org.optaplanner.core.api.domain.variable.PlanningVariable
import java.util.*

@PlanningEntity
data class ItemPlanningEntity @JvmOverloads constructor(
    @PlanningId
    val id: UUID? = null,
    val rarity: Rarity? = null,
    @PlanningVariable(valueRangeProviderRefs = ["reforgeRange"])
    var reforge: ReforgeProblemFact? = null,
    @ValueRangeProvider(id = "reforgeRange")
    @ProblemFactCollectionProperty
    val availableReforges: List<ReforgeProblemFact>? = null
)