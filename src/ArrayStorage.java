import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        if (r != null) {
            storage[size] = r;
            size++;
        }
    }

    Resume get(String uuid) {
        return Arrays.stream(storage).limit(size).filter(resume -> resume.toString().equals(uuid)).findFirst().orElse(null);
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = null;
                if (size - i >= 0) System.arraycopy(storage, i + 1, storage, i, size - i);
                size--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.stream(storage).limit(size).toArray(Resume[]::new);
    }

    int size() {
        return size;
    }


}
