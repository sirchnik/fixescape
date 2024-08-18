package fr.oxodao.fixescape;

import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(FixEscape.MOD_ID)
public class FixEscape
{
    public static final String MOD_ID = "fixescape";

    public FixEscape(IEventBus modEventBus, ModContainer modContainer)
    {
        NeoForge.EVENT_BUS.register(new ClientEventHandler());
    }
}
