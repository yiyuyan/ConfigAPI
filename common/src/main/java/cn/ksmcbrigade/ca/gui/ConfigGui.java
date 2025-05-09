package cn.ksmcbrigade.ca.gui;

import cn.ksmcbrigade.ca.config.Config;
import cn.ksmcbrigade.ca.gui.list.ConfigList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class ConfigGui extends OptionsSubScreen {
    public final Config config;
    public ConfigList list;

    public ConfigGui(Screen pLastScreen, Config config) {
        super(pLastScreen, Minecraft.getInstance().options, Component.literal("Config"));
        this.config = config;
    }

    @Override
    protected void addOptions() {

    }

    protected void addContents() {
        this.list = this.layout.addToContents(new ConfigList(this, this.minecraft,this.config));
    }

    protected void repositionElements() {
        this.layout.arrangeElements();
        this.list.updateSize(this.width, this.layout);
    }

    protected void addFooter() {
        LinearLayout linearlayout = this.layout.addToFooter(LinearLayout.horizontal().spacing(8));
        linearlayout.addChild(Button.builder(CommonComponents.GUI_DONE, (p_344249_) -> this.onClose()).build());
    }

    public void render(@NotNull GuiGraphics p_344555_, int p_344302_, int p_344298_, float p_344857_) {
        super.render(p_344555_, p_344302_, p_344298_, p_344857_);
    }
}
