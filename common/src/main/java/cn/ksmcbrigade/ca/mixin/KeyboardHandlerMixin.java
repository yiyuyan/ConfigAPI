package cn.ksmcbrigade.ca.mixin;

import cn.ksmcbrigade.ca.ClientConstants;
import cn.ksmcbrigade.ca.gui.ConfigsGui;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public class KeyboardHandlerMixin {
    @Inject(method = "keyPress",at = @At("HEAD"))
    public void key(long pWindowPointer, int pKey, int pScanCode, int pAction, int pModifiers, CallbackInfo ci){
        if(ClientConstants.key.isDown()){
            Minecraft.getInstance().setScreen(new ConfigsGui(Minecraft.getInstance().screen));
        }
    }
}
