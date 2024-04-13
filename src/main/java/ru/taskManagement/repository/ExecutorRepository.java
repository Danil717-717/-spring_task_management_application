package ru.taskManagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.taskManagement.model.entity.Executor;

/**
 * Репозиторий исполнителя
 *
 * @author Строев Д.В.
 * @version 1.0
 */
public interface ExecutorRepository extends JpaRepository<Executor, Long> {

}
