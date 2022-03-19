package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ArrayStorage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");


        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("....................................");
        System.out.println("My test started");
        System.out.println("....................................");

        Resume r4 = new Resume();
        r4.setUuid("uuid4");
        ARRAY_STORAGE.save(r4);

        System.out.println("Test save again");
        ARRAY_STORAGE.save(r4);
        System.out.println("....................................");
        ARRAY_STORAGE.update(r4, "kek");
        ARRAY_STORAGE.get("kek");
        ARRAY_STORAGE.delete(r4.getUuid());

        System.out.println("Test delete again");
        ARRAY_STORAGE.delete(r4.getUuid());
        System.out.println("....................................");

        System.out.println("Test update deleted resume again");
        ARRAY_STORAGE.update(r4,"kek");
        System.out.println("....................................");

        System.out.println("My test finished");
        System.out.println("....................................");

        System.out.println("Test basejava started");
        System.out.println("....................................");
        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());
        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
        System.out.println("....................................");
        System.out.println("Test basejava finished");
        System.out.println("....................................");
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
