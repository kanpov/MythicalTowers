package com.redgrapefruit.mythicaltowers.common.util

import net.minecraft.entity.Entity
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandler
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0

/**
 * An ease-of-use delegate for entity tracked data to not call `dataTracker.get(myTracked)` every time.
 *
 * Recommended to create via [trackedData] function.
 *
 * How to declare:
 *
 * ```kotlin
 * val myTracked: Int by trackedData(dataTracker, TrackedDataHandlerRegistry.INTEGER)
 * ```
 *
 * How to register (inside of the `initDataTracker` override in your `Entity` class):
 *
 * ```kotlin
 * myTracked.register(default = 0)
 * ```
 *
 * How to read from NBT (inside of the `readCustomDataFromNbt` override):
 *
 * ```kotlin
 * myTracked.readNbt(nbt, "My Tracked")
 * ```
 *
 * How to write to NBT (inside of the `writeCustomDataToNbt` override):
 *
 * ```kotlin
 * myTracked.writeNbt(nbt, "My Tracked")
 * ```
 */
class TrackedDataProperty<TOwner, TSelf>(
    private val tracker: DataTracker,
    clazz: Class<TOwner>,
    handler: TrackedDataHandler<TSelf>,
    internal val data: TrackedData<TSelf> = DataTracker.registerData(clazz, handler)
) : ReadWriteProperty<TOwner, TSelf> where TOwner : Entity {

    override fun getValue(thisRef: TOwner, property: KProperty<*>): TSelf {
        return tracker.get(data)
    }

    override fun setValue(thisRef: TOwner, property: KProperty<*>, value: TSelf) {
        tracker.set(data, value)
    }
}

/**
 * Returns the entry of the tracked data
 */
fun <TSelf> trackedDataEntry(property: KProperty0<*>): TrackedData<TSelf> {
    // kind of a hacky way to do it
    @Suppress("UNCHECKED_CAST")
    return (property.getDelegate() as TrackedDataProperty<*, TSelf>).data
}

/**
 * A convenience method for creating [TrackedDataProperty]s that is inline to provide the Java [Class] automatically
 * through a reified type parameter.
 */
inline fun <reified TOwner, TSelf> trackedData(tracker: DataTracker, handler: TrackedDataHandler<TSelf>): TrackedDataProperty<TOwner, TSelf> where TOwner : Entity {
    return TrackedDataProperty(tracker, TOwner::class.java, handler)
}
