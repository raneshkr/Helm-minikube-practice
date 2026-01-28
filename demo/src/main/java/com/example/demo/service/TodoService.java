package com.example.demo.service;

import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    public List<Todo> getCompletedTodos() {
        return todoRepository.findByCompleted(true);
    }

    public List<Todo> getIncompleteTodos() {
        return todoRepository.findByCompleted(false);
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long id, Todo todoDetails) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()) {
            Todo existingTodo = todo.get();
            if (todoDetails.getTitle() != null) {
                existingTodo.setTitle(todoDetails.getTitle());
            }
            if (todoDetails.getDescription() != null) {
                existingTodo.setDescription(todoDetails.getDescription());
            }
            if (todoDetails.getCompleted() != null) {
                existingTodo.setCompleted(todoDetails.getCompleted());
            }
            return todoRepository.save(existingTodo);
        }
        return null;
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    public void deleteAllCompletedTodos() {
        List<Todo> completedTodos = todoRepository.findByCompleted(true);
        todoRepository.deleteAll(completedTodos);
    }
}
