package cn.ksmcbrigade.ca.mixin;

import cn.ksmcbrigade.ca.config.Configs;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {

    @Inject(at = @At("HEAD"), method = "stopServer")
    private void init(CallbackInfo info) {
        Configs.stoppedWatchDog = true;
    }
}
