package cn.ksmcbrigade.ca.gui.list.entrys;

import net.minecraft.client.gui.components.ContainerObjectSelectionList;

public abstract class Entry extends ContainerObjectSelectionList.Entry<Entry> {
    public Entry() {}

    abstract void refreshEntry();
}
