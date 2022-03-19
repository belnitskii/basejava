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
        if (r != null) {
            if (Arrays.stream(storage).limit(size).filter(resume -> resume.toString().equals(r.getUuid())).findFirst().orElse(null) != null) {
                System.out.println("ERROR: Резюме " + r.getUuid() + " уже сохранено");
            } else if (size >= storage.length) {
                System.out.println("ERROR: Хранилище переполнено");
            } else {
                storage[size++] = r;
            }
        }
}

    public void update(Resume r) {
        if (r != null) {
            if (get(r.getUuid()) != null) {
                for (int i = 0; i < size; i++) {
                    if (r.getUuid().equals(storage[i].getUuid())) {
                        storage[i] = r;
                    }
                }
            }
        }
    }

    public Resume get(String uuid) {
        Resume r = (Arrays.stream(storage).limit(size).filter(resume -> resume.toString().equals(uuid)).findFirst().orElse(null));
        if (r == null) {
            System.out.println("ERROR: Резюме " + uuid + " нет в хранилище");
        }
        return r;
    }

    public void delete(String uuid) {
        if (get(uuid) != null) {
            for (int i = 0; i < size; i++) {
                if (storage[i].toString().equals(uuid)) {
                    storage[i] = null;
                    size--;
                    if ((i != size) && (size - i >= 0)) {
                        System.arraycopy(storage, i + 1, storage, i, size - i);
                    }
                    break;
                }
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
}
