package me.hijinks001.stormtotemmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.gui.components.Button;

public class StormBookScreen extends Screen {
    private int currentPage = 0;
    private static final int TOTAL_PAGES = 5;
    private static final ResourceLocation BOOK_BG = 
            new ResourceLocation("stormtotemmod", "textures/gui/storm_book_bg.png");

    private static final Component[] PAGES = {
        Component.translatable("book.stormtotemmod.page1"),
        Component.translatable("book.stormtotemmod.page2"),
        Component.translatable("book.stormtotemmod.page3"),
        Component.translatable("book.stormtotemmod.page4"),
        Component.translatable("book.stormtotemmod.page5")
    };

    public StormBookScreen() {
        super(Component.translatable("book.stormtotemmod.title"));
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int bottomY = this.height - 35;

        // Стрелка влево ◄
        this.addRenderableWidget(Button.builder(
            Component.literal("◄"),
            btn -> { if (currentPage > 0) currentPage--; }
        ).pos(centerX - 50, bottomY).size(20, 20).build());

        // Стрелка вправо ►
        this.addRenderableWidget(Button.builder(
            Component.literal("►"),
            btn -> { if (currentPage < TOTAL_PAGES - 1) currentPage++; }
        ).pos(centerX + 30, bottomY).size(20, 20).build());

        // Кнопка закрыть ✕
        this.addRenderableWidget(Button.builder(
            Component.literal("✕"),
            btn -> this.onClose()
        ).pos(this.width - 30, 10).size(20, 20).build());
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        // Рендерим фон (текстура пергамента)
        RenderSystem.setShaderTexture(0, BOOK_BG);
        graphics.blit(BOOK_BG, 0, 0, 0, 0, this.width, this.height, this.width, this.height);

        super.render(graphics, mouseX, mouseY, partialTick);

        // Заголовок
        graphics.drawCenteredString(this.font,
            Component.translatable("book.stormtotemmod.title"),
            this.width / 2, 25, 0x3a2a0a);

        // Линия под заголовком
        graphics.fill(this.width / 2 - 60, 40, this.width / 2 + 60, 41, 0x553a1a);

        // Текст страницы (тёмно-коричневый, как чернила)
        graphics.drawWordWrap(this.font,
            PAGES[currentPage],
            50, 55, this.width - 100, 0x3a2a0a);

        // Номер страницы
        graphics.drawCenteredString(this.font,
            Component.literal((currentPage + 1) + " / " + TOTAL_PAGES),
            this.width / 2, this.height - 25, 0x885522);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}