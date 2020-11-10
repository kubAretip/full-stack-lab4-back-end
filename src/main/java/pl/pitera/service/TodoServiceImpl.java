package pl.pitera.service;

import org.springframework.stereotype.Service;
import pl.pitera.entity.Todo;
import pl.pitera.repository.TodoRepository;

import java.util.List;

@Service
class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Todo getTodoById(long id) {
        return todoRepository.findById(id).orElse(null);
    }

    @Override
    public void removeTodo(long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Todo editTodo(long id, Todo todo) {

        var todoOptional = todoRepository.findById(id);

        if (todoOptional.isPresent()) {
            var todo1 = todoOptional.get();
            if (todo.getTitle() != null)
                todo1.setTitle(todo.getTitle());

            if (todo.getDescription() != null)
                todo1.setDescription(todo.getDescription());

            todo1.setDone(todo.isDone());
            return todoRepository.save(todo1);
        }

        return null;
    }
}
