package pl.pitera.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pl.pitera.entity.Todo;
import pl.pitera.service.TodoService;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {

        var todoList = todoService.getAllTodos();

        if (todoList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(todoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable long id) {

        var todo = todoService.getTodoById(id);

        if (isNull(todo))
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeTodoById(@PathVariable long id) {

        todoService.removeTodo(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo, UriComponentsBuilder uriComponentsBuilder) {
        var savedTodo = todoService.createTodo(todo);

        UriComponents location =
                uriComponentsBuilder.path("/{id}").buildAndExpand(savedTodo.getId());

        return ResponseEntity.created(location.toUri()).body(savedTodo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable long id, @RequestBody Todo todo) {
        var updatedTodo = todoService.editTodo(id, todo);

        if (isNull(updatedTodo))
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }



}
