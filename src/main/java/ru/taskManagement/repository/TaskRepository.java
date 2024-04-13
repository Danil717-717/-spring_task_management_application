package ru.taskManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.taskManagement.model.entity.Task;

import java.util.List;

/**
 * Репозиторий задачи
 *
 * @author Строев Д.В.
 * @version 1.0
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Метод поиска задач по статусу
     *
     * @param keyword статус
     * @return список задач
     */
    @Query(value = "select * from tasks s where s.status like %:keyword% "
            , nativeQuery = true)
    List<Task> findAll(String keyword);
}
