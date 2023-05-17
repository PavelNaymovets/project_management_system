package com.digdes.pms.repository.employee;

import com.digdes.pms.model.employee.Employee;
import com.digdes.pms.repository.employee.util.FileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStorage implements EmployeeRepository {
    private FileUtil fileUtil;
    private Map<Long, Employee> storage;

    public DataStorage() {
        this.fileUtil = new FileUtil();
        storage = fileUtil.getStorage();
    }

    @Override
    public boolean create(Employee employee) {
        fileUtil.readObjectsFromFile();
        long id = findAll().size();

        if (id != 0) { //возможно какой-то из объектов был удален. Нужно сделать id больше на 1 чем у последнего объекта.
            long lastEmployeeId = storage.get(id).getId();
            employee.setId(lastEmployeeId + 1);
            storage.put(lastEmployeeId + 1, employee);
        } else {
            employee.setId(id + 1);
            storage.put(id + 1, employee);
        }

        fileUtil.writeObjectsIntoFile();

        return true;
    }

    @Override
    public boolean update(Employee employee) {
        fileUtil.readObjectsFromFile();
        storage.put(employee.getId(), employee);
        fileUtil.writeObjectsIntoFile();

        return true;
    }

    @Override
    public Employee findById(Long id) {
        fileUtil.readObjectsFromFile();

        return storage.get(id);
    }

    @Override
    public List<Employee> findAll() {
        fileUtil.readObjectsFromFile();
        List<Employee> list = new ArrayList<>();

        for (Long id : storage.keySet()) {
            list.add(storage.get(id));
        }

        return list;
    }

    @Override
    public boolean deleteById(Long id) {
        fileUtil.readObjectsFromFile();
        storage.remove(id);
        fileUtil.writeObjectsIntoFile();

        return true;
    }
}
