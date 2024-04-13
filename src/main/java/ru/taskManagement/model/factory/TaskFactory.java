package ru.taskManagement.model.factory;

import ru.taskManagement.model.entity.ITask;
import ru.taskManagement.model.enums.TaskType;

/**
 * Интерфейс фабрики задач
 *
 * @author Строев Д.В.
 * @version 1.0
 */
public interface TaskFactory {

    /**
     * Метод создания задачи
     *
     * @param type тип задачи
     * @return задачу
     */
    ITask createTask(TaskType type);
}
