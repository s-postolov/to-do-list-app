package com.example.todolist.service;

import com.example.todolist.model.ToDo;
import com.example.todolist.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService{
    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDo> getAllTodos(){
        return toDoRepository.findAll();
    }

    public ToDo findById(Long id){
        return toDoRepository.findById(id).orElseThrow();
    }

    public ToDo addTodo(String title, String description, Boolean completed){
        ToDo todo = new ToDo(title, description, completed);
        toDoRepository.save(todo);
        return todo;
    }

    public ToDo updateTodo(Long id, String title, String description, Boolean completed){
        ToDo todo = toDoRepository.findById(id).orElseThrow();
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setCompleted(completed);
        toDoRepository.save(todo);
        return todo;
    }

    public ToDo deleteTodo(Long id) {
        ToDo todo = toDoRepository.findById(id).orElseThrow();
        toDoRepository.delete(todo);
        return todo;
    }
}