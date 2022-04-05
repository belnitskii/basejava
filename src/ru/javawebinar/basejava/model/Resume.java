package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.storage.SortedArrayStorage;

import java.util.Comparator;
import java.util.UUID;

/**
 * ru.javawebinar.basejava.model.Resume class
 */
public class Resume{
    public static Comparator<Resume> resumeUuidComparator = new ResumeUuidComparator();
    public static Comparator<Resume> resumeComparator = new ResumeNameComparator().thenComparing(resumeUuidComparator);

    // Unique identifier
    private final String uuid;

    private String fullName;

    public Resume() {
        this(UUID.randomUUID().toString());
    }

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName(){
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return uuid + " " + fullName;
    }

    public static class ResumeNameComparator implements Comparator<Resume>{
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getFullName().compareTo(o2.getFullName());
        }
    }

    public static class ResumeUuidComparator implements Comparator<Resume> {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    }
}