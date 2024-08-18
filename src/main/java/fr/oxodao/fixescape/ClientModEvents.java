package fr.oxodao.fixescape;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = FixEscape.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents
{
    public static final Lazy<KeyMapping> NEW_ESCAPE = Lazy.of(() -> new KeyMapping(
        "key.fixescape.second_escape",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.categories.misc"
    ));

    @SubscribeEvent
    public static void registerKeybinds(RegisterKeyMappingsEvent evt)
    {
        evt.register(NEW_ESCAPE.get());
    }
}
