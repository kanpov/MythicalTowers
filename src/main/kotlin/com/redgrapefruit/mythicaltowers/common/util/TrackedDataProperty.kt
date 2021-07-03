package com.redgrapefruit.mythicaltowers.common.util

import net.minecraft.entity.Entity
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandler
import net.minecraft.nbt.NbtCompound
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

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
    private val operable: NbtOperable<TSelf>,
    private val data: TrackedData<TSelf> = DataTracker.registerData(clazz, handler)

) : ReadOnlyProperty<TOwner, TSelf> where TOwner : Entity {

    /**
     * Registers the property with given default value.
     *
     * Should only be called in `initDataTracker` overrides
     */
    fun register(default: TSelf) {
        tracker.startTracking(data, default)
    }

    /**
     * Reads this property from NBT via the [operable]
     */
    fun readNbt(nbt: NbtCompound, key: String) {
        tracker.set(data, operable.read(nbt, key))
    }

    /**
     * Writes this property to NBT via the [operable]
     */
    fun writeNbt(nbt: NbtCompound, key: String) {
        operable.write(nbt, key, tracker.get(data))
    }

    override fun getValue(thisRef: TOwner, property: KProperty<*>): TSelf {
        return tracker.get(data)
    }
}

/**
 * A convenience method for creating [TrackedDataProperty]s that is inline to provide the Java [Class] automatically
 * through a reified type parameter.
 */
inline fun <reified TOwner, TSelf> trackedData(tracker: DataTracker, handler: TrackedDataHandler<TSelf>, operable: NbtOperable<TSelf>): TrackedDataProperty<TOwner, TSelf> where TOwner : Entity {
    return TrackedDataProperty(tracker, TOwner::class.java, handler, operable)
}
