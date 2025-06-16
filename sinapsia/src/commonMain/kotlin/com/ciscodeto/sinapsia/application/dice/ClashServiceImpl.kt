package com.ciscodeto.sinapsia.application.dice

import com.ciscodeto.sinapsia.application.action.find.FindAction
import com.ciscodeto.sinapsia.application.action.repository.toDomain
import com.ciscodeto.sinapsia.application.character.find.FindCharacter
import com.ciscodeto.sinapsia.application.character.repository.toDomain
import com.ciscodeto.sinapsia.domain.actions.ActionResult
import com.ciscodeto.sinapsia.domain.dice.DiceClash
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ClashServiceImpl(
    private val findCharacter: FindCharacter,
    private val findAction: FindAction,
) : ClashService {
    private lateinit var diceClash: DiceClash

    override suspend fun executeActorAction(
        actionId: Uuid,
        actorId: Uuid,
        targetId: Uuid?,
    ): ActionResult? {
        val actor = findCharacter.findById(actorId)?.toDomain()

        val counterActionsDtos = findAction.findCounterActionsFor(actionId)
        val counterActions = counterActionsDtos.map { it.toDomain(emptyList()) }

        val action = findAction.findById(actionId)?.toDomain(counterActions)

        if (action == null || actor == null) return null

        val target = if (targetId != null) findCharacter.findById(targetId)?.toDomain() else null

        diceClash = DiceClash(actor, action, target)

        return diceClash.executeInitialAction()
    }

    override suspend fun executeReaction(reactionId: Uuid): List<String>? {
        val reaction = findAction.findById(reactionId)?.toDomain(emptyList()) ?: return null
        diceClash.executeCounterAction(reaction)

        return diceClash.applyResolvedEffects(diceClash.resolveClash())
    }
}