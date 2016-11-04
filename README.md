# Fitts's law experiment application

A Java application to measure the effects predicted by Fitts's law. 

## Building & running

1. Import the project in Eclipse
2. Create a user library called `SWTLib`, and add your platform's SWT JAR file as an external JAR file (you can find that JAR file in the `plugin` folder of your Eclipse installation)). 
3. Run the project as a Java application.

## Usage

1. Adapt the size of the target (`T`) using the spinner 
2. Note down the target size
3. Place the target and the movable object (`M`) at the desired distance from each another
4. Note down the distance
5. Hit the `Start` button
6. Drag the movable object towards the target - the timer starts as soon as the movable object's position has changed (note: the target cannot be moved at this point any longer)
7. When the movable object's boundary collides with the target's boundary, the timer stops
8. Note down the elapsed time
9. Apply the Fitts’s law formula using the noted down target size, distance and elapsed time
10. Repeat for different target sizes and distances

## Known issues

* The timer does not accurately measure actual time - the actual elapsed time is generally significantly longer than what is displayed. While this might be annoying, it does not obstruct the goal of demonstrating the effect predicted by Fitts's law.
