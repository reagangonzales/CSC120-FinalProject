# CSC120-FinalProject

## Deliverables:
 - Your final codebase
 - Your revised annotated architecture diagram
 - Design justification (including a brief discussion of at least one alternative you considered)
 - A map of your game's layout (if applicable)
 - `cheatsheet.md`
 - Completed `rubric.md`
  
## Additional Reflection Questions
 - What was your **overall approach** to tackling this project?
 - What **new thing(s)** did you learn / figure out in completing this project?
 - Is there anything that you wish you had **implemented differently**?
 - If you had **unlimited time**, what additional features would you implement?
 - What was the most helpful **piece of feedback** you received while working on your project? Who gave it to you?
 - If you could go back in time and give your past self some **advice** about this project, what hints would you give?
 - _If you worked with a team:_ please comment on how your **team dynamics** influenced your experience working on this project.

My approach to this project was to break it down into manageable components and then build and test each part. I started by designing the game structure, including defining the key objects like ingredients, recipes, and locations. From there, I wrote and tested methods for navigation, item interactions, and baking.
I learned how to effectively implement and manage state in a text-based game, such as tracking inventory and game progress using variables. I also learned through an online source how to delay code execution to simulate my baking time.
If I could redo the project, I would spend more time planning the game flow before coding. I underestimated the complexity of managing player navigation/interactions, which led to some inefficiencies in my code. For example, I would implement a centralized system for handling item interactions instead of relying on multiple scattered switch case methods in the game loop.
If I had unlimited time, I would implement a more complex cookie baking system with more complex recipes. Maybe one where you had to bake with a specific number of items (4 eggs for example) or a certain measure/amount of butter, sugar, etc. I would also add more locations to explore and items to find.
The most helpful feedback I received was from friend who played through my game and helped me find most of my bugs. She had showed me a bug where I had no way to go back to a certain location, so she helped me fix that.
I would tell my past self to spend more time planning the relationships between game objects, and test methods thoroughly before integrating them into the game loop. I w ould avoid over-complicating mechanics in the initial build and rather focus on a solid foundation first.
