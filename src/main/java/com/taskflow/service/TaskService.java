package com.taskflow.service;

import com.taskflow.entity.Task;
import com.taskflow.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task create(Task task) {
        task.setId(null);
        return repository.save(task);
    }

    public List<Task> getAll() {
        return repository.findAll();
    }

    public Task getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task not found: " + id));
    }

    public Task update(Long id, Task updated) {
        Task task = getById(id);
        task.setTitle(updated.getTitle());
        task.setDescription(updated.getDescription());
        task.setPriority(updated.getPriority());
        task.setCompleted(updated.isCompleted());
        return repository.save(task);
    }

    public Task toggle(Long id) {
        Task task = getById(id);
        task.setCompleted(!task.isCompleted());
        return repository.save(task);
    }

    public void delete(Long id) {
        repository.delete(getById(id));
    }

    public List<Task> getByPriority(String priority) {
        return repository.findByPriorityIgnoreCase(priority);
    }
}
