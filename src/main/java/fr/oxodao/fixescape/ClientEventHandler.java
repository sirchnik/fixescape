package fr.oxodao.fixescape;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientEventHandler {
    private static boolean IS_KEY_PRESSED = false;

    @SubscribeEvent
    public void onScreenKeyPressed(ScreenEvent.KeyPressed.Post evt){
        if (ClientEventHandler.IS_KEY_PRESSED) {
            return;
        }

        if (ClientModEvents.NEW_ESCAPE.get().getKey().getValue() != evt.getKeyCode()) {
            return;
        }

        ClientEventHandler.IS_KEY_PRESSED = true;

        Minecraft mc = Minecraft.getInstance();
        if (mc.screen != null && mc.screen.shouldCloseOnEsc()) {
            mc.screen.onClose();
        }
    }

    @SubscribeEvent
    public void onScreenKeyRelease(ScreenEvent.KeyReleased.Post evt){
        ClientEventHandler.IS_KEY_PRESSED = false;
    }

    @SubscribeEvent
    public void onKeyPress(InputEvent.Key evt) {
        var isCorrectKey = ClientModEvents.NEW_ESCAPE.get().getKey().getValue() == evt.getKey();

        if (evt.getAction() == InputConstants.RELEASE && ClientEventHandler.IS_KEY_PRESSED && isCorrectKey) {
            ClientEventHandler.IS_KEY_PRESSED = false;
            return;
        } else if (evt.getAction() != InputConstants.PRESS || !isCorrectKey) {
            return;
        }

        if (!ClientEventHandler.IS_KEY_PRESSED) {
            ClientEventHandler.IS_KEY_PRESSED = true;

            Minecraft mc = Minecraft.getInstance();
            if (mc.screen == null) {
                mc.pauseGame(false);
            }
        }

    }
}
