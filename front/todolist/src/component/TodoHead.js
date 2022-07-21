import React from 'react';
import styled from 'styled-components';

const TodoHeadBlock = styled.div`
  h1 {
    margin: 0;
    font-size: 36px;
    color: #343a40;
  }
  .day {
    margin-top: 4px;
    color: #868e96;
    font-size: 21px;
  }
  padding-top: 48px;
  padding-left: 32px;
  padding-right: 32px;
  padding-bottom: 24px;
  border-bottom: 1px solid #e9ecef;
`;

const TasksLeft = styled.div`
  color: #20c997;
  font-size: 18px;
  margin-top: 40px;
  font-weight: bold;
`;

function TodoHead(props) {
  const now = new Date();
  const week = ['일', '월', '화', '수', '목', '금', '토'];


  return (
    <TodoHeadBlock>
      <h1>{now.getFullYear()}년 {now.getMonth()+1}월 {now.getDate()}일 </h1>
      <div className="day">{week[now.getDay()]}요일</div>
      <TasksLeft>할 일 {props.cnt}개 남음</TasksLeft>
    </TodoHeadBlock>
  );
}

export default TodoHead;
