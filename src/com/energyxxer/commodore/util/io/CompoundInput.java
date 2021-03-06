package com.energyxxer.commodore.util.io;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.CodeSource;

public interface CompoundInput extends AutoCloseable {

    @Nullable
    InputStream get(@NotNull String path) throws IOException;
    default long getEntryLength(@NotNull String path) {
        return 0L;
    }
    boolean isDirectory(@NotNull String path);
    Iterable<String> listSubEntries(@NotNull String path);
    void open() throws IOException;
    void close() throws IOException;
    File getRootFile();

    class Static {
        @NotNull
        public static CompoundInput chooseInputForClasspath(@NotNull String rootPath, @NotNull Class cls) {
            CodeSource src = cls.getProtectionDomain().getCodeSource();
            if(src != null && src.getLocation().getFile().endsWith(".jar")) {
                try {
                    return new ZipCompoundInput(new File(URLDecoder.decode(src.getLocation().getFile().replace("+","%2b"), "UTF-8")), rootPath);
                } catch (UnsupportedEncodingException e) {
                    //this is never gonna happen
                    e.printStackTrace();
                }
            }
            return new ResourceCompoundInput(rootPath, cls);
        }
    }
}
