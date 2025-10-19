#import "@preview/ilm:1.4.1": *

#set text(lang: "en")

#show: ilm.with(
  title: [Project Plan],
  author: "Yu Duo Zhang & Yixin Liu",
  date: datetime(year: 2025, month: 10, day: 17),
  abstract: "Vanier College\n2025 - 2026\n420-SF3-RE\nProgram Development in a Graphical Environment\nProf.: Nagat Drawel",
  bibliography: none,
  figure-index: (enabled: true),
  table-index: (enabled: true),
  listing-index: (enabled: true),
  paper-size: "us-letter",
)

= Project Summary
== Project Idea 
The _Interactive Wave Physics Simulator_ is an interactive application that simulate various types of waves. The purpose is to provide an educational tool for college students to understand better the concept of waves as it is also part of contents for our *Physics* course for _Computer Science and Mathematic_ students in Quebec. 

The application will allow users to modify parameters (frequency, amplitude, wavelength, etc.) in real time and visualize waves equations and their mathematical relationships. 

== Model-View-Controller (MVC)
=== Model (Logic & Data Structure)
The core of the application is built up with the wave equations and their parameters. Thus, the main data structure will include all parameters needed to define a wave, configured by the user through the user interface. 

The application would also guide the user to understand how to use the simulator through the _Help_ menu. Aside, user can also save the parameters defined in the simulator in a local file and load it back when needed. Graph exporting feature will also be implemented to allow user to save the wave graph as an image file.

The models should include:
- Data Fields
  - Including all parameters 
- Basic Constructor
  - Default & With Parameters
- Getters & Setters
  - For all parameters
- Methods for Calculations
  - For the Waves Equation

=== View (UI/UX Design)
The root of user interface will be implemented by using BorderPane Layout from JavaFx. This root will be divided into different sections:

- Top: Menu Bar (File, Exit, Help, etc.)
  - Each of the control item will be a drop-down menu that contains different options for user to choose from.
- Left: Parameter Panel (Frequency, Amplitude, Wavelength, Wave Type, etc.)
  - Each Parameter will be represented by a Label and a corresponding TextField and ComboBox to input the value and unit.
- Center: Visualization (Wave Graph)
  - This section will be implemented using ChartFX library to display the corresponding wave graph based on the user input.
- Right: Control Panel (Additional Controls)
  - Each control will first have a CheckBox to enable or disable the feature, followed by corresponding controls to adjust the settings.
- Bottom: Equation Display
  - The bottom section of the root layout will display the mathematical equation corresponding to current parameters defined by the user and to the graph. This will be shown by using a Label that updates dynamically as user changes the parameters.

=== Controller (Event Handling)
All components / controllers of the user interface in the view are attached to a corresponding event handler or listener. When user interacts with the interface, the corresponding event handler or listener will be involved to process the change, such as updating the graph and equation.

= Task Breakdown
== Model 
- Wave Equations
  - General Equation: $y(x,t) = A sin(k x - w t + delta)$
- Core Logic
  - Default Constructor
  - Variable Setters and Getters
  - Wave Calculation Methods
- Data Structures
  - All parameters will be saved as data members in the `WaveSimulator.java` class.
  - Parameters: 
      #table(
      columns: (1fr, 1fr),
      inset: 10pt,
      align: center,
      table.header(
        [Variable],[Meaning]
      ),
      [$A$],[Amplitude],
      [$k$],[Angular Wave Number],
      [$w$],[Angular Frequency],
      [$delta$],[Phase Difference]
    ) 

== View
- UI Layout (`WaveDisplay.java`)
  - Left Control Panel 
  - Center Graph Visualization
  - Right Control Panel
  - Top Menu Bar
  - Bottom Equation Display

- Styling (`style.css`)
  - CSS Implementation

== Controller
- Event Handlers
  - Menu Actions
  - Graph Update
  - Equation Display Update
  - Additional Controls
- User Input
  - Parameter Update

== Other
- File I/O
  - Parameter Saving and Loading
- Help Menu
  - User Guide with Pop-up Windows
- Graph Exporting
  - Image Exporting
- Testing
  - Unit Testing for Calculation Methods
  - Integration Testing for User Interface
- Documentation
  - Code Comments

= Responsibilities and Task Allocation
== `WaveSimulator.java` Class
#table(
  columns: (1fr, 1fr, 1fr),
  inset: 10pt,
  align: horizon,
  table.header(
    [Task],[Team Member],[Deadline]
  ),
  [Data Field],[Yixin Liu],[Week 8],
  [Constructor],[Yu Duo Zhang],[Week 8],
  [Getters and Setters],[Yu Duo Zhang],[Week 8],
  [Calculation Methods],[Yu Duo Zhang],[Week 10],
)
== `WaveDisplay.java` Class
#table(
  columns: (1fr, 1fr, 1fr),
  inset: 10pt,
  align: horizon,
  table.header(
    [Task],[Team Member],[Deadline]
  ),  
  [Root Layout],[Yixin Liu],[Week 8],
  [Left Control Panel],[Yixin Liu],[Week 10],
  [Right Control Panel],[Yu Duo Zhang],[Week 10],
  [Bottom Equation Display],[Yixin Liu],[Week 10],
  [Top Menu Bar],[Yixin Liu],[Week 10],
  [Center Graph Visualization],[Yu Duo Zhang],[Week 14],
  [CSS Styling],[Yu Duo Zhang],[Week 14],
)
== `Utils` Class
#table(
  columns: (1fr, 1fr, 1fr),
  inset: 10pt,
  align: horizon,
  table.header(
    [Task],[Team Member],[Deadline]
  ),  
  [File I/O],[Yixin Liu],[Week 14],
  [Graph Exporting],[Yu Duo Zhang],[Week 14],
)
== Event Handlers
#table(
  columns: (1fr, 1fr, 1fr),
  inset: 10pt,
  align: horizon,
  table.header(
    [Task],[Team Member],[Deadline]
  ),  
  [Menu Actions Except `Help`],[Yu Duo Zhang],[Week 10],
  [Help Menu],[Yu Duo Zhang],[Week 14],
  [Parameter Update],[Yu Duo Zhang],[Week 10],
  [Graph Update],[Yu Duo Zhang],[Week 14],
  [Equation Display Update],[Yixin Liu],[Week 10],
  [Additional Controls],[Yu Duo Zhang],[Week 14],
)
== Other Tasks
#table(
  columns: (1fr, 1fr, 1fr),
  inset: 10pt,
  align: horizon,
  table.header(
    [Task],[Team Member],[Deadline]
  ),
  [Unit Testing],[Yixin Liu],[Week 14],
  [Integration Testing],[Yixin Liu],[Week 14],
  [Code Comments],[Yu Duo Zhang & Yixin Liu],[Along the Development],
)

= Trello Board Integration
== Links (Trello Board & Git Repo)
Trello: https://trello.com/b/QBSKjbH8/project 

GitHub: https://github.com/Cloumy074/Vanier_420-SF3-RE_F25_Team-Project.git

== Trello Board Screenshot:
#image("TrelloBoard.png", alt: "Trello Board Screenshot")

= Risk Management
== Technical Issues
If one member meets hardware issues, they can use GitHub to continue their development on other hardwares. 

If one member meets coding issues, such as incomprehensible bugs, the member should inform the other member as soon as possible, so the team can find a solution together before the due date / deadline.

If the issue cannot be resolved in time / is a long term issue, the member can reallocate some of their task to the other member to not affect the project timeline.

== Scheduling Issues
If any scheduling conflict arises due to exams / personal scheduling, the team member should inform the other member in advance to adjust the workload distribution accordingly, so that the project timeline is not affected.

== Teamwork-Related Issues
If codes written by one member cannot be merged properly to the main branch due to conflicts, the member should inform the other member for an review and assistance to resolve the conflicts. 

If any communication related issue arises, the team member should inform the other member by other ways (in class, MIO, etc.) to ensure an effective update.
