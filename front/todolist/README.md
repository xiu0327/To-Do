![ezgif.com-gif-maker.gif](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/4858e35f-6715-48eb-8294-890bf9e1dd2e/ezgif.com-gif-maker.gif)

1. 컴포넌트 분리 
    
    
    ![스크린샷 2022-07-21 오후 9.27.15.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e8c33bd9-89b4-44d0-861c-5f6ef4b8c777/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2022-07-21_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_9.27.15.png)
    
    - TodoCreate : To Do를 생성하는 컴포넌트
    - TodoHead : 현재 날짜와 남은 To Do 개수를 알려주는 컴포넌트
    - TodoItem : To Do를 이루는 컴포넌트
    - TodoTemplate : To Do 창 뒷 배경 컴포넌트

1. src/TodoCreate.js
    
    
    - style
    
    ```jsx
    const CircleButton = styled.button`
      background: #38d9a9;
      &:hover {
        background: #63e6be;
      }
      &:active {
        background: #20c997;
      }
    
      z-index: 5;
      cursor: pointer;
      width: 80px;
      height: 80px;
      display: block;
      align-items: center;
      justify-content: center;
      font-size: 60px;
      position: absolute;
      left: 50%;
      bottom: 0px;
      transform: translate(-50%, 50%);
      color: white;
      border-radius: 50%;
      border: none;
      outline: none;
      display: flex;
      align-items: center;
      justify-content: center;
    
      transition: 0.125s all ease-in;
      ${props =>
        props.open &&
        css`
          background: #ff6b6b;
          &:hover {
            background: #ff8787;
          }
          &:active {
            background: #fa5252;
          }
          transform: translate(-50%, 50%) rotate(45deg);
        `}
    `;
    
    const InsertFormPositioner = styled.div`
      width: 100%;
      bottom: 0;
      left: 0;
      position: absolute;
    `;
    
    const InsertForm = styled.form`
      background: #f8f9fa;
      padding-left: 32px;
      padding-top: 32px;
      padding-right: 32px;
      padding-bottom: 72px;
    
      border-bottom-left-radius: 16px;
      border-bottom-right-radius: 16px;
      border-top: 1px solid #e9ecef;
    `;
    
    const Input = styled.input`
      padding: 12px;
      border-radius: 4px;
      border: 1px solid #dee2e6;
      width: 100%;
      outline: none;
      font-size: 18px;
      box-sizing: border-box;
    `;
    ```
    
    이왕 만드는 김에 제대로 된 디자인을 사용하고 싶어서 ‘벨로퍼트와 함께하는 모던 리액트'의 멋진 투두리스트 만들기를 참고했다. 
    
    우선 styled-components를 설치했다.
    
    ```jsx
    npm i react-icons styled-components
    ```
    
    이 라이브러리를 사용하기 전에는 웹페이지를 HTML, CSS, JavaScript 3개로 분리하여 코딩했다. 하지만 그렇게 했을 때 style 정리가 어려웠던 경험이 있다. 그래서 여러 개의 컴포넌트로 분리하고 각 컴포넌트에 HTML, CSS, JavaScript를 적용하여 코드를 작성하려한다. 
    
    <기본 문법>
    
    styled-components 패키지에서 styled 함수를 임포트한다.
    
    ```jsx
    import styled, { css } from 'styled-components';
    ```
    
    styled는 Styled Components의 근간이 되는 가장 중요한 라이브러리이다. HTMl element나 React 컴포넌트에 원하는 스타일을 적용하기 위해서다. 
    
    HTML element를 스타일링 할 때는 모든 HTML 태그에 대해서 이미 속성이 정의되어 있기 때문에 해당 태그명의 속성에 접근한다.
    
    ```jsx
    import styled from "styled-components";
    
    styled.button`
    	//<button> HTML element에 대한 스타일 정의
    `;
    ```
    
    혹은 아래와 같이 고정 스타일의 컴포넌트로 스타일링할 수 있다.
    
    ```jsx
    import React from "react";
    import styled from "styled-components";
    
    const StyledButton = styled.button`
      padding: 0.375rem 0.75rem;
      border-radius: 0.25rem;
      font-size: 1rem;
      line-height: 1.5;
      border: 1px solid lightgray;
      color: gray;
      backgroud: white;
    `;
    
    function Button({ children }) {
      return <StyledButton>{children}</StyledButton>;
    }
    ```
    
    사용자의 행위에 따라 스타일을 변화시킬 수도 있다.
    
    ```jsx
    import React from "react";
    import styled from "styled-components";
    
    const StyledButton = styled.button`
      padding: 0.375rem 0.75rem;
      border-radius: 0.25rem;
      font-size: 1rem;
      line-height: 1.5;
      border: 1px solid lightgray;
    
      color: ${(props) => props.color || "gray"};
      background: ${(props) => props.background || "white"};
    `;
    
    function Button({ children, color, background }) {
      return (
        <StyledButton color={color} background={background} Î>
          {children}
        </StyledButton>
      );
    }
    ```
    
    prop에 따라 바꾸고 싶은 CSS 속성이 위와 같이 하나가 아니라 여러 개 일 수 있다. 이럴 경우에는 Style Components에서 제공하는 css 함수를 사용해서 여러 개의 CSS 속성을 묶어서 정의할 수 있다.
    
    예를 들어 primary prop이 넘어온 경우, 글자색을 흰색, 배경색과 경계색은 남색으로 변경하고 싶다면 다음과 같이 예제코드를 수정할 수 있다. 
    
    ```jsx
    import React from "react";
    import styled, { css } from "styled-components";
    
    const StyledButton = styled.button`
      padding: 0.375rem 0.75rem;
      border-radius: 0.25rem;
      font-size: 1rem;
      line-height: 1.5;
      border: 1px solid lightgray;
    
      ${(props) =>
        props.primary &&
        css`
          color: white;
          background: navy;
          border-color: navy;
        `}
    `;
    
    function Button({ children, ...props }) {
      return <StyledButton {...props}>{children}</StyledButton>;
    }
    ```
    
    자바스크립트의 && 연산자를 사용해서, primary prop이 존재하는 경우에만 css로 정의된 스타일을 적용한다. 
    
    또한 넘겨야할 prop 값이 많아질 경우, `…props`구문을 사용해서 `children` 외에 모든 prop을 간편하게 전달할 수 있다. 
    
