package ru.taskManagement.controller.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.taskManagement.model.entity.Executor;
import ru.taskManagement.model.entity.Subscriber;
import ru.taskManagement.model.entity.Task;
import ru.taskManagement.model.enums.TaskType;
import ru.taskManagement.model.factory.TaskFactoryImpl;
import ru.taskManagement.service.ExecutorService;
import ru.taskManagement.service.SubscriberService;
import ru.taskManagement.service.TaskService;

import java.util.List;

/**
 * Rest контроллер
 *
 * @author Строев Д.В.
 * @version 1.0
 */
@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestController {

    /**
     * Поле taskService
     */
    private final TaskService taskService;

    /**
     * Поле executorService
     */
    private final ExecutorService executorService;

    /**
     * Поле subscriberService
     */
    private final SubscriberService subscriberService;

    /**
     * Поле taskFactory
     */
    private final TaskFactoryImpl taskFactory;

    /**
     * Tasks
     */

    /**
     * Метод возвращает все задачи
     *
     * @param keyword условие
     * @return список задач
     */
    @GetMapping("/tasks")
    public List<Task> getAllTask(String keyword) {
        return taskService.getAllTasks(keyword);
    }

    /**
     * Метод возвращает задачу по id
     *
     * @param id id задачи
     * @return задачу
     */
    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    /**
     * Метод создания задачи
     *
     * @param task задача
     * @return созданную задачу
     */
    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    /**
     * Метод создания быстрой задачи
     *
     * @param task задача
     * @param type тип быстрой задачи
     * @return созданную задачу
     */
    @PostMapping("/tasks/{type}")
    public Task createTask(@RequestBody Task task, @PathVariable TaskType type) {
        task = (Task) taskFactory.createTask(type);
        return taskService.createTask(task);
    }

    /**
     * Метод обновления задачи
     *
     * @param id   id задачи
     * @param task задача
     * @return обновленную задачу
     */
    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    /**
     * Метод удаления задачи
     *
     * @param id id задачи
     */
    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
    }

    /**
     * Метод создания исполнителя для задачи
     *
     * @param id       id задачи
     * @param executor исполнитель
     * @return задачу
     */
    @PutMapping("/tasks/{id}/executors")
    public Task createExecutorForTask(@PathVariable Long id, @RequestBody Executor executor) {
        return taskService.createExecutorForTask(id, executor);
    }

    /**
     * Метод добавления исполнителя задаче по id
     *
     * @param id          id задачи
     * @param executorsId id исполнителя
     * @return задачу
     */
    @PutMapping("/tasks/{id}/executors/{executorsId}")
    public Task addExecInTask(@PathVariable Long id, @PathVariable Long executorsId) {
        return taskService.assignExecutor(id, executorsId);
    }

    /**
     * Метод возвращает всех исполнителей у задачи
     *
     * @param id id задачи
     * @return список задач
     */
    @GetMapping("/tasks/{id}/executor")
    public List<Executor> getExecutorsByTask(@PathVariable Long id) {
        return taskService.getExecutorsTask(id);
    }

    /**
     * Метод удаления исполнителя у задачи
     *
     * @param id          id задачи
     * @param executorsId id исполнителя
     */
    @DeleteMapping("/tasks/{id}/executors/{executorsId}")
    public void deleteExecutorFromTask(@PathVariable Long id, @PathVariable Long executorsId) {
        taskService.removingExecutorFromTask(id, executorsId);
    }

    /**
     * Метод возвращает всех слушателей у задачи
     *
     * @param id id задачи
     * @return список слушателей
     */
    @GetMapping("/tasks/{id}/subscribers")
    public List<Subscriber> getSubscriberByTask(@PathVariable Long id) {
        return taskService.getSubscriberTask(id);
    }

    /**
     * Метод удаления слушателя у задачи
     *
     * @param id            id задачи
     * @param subscribersId id слушателя
     */
    @DeleteMapping("/tasks/{id}/subscribers/{subscribersId}")
    public void deleteSubscribersFromTask(@PathVariable Long id, @PathVariable Long subscribersId) {
        taskService.removingSubscriberFromTask(id, subscribersId);
    }


    /**
     * Executor
     */

    /**
     * Метод создания исполнителя
     *
     * @param executor исполнитель
     * @return исполнителя
     */
    @PostMapping("/executors")
    public Executor createExecutor(@RequestBody Executor executor) {
        return executorService.save(executor);
    }

    /**
     * Метод возвращает всех исполнителей
     *
     * @return список исполнителей
     */
    @GetMapping("/executors")
    public List<Executor> getAllExecutor() {
        return executorService.findAll();
    }

    /**
     * Метод возвращает исполнителя по id
     *
     * @param id id исполнителя
     * @return исполнителя
     */
    @GetMapping("/executors/{id}")
    public Executor getExecutorById(@PathVariable Long id) {
        return executorService.findById(id);
    }

    /**
     * Метод обновления исполнителя
     *
     * @param id       id исполнителя
     * @param executor исполнителя
     * @return исполнителя
     */
    @PutMapping("/executors/{id}")
    public Executor updateExecutor(@PathVariable Long id, @RequestBody Executor executor) {
        return executorService.updateExecutor(id, executor);
    }

    /**
     * Метод добавления задачи исполнителю
     *
     * @param id      id исполнителя
     * @param tasksId id задачи
     * @return исполнителя
     */
    @PutMapping("/executors/{id}/tasks/{tasksId}")
    public Executor addTaskInExecutor(@PathVariable Long id, @PathVariable Long tasksId) {
        return executorService.assignTask(id, tasksId);
    }

    /**
     * Метод возвращает все задачи у исполнителя
     *
     * @param id id исполнителя
     * @return список задач
     */
    @GetMapping("/executor/{id}/tasks")
    public List<Task> getTasksPoExecutors(@PathVariable Long id) {
        return taskService.getTasksExecutor(id);
    }

    /**
     * Метод создания задачи у исполнителя
     *
     * @param id   id исполнителя
     * @param task задача
     * @return исполнителя
     */
    @PutMapping("/executors/{id}/tasks")
    public Executor createTaskForExecutor(@PathVariable Long id, @RequestBody Task task) {
        return executorService.createTaskForExecutor(id, task);
    }

    /**
     * Метод удаления исполнителя по id
     *
     * @param id id исполнителя
     */
    @DeleteMapping("/executors/{id}")
    public void deleteExecutor(@PathVariable Long id) {
        executorService.deleteById(id);
    }

    /**
     * Метод удаления задачи у исполнителя
     *
     * @param id     id исполнителя
     * @param taskId id задачи
     */
    @DeleteMapping("/executors/{id}/tasks/{taskId}")
    public void deleteTaskFromExecutor(@PathVariable Long id, @PathVariable Long taskId) {
        executorService.removingTaskFromExecutor(id, taskId);
    }

    /**
     * Subscriber
     */

    /**
     * Метод возвращает всех слушателей
     *
     * @return список слушателей
     */
    @GetMapping("/subscribers")
    public List<Subscriber> getAllSubscriber() {
        return subscriberService.findAll();
    }

    /**
     * Метод возвращает слушателя по id
     *
     * @param id id слушателя
     * @return слушателя
     */
    @GetMapping("/subscribers/{id}")
    public Subscriber getSubscriberById(@PathVariable Long id) {
        return subscriberService.findById(id);
    }

    /**
     * Метод создания слушателя
     *
     * @param subscriber слушатель
     * @return слушателя
     */
    @PostMapping("/subscribers")
    public Subscriber createSubscriber(@RequestBody Subscriber subscriber) {
        return subscriberService.save(subscriber);
    }

    /**
     * Метод возвращает задачи у слушателя
     *
     * @param id id слушателя
     * @return список задач
     */
    @GetMapping("/subscribers/{id}/tasks")
    public List<Task> getTasksSubscribers(@PathVariable Long id) {
        return subscriberService.getTasksSubscriber(id);
    }

    /**
     * Метод обновления слушателя
     *
     * @param id         id слушателя
     * @param subscriber слушатель
     * @return обновленного слушателя
     */
    @PutMapping("/subscribers/{id}")
    public Subscriber updateSubscriber(@PathVariable Long id, @RequestBody Subscriber subscriber) {
        return subscriberService.updateSubscriber(id, subscriber);
    }

    /**
     * Метод удаления слушателя
     *
     * @param id id слушателя
     */
    @DeleteMapping("/subscribers/{id}")
    public void deleteSubscriber(@PathVariable Long id) {
        subscriberService.deleteById(id);
    }

    /**
     * Метод добавления задачи слушателю
     *
     * @param id     id слушателя
     * @param taskid id задачи
     * @return слушателя
     */
    @PutMapping("/subscribers/{id}/tasks/{taskid}")
    public Subscriber addTaskInSubscriber(@PathVariable Long id, @PathVariable Long taskid) {
        return subscriberService.assignTask(id, taskid);
    }

    /**
     * Метод удаления задачи у слушателя
     *
     * @param id     id слушателя
     * @param taskId id задачи
     */
    @DeleteMapping("/subscribers/{id}/tasks/{taskId}")
    public void deleteTaskFromSubscriber(@PathVariable Long id, @PathVariable Long taskId) {
        subscriberService.removingTaskFromSubscriber(id, taskId);
    }
}

