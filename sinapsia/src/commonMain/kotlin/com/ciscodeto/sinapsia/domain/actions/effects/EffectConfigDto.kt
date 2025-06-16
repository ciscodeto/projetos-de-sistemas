package com.ciscodeto.sinapsia.domain.actions.effects

data class EffectConfigDto(
    val effectType: EffectType,
    val calculationType: String,
    val baseAmount: Int = 0,
    val target: String,
    val additionalParams: Map<String, String> = emptyMap()
)