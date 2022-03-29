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
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
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
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("notExist"));
    }

    @Test
    public void getAll() {
        Resume[] dummyArray = new Resume[]{new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        Assert.assertArrayEquals(storage.getAll(), dummyArray);
        storage.clear();
        Resume[] dummyArrayClear = new Resume[0];
        Assert.assertArrayEquals(storage.getAll(), dummyArrayClear);
    }

    @Test
    public void save() {
        String uuid = "dummy";
        Resume dummy = new Resume(uuid);
        storage.save(dummy);
        Assert.assertSame(dummy, storage.get(uuid));
    }

    @Test(expected = StorageException.class)
    public void saveOutOfLimit() {
        storage.clear();
        try {
            for (int i = 0; i < getStorageLimit(); i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("Переполнение произошло раньше времени");
        }
        storage.save(new Resume());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_1));
    }

    @Test
    public void delete() {
        Assert.assertEquals(3, storage.size());
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("notExist");
    }

    @Test
    public void get(){
        storage.clear();
        Resume dummy = new Resume(UUID_1);
        storage.save(dummy);
        Assert.assertSame(storage.get(UUID_1), dummy);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("notExist");
    }

    protected abstract int getStorageLimit();
}