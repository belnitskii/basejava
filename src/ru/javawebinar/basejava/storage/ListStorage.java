package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;
import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    ArrayList<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void updateElement(Resume r) {
        storage.set(storage.indexOf(r), r);
    }

    @Override
    protected void saveElement(Resume r) {
        storage.add(r);
    }

    @Override
    protected Resume getElement(String uuid) {
        return storage.get(storage.indexOf(new Resume(uuid)));
    }

    @Override
    protected void fillDeletedElement(String uuid) {
        storage.remove(new Resume(uuid));
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean contains(Resume r) {
        return storage.contains(r);
    }
}
