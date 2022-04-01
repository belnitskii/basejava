package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void updateElement(Resume r) {
        storage[getIndex(r.getUuid())] = r;
    }

    @Override
    protected void saveElement(Resume r) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElementInArray(r, getIndex(r.getUuid()));
            size++;
        }
    }

    @Override
    protected Resume getElement(String uuid) {
        return storage[getIndex(uuid)];
    }

    @Override
    protected void fillDeletedElement(String uuid) {
        fillDeletedElementInArray(getIndex(uuid));
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    @Override
    protected boolean contains(Resume r) {
        return getIndex(r.getUuid()) >= 0;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertElementInArray(Resume r, int index);

    protected abstract void fillDeletedElementInArray(int index);

}
