package ru.taskManagement.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import ru.taskManagement.model.enums.Status;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс сущности задача имплементирующий интерфейс ITask
 * со свойствами id, description, status, createdAt,
 * updatedAt, executors, subs
 *
 * @author Строев Д.В.
 * @version 1.0
 */
@Entity(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Task implements ITask {

    /**
     * Поле id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Поле description
     */
    @NotEmpty
    @Column(nullable = false)
    private String description;

    /**
     * Поле status
     */
    @Enumerated(EnumType.STRING)
    private Status status;

    /**
     * Поле createdAt
     */
    @Column
    @CreationTimestamp(source = SourceType.DB)
    private Timestamp createdAt;

    /**
     * Поле updatedAt
     */
    @Column
    @UpdateTimestamp(source = SourceType.DB)
    private Timestamp updatedAt;

    /**
     * Поле executors
     */
    @ManyToMany
    @JoinTable(
            name = "execute_task",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "executor_id"))
    private List<Executor> executors = new ArrayList<>();

    /**
     * Поле subs
     */
    @OneToMany
    private List<Subscriber> subs = new ArrayList<>();

    /**
     * Конструктор по умолчанию - создание нового объекта
     *
     * @see Task#Task()
     */
    public Task() {
        // Nothing to do
    }

    /**
     * Конструктор - создание нового объекта
     *
     * @param description - id пользователя
     * @see Task#Task(String)
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Переопределенный метод getTaskId
     *
     * @return id задачи
     */
    @Override
    public Long getTaskId() {
        return getId();
    }

    /**
     * Добавление исполнителя задаче
     *
     * @param executor исполнитель
     */
    public void addExecutor(Executor executor) {
        this.executors.add(executor);
    }

    /**
     * Удаление исполнителя у задачи
     *
     * @param executorId id исполнителя
     */
    public void removeExecutor(long executorId) {
        Executor executor = this.executors.stream().filter(t -> t.getId() == executorId)
                .findFirst().orElse(null);
        if (executor != null) {
            this.executors.remove(executor);
            executor.getTasks().remove(this);
        }
    }

    /**
     * Добавление слушателя задаче
     *
     * @param sub слушатель
     */
    public void addSubscribe(Subscriber sub) {
        subs.add(sub);
    }

    /**
     * Удаление слушателя у задачи
     *
     * @param subscriberId id исполнителя
     */
    public void delSubscribe(long subscriberId) {
        Subscriber subscriber = this.subs.stream().filter(t -> t.getId() == subscriberId)
                .findFirst().orElse(null);
        if (subscriber != null) {
            this.subs.remove(subscriber);
            subscriber.getTasks().remove(this);
        }
    }

    /**
     * Оповещение слушателей задачи
     */
    public void notifySubscribers() {
        for (Subscriber sub : subs) {
            sub.update();
        }
    }
}
