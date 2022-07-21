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
