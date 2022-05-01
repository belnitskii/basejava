package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import serialize.SerializeStrategy;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;

    SerializeStrategy serializeStrategy;

    protected PathStorage(String dir, SerializeStrategy serializeStrategy) {
        Objects.requireNonNull(dir, "directory must not be null");
        directory = Paths.get(dir);
        this.serializeStrategy = serializeStrategy;
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {
        try {
            return (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("Directory read error", null);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return Paths.get(String.valueOf(directory), uuid);
    }

    @Override
    protected void doUpdate(Resume r, Path path) {
        try {
            serializeStrategy.doWrite(r, new BufferedOutputStream(Files.newOutputStream(path)));

//            doWrite(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", r.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected void doSave(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create path ", path.toString(), e);
        }
        doUpdate(r, path);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return serializeStrategy.doRead(new BufferedInputStream(Files.newInputStream(path)));
//            return doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path read error", path.toString(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error", String.valueOf(path));
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        try {
            List<Path> paths = Files.list(directory).collect(Collectors.toList());
            List<Resume> list = new ArrayList<>(paths.size());
            for (Path Path : paths) {
                list.add(doGet(Path));
            }
            return list;
        } catch (IOException e) {
            throw new StorageException("Directory read error", null);
        }

    }
}
