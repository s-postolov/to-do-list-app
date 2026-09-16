package com.example.todolist.web.controller;

import com.example.todolist.model.ToDo;
import com.example.todolist.service.ToDoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ToDoController {
    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/api/todos")
    public String getAllTodos(Model model){
        List<ToDo> todos = toDoService.getAllTodos();
        model.addAttribute("todos", todos);
        return "todos";
    }

    @GetMapping("/api/todos/add")
    public String showAdd(Model model){
        model.addAttribute("todo", new ToDo());
        return "form";
    }

    @PostMapping("/api/todos")
    public String addTodo(@ModelAttribute ToDo todo){
        toDoService.addTodo(todo.getTitle(), todo.getDescription(), todo.getCompleted());
        return "redirect:/api/todos";
    }

    @GetMapping("/api/todos/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model){
        ToDo todo = toDoService.findById(id);
        model.addAttribute("todo", todo);
        return "form";
    }

    @PostMapping("/api/todos/{id}")
    public String updateTodo(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam(required = false, defaultValue = "false") Boolean completed){
        toDoService.updateTodo(id, title, description, completed);
        return "redirect:/api/todos";
    }

    @PostMapping("/api/todos/{id}/delete")
    public String deleteTodo(@PathVariable Long id){
        toDoService.deleteTodo(id);
        return "redirect:/api/todos";
    }
}