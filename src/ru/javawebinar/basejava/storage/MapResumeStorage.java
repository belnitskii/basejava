package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapResumeStorage extends AbstractStorage{
    private final Map<String, Resume> map = new HashMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsValue((Resume) searchKey);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove(((Resume) searchKey).getUuid());
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return map.values().stream().sorted(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid)).collect(Collectors.toList());
    }

    @Override
    public int size() {
        return map.size();
    }
}
