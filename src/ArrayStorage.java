import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        return Arrays.stream(storage)
                .filter(Objects::nonNull)
                .filter(resume -> resume.toString().equals(uuid)).findFirst().orElse(null);
    }

    void delete(String uuid) {
        int indexOfDelete = -1;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            }
            if (storage[i].toString().equals(uuid)) {
                indexOfDelete = i;
            }
        }
        if (indexOfDelete >= 0) {
            for (int i = indexOfDelete; i < storage.length - 1; i++) {
                if (storage[i] == null) {
                    break;
                }
                Resume toBeDeleted = storage[i];
                storage[i] = storage[i + 1];
                storage[i + 1] = toBeDeleted;

            }
        }
        storage[storage.length - 1] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.stream(storage).filter(Objects::nonNull).toArray(Resume[]::new);
    }

    int size() {
        return (int) Arrays.stream(storage).filter(Objects::nonNull).count();
    }
}
