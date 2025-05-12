package com.ciscodeto.domain.character

import kotlin.reflect.KClass

class AttributesBundle(private val list: List<Attribute>) {
    private val map: Map<KClass<out Attribute>, Attribute> =
        list.associateBy { it::class }

    init {
        require(map.size == list.size) { "" }
    }

    fun <T: Attribute> get(cls: KClass<T>): T? =
        map[cls] as? T

    fun all(): Collection<Attribute> = map.values
}