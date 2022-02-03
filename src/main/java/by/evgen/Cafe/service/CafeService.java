package by.evgen.Cafe.service;

import by.evgen.Cafe.model.CafeModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CafeService<T extends CafeModel> {
    T findById(Long id) throws Exception;
    List<T> findAll();
    void save(T model);
    void update(T model) throws Exception;
    void deleteById(Long id);
}
