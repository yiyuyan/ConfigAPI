package cn.ksmcbrigade.ca.gui.list.entrys;

import cn.ksmcbrigade.ca.config.Config;
import cn.ksmcbrigade.ca.gui.entry.ArrStringGui;
import cn.ksmcbrigade.ca.gui.list.ArrayConfigList;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.util.List;

public class ArrStringEntry extends Entry{

    final ArrayConfigList instance;
    final Config config;
    final String key;
    final int keyKey;
    final Button box;

    public ArrStringEntry(ArrayConfigList instance, Config config, String key, int keyKey){
        this.instance = instance;
        this.config = config;
        this.key = key;
        this.keyKey = keyKey;
        this.box = Button.builder(Component.literal("Edit"),(button -> {
            if(!InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(),InputConstants.KEY_LCONTROL) && !InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(),InputConstants.KEY_RCONTROL))
                Minecraft.getInstance().setScreen(new ArrStringGui(this.instance.last,this.config,this.key,keyKey));
        })).size(50,20).build();
    }

    @Override
    void refreshEntry() {

    }

    @Override
    public List<? extends NarratableEntry> narratables() {
        return List.of(this.box);
    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pIndex, int pTop, int pLeft, int pWidth, int pHeight, int pMouseX, int pMouseY, boolean pHovering, float pPartialTick) {
        int i = this.instance.getScrollbarPosition() - this.box.getWidth() - 10;
        int j = pTop - 2;
        this.box.setPosition(i, j);
        this.box.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        pGuiGraphics.drawString(Minecraft.getInstance().font, this.key+"#"+(this.keyKey+1), pLeft, pTop + pHeight / 2 - 4, -1);
        if(this.isFocused()){
            pGuiGraphics.fill(pLeft-1, pTop + pHeight / 2,pLeft, pTop + pHeight / 2+1, Color.RED.getRGB());
        }
    }

    public String getContext(){
        return this.config.getArray(this.key).get(this.keyKey);
    }

    @Override
    public List<? extends GuiEventListener> children() {
        return List.of(this.box);
    }
}
