package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume r) {
        if (!contains(r)) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            updateElement(r);
        }
    }

    public void save(Resume r) {
        if (contains(r)) {
            throw new ExistStorageException(r.getUuid());
        } else {
            saveElement(r);
        }
    }

    public Resume get(String uuid) {
        if (!contains(new Resume(uuid))) {
            throw new NotExistStorageException(uuid);
        } else {
            return getElement(uuid);
        }
    }

    public void delete(String uuid) {
        if (!contains(new Resume(uuid))) {
            throw new NotExistStorageException(uuid);
        } else {
            fillDeletedElement(uuid);
        }
    }

    protected abstract boolean contains(Resume r);

    protected abstract void updateElement(Resume r);

    protected abstract void saveElement(Resume r);

    protected abstract Resume getElement(String uuid);

    protected abstract void fillDeletedElement(String uuid);
}
