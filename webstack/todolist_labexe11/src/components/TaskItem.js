import React, { useState } from 'react';

const TaskItem = ({ task, onDelete, onEdit }) => {
  const [isEditing, setIsEditing] = useState(false);
  const [editedTask, setEditedTask] = useState(task.name);

  const handleEdit = () => {
    setIsEditing(true);
  };

  const handleSave = () => {
    onEdit(task.id, editedTask);
    setIsEditing(false);
  };

  return (
    <li className="task-item">
      {isEditing ? (
        <>
          <input
            type="text"
            value={editedTask}
            onChange={(e) => setEditedTask(e.target.value)}
          />
          <button className="save-button" onClick={handleSave}>Save</button>
        </>
      ) : (
        <>
          <span>{task.name}</span>
          <button className="edit-button" onClick={handleEdit}>Edit</button>
          <button className="delete-button" onClick={() => onDelete(task.id)}>Delete</button>
        </>
      )}
    </li>
  );
};

export default TaskItem;
