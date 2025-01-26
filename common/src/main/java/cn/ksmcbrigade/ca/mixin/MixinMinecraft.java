package cn.ksmcbrigade.ca.mixin;

import cn.ksmcbrigade.ca.config.Configs;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Inject(at = @At("HEAD"), method = "close")
    private void init(CallbackInfo info) {
        Configs.stoppedWatchDog = true;
    }
}
