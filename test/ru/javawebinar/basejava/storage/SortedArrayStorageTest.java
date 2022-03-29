package ru.javawebinar.basejava.storage;

public class SortedArrayStorageTest extends AbstractArrayStorageTest{

    private static final SortedArrayStorage storage = new SortedArrayStorage();

    public SortedArrayStorageTest() {
        super(storage);
    }

    @Override
    protected int getStorageLimit() {
        return SortedArrayStorage.STORAGE_LIMIT;
    }
}