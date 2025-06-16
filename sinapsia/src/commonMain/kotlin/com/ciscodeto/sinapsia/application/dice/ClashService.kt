package com.ciscodeto.sinapsia.application.dice

import com.ciscodeto.sinapsia.domain.actions.ActionResult
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface ClashService {
    suspend fun executeActorAction(actionId: Uuid, actorId: Uuid, targetId: Uuid?): ActionResult?
    suspend fun executeReaction(reactionId: Uuid): List<String>?
}