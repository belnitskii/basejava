package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage{

    Map<String, Resume> storage = new HashMap<>();

    @Override
    protected boolean contains(Resume r) {
        return storage.containsKey(r.getUuid());
    }

    @Override
    protected void updateElement(Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void saveElement(Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume getElement(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void fillDeletedElement(String uuid) {
        storage.remove(uuid);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
