import React, { useState } from "react";
// import TextField from "@material-ui/core/TextField";
import { TextField } from '@material-ui/core';

const TodoItem = ({ item, onItemUpdate, onItemDelete }) => {
  const [isEditable, setIsEditable] = useState(false);
  const [todoItem, setTodoItem] = useState(item);

  return (
    <li className="task-item" id={"task-item-" + todoItem.id} data-testid="task-item">
      <TextField
        value={todoItem.content}
        multiline
        margin="dense"
        data-testid="task-item-content"
      />
    </li>
  );
};

export default TodoItem;
