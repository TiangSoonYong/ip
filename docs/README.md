# Doraemon User Guide
![Doraemon.jpeg](../data/Doraemon.jpeg)

**Doraemon** is a **Personal Assistant Chatbot** that helps you
keep track of various tasks, specifically to-do, deadline and event


### Upon Start-Up
Doraemon will greet you and automatically load your saved file, 
no worries if you do not have a saved file\
Example:
``` 
   __________________________________________________
   Hello! I'm Doraemon!
   What can I do for you?
   Check out the User Guide on https://tiangsoonyong.github.io/ip/
   Type [help] for all commands
   Note to User:
   - The current version follows STRICTLY to Date-Time Format: yyyy-MM-dd HHmm
    Example: 2025-03-07 0240 translates to 07 Mar 2025, 02:40am
    Future versions will accept flexible formatting
   - Files are not saved automatically! Use the save command frequently!
   __________________________________________________
   Loading Tasks.txt
   Tasks.txt read successfully
   Here are the tasks in your list:
   1. 	[T][ ] finish iP
   2. 	[D][X] do week 5 task (OVERDUE) (by: 14 Feb 2025, 04:00pm)
   3. 	[E][ ] exam week (from: 26 Apr 2025, 08:00am to: 10 May 2025, 11:59pm)
   __________________________________________________
```

## Features
> ### Note to User
>- Command keywords are **not case-sensitive**  
>- The current version follows **STRICTLY** to Date-Time Format: `yyyy-MM-dd HHmm`  
>_Optional_ to specify the seconds  
>Example: `2025-03-07 0240` translates to `07 Mar 2025, 02:40am`  
>Future versions will accept flexible formatting
>- Files are **not saved automatically**! Use the `save` command frequently!

### Viewing help: `help`
Displays all commands with examples and formats\
Format: `help`
``` 
help
    __________________________________________________
    Command entered: help
    ...
    [help]: Displays this current message, showing all commands with examples and format
    Example: help
    
    [bye]: Exits the programme
    Example: bye
    
    __________________________________________________
``` 

### Adding To-Do task: `todo`  
Adds a task that you need to do into the list\
Parameters: `todo description`\
Example:
``` 
todo finish iP
    __________________________________________________
    Command entered: todo finish iP
    Got it. I've added this task:
    [T][ ] finish iP
    Now you have 1 tasks in the list.
    __________________________________________________
```

### Adding Deadline task: `deadline`
Adds a task that needs to be finished by a deadline into the list\
Parameters: `deadline description /by end date`\
Example:
```
deadline do week 5 task /by 2025-02-14 1600
   __________________________________________________
   Command entered: deadline do week 5 task /by 2025-02-14 1600
   Got it. I've added this task:
     [D][ ] do week 5 task (OVERDUE) (by: 14 Feb 2025, 04:00pm)
   Now you have 2 tasks in the list.
   __________________________________________________
```
### Adding Event task: `event`
Adds a task that have a start and end date into the list\
Accepted Parameters: 
- `event description /from start date /to end date` or 
- `event description /to end date /from start date`

Example:
 ```
event exam week /from 2025-04-26 0800 /to 2025-05-10 2359
   __________________________________________________
   Command entered: event exam week /from 2025-04-26 0800 /to 2025-05-10 2359
   Got it. I've added this task:
     [E][ ] exam week (from: 26 Apr 2025, 08:00am to: 10 May 2025, 11:59pm)
   Now you have 3 tasks in the list.
   __________________________________________________
 ```

### Listing all tasks: `list`
Display every task with task number, its type, whether it is done and overdue\
Format: `[TaskType][isDone] Task_Description (isOverdue?)`\
Example:
```
list
    __________________________________________________
    Command entered: list
    Here are the tasks in your list:
    1. 	[T][ ] finish iP
    2. 	[D][X] do week 5 task (OVERDUE) (by: 14 Feb 2025, 4:00pm)
    3. 	[E][ ] exam week (from: 26 Apr 2025, 08:00am to: 10 May 2025, 11:59pm)
    __________________________________________________

```

### Listing upcoming tasks: `upcoming`
Display sorted list of tasks with date and time\
Example:
```
upcoming
   __________________________________________________
   Command entered: upcoming
   Here are the upcoming tasks in your list:
   2. 	[D][ ] do week 5 task (OVERDUE) (by: 14 Feb 2025, 04:00pm)
   3. 	[E][ ] exam week (from: 26 Apr 2025, 08:00am to: 10 May 2025, 11:59pm)
   __________________________________________________
```

### Marking task as done: `mark`
Mark specified task as done\
Parameters: `mark task-number`\
Example:
```
mark 2
   __________________________________________________
   Command entered: mark 2
   Nice! I've marked this task as done:
      [D][X] do week 5 task (OVERDUE) (by: 14 Feb 2025, 04:00pm)
   __________________________________________________
```

### Unmarking task as not done: `unmark`
Unmark specified task as not done\
Parameters: `unmark task-number`\
Example:
```
unmark 1
   __________________________________________________
   Command entered: unmark 1
   Nice! I've marked this task as not done yet:
      [T][ ] finish iP
   __________________________________________________
```

### Deleting task: `delete`
Delete specified task\
Parameters: `delete task-number`\
Example:
```
delete 2
   __________________________________________________
   Command entered: delete 2
   Noted. I've removed this task
     [D][X] do week 5 task (OVERDUE) (by: 14 Feb 2025, 04:00pm)
   Now you have 2 tasks in the list.
   __________________________________________________
```
 
### Finding task: `find`
Find tasks that matches specified keyword\
Parameters: `find keyword`\
Example:
```
find finish
    __________________________________________________
    Command entered: find finish
    Here are the matching tasks in your list
    1. [T][ ] finish iP
    __________________________________________________
```
 
### Clearing all tasks: `clear`
Clear all tasks in the list\
Example: `clear`
```
clear
    __________________________________________________
    Command entered: clear
    All tasks has been cleared
    __________________________________________________

```

### Saving the data: `save`
Save all tasks into a text file\
Example: `save`
```
save
    __________________________________________________
    Command entered: save
    Tasks.txt saved successfully
    full path: {YOUR_FULL_PATH\data\tasks.txt}
    __________________________________________________
```
 
### Exiting the program: `bye`
Exits the programme\
Example: `bye`
```
bye
    __________________________________________________
    Command entered: bye
    Bye. Hope to see you again soon!
    __________________________________________________
```

## Known Issues
1. (**RESOLVED**) When printing with Date-Time objects, **?** appears\
   Used custom `DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")` instead of defined method in package
```
    Command entered: event project meeting /from 2025-03-03t14:00 /to 2025-03-03t16:00
    Got it. I've added this task:
    [E][ ] project meeting (from: 3 Mar 2025, 2:00:00?pm to: 3 Mar 2025, 4:00:00?pm)
    Now you have 8 tasks in the list.
```