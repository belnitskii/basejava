package ru.javawebinar.basejava.storage;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    private static final ArrayStorage storage = new ArrayStorage();

    public ArrayStorageTest() {
        super(storage);
    }

    @Override
    protected int getStorageLimit() {
        return ArrayStorage.STORAGE_LIMIT;
    }
}