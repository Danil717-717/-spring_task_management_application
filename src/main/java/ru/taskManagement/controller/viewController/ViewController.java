package ru.taskManagement.controller.viewController;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.taskManagement.model.entity.Executor;
import ru.taskManagement.model.entity.Task;
import ru.taskManagement.model.factory.TaskFactoryImpl;
import ru.taskManagement.service.ExecutorService;
import ru.taskManagement.service.TaskService;

import static ru.taskManagement.model.enums.TaskType.NORMAL_EXECUTION;
import static ru.taskManagement.model.enums.TaskType.URGENT_IMPLEMENTATION;

/**
 * Контроллер представления
 *
 * @author Строев Д.В.
 * @version 1.0
 */
@Controller
@RequestMapping("/index")
public class ViewController {

    /**
     * поле taskService
     */
    private final TaskService taskService;

    /**
     * поле executorService
     */
    private final ExecutorService executorService;

    /**
     * поле taskFactory
     */
    private final TaskFactoryImpl taskFactory;

    @Autowired
    public ViewController(TaskService taskService, ExecutorService executorService, TaskFactoryImpl taskFactory) {
        this.taskService = taskService;
        this.executorService = executorService;
        this.taskFactory = taskFactory;
    }

    /**
     * Метод возвращает главную страницу
     *
     * @return index.html
     */
    @GetMapping
    public String getIndex() {
        return "index";
    }

    /**
     * Метод представления списка всех задач
     *
     * @param model   модель
     * @param keyword условие
     * @return tasks.html
     */
    @GetMapping("/tasks")
    public String indexTask(Model model, @Param("keyword") String keyword) {
        model.addAttribute("tasks", taskService.getAllTasks(keyword));
        model.addAttribute("keyword", keyword);
        return "tasks";
    }

    /**
     * Метод представления списка исполнителей
     *
     * @param model модель
     * @return executors.html
     */
    @GetMapping("/executors")
    public String indexExec(Model model) {
        model.addAttribute("executors", executorService.findAll());
        return "executors";
    }

    /**
     * Метод представления страницы создания новой задачи
     *
     * @param model модель
     * @return newTask.html
     */
    @GetMapping("/tasks/new")
    public String newTask(Model model) {
        model.addAttribute("task", new Task());
        return "newTask";
    }

    /**
     * Метод представления страницы создания нового исполнителя
     *
     * @param model модель
     * @return newExecutor.html
     */
    @GetMapping("/executors/newExecutor")
    public String newExecutor(Model model) {
        model.addAttribute("executor", new Executor());
        return "newExecutor";
    }

    /**
     * Метод создания и добавления новой задачи
     *
     * @param task   задача
     * @param result результат
     * @return обновленная страница tasks.html
     */
    @PostMapping("/tasks")
    public String create(@ModelAttribute("task") @Valid Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "newTask";
        }
        taskService.createTask(task);
        return "redirect:tasks";
    }

    /**
     * Метод создания и добавления быстрой задачи NORMAL_EXECUTION
     *
     * @param task задача
     * @return обновленная страница tasks.html
     */
    @PostMapping("/tasks/ntype")
    public String createNTask(@ModelAttribute("task") Task task) {
        task = (Task) taskFactory.createTask(NORMAL_EXECUTION);
        taskService.createTask(task);
        return "redirect:/index/tasks";
    }

    /**
     * Метод создания и добавления быстрой задачи URGENT_IMPLEMENTATION
     *
     * @param task задача
     * @return обновленная страница tasks.html
     */
    @PostMapping("/tasks/utype")
    public String createUTask(@ModelAttribute("task") Task task) {
        task = (Task) taskFactory.createTask(URGENT_IMPLEMENTATION);
        taskService.createTask(task);
        return "redirect:/index/tasks";
    }

    /**
     * Метод создания и добавления исполнителя
     *
     * @param executor исполнитель
     * @param result   результат
     * @return обновленная страница executors.html
     */
    @PostMapping("/executors")
    public String createExecutors(@ModelAttribute("executor") @Valid Executor executor, BindingResult result) {
        if (result.hasErrors()) {
            return "newExecutor";
        }
        executorService.save(executor);
        return "redirect:executors";
    }

    /**
     * Метод представления задачи по id
     *
     * @param id    id задачи
     * @param model модель
     * @return страницу taskProfile.html
     */
    @GetMapping("/tasks/{id}")
    public String getTask(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.getTaskById(id));
        return "taskProfile";
    }

    /**
     * Метод представления исполнителя по id
     *
     * @param id    id исполнителя
     * @param model модель
     * @return страницу executorProfile.html
     */
    @GetMapping("/executors/{id}")
    public String getExecutor(@PathVariable Long id, Model model) {
        model.addAttribute("executor", executorService.findById(id));
        return "executorProfile";
    }

    /**
     * Метод обновления задачи
     *
     * @param id     id задачи
     * @param task   задача
     * @param result результат
     * @return обновленная страница tasks.html
     */
    @PostMapping("/tasks/update/{id}")
    private String updateTaskValid(@PathVariable("id") Long id, @ModelAttribute @Valid Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "updateTask";
        }
        task.setId(id);
        taskService.apdateTask(task);
        return "redirect:/index/tasks";
    }

    /**
     * Метод обновления задачи
     *
     * @param id    id задачи
     * @param model модель
     * @return страницу updateTask.html
     */
    @GetMapping("/tasks/updateTask/{id}")
    public String updateTask(@PathVariable(value = "id") Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "updateTask";
    }

    /**
     * Метод обновления исполнителя
     *
     * @param id       id исполнителя
     * @param executor исполнитель
     * @param result   результат
     * @return обновленную страницу executors.html
     */
    @PostMapping("/executors/updateExecutor/{id}")
    private String updateExecutorValid(@PathVariable("id") Long id,
                                       @ModelAttribute @Valid Executor executor, BindingResult result) {
        if (result.hasErrors()) {
            return "updateExecutor";
        }
        executor.setId(id);
        executorService.apdateExecutor(executor);
        return "redirect:/index/executors";
    }

    /**
     * Метод представления страницы обновления исполнителя
     *
     * @param id    id исполнителя
     * @param model модель
     * @return updateExecutor.html
     */
    @GetMapping("/executors/update/{id}")
    public String updateExecutor(@PathVariable(value = "id") Long id, Model model) {
        Executor executor = executorService.findById(id);
        model.addAttribute("executor", executor);
        return "updateExecutor";
    }

    /**
     * Метод добавления исполнителя задаче
     *
     * @param id       id исполнителя
     * @param executor исполнитель
     * @return обновленная страница taskProfile.html
     */
    @PostMapping("/tasks/executors/{id}")
    public String addExecutorInTask(@PathVariable Long id, @ModelAttribute("executor") @Valid Executor executor) {
        taskService.createExecutorForTask(id, executor);
        return "redirect:/index/taskProfile";
    }

    /**
     * Метод удаления задачи
     *
     * @param id id задачи
     * @return обновленную страницу tasks.html
     */
    @GetMapping("/tasks/delete/{id}")
    private String deleteTask(@PathVariable("id") Long id) {
        taskService.deleteById(id);
        return "redirect:/index/tasks";
    }

    /**
     * Метод удаления исполнителя
     *
     * @param id id исполнителя
     * @return обновленную страницу executors.html
     */
    @GetMapping("/executors/delete/{id}")
    private String deleteExecutor(@PathVariable("id") Long id) {
        executorService.deleteById(id);
        return "redirect:/index/executors";
    }
}
