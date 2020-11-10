package pl.pitera.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pitera.entity.Todo;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {

    @Override
    List<Todo> findAll();
}
