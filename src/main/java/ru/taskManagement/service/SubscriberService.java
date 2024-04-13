package ru.taskManagement.service;

import ru.taskManagement.model.entity.Subscriber;
import ru.taskManagement.model.entity.Task;

import java.util.List;

/**
 * Интерфейс сервиса слушателя
 *
 * @author Строев Д.В.
 * @version 1.0
 */
public interface SubscriberService {

    /**
     * Метод поиска всех слушателей
     *
     * @return список слушателей
     */
    List<Subscriber> findAll();

    /**
     * Метод поиска по id слушателя
     *
     * @param id id слушателя
     * @return слушателя
     */
    Subscriber findById(Long id);

    /**
     * Метод сохранения слушателя
     *
     * @param subscriber слушатель
     * @return сохраненного слушателя
     */
    Subscriber save(Subscriber subscriber);

    /**
     * Метод обновления слушателя
     *
     * @param id         id слушателя
     * @param subscriber слушатель
     * @return обновленного слушателя
     */
    Subscriber updateSubscriber(Long id, Subscriber subscriber);

    /**
     * Метод удаления слушателя по id
     *
     * @param id id слушателя
     */
    void deleteById(Long id);

    /**
     * Метод поиска задач у слушателя
     *
     * @param id id слушателя
     * @return список задач
     */
    List<Task> getTasksSubscriber(Long id);

    /**
     * Метод удаления задачи у слушателя
     * @param id     id слушателя
     * @param taskId id задачи
     */
    void removingTaskFromSubscriber(Long id, Long taskId);

    /**
     * Метод добавления слушателю задачи
     *
     * @param id     id слушателя
     * @param taskId id задачи
     * @return слушателя
     */
    Subscriber assignTask(Long id, Long taskId);
}
