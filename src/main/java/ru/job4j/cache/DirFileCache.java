package ru.job4j.cache;

import java.io.FileInputStream;
import java.lang.ref.SoftReference;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        SoftReference<String> soft = cache.get(key);
        String rsl;
        if (soft == null) {
            StringBuilder text = new StringBuilder();
            try (FileInputStream in = new FileInputStream(cachingDir.concat("\\").concat(key))) {
                int read;
                while ((read = in.read()) != -1) {
                    text.append((char) read);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            rsl = text.toString();
            put(key, rsl);
        } else {
            rsl = soft.get();
        }
        return rsl;
    }
}