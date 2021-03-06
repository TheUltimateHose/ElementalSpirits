package theultimatehose.elementalspirits.scroll.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import theultimatehose.elementalspirits.scroll.GuiScroll;
import theultimatehose.elementalspirits.scroll.structure.*;
import theultimatehose.elementalspirits.util.ModUtil;

public class PageButton extends GuiButton {

    public ResourceLocation resLoc = new ResourceLocation(ModUtil.MOD_ID_LOWER, "textures/gui/GuiAncientScroll.png");

    GuiScroll parent;
    BookMark bookMark;
    Direction direction;

    public enum Direction {
        back, left, right
    }

    public PageButton(int x, int y, Direction direction, GuiScroll parent, BookMark bookMark) {
        super(0, x, y, "");
        this.xPosition = x;
        this.yPosition = y;
        this.height = 10;
        this.width = 20;
        this.direction = direction;
        this.parent = parent;
        this.bookMark = bookMark;
    }

    @Override
    public void drawButtonForegroundLayer(int mouseX, int mouseY) {
        int textureX = 0, textureY = 0;

        if (this.direction == Direction.right)
            textureY = 205;
        else if (this.direction == Direction.left)
            textureY = 215;
        else if (this.direction == Direction.back)
            textureY = 225;

        if (mouseX > this.xPosition && mouseY > this.yPosition && mouseX < this.xPosition + getButtonWidth() && mouseY < this.yPosition + this.height) {
            textureX = 20;
        }
        Minecraft.getMinecraft().getTextureManager().bindTexture(this.resLoc);
        drawTexturedModalRect(this.xPosition, this.yPosition, textureX, textureY, this.width, this.height);
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        if (mouseX > this.xPosition && mouseY > this.yPosition && mouseX < this.xPosition + getButtonWidth() && mouseY < this.yPosition + this.height) {
            this.bookMark.jumpTo(this.parent);
            return true;
        } else {
            return false;
        }
    }

}
