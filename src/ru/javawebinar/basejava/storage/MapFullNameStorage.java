package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapFullNameStorage extends AbstractStorage{
    private final Map<String, Resume> map = new HashMap<>();

    @Override
    public void update(Resume r) {
        Object searchKey = getExistedSearchKey(r.getFullName());
        doUpdate(r, searchKey);
    }

    @Override
    public void save(Resume r) {
        Object searchKey = getNotExistedSearchKey(r.getFullName());
        doSave(r, searchKey);
    }

    @Override
    protected String getSearchKey(String fullName) {
        return fullName;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        map.put((String) searchKey, r);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsKey((String) searchKey);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put((String) searchKey, r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get((String) searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove((String) searchKey);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return map.values().stream().sorted(Resume.resumeComparator).collect(Collectors.toList());
    }

    @Override
    public int size() {
        return map.size();
    }
}
