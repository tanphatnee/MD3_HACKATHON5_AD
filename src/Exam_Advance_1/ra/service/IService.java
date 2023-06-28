package Exam_Advance_1.ra.service;

import Exam_Advance_1.ra.model.Catalog;

import java.util.List;

public interface IService<T, E> {
    List<T> getAll();
    void save(T t);
    Catalog findById(E e);
    void delete(E e);
}
