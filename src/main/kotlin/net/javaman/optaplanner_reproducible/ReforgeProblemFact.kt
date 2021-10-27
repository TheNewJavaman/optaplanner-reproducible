package net.javaman.optaplanner_reproducible

import org.optaplanner.core.api.domain.lookup.PlanningId
import java.util.*

data class ReforgeProblemFact(
    @PlanningId
    val id: UUID,
    val name: String,
    val rarity: Rarity,
    val strength: Int,
    val critDamage: Int
)