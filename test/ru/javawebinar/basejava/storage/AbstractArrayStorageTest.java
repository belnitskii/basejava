package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
        storage.clear();
        Assert.assertEquals(0, storage.size());

    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
        storage.clear();
        Assert.assertEquals(0, storage.size());

    }

    @Test
    public void update() throws Exception {
        storage.clear();
        Resume resume = new Resume(UUID_1);
        storage.save(resume);
        storage.update(resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void getAll() throws Exception {
        Resume[] dummyArray = new Resume[]{new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        Assert.assertArrayEquals(storage.getAll(), dummyArray);
        storage.clear();
        Resume[] dummyArrayClear = new Resume[0];
        Assert.assertArrayEquals(storage.getAll(), dummyArrayClear);
    }

    @Test
    public void save() throws Exception {
        storage.clear();
        Resume dummy = new Resume(UUID_1);
        storage.save(dummy);
        Assert.assertSame(dummy, storage.get(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void saveOutOfLimit() throws Exception {
        storage.clear();
        try {
            for (int i = 0; i < 10000; i++) {
                Resume resume = new Resume();
                storage.save(resume);
            }
        } catch (StorageException e) {
            Assert.fail("Переполнение произошло раньше времени");
        }
        storage.save(new Resume());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(new Resume(UUID_1));
    }

    @Test
    public void delete() throws Exception {
        Assert.assertEquals(3, storage.size());
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void get() throws Exception {
        storage.clear();
        Resume dummy = new Resume(UUID_1);
        storage.save(dummy);
        Assert.assertSame(storage.get(UUID_1), dummy);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}