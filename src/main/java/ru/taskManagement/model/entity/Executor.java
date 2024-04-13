package ru.taskManagement.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс сущности исполнитель со свойствами id, name, tasks
 *
 * @author Строев Д.В.
 * @version 1.0
 */
@Entity(name = "executors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Executor {

    /**
     * Поле id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Поле name
     */
    @NotEmpty
    @Column(nullable = false)
    private String name;

    /**
     * Поле tasks
     */
    @ManyToMany
    private List<Task> tasks = new ArrayList<>();

    /**
     * Добавление задачи исполнителю
     *
     * @param task задача
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Удаление задачи у исполнителя
     *
     * @param taskId id задачи
     */
    public void removeTask(long taskId) {
        Task task = this.tasks.stream().filter(t -> t.getId() == taskId)
                .findFirst().orElse(null);
        if (task != null) {
            this.tasks.remove(task);
        }
    }
}
