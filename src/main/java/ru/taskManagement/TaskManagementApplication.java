package ru.taskManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Программа TaskManagementApplication реализует Spring приложение для управления задачами
 *
 * @author Строев Д.В.
 * @version 1.0
 */
@SpringBootApplication
public class TaskManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApplication.class, args);
	}

}
