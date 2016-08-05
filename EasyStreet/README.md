# Easy Street
TCSS 305 Programming Practicum, Spring 2016 <br>
Assignment 3 – Easy Street
<p>
Description: <br>
Starter code for the project includes a complete and correct graphical user interface (GUI) <br>
Write the parent AbstractVehicle and Vehicle sub classes <br>
Write the vehicle behavior <br>
<p>

Truck <br>
Trucks travel only on streets and through lights and crosswalks <br>
Randomly select to go straight, turn left, or turn right <br>
As a last resort, if none of these three directions is legal (all not streets, lights, or crosswalks), the truck turns around <br>
Trucks drive through all traffic lights without stopping <br>
Trucks stop for red crosswalk lights, but drive through yellow or green crosswalk lights without stopping <br>
A truck survives a collision with anything, living or dead <br>
<p>

Taxi <br>
Taxis can only travel on streets and through lights and crosswalks <br>
A taxi prefers to drive straight ahead if it can. If it cannot move straight ahead, it turns left if possible <br>
If it cannot turn left, it turns right if possible, as a last resort, it turns around <br>
Taxis stop for red lights <br>
If a traffic light is immediately ahead of the taxi and the light is red, the Taxi does not move until the light turns green <br>
Taxis do not turn to avoid lights. When the light turns green the taxi resumes its original direction <br>
Taxis drive through all crosswalk lights without stopping <br>
A taxi dies if it collides with a living truck, and stays dead for 10 moves. <br>
<p>

ATV <br>
ATVs can travel on any terrain except walls <br>
They randomly select to go straight, turn left, or turn right <br>
ATV’s never reverse direction (they never need to) <br>
ATV’s drive through all traffic lights and crosswalk lights without stopping <br>
An ATV dies if it collides with a living truck or taxi, and stays dead for 20 moves <br>
<p>

Bicycle <br>
Bicycles can travel on streets and through lights and crosswalk lights, but they prefer to travel on trails <br>
If the terrain in front of a bicycle is a trail, the bicycle always goes straight ahead in the direction it is facing <br> 
Trails are guaranteed to be straight (horizontal or vertical) lines that end at streets <br>
A bicycle will never start on a trail facing terrain it cannot traverse <br>
If a bicycle is not facing a trail, but there is a trail either to the left or to the right of the bicycle’s current direction, then the bicycle turns to face the trail and moves in that direction <br>
If there is no trail straight ahead, to the left, or to the right, the bicycle prefers to move straight ahead on a street (or light or crosswalk light) <br>
If it cannot move straight ahead, it turns left if possible, if it cannot turn left, it turns right if possible <br>
As a last resort, if none of these three directions is legal (all not streets or lights or crosswalk lights), the bicycle turns around <br>
Bicycles ignore green lights <br>
Bicycles stop for yellow and red lights
If a traffic light or crosswalk light is immediately ahead of the bicycle and the light is not green, the bicycle stays still and does not move unless a trail is to the left or right <br>
If a bicycle is facing a red or yellow light and there is a trail to the left or right, the bicycle will turn to face the trail <br>
A bicycle dies if it collides with a living truck, taxi, or ATV. It stays dead for 30 moves <br>
<p>

Human <br>
Humans move in a random direction (straight, left, right, or reverse), always on grass or crosswalks <br>
A human never reverses direction unless there is no other option <br>
If a human is next to a crosswalk it will always choose to turn to face in the direction of the crosswalk <br>
Humans do not travel through crosswalks when the crosswalk light is green <br>
If a human is facing a green crosswalk, it will wait until the light changes to yellow and then cross through the crosswalk <br>
A human will not turn to avoid a crosswalk <br>
Humans travel through crosswalks when the crosswalk light is yellow or red <br>
Humans ignore the color of traffic lights <br>
A human dies if it collides with any living vehicle except another human, and stays dead for 50 moves <br>
