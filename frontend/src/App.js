import React, { useState, Fragment, useEffect } from "react";
import TodoItem from "./TodoItem";
import { getTodos, addTodo, deleteTodo, updateTodo } from "./api/TodoApi";
import "./App.css";
import _ from "lodash";

const App = () => {
  const [inputValue, setInputValue] = useState("");
  const [list, setList] = useState(null);
  const [error, setError] = useState("");
  const [inputValue, setInputValue] = useState("");

  const handleLoadTasks = () => {
    getTodos()
      .then((response) => {
        setList(response);
      })
      .catch((error) => {
        setError("Unable to retrieve todo's");
      });
  };

 

  const handleDeleteTask = (id) =>
    deleteTodo(id).then(() => {
      setList(list.filter((item) => item.id !== id));
    });

  const handleUpdateTask = (task) => {
    if (task.content === "") return;

    updateTodo(task).then((response) => {
      setList(
        list.map((x) => (x.id === response.id ? { ...x, ...response } : x))
      );
    });
  };

  useEffect(() => {
    handleLoadTasks();
  }, []);

  if (list === null) {
    return <div>Tasks is loading ...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <Fragment>
      
      <ul data-testid="task-items" className="task-items">
        {list.map((item) => (
          <TodoItem
            key={item.id}
            item={item}
            index={item.id}
            onItemDelete={handleDeleteTask}
            onItemUpdate={handleUpdateTask}
          />
        ))}
      </ul>
    </Fragment>
  );
};

export default App;
