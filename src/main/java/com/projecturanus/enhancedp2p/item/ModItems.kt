package com.projecturanus.enhancedp2p.item

import com.projecturanus.enhancedp2p.MODID
import net.minecraft.item.Item
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Mod.EventBusSubscriber(modid = MODID)
object ModItems {
    @JvmStatic
    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        event.registry.register(ItemAdvancedMemoryCard.setRegistryName(MODID, "advanced_memory_card"))
    }
}
