package net.javaman.optaplanner_reproducible

import org.optaplanner.core.api.solver.SolverManager
import org.optaplanner.core.config.solver.SolverConfig
import java.util.*

class Main {
    companion object {
        private val allReforges = listOf(
            ReforgeProblemFact(UUID.randomUUID(), "Clean", Rarity.COMMON, 0, 3),
            ReforgeProblemFact(UUID.randomUUID(), "Fierce", Rarity.COMMON, 4, 0),
            ReforgeProblemFact(UUID.randomUUID(), "Shiny", Rarity.COMMON, 2, 1),
            ReforgeProblemFact(UUID.randomUUID(), "Clean", Rarity.RARE, 1, 3),
            ReforgeProblemFact(UUID.randomUUID(), "Fierce", Rarity.RARE, 5, 0),
            ReforgeProblemFact(UUID.randomUUID(), "Shiny", Rarity.RARE, 3, 2),
            ReforgeProblemFact(UUID.randomUUID(), "Clean", Rarity.LEGENDARY, 1, 4),
            ReforgeProblemFact(UUID.randomUUID(), "Fierce", Rarity.LEGENDARY, 6, 0),
            ReforgeProblemFact(UUID.randomUUID(), "Shiny", Rarity.LEGENDARY, 4, 2),
        )
        private val solverManager: SolverManager<ReforgePlanningSolution, UUID> = SolverManager.create(
            SolverConfig.createFromXmlResource("reforgeSolverConfig.xml")
        )

        @JvmStatic
        fun main(args: Array<String>) {
            val availableItems = generateAvailableItems(
                mapOf(
                    Rarity.COMMON to 4,
                    Rarity.RARE to 3,
                    Rarity.LEGENDARY to 1
                )
            )
            val solverJob = solverManager.solve(UUID.randomUUID(), ReforgePlanningSolution(availableItems))
            val solution = solverJob.finalBestSolution
            solution.availableItems!!
                .map { it.reforge!! }
                .forEach { println(it.rarity.name + " " + it.name) }
        }

        private fun generateAvailableItems(itemCounts: Map<Rarity, Int>): MutableList<ItemPlanningEntity> {
            val availableItems = mutableListOf<ItemPlanningEntity>()
            for (itemCount in itemCounts) {
                for (count in 0 until itemCount.value) {
                    val rarity = itemCount.key
                    availableItems.add(
                        ItemPlanningEntity(
                            UUID.randomUUID(),
                            rarity,
                            null,
                            allReforges.filter { it.rarity == rarity }
                        )
                    )
                }
            }
            return availableItems
        }
    }
}
