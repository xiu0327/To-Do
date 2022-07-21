package com.todolist.todolist.todo;

import com.todolist.todolist.todo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Service return -> controller
@RestController //RESTful API
@RequestMapping("/todos") // API URL 지정
public class TodoItemController {
    @Autowired // 서비스 인스턴스를 컨트롤러에 주입하기 위해
    private TodoItemService todoItemService;
    private final static String DEFAULT_ERR_MSG = "no errors";

    // 모든 todo를 가져온다
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody TodoItemListResponse getTodos(){
        String errors = DEFAULT_ERR_MSG;
        List<TodoItem> todoItems = null;

        try{
            todoItems = todoItemService.getTodos();
        } catch(final Exception e){
            errors = e.getMessage();
        }

        return TodoItemAdapter.todoItemListResponse(todoItems, errors);
    }

    // 기존 todo값을 가져온다
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody TodoItemResponse getTodoById(@PathVariable(value="id") final Long id){
        String errors = DEFAULT_ERR_MSG;
        TodoItem todoItem = null;

        try{
            todoItem = todoItemService.getTodoById(id);
        } catch(final Exception e){
            errors = e.getMessage();
        }

        return TodoItemAdapter.todoItemResponse(todoItem, errors);
    }

    // 새로운 todo를 생성한다
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody TodoItemResponse createTodo(@RequestBody final TodoItemRequest todoItemRequest){
        String errors = DEFAULT_ERR_MSG;
        TodoItem createTodoItem = TodoItemAdapter.todoItem(todoItemRequest);

        try {
            createTodoItem = todoItemService.createTodo(createTodoItem);
        } catch (final Exception e){
            errors = e.getMessage();
        }

        return TodoItemAdapter.todoItemResponse(createTodoItem, errors);
    }

    // 기존 todo를 수정한다
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public @ResponseBody TodoItemResponse updateTodo(@PathVariable(value = "id") final Long id, final TodoItemRequest todoItemRequest){
        String errors = DEFAULT_ERR_MSG;
        TodoItem updateTodoitem = TodoItemAdapter.todoItem(todoItemRequest);

        try{
            updateTodoitem = todoItemService.updateTodo(id, updateTodoitem);
        } catch(final Exception e){
            errors = e.getMessage();
        }

        return TodoItemAdapter.todoItemResponse(updateTodoitem, errors);
    }

    @RequestMapping(method = RequestMethod.DELETE, value={"/{id}"})
    public @ResponseBody TodoItemResponse deleteTodo(@PathVariable(value = "id") final Long id){
        String erros = DEFAULT_ERR_MSG;

        try{
            todoItemService.deleteTodo(id);
        } catch (final Exception e){
            erros = e.getMessage();
        }

        return TodoItemAdapter.todoItemResponse(null, erros);
    }


}
