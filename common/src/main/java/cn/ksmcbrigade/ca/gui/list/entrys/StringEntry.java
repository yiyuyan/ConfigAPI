package cn.ksmcbrigade.ca.gui.list.entrys;

import cn.ksmcbrigade.ca.config.Config;
import cn.ksmcbrigade.ca.gui.entry.StringGui;
import cn.ksmcbrigade.ca.gui.list.ConfigList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StringEntry extends Entry{

    final ConfigList instance;
    final Config config;
    final String key;
    final Button box;

    public StringEntry(ConfigList instance, Config config, String key){
        this.instance = instance;
        this.config = config;
        this.key = key;
        this.box = Button.builder(Component.literal("Edit"),(button -> {
            Minecraft.getInstance().setScreen(new StringGui(this.instance.configGui,this.config,this.key));
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
        pGuiGraphics.drawString(Minecraft.getInstance().font, this.key, pLeft, pTop + pHeight / 2 - 4, -1);
    }

    @Override
    public List<? extends GuiEventListener> children() {
        return List.of(this.box);
    }
}
