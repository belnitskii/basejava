package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import java.util.Arrays;

public class MapStorageTest {
    private static final MapStorage storage = new MapStorage();

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void contains() {
        Assert.assertTrue(storage.contains(RESUME_1));
        Assert.assertFalse(storage.contains(new Resume()));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume updated = new Resume(UUID_1);
//        updated.setName("newName");
        storage.update(updated);
        Assert.assertEquals(storage.get(UUID_1), updated);
        Assert.assertEquals(storage.get(updated.getUuid()), updated);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume());
    }

    @Test
    public void save() {
        Resume resume = new Resume();
        storage.save(resume);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(storage.get(resume.getUuid()), resume);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void get() {
        Assert.assertEquals(storage.get(UUID_1), RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("notExist");
    }

    @Test
    public void delete() {
        storage.delete(RESUME_1.getUuid());
        Assert.assertEquals(2, storage.size());
        try {
            storage.get(RESUME_1.getUuid());
        } catch (NotExistStorageException ignored) {
        }
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(new Resume().getUuid());
    }

    @Test
    public void getAll() {
        Resume[] resumes = new Resume[]{RESUME_1, RESUME_2, RESUME_3};
        Resume[] resumesFromMap = storage.getAll();
        Arrays.sort(resumesFromMap);
        Assert.assertArrayEquals(resumesFromMap, resumes);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}