# A Star Pathfinding Algorithm 

The following is an A* Pathfinding Algorithm made in the Eclipse IDE. The algorithm works by using the heuristic of the path from a particular node to a goal. The "Robot" is denoted as the red square and the "Goal Location" is denoted by the Green Square. The blue denotes all the possible neighbors of each node the robot encounters. The purple is the shortest path the robot takes to get from the red square to the green square. This program took around 15 hours to complete, and it still has some bugs that need improvement. For example, the robot does not recognize edge squares, and thus throws an exception. Whenever the robot is in a part of the maze that each possible neighbor worsens the heuristic, an infinite loop is entered where the robot remains on the red square. This is the first time also utilizing JPanel, so some of the uncertainties are still needing to be clarified, such as pairing the paint() method with the main() method. These will be fixed in the near future. 

Full Demonstration 

https://www.youtube.com/watch?v=G4PGtvxhVWo

![A Demonstration of the Algorithm in Work](https://i.imgur.com/hwrdtmw.png)
![A Demonstration of the Algorithm in Work](https://i.imgur.com/SZY3VJs.png)
