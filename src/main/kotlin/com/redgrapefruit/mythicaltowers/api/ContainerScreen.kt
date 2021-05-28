package com.redgrapefruit.mythicaltowers.api

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import net.minecraft.util.Identifier

/**
 * A container [Screen] displaying the GUI.
 *
 * Can be overridden using built-in events to add custom GUI elements using vanilla [Screen] and [HandledScreen] features
 */
abstract class ContainerScreen protected constructor(
    handler: ScreenHandler,
    inventory: PlayerInventory,
    title: Text
) : HandledScreen<ScreenHandler>(handler, inventory, title) {

    // region Abstract properties & events

    private val texture = getTexture()

    /**
     * Returns the [Screen] GUI texture
     *
     * @return GUI texture resource ID
     */
    protected abstract fun getTexture(): Identifier

    /**
     * An event reserved for custom rendering and custom GUI elements
     */
    protected abstract fun onRender(matrices: MatrixStack)

    // endregion

    // region Implementation

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        // Reset color
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f)
        // Bind texture
        client!!.textureManager.bindTexture(texture)
        // Calculate center position
        val x = (width - backgroundWidth) / 2
        val y = (height - backgroundHeight) / 2
        // Render the texture
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight)
    }

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        // Render background and mouseover tooltip
        renderBackground(matrices)
        super.render(matrices, mouseX, mouseY, delta)
        drawMouseoverTooltip(matrices, mouseX, mouseY)
        // Fire event
        onRender(matrices)
    }

    override fun init() {
        super.init()
        // Center title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2
    }

    // endregion
}