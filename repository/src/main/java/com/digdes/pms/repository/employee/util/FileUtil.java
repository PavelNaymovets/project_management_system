package com.digdes.pms.repository.employee.util;

import com.digdes.pms.model.employee.Employee;

import java.io.*;
import java.util.*;


public class FileUtil {
    private final static File PATH = new File("C:\\Users\\Павел\\IdeaProjects\\project_management_system\\datastorage\\Employee.txt");
    private Map<Long, Employee> storage;

    public FileUtil() {
        this.storage = new HashMap<>();
    }

    public void writeObjectsIntoFile() {
        try {
            FileOutputStream fos = new FileOutputStream(PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(storage);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readObjectsFromFile() {
        try {
            FileInputStream fis = new FileInputStream(PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
            storage.putAll((Map<Long, Employee>) ois.readObject());
            fis.close();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<Long, Employee> getStorage() {
        return storage;
    }
}
