package ru.taskManagement.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.taskManagement.model.entity.Subscriber;
import ru.taskManagement.model.entity.Task;
import ru.taskManagement.repository.SubscriberRepository;
import ru.taskManagement.service.SubscriberService;
import ru.taskManagement.service.TaskService;

import java.util.List;

/**
 * Класс имплементирующий интерфейс сервис слушателя
 *
 * @author Строев Д.В.
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class SubscriberServiceImpl implements SubscriberService {

    /**
     * поле subscriberRepository
     */
    private final SubscriberRepository subscriberRepository;

    /**
     * поле taskService
     */
    private final TaskService taskService;

    @Override
    public List<Subscriber> findAll() {
        return subscriberRepository.findAll();
    }

    @Override
    public Subscriber findById(Long id) {
        return subscriberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reader not found"));
    }

    @Override
    public Subscriber save(Subscriber subscriber) {
        return subscriberRepository.save(subscriber);
    }

    public Subscriber getSubscriber(Long id) {
        return findAll().stream().filter(subscriber -> subscriber.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Subscriber updateSubscriber(Long id, Subscriber subscriber) {
        Subscriber subOld = getSubscriber(id);
        if (subOld != null) {
            subOld.setName(subscriber.getName());
        }
        return subOld;
    }

    @Override
    public void deleteById(Long id) {
        subscriberRepository.deleteById(id);
    }

    @Override
    public List<Task> getTasksSubscriber(Long id) {
        Subscriber subscriber = findById(id);
        return subscriber.getTasks();
    }

    @Override
    public void removingTaskFromSubscriber(Long id, Long taskId) {
        Subscriber subscriber = findById(id);
        subscriber.removeTask(taskId);
        save(subscriber);
    }

    @Override
    public Subscriber assignTask(Long id, Long taskId) {
        Subscriber subscriber = getSubscriber(id);
        Task task = taskService.getTaskById(taskId);
        subscriber.addTask(task);
        return subscriberRepository.save(subscriber);
    }
}
