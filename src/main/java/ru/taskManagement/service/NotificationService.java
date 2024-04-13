package ru.taskManagement.service;

import org.springframework.stereotype.Service;
import ru.taskManagement.model.entity.Task;
import ru.taskManagement.model.enums.Status;

import java.util.List;

/**
 * Класс оповещения слушателей
 *
 * @author Строев Д.В
 * @version 1.0
 */
@Service
public class NotificationService {

    /**
     * Метод оповещения о создание задачи
     * @param task задача
     */
    public void notifyCreatedTask(Task task) {
        System.out.println("A new task has been created: " + task.getDescription());
    }

    /**
     * Метод представления задач
     *
     * @param list список задач
     */
    public void notifyListTask(List<Task> list) {
        System.out.println("The list Tasks: " + list);
    }

    /**
     * Метод оповещения об изменение статуса у задачи
     *
     * @param status статус
     */
    public void notifyStutusUpdate(Status status) {
        System.out.println("Update status task " + status);
    }
}
