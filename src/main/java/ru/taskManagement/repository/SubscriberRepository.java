package ru.taskManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.taskManagement.model.entity.Subscriber;

/**
 * Репозиторий слушателя
 *
 * @author Строев Д.В.
 * @version 1.0
 */
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

}
