package model.resolver;

import java.util.List;
import java.util.Map;

public abstract class AbstractResolver<T> {
    abstract public T get(int id);
    abstract public List<T> get();
    abstract public boolean delete(int id);
    abstract public boolean insert(Map<String, String> arguments);
    abstract public boolean update(int id, Map<String, String> params);
    abstract public void printStudentsList();
}
