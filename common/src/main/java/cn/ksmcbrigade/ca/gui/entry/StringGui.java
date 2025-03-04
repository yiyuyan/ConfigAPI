package cn.ksmcbrigade.ca.gui.entry;

import cn.ksmcbrigade.ca.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class StringGui extends Screen {
    private static final Component ENTER_IP_LABEL = Component.literal("str");
    private final Config config;
    private final String key;
    private EditBox ipEdit;
    private final Screen lastScreen;

    public StringGui(Screen pLastScreen,Config config,String key) {
        super(Component.literal("Edit String Config"));
        this.lastScreen = pLastScreen;
        this.config = config;
        this.key = key;
    }

    protected void init() {
        this.ipEdit = new EditBox(this.font, this.width / 2 - 100, 116, 200, 20, Component.literal("str"));
        this.ipEdit.setMaxLength(320);
        try {
            this.ipEdit.setValue(config.getString(key));
        } catch (Exception e) {

        }
        this.ipEdit.setResponder(this::updateSelectButtonStatus);
        this.addWidget(this.ipEdit);
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_CANCEL, (p_95977_) -> {
            this.onClose();
        }).bounds(this.width / 2 - 100, this.height / 4 + 120 + 12, 200, 20).build());
    }

    protected void setInitialFocus() {
        this.setInitialFocus(this.ipEdit);
    }

    public void resize(@NotNull Minecraft pMinecraft, int pWidth, int pHeight) {
        String s = this.ipEdit.getValue();
        this.init(pMinecraft, pWidth, pHeight);
        this.ipEdit.setValue(s);
    }

    public void onClose() {
        this.minecraft.setScreen(this.lastScreen);
    }

    private void updateSelectButtonStatus(String c) {
        this.config.put(key,c);
    }

    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        pGuiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 16777215);
        pGuiGraphics.drawString(this.font, ENTER_IP_LABEL, this.width / 2 - 100 + 1, 100, 10526880);
        this.ipEdit.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }
}

