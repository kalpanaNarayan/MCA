import React, { useState } from 'react';

const TaskForm = ({ onSubmit, initialTask }) => {
  const [task, setTask] = useState(initialTask || '');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (task.trim() === '') return; // Add validation here
    onSubmit(task);
    setTask('');
  };

  return (
    <form className="task-form" onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Add a new task"
        value={task}
        onChange={(e) => setTask(e.target.value)}
      />
      <button type="submit">{initialTask ? 'Edit Task' : 'Add Task'}</button>
    </form>
  );
};

export default TaskForm;