- function
    
    ```jsx
    function TodoCreate(props) {
        const [open, setOpen] = useState(false);
        const onToggle = () => setOpen(!open);
    
        return (
          <>
            {open && (
              <InsertFormPositioner>
                <InsertForm onSubmit={props.handleSubmit}>
                  <Input
                    autoFocus
                    required={true}
                    value={props.input}
                    onChange={props.handleChange}
                    placeholder="할 일을 입력 후, Enter 를 누르세요"
                  />
                </InsertForm>
              </InsertFormPositioner>
            )}
            <CircleButton onClick={onToggle} open={open}>
              <MdAdd />
            </CircleButton>
          </>
        );
      }
    ```
    

1. src/TodoHead.js
    
    ```jsx
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
    ```
    

1. src/TodoItem.js
    
    ```jsx
    import React from 'react';
    import styled, { css } from 'styled-components';
    import { MdDone, MdDelete } from 'react-icons/md';
    
    const Remove = styled.div`
      display: flex;
      align-items: center;
      justify-content: center;
      color: #dee2e6;
      font-size: 24px;
      cursor: pointer;
      &:hover {
        color: #ff6b6b;
      }
      display: none;
    `;
    
    const TodoItemBlock = styled.div`
      display: flex;
      align-items: center;
      padding-top: 12px;
      padding-bottom: 12px;
      &:hover {
        ${Remove} {
          display: initial;
        }
      }
    `;
    
    const CheckCircle = styled.div`
      width: 32px;
      height: 32px;
      border-radius: 16px;
      border: 1px solid #ced4da;
      font-size: 24px;
      display: flex;
      align-items: center;
      justify-content: center;
      margin-right: 20px;
      cursor: pointer;
      ${props =>
        props.done &&
        css`
          border: 1px solid #38d9a9;
          color: #38d9a9;
        `}
    `;
    
    const Text = styled.div`
      flex: 1;
      font-size: 21px;
      color: #495057;
      ${props =>
        props.done &&
        css`
          color: #ced4da;
        `}
    `;
    
    function TodoItem(props) {
      
        return (
          <TodoItemBlock>
            <CheckCircle done={props.done} onClick={()=>props.handleUpdate(props.id)}>
              {props.done && <MdDone />}
            </CheckCircle>
            <Text done={props.done}>{props.title}</Text>
            <Remove onClick={()=> props.handleRemove(props.id)}>
              <MdDelete />
            </Remove>
          </TodoItemBlock>
        );
      }
      
      export default React.memo(TodoItem);
    ```
    
2. src/TodoTemplate.js
    
    ```jsx
    import React from 'react';
    import styled from 'styled-components';
    
    const TodoTemplateBlock = styled.div`
      width: 512px;
      height: 768px;
    
      position: relative;
      background: white;
      border-radius: 16px;
      box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.04);
      margin: 0 auto;
      margin-top: 96px;
      margin-bottom: 32px;
      display: flex;
      flex-direction: column;
      position: relative;
    `;
    
    function TodoTemplate({ children }) {
      return <TodoTemplateBlock>{children}</TodoTemplateBlock>;
    }
    
    export default TodoTemplate;
    ```
    
