package org.bukkit.metadata;

import org.apache.commons.lang.Validate;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.NumberConversions;

import java.lang.ref.WeakReference;

/**
 * Optional base class for facilitating MetadataValue implementations.
 * <p>
 * This provides all the conversion functions for MetadataValue so that
 * writing an implementation of MetadataValue is as simple as implementing
 * value() and invalidate().
 */
public abstract class MetadataValueAdapter implements MetadataValue {
    protected final WeakReference<Plugin> owningPlugin;

    protected MetadataValueAdapter(Plugin owningPlugin) {
        Validate.notNull(owningPlugin, "owningPlugin cannot be null");
        this.owningPlugin = new WeakReference<Plugin>(owningPlugin);
    }

    @Override
    public Plugin getOwningPlugin() {
        return owningPlugin.get();
    }

    @Override
    public int asInt() {
        return NumberConversions.toInt(value());
    }

    @Override
    public float asFloat() {
        return NumberConversions.toFloat(value());
    }

    @Override
    public double asDouble() {
        return NumberConversions.toDouble(value());
    }

    @Override
    public long asLong() {
        return NumberConversions.toLong(value());
    }

    @Override
    public short asShort() {
        return NumberConversions.toShort(value());
    }

    @Override
    public byte asByte() {
        return NumberConversions.toByte(value());
    }

    @Override
    public boolean asBoolean() {
        Object value = value();
        if (value instanceof Boolean) {
            return (Boolean) value;
        }

        if (value instanceof Number) {
            return ((Number) value).intValue() != 0;
        }

        if (value instanceof String) {
            return Boolean.parseBoolean((String) value);
        }

        return value != null;
    }

    @Override
    public String asString() {
        Object value = value();

        if (value == null) {
            return "";
        }
        return value.toString();
    }

}
