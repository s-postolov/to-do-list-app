package com.example.todolist.web.controller;

import com.example.todolist.model.ToDo;
import com.example.todolist.service.ToDoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/todos")
public class ToDoController {
    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping
    public String getAllTodos(Model model){
        List<ToDo> todos = toDoService.getAllTodos();
        model.addAttribute("todos", todos);
        return "todos";
    }

    @PostMapping
    public String createTodo(@ModelAttribute ToDo todo){
        toDoService.createTodo(todo);
        return "redirect:/api/todos";
    }
}