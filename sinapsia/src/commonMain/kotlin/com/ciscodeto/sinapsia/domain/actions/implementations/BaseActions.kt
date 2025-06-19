package com.ciscodeto.sinapsia.domain.actions.implementations

import com.ciscodeto.sinapsia.application.action.repository.ActionRepository
import com.ciscodeto.sinapsia.application.action.repository.toDto
import com.ciscodeto.sinapsia.domain.actions.ActionId
import com.ciscodeto.sinapsia.domain.actions.ConfigurableAction
import com.ciscodeto.sinapsia.domain.actions.effects.EffectType
import com.ciscodeto.sinapsia.domain.attributes.AttributeType
import kotlinx.coroutines.flow.first
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
fun baseActions(): List<ConfigurableAction> {
    val apararId = ActionId()
    val esquivarId = ActionId()
    val barreiraId = ActionId()
    val ataquePoderosoId = ActionId()
    val tiroCerteiroId = ActionId()
    val bolaDeFogoId = ActionId()

    val descansar = ConfigurableAction(
        id = apararId,
        name = "Descansar",
        healthCost = 0,
        energyCost = 0,
        goldCost = 0,
        requiresTarget = false,
        requiresReaction = false,
        successAttributes = listOf(),
        effectAttributes = AttributeType.entries,
        effectsOnSuccess = listOf(EffectType.ENERGY),
        counterActions = emptyList()
    )

    val esquivar = ConfigurableAction(
        id = esquivarId,
        name = "Esquivar",
        healthCost = 0,
        energyCost = 15,
        goldCost = 0,
        requiresTarget = false,
        requiresReaction = false,
        successAttributes = listOf(AttributeType.DEXTERITY),
        effectAttributes = emptyList(),
        effectsOnSuccess = listOf(EffectType.MISS),
        counterActions = emptyList()
    )

    val barreiraMagica = ConfigurableAction(
        id = barreiraId,
        name = "Barreira Mágica",
        healthCost = 0,
        energyCost = 20,
        goldCost = 0,
        requiresTarget = false,
        requiresReaction = false,
        successAttributes = listOf(AttributeType.INTELLIGENCE, AttributeType.WISDOM),
        effectAttributes = listOf(AttributeType.INTELLIGENCE),
        effectsOnSuccess = listOf(EffectType.MISS),
        counterActions = emptyList()
    )

    val ataquePoderoso = ConfigurableAction(
        id = ataquePoderosoId,
        name = "Ataque Poderoso",
        healthCost = 0,
        energyCost = 20,
        goldCost = 0,
        requiresTarget = true,
        requiresReaction = true,
        successAttributes = listOf(AttributeType.STRENGTH),
        effectAttributes = listOf(AttributeType.STRENGTH),
        effectsOnSuccess = listOf(EffectType.DAMAGE),
        counterActions = listOf(esquivar)
    )

    val tiroCerteiro = ConfigurableAction(
        id = tiroCerteiroId,
        name = "Tiro Certeiro",
        healthCost = 0,
        energyCost = 15,
        goldCost = 0,
        requiresTarget = true,
        requiresReaction = true,
        successAttributes = listOf(AttributeType.DEXTERITY, AttributeType.INTELLIGENCE),
        effectAttributes = listOf(AttributeType.DEXTERITY),
        effectsOnSuccess = listOf(EffectType.DAMAGE),
        counterActions = listOf(esquivar)
    )

    val golpeSimples = ConfigurableAction(
        id = ActionId(),
        name = "Golpe Simples",
        healthCost = 0,
        energyCost = 8,
        goldCost = 0,
        requiresTarget = true,
        requiresReaction = false,
        successAttributes = listOf(AttributeType.STRENGTH),
        effectAttributes = listOf(AttributeType.STRENGTH),
        effectsOnSuccess = listOf(EffectType.DAMAGE),
        counterActions = emptyList()
    )

    val disparoRapido = ConfigurableAction(
        id = ActionId(),
        name = "Disparo Rápido",
        healthCost = 0,
        energyCost = 10,
        goldCost = 0,
        requiresTarget = true,
        requiresReaction = false,
        successAttributes = listOf(AttributeType.DEXTERITY),
        effectAttributes = listOf(AttributeType.DEXTERITY),
        effectsOnSuccess = listOf(EffectType.DAMAGE),
        counterActions = emptyList()
    )

    val bolaDeFogo = ConfigurableAction(
        id = bolaDeFogoId,
        name = "Bola de Fogo",
        healthCost = 0,
        energyCost = 30,
        goldCost = 0,
        requiresTarget = true,
        requiresReaction = true,
        successAttributes = listOf(AttributeType.INTELLIGENCE),
        effectAttributes = listOf(AttributeType.INTELLIGENCE),
        effectsOnSuccess = listOf(EffectType.DAMAGE),
        counterActions = listOf(esquivar, barreiraMagica)
    )

    return listOf(
        descansar,
        esquivar,
        barreiraMagica,
        ataquePoderoso,
        tiroCerteiro,
        bolaDeFogo,
        golpeSimples,
        disparoRapido
    )
}

suspend fun seedBaseActionsIfEmpty(actionRepository: ActionRepository) {
    val existing = actionRepository.getAllActions().first()
    if (existing.isEmpty()) {
        baseActions().forEach { actionRepository.saveAction(it.toDto()) }
        println("✔ Base actions seed concluído.")
    } else {
        println("ℹ Base actions já estavam presentes.")
    }
}