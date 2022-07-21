package com.todolist.todolist.todo.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name="todos")
@Getter @Setter         //getter, setter 메소드
@NoArgsConstructor      //인자 없는 기본 생성자
@AllArgsConstructor     //모든 인자 가지는 생성자
@Builder
public class TodoItem {
    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="done", nullable = false)
    private boolean done;
}
