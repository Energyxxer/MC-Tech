package com.energyxxer.commodore.functionlogic.nbt.path;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class NBTPath implements Iterable<NBTPath> {

    private final NBTPathNode node;
    private final NBTPath next;

    public NBTPath(String key) {
        this(key, null);
    }

    public NBTPath(String key, NBTPath next) {
        this(new NBTPathKey(key), next);
    }

    public NBTPath(int index) {
        this(index, null);
    }

    public NBTPath(int index, NBTPath next) {
        this(new NBTPathIndex(index), next);
    }

    protected NBTPath(NBTPathNode node, NBTPath next) {
        this.node = node;
        this.next = next;
    }

    public NBTPath(NBTPathNode... nodes) {
        if(nodes.length >= 1) {
            this.node = nodes[0];
            if(nodes.length > 1) {
                this.next = new NBTPath(Arrays.copyOfRange(nodes, 1, nodes.length));
            } else this.next = null;
        } else throw new IllegalArgumentException("Received empty array of path nodes");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(node.getPathString());
        if(next != null) {
            sb.append(next.node.getPathSeparator());
            sb.append(next.toString());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NBTPath nbtPath = (NBTPath) o;
        return Objects.equals(node, nbtPath.node) &&
                Objects.equals(next, nbtPath.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(node, next);
    }

    public NBTPathNode getNode() {
        return node;
    }

    public NBTPath getNext() {
        return next;
    }

    public boolean hasNext() {
        return next != null;
    }

    @NotNull
    @Override
    public Iterator<NBTPath> iterator() {
        return new NBTPathTraverser(this);
    }
}