3. App.js
    
    ```jsx
    import './App.css';
    import React ,{ useState, useEffect } from "react";
    import styled from 'styled-components';
    import { createGlobalStyle } from 'styled-components';
    import TodoTemplate from './component/TodoTemplate';
    import TodoHead from './component/TodoHead';
    import TodoCreate from './component/TodoCreate';
    import axios from "axios";
    import TodoItem from './component/TodoItem';
    
    const GlobalStyle = createGlobalStyle`
      body {
        background: #e9ecef
      }
    `;
    
    const TodoListBlock = styled.div`
      flex: 1;
      padding: 20px 32px;
      padding-bottom: 48px;
      overflow-y: auto;
    `;
    
    function App() {
    
      const baseUrl = "http://localhost:8080";
    
      const [input, setInput] = useState("");
      const [todos, setTodos] = useState([]);
    
      useEffect(()=>{
        getTodos();
      }, []); 
    
      async function getTodos(){
        await axios
          .get(baseUrl + "/todos")
          .then((response)=>{
            console.log(response.data);
            setTodos(response.data.data);
          })
          .catch((error)=>{
            console.error(error);
          })
    
      }
    
      function insertTodo(e){
        e.preventDefault();
        const insertTodo = async () => {
          await axios
            .post(baseUrl + "/todos", {
              title:input,
              done:false
            })
            .then((response)=>{
              console.log(response.data);
              setInput("");
              getTodos();
            })
            .catch((error) => {
              console.error(error);
            })
        }
        insertTodo();
        console.log("할일이 추가됨");
    
      }
    
      function updateTodo(id){
        const updateTodo = async () => {
          await axios
            .put(baseUrl + "/todos/" + id, {})
            .then((response)=>{
              setTodos(
                todos.map((todo)=>
                  todo.id === id ? { ...todo, done: !todo.done} : todo
                )
              )
            })
            .catch((error) => {
              console.error(error);
            })
        }
        updateTodo();
      }
    
      function deleteTodo(id){
        const deleteTodo = async () => {
          await axios
            .delete(baseUrl + "/todos/" + id, {})
            .then((response)=>{
              setTodos(
                todos.filter((todo) => todo.id !== id)
              )
            })
            .catch((error) => {
              console.error(error);
            })
        }
        deleteTodo();
      }
    
      function changeText(e){
        e.preventDefault();
        setInput(e.target.value);
      }
      return (
        <>
          <GlobalStyle />
          <TodoTemplate>
            <TodoHead cnt={todos.length}/>
            <TodoCreate handleSubmit={insertTodo} input={input} handleChange={changeText}/>
            <TodoListBlock>
              {
                todos
                ? todos.map((todo) => {
                  return(
                    <TodoItem key={todo.id} id={todo.id} done={todo.done} title={todo.title} handleUpdate={updateTodo} handleRemove={deleteTodo}/>
                  )
                }) : null
              }
            </TodoListBlock>
          </TodoTemplate>
        </>
      );
    }
    
    export default App;
    ```
    

ToDo 앱을 만들면서 각 컴포넌트를 분리하여 props 값을 넘기는 법과 RESTfull API와 연동하는 법을 제대로 배운 것 같다. 다만 지금은 공부하는 단계라 App.js에 GET, POST, PUT, DELETE 함수를 모두다 넣어뒀으나, 나중에 코드가 복잡해지면 함수를 분리해야할 것 같다. 

컴포넌트 별로 함수를 작성해봤는데, 새로운 Todo를 작성했을 때.. 완료 버튼을 누르자마자 새로운 Todo가 리스트에 업데이트 되어야 하는데 성공하지 못했다. 그래서 App.js에 insertTodo를 작성했다. 입력값을 DB에 저장한 이후 입력창을 “”로 리셋하고 getTodos()를 호출하여 TodoList를 다시 불러왔다. 

한 컴포넌트에 HTML, CSS, JS가 포함될 수 있도록 분리하는 공부를 더 해야할 것 같다.

참고

[3장. 멋진 투두리스트 만들기](https://react.vlpt.us/mashup-todolist/)

[[React / Spring Boot] ToDoList #4(fin) - 리액트 컴포넌트 분리 / 마무리](https://youtu.be/JoCB5BDxZTE)

[[React] Styled Components 사용법](https://www.daleseo.com/react-styled-components/)
