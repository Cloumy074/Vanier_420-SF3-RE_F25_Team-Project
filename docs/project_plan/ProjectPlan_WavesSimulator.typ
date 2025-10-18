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
The _Interactive Wave Physics Simulator_ is an interactive application that demonstrates various types of waves. The purpose is to provide an educational tool for college students to understand better the concept of waves as it is also one of the *Physics* course for _Computer Science and Mathematic_ students in Quebec. 

The application will allow users to modify parameters (frequency, amplitude, wavelength, etc.) in real time and visualize waves equations and their mathematical relationships. 

== Model-View-Controller (MVC)
=== Model (Logic & Data Structure)
The core of the application is built up with the wave equations and their parameters. Thus, the main data structure will include all parameters needed to define a wave, configured by the user through the user interface. 

The application would also guide the user to understand how to use the simulator through the _Help_ menu. Aside, user can also save the parameters defined in the simulator in a local file and load it back when needed. Graph exporting feature will also be implemented to allow user to save the wave graph as an image file.

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
All controls in the user interface will have event handlers or listeners attached. When user interacts with any control, the corresponding controller method will be involved to process the change, update the graph and equation.

= Task Breakdown
== Model 
- Wave Equations
  - General Equation: $ y(x,t) = A sin(k x - w t +  delta)  $
- Core Logic
  - Default Constructor
  - Parameter Setters and Getters
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
- Graph Exporting
  - Image Exporting
- Help Menu
  - User Guide with Pop-up Windows
- Testing
  - Unit Testing for Calculation Methods
  - Integration Testing for User Interface
- Documentation
  - Code Comments
  - User Manual
  - Guide Documentation

= Responsibilities and Task Allocation
#table(
  columns: (1fr, 1fr, 1fr),
  inset: 10pt,
  align: horizon,
  table.header(
    [Module / Task],[Team Member],[Deadline]
  ),
)

= Trello Board Integration

= Risk Management
== Technical Issues

== Scheduling Issues

== Teamwork-Related Issues

= Links (Trello Board & Git Repo)
Trello: https://trello.com/b/QBSKjbH8/project 
