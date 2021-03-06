package com.energyxxer.commodore.functionlogic.rotation;

import com.energyxxer.commodore.CommandUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.energyxxer.commodore.util.MiscValidator.assertFinite;

public class RotationUnit {

    public enum Type {
        ABSOLUTE(""), RELATIVE("~");

        public final String prefix;

        Type(String prefix) {
            this.prefix = prefix;
        }
    }
    @NotNull
    private final Type type;
    private final double value;

    public RotationUnit(double value) {
        this(Type.ABSOLUTE, value);
    }

    public RotationUnit(@NotNull Type type, double value) {
        this.type = type;
        this.value = value;
        assertFinite(value, "magnitude");
    }

    public boolean isIdempotent() {
        return isAbsolute();
    }

    public boolean isSignificant() {
        return type == Type.ABSOLUTE || value != 0;
    }

    public boolean isAbsolute() {
        return type == Type.ABSOLUTE;
    }

    @Override
    public String toString() {
        return type.prefix + ((value == 0 && type == Type.RELATIVE) ? "" : CommandUtils.numberToPlainString(value));
    }

    @NotNull
    public Type getType() {
        return type;
    }

    public double getMagnitude() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RotationUnit that = (RotationUnit) o;
        return Double.compare(that.value, value) == 0 &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }
}
