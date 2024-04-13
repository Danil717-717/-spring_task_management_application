package ru.taskManagement.model.factory;

import org.springframework.stereotype.Component;
import ru.taskManagement.model.entity.ITask;
import ru.taskManagement.model.entity.Task;
import ru.taskManagement.model.enums.TaskType;

import static ru.taskManagement.model.enums.Status.NORMAL_EXECUTION;
import static ru.taskManagement.model.enums.Status.URGENT_IMPLEMENTATION;

/**
 * Класс имплементирующий интерфейс фабрики задач
 *
 * @author Строев Д.В.
 * @version 1.0
 */
@Component
public class TaskFactoryImpl implements TaskFactory {

    /**
     * Переопределенный метод создания задач
     *
     * @param type тип задачи
     * @return задачу
     */
    @Override
    public ITask createTask(TaskType type) {
        switch (type) {
            case NORMAL_EXECUTION:
                return Task.builder().description("NORMAL_EXECUTION").status(NORMAL_EXECUTION).build();
            case URGENT_IMPLEMENTATION:
                return Task.builder().description("URGENT_IMPLEMENTATION").status(URGENT_IMPLEMENTATION).build();
            default:
                return new Task();
        }
    }
}
