package pl.pitera.service;

import pl.pitera.entity.Todo;

import java.util.List;

public interface TodoService {

    List<Todo> getAllTodos();

    Todo getTodoById(long id);

    void removeTodo(long id);

    Todo createTodo(Todo todo);

    Todo editTodo(long id, Todo todo);
}
