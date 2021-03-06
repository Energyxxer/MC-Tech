package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.nbt.DataHolder;
import com.energyxxer.commodore.functionlogic.nbt.DataHolderBlock;
import com.energyxxer.commodore.functionlogic.nbt.DataHolderEntity;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NBTTextComponent extends TextComponent {
    @NotNull
    private final NBTPath path;
    @NotNull
    private final DataHolder toPrint;
    private final boolean interpret;
    @Nullable
    private final TextComponent separator;

    public NBTTextComponent(NBTPath path, CoordinateSet block) {
        this(path, block, false);
    }

    public NBTTextComponent(NBTPath path, CoordinateSet block, boolean interpret) {
        this(path, new DataHolderBlock(block), interpret);
    }

    public NBTTextComponent(NBTPath path, Entity entity) {
        this(path, entity, false);
    }

    public NBTTextComponent(NBTPath path, Entity entity, boolean interpret) {
        this(path, new DataHolderEntity(entity), interpret);
    }

    @Deprecated
    private NBTTextComponent(@NotNull NBTPath path, @NotNull Object toPrint, boolean interpret) {
        this.path = path;
        this.toPrint = toPrint instanceof Entity ? new DataHolderEntity(((Entity) toPrint)) : new DataHolderBlock(((CoordinateSet) toPrint));
        this.interpret = interpret;
        this.separator = null;
    }

    private NBTTextComponent(@NotNull NBTPath path, @NotNull DataHolder toPrint, boolean interpret) {
        this(path, toPrint, interpret, null);
    }

    private NBTTextComponent(@NotNull NBTPath path, @NotNull DataHolder toPrint, boolean interpret, @Nullable TextComponent separator) {
        this.path = path;
        this.toPrint = toPrint;
        this.interpret = interpret;
        this.separator = separator;
    }

    @Override
    public boolean supportsProperties() {
        return true;
    }

    @Override
    public String toString(TextStyle parentStyle) {
        String baseProperties = this.getBaseProperties(parentStyle);

        String extra = toPrint.getTextComponentKey() + ":" + CommandUtils.quote(toPrint.getTextComponentValue());
        if(interpret) extra += ",\"interpret\":true";
        return "{\"nbt\":\"" + CommandUtils.escape(path.toString()) + "\"," +
                extra +
                (separator != null ? ",\"separator\":" + separator.toString(style) : "") +
                (baseProperties != null ? "," + baseProperties : "") +
                '}';
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("textcomponent.nbt");
        super.assertAvailable();
        VersionFeatureManager.assertEnabled("nbt.access");
        toPrint.assertAvailable();
        if(separator != null) VersionFeatureManager.assertEnabled("textcomponent.separators");
    }
}
