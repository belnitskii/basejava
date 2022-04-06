package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.*;

public class MapFullNameStorageTest extends AbstractStorageTest{

    public MapFullNameStorageTest() {
        super(new MapFullNameStorage());
    }

    @Override
    protected void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getFullName()));
    }
}