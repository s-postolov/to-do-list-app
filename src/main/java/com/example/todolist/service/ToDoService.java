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

    public ToDo createTodo(ToDo toDo){
        return toDoRepository.save(toDo);
    }
}