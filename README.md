# My Personal Project

## Lifestyle Tracker

The *Lifestyle Tracker* will be designed to allow users to track their lifestyle habits
in an efficient and convenient manner so users can better achieve their lifestyle goals.

The *Lifestyle Tracker* will:
- Store/add custom workouts and workout templates
- Supply a database of preset workout routines for users to choose from
- Allow users to classify their favourite workouts
- Allow users to view their workouts from a weekly perspective

This application will be used by anyone trying to track or better their exercise habits.
This includes exercise professionals, athletes, recreational gym-goers and many more types of people!

This project is of interest to me because I am heavily involved in the fitness community and have 
a strong background in the field of kinesiology. It is my intentions to expand on this application
after the course of the term by making it more personalized to exercise professionals. For example,
 it would be ideal to have a database of workouts available to each client, accessible by both the client
 and trainer to enhance communication between the two individuals. Furthermore, such a tool would be useful 
 in expanding my own personal training ventures.
 
 ## User Stories
 
 ### Phase 1
 
 - As a user, I want to be able to classify workouts as my favourite workouts
 - As a user, I want to be able to view the exercises within the workout I created
 - As a user, I want to be able to add exercises to a workout I am creating
 - As a user, I want to be able to modify sets and repetitions assigned to each exercise within a workout
 - As a user, I want to be able to delete exercises from a workout
 - As a user, I want to be able to add notes to exercises
 
 ### Phase 2
 
 - As a user, I want to be able to save my workouts and associated exercises to file
 - As a user, I want to be able to load my workouts and associated exercises from file when the program starts
 
 ### Instructions for Grader
 
- You can generate the first required event by clicking the "Add Workout" button. You will be asked to name and describe the workout. Please click add workout when you are done. You will then see the name of the workout you added on the left of the application.
- You can generate the second required event by double clicking the workout you just made. That will take you to the workouts list of exercises (it will be empty). You can add an exercise by clicking the "Add Exercise" button. The application will ask you to enter the name of the exercise, its sets and its reps. When you are done, click "Add Exercise". You will be redirected to the list of exercises and you should be able to see the exercise you added on the left of the application with its sets and reps displayed.
- You can locate my visual component on the first frame seen when you open the program. It is a dumbbell icon in the bottom right corner of the application. If you are still on the exercise list screen, you can press the back button to get back to the list of workouts (i.e., where you will find the image).
- You can save the state of my application by clicking the save button on the "List of workouts" frame.
- You can reload the state of my application by clicking the load button on the "List of workouts" frame. Please note that it will delete all the current data you have if you have yet to save it. If you click the load button without saving anything, a popup box will appear stating that the loading operation was unsuccessful.
 
Please read below for more detailed instructions on how to use the app that were not included in the template provided to us on edX. The following instructions will guide you through each of the user stories:
- To start the program, please run the Main class from the UI package
- After running, an empty workout list will pop up. If you have a previous workout saved, you can load it; Otherwise, the load button will load an empty workout list (i.e., no content).
- Please note the dumbbell picture I have in the lower right corner for the audiovisual component
- To add a workout, please click the add workout button. You will be asked to name and describe the workout. Please click add workout when you are done.
- Now you should be able to see the name of the workout you created (this is the adding X to Y component).
- If you press the toggle fav button after clicking a workout, it will star or un-star the selected workout to indicate whether that workout is a favourite.
- At any time, you can save the current state of the workout list by clicking on the save button.
- If you click once on the workout, the description of the workout will pop up in the right box.
- If you double click on the workout, you will be taken into the it (i.e., the workout's list of exercises).
- From there, you can click 'add exercise' to add an exercise to the workout. You will be asked the name of the exercise as well as its quantity of sets and reps.
- The set and rep boxes will only take numbers and the button, delete will clear the whole text box. When you are done, click add exercise.
- You should now see the name of your exercise followed by its sets and reps indicated my "S" and "R", respectively.
- If you double click the exercise, you can modify its attributes.
- If you single click the exercise, you can remove it by clicking the remove exercise button and you can add a note to it by clicking the modify note button.
- After clicking the modify note button, you will be shown a list of your current notes (if you have any) and you will have the option of typing your own note at the bottom of the window.
- If you click on any current notes you have, you can delete it by clicking the remove note button.
- After typing out your own note, click add note and you will be directed to your list of exercises.
- Now after adding your note, if you click on the exercise associated with that note, you should see the note pop up on the right side of the window.
- That describes all the functionality of the program. You can navigate back to the opening frame by using the back button.

### Phase 4: Task 2

I have already made appropriate use of the map interface in my code. One example can be found in the *WorkoutListGUI* class.
There is a private Map field called *populatedList* that has Integers for keys and Workouts for values. This Map interface is 
used in the method called *populateList* which has the responsibility of clearing the JList associated with the *WorkoutListGUI* form 
and repopulating it. The *populatedList* map is used to bind the workouts associated with the workout list to the JList. The key 
value serves as an index for each workout in the list (i.e., it stores the position of the workout in the JList). This was used to 
help implement the item selection functionality in the JList. The method *createListSelectFunctionality* finds the last selected 
object and returns that objects index within the JList. I then take that index and find its associated workout using the map. 
This allows the program to perform specific functions with the last selected object in the JList.

