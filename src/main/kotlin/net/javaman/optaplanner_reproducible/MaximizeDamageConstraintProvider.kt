package net.javaman.optaplanner_reproducible

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore
import org.optaplanner.core.api.score.stream.Constraint
import org.optaplanner.core.api.score.stream.ConstraintCollectors.toList
import org.optaplanner.core.api.score.stream.ConstraintFactory
import org.optaplanner.core.api.score.stream.ConstraintProvider
import java.util.stream.Collectors

class MaximizeDamageConstraintProvider : ConstraintProvider {
    override fun defineConstraints(factory: ConstraintFactory): Array<Constraint> = arrayOf(maximizeDamage(factory))

    // This approach does not take full advantage of incremental solving,
    // but it is necessary to compute strength and critDamage together in the same equation
    private fun maximizeDamage(factory: ConstraintFactory) = factory.from(ItemPlanningEntity::class.java)
        .map(ItemPlanningEntity::reforge) // Get each item's reforge
        .groupBy({ 0 }, toList { reforge: ReforgeProblemFact? -> reforge }) // Compile into one List<ReforgeProblemFact>
        .reward("damage", HardSoftScore.ONE_SOFT) { _, reforges: List<ReforgeProblemFact?> ->
            val strengthSum = reforges.stream().collect(Collectors.summingInt { reforge -> reforge?.strength ?: 0 })
            val critDamageSum = reforges.stream().collect(Collectors.summingInt { reforge -> reforge?.critDamage ?: 0 })
            (100 + strengthSum) * (100 + critDamageSum)
        }
}