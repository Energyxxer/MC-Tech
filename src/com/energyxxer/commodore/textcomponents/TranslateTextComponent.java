package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.CommandUtils;
import org.jetbrains.annotations.NotNull;

public class TranslateTextComponent extends TextComponent {
    @NotNull
    private final String translateKey;

    public TranslateTextComponent(@NotNull String translateKey) {
        this.translateKey = translateKey;
    }

    @Override
    public boolean supportsProperties() {
        return true;
    }

    @Override
    public String toString(TextComponent parent) {
        String escapedText = "\"" + CommandUtils.escape(translateKey) + "\"";
        String baseProperties = this.getBaseProperties(parent);
        return "{\"translate\":" +
                        escapedText +
                        (baseProperties != null ? "," + baseProperties : "") +
                        '}';
    }
}
