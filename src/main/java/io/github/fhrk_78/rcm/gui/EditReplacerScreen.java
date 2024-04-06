package io.github.fhrk_78.rcm.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import static net.minecraft.client.MinecraftClient.getInstance;

public class EditReplacerScreen extends Screen {
    private final Screen parent;
    public EditReplacerScreen(Screen parent) {
        super(Text.literal("Edit Replacer Screen"));
        this.parent = parent;
    }

    public ButtonWidget close_screen;

    @Override
    protected void init() {
        this.close_screen = ButtonWidget.builder(Text.translatable("text.maruma_sign.close"),
                        button -> getInstance().setScreen(parent))
                .dimensions(width / 2 - 105, height - 20, 200, 20)
                .build();

        addDrawableChild(close_screen);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        //
    }
}
