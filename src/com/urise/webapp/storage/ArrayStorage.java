package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size;


    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (r == null) {
            System.out.println("Введены некорректные данные");
        } else {
            int index = getIndex(r.toString());
            if (index == -1) {
                if (size < storage.length) {
                    storage[size++] = r;
                }
            } else {
                System.out.println("Вы пытаетесь сохранить резюме повторно");
            }
        }
    }


    public void update(Resume r) {
        if (r == null) {
            System.out.println("Введены некорректные данные");
        } else {
            int index = getIndex(r.toString());
            checkResumeOnMissing(index);
            if (index >= 0) {
                storage[index] = r;
            }
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        checkResumeOnMissing(index);
        if (index >= 0) {
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        if (uuid != null) {
            int indexOfDelete = getIndex(uuid);
            if (indexOfDelete >= 0) {
                storage[indexOfDelete] = null;
                for (int i = indexOfDelete; i < size; i++) {
                    Resume resume = storage[i];
                    storage[i] = storage[i + 1];
                    storage[i + 1] = resume;
                }
                size--;
            } else {
                checkResumeOnMissing(indexOfDelete);
            }
        }
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

    public int getIndex(String uuid) {
        if (uuid != null) {
            for (int i = 0; i < size; i++) {
                if (storage[i].toString().equals(uuid)) {
                    return i;
                }
            }
            return -1;
        }
        return -2;
    }

    public void checkResumeOnMissing(int i) {
        if (i == -1) {
            System.out.println("Данное резюме отсутствует в базе");
        }
        if (i == -2) {
            System.out.println("Введены некорректные данные");
        }
    }
}
