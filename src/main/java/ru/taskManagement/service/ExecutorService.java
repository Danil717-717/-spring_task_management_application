package ru.taskManagement.service;

import org.springframework.stereotype.Service;
import ru.taskManagement.model.entity.Executor;
import ru.taskManagement.model.entity.Task;

import java.util.List;

/**
 * Интерфейс сервиса исполнителя
 *
 * @author Строев Д.В.
 * @version 1.0
 */
@Service
public interface ExecutorService {

    /**
     * Метод возвращающий исполнителей
     *
     * @return список исполнителей
     */
    List<Executor> findAll();

    /**
     * Метод поиска исполнителя по id
     * @param id id исполнителя
     * @return исполнителя с этим id
     */
    Executor findById(Long id);

    /**
     * Метод сохранения исполнителя
     *
     * @param executor исполнитель
     * @return исполнителя с этим id
     */
    Executor save(Executor executor);

    /**
     * Метод обновления исполнителя
     *
     * @param id id исполнителя
     * @param executor исполнитель
     * @return обновленного исполнителя
     */
    Executor updateExecutor(Long id, Executor executor);

    /**
     * Метод обновления исполнителя
     *
     * @param executor исполнитель
     * @return обновленного исполнителя
     */
    Executor apdateExecutor(Executor executor);

    /**
     * Удаление исполнителя по id
     *
     * @param id id исполнителя
     */
    void deleteById(Long id);

    /**
     * Метод создания задачи для исполнителя
     *
     * @param id id исполнителя
     * @param task задача
     * @return исполнителя с задачей
     */
    Executor createTaskForExecutor(Long id, Task task);

    /**
     * Метода удаления задачи у исполнителя
     * @param executorId id исполнителя
     * @param taskId id задачи
     */
    void removingTaskFromExecutor(Long executorId, Long taskId);

    /**
     * Добавление задачи исполнителю
     *
     * @param id id исполнителя
     * @param taskId id задачи
     * @return исполнителя с новой задачей
     */
    Executor assignTask(Long id, Long taskId);
}
