package theultimatehose.elementalspirits.scroll.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import theultimatehose.elementalspirits.scroll.GuiScroll;
import theultimatehose.elementalspirits.scroll.structure.BookMark;
import theultimatehose.elementalspirits.util.ModUtil;

public class BookMarkButton extends GuiButton {

    private ResourceLocation resLoc = new ResourceLocation(ModUtil.MOD_ID_LOWER, "textures/gui/GuiAncientScroll.png");

    public BookMark bookMark;
    public GuiScroll parent;
    public int bookMarkID;

    public BookMarkButton(int buttonId, int x, int y, GuiScroll parent, BookMark bookMark, int bookMarkID) {
        super(buttonId, x, y, "");
        this.xPosition = x;
        this.yPosition = y;
        this.width = 127;
        this.height = 25;
        this.parent = parent;
        this.bookMark = bookMark;
        this.bookMarkID = bookMarkID;
    }

    @Override
    public void drawButtonForegroundLayer(int mouseX, int mouseY) {
        int textureX = 62, textureY = 205;
        int renderX = this.xPosition;

        if (this.bookMark == null)
            renderX -= 100;

        if (mouseX > this.parent.guiLeft + this.parent.guiWidth) {
            if (mouseX > renderX && mouseY > this.yPosition && mouseX < renderX + getButtonWidth() && mouseY < this.yPosition + this.height) {
                renderX += 10;
            }
        }

        Minecraft.getMinecraft().getTextureManager().bindTexture(resLoc);
        drawTexturedModalRect(renderX, this.yPosition, textureX, textureY, this.width, this.height);

        FontRenderer fr = this.parent.getFontRendererObj();
        boolean unicode = fr.getUnicodeFlag();
        fr.setUnicodeFlag(true);
        if (this.bookMark != null) {
            if (this.bookMark.targetEntry == null)
                fr.drawString(this.parent.parseIdentifier(this.bookMark.targetChapter.identifier + ".name"), renderX + 17, this.yPosition + 7, 0, false);
            else {
                fr.drawString(this.parent.parseIdentifier(this.bookMark.targetChapter.identifier + "." + this.bookMark.targetEntry.subIdentifier + ".name") + TextFormatting.BLACK + "/" + this.bookMark.targetPage.number + TextFormatting.WHITE, renderX + 17, this.yPosition + 7, 0, false);
            }
        }
        fr.setUnicodeFlag(unicode);
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        if (mouseX > xPosition && mouseY > yPosition && mouseX < xPosition + getButtonWidth() && mouseY < yPosition + this.height) {
            if (this.parent.isPlayerShifting)
                this.bookMark = null;
            else {
                if (this.bookMark != null)
                    this.bookMark.jumpTo(this.parent);
                else {
                    if (this.parent.currentChapter != null)
                        this.bookMark = new BookMark(this.bookMarkID, this.parent.currentChapter, this.parent.currentEntry, this.parent.currentPage);
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
