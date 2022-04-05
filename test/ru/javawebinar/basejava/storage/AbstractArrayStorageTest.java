package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest{

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Override
    @Test(expected = StorageException.class)
    public void saveOverflow(){
            try {
                for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                    this.storage.save(new Resume());
                }
            } catch (StorageException e) {
                Assert.fail();
            }
            storage.save(new Resume());
        }
    }
