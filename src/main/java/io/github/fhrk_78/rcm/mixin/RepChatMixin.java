package io.github.fhrk_78.rcm.mixin;

import io.github.fhrk_78.rcm.RepChatMessage;
import io.github.fhrk_78.rcm.client.RepChatMessageClient;
import io.github.fhrk_78.rcm.gui.EditReplacerScreen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.client.MinecraftClient.getInstance;

@Mixin(value = ChatScreen.class)
public class RepChatMixin {
    @Shadow
    protected TextFieldWidget chatField;

    @Unique
    public ButtonWidget openrcme;

    @Inject(method = "init()V", at = @At("RETURN"))
    protected void injectInit(CallbackInfo info) {
        this.openrcme = ButtonWidget.builder(Text.translatable("gui.repchatmessage.config"),
                button -> {
                    getInstance().setScreen(new EditReplacerScreen(getInstance().currentScreen));
                    RepChatMessage.LOGGER.info("RepChatMessage: Clicked Config", getInstance().currentScreen);
                })
                .dimensions(10, 10, 200, 20)
                .build();
    }

    @Inject(method = "render(Lnet/minecraft/client/gui/DrawContext;IIF)V", at = @At("HEAD"))
    protected void injectRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo info) {
        this.openrcme.render(context, mouseX, mouseY, delta);
    }

    /**
     * @author PizzaHarumaki
     * @reason Replace Insert Text
     */
    @Overwrite
    protected void insertText(String text, boolean override) {
        if (override) {
            this.chatField.setText(RepChatMessageClient.accessRepeatReplace(text));
        } else {
            this.chatField.write(RepChatMessageClient.accessRepeatReplace(text));
        }
    }
}
