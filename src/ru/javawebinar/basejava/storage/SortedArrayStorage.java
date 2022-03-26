package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Resume " + r.getUuid() + " not exist");
        } else {
            for (int i = index; i < size; i++) {
                Resume resume = storage[i];
                storage[i] = storage[i + 1];
                storage[i + 1] = resume;
            }
            storage[size--] = null;
            save(r);
        }
    }

    @Override
    public void save(Resume r) {
        if (getIndex(r.getUuid()) >= 0) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            size++;
            int index = Math.abs(Arrays.binarySearch(storage, 0, size - 1, r) + 1);
            System.arraycopy(storage, index, storage, index + 1, (size - 1) - index);
            storage[index] = r;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            for (int i = index; i < size; i++) {
                Resume resume = storage[i];
                storage[i] = storage[i + 1];
                storage[i + 1] = resume;
            }
            storage[size--] = null;
        }
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public Resume get(String uuid) {
        return super.get(uuid);
    }

    @Override
    public Resume[] getAll() {
        return super.getAll();
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}

