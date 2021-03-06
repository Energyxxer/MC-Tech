package com.energyxxer.commodore.types.defaults;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;

public class EffectType extends Type {
    public static final String CATEGORY = "effect";

    protected EffectType(Namespace namespace, String id) {
        super(CATEGORY, namespace, id);
    }

    @Override
    public boolean isStandalone() {
        return true;
    }
}
