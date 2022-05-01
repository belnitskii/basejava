package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.AbstractStorageTest;
import serialize.ObjectStreamSerialize;

import static org.junit.Assert.*;
public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerialize()));

    }
}