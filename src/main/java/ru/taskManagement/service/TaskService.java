package ru.taskManagement.service;

import org.springframework.stereotype.Service;
import ru.taskManagement.model.entity.Executor;
import ru.taskManagement.model.entity.Subscriber;
import ru.taskManagement.model.entity.Task;
import ru.taskManagement.model.enums.Status;

import java.util.List;

/**
 * Интерфейс сервиса задачи
 *
 * @author Строев Д.В.
 * @version 1.0
 */
@Service
public interface TaskService {

    /**
     * Метод создания задачи
     *
     * @param task задача
     * @return задачу
     */
    Task createTask(Task task);

    /**
     * Метод сохранения задачи
     *
     * @param task задача
     * @return сохраненную задачу
     */
    Task save(Task task);

    /**
     * Метод возвращающий задачи
     *
     * @param keyword условие возврата задачи
     * @return список задач
     */
    List<Task> getAllTasks(String keyword);

    /**
     * Метод возвращающий задачи
     *
     * @return список задач
     */
    List<Task> getTasks();

    /**
     * Метод поиска задачи по id
     *
     * @param id id задачи
     * @return задачу
     */
    Task getTaskById(Long id);

    /**
     * Метод обновления задачи
     *
     * @param id   id задачи
     * @param task задача
     * @return обновленную задачу
     */
    Task updateTask(Long id, Task task);

    /**
     * Метод обновления задачи
     *
     * @param task задача
     * @return обновленную задачу
     */
    Task apdateTask(Task task);

    /**
     * Метод удаления задачи
     *
     * @param id id задачи
     */
    void deleteById(Long id);

    /**
     * Метод создания исполнителя у задачи
     *
     * @param id       id задачи
     * @param executor исполнитель
     * @return задачу с исполнителем
     */
    Task createExecutorForTask(Long id, Executor executor);

    /**
     * Метод добавления исполнителя задаче
     *
     * @param id         id задачи
     * @param executorId id исполнителя
     * @return задачу
     */
    Task assignExecutor(Long id, Long executorId);

    /**
     * Метод просмотра задачей у исполнителя
     *
     * @param id id исполнителя
     * @return список задач
     */
    List<Task> getTasksExecutor(Long id);

    /**
     * Метод просмотра исполнителей у задачи
     *
     * @param id id задачи
     * @return список исполнителя
     */
    List<Executor> getExecutorsTask(Long id);

    /**
     * Метод просмотра слушателей у задачи
     *
     * @param id id задачи
     * @return список слушателей
     */
    List<Subscriber> getSubscriberTask(Long id);

    /**
     * Метод удаления исполнителя у задачи
     *
     * @param taskId     id задачи
     * @param executorId id исполнителя
     */
    void removingExecutorFromTask(Long taskId, Long executorId);

    /**
     * Метод удаления слушателя у задачи
     *
     * @param taskId       id задачи
     * @param subscriberId id слушателя
     */
    void removingSubscriberFromTask(Long taskId, Long subscriberId);
}
