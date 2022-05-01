package ru.javawebinar.basejava.storage;

import serialize.ObjectStreamSerialize;

public class FileStorageTest extends AbstractStorageTest{
    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerialize()));
    }
}