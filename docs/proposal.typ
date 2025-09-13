#import "@preview/bubble:0.2.2": *

#show: bubble.with(
  title: "Interactive Wave Physics Simulator",
  subtitle: "Team Project Proposal",
  author: "Yu Duo Zhang & Yixin Liu",
  affiliation: "Vanier College",
  date: [September 12#super[th], 2025],
  year: "2025 - 2026",
  class: "420-SF3-RE",
  other: ("Program Development in a Graphical Environment", "Prof.: Nagat Drawel"),
  main-color: "D1343E",
  logo: image("vanier-logo.png"),
)

= Task Description

Our project, the _Interactive Wave Physics Simulator_, is an application which aims to develop an interactive application that demonstrates various types of waves. The purpose is to provide an educational tool for students to understand better the concept of waves as it is also one of our program course for this semester.

The application will allow users to modify parameters (frequency, amplitude, wavelength, etc.) in real time and visualize waves equations and their mathematical relationships. Thus, the application is centered in the domain of physics simulations.

=== Functionality

- Simulate and display different types of waves (e.g. non-damped, damped, standing, traveling).
- Allow users to adjust wave parameters and instantly observe effects.
- Display equation and graphs relating to the simulation.
- Export visuals for reports or presentations.

= Interface Visualization

Main Window:
- Top: Menu bar (File, Export, Help)
- Left Panel: Controls for wave parameters (frequency / amplitude / speed / wavelength / etc.) and wave type.
- Center: Animated wave visualization (2D graph for displacement to position or time).
- Right Panel: Additional controls (second wave / damping / etc.)
- Bottom: Real-time equation display.

Visualization (Sample, might have differences with the final product):

#figure(
  image("interface_mockup.jpg", height: 205pt),
  caption: "(Hand-drawn Sample) Mockup of the main interface",
)

= Proposed Implementation Approach

Technical overview:
- Programming language: Java 22
- Framework: JavaFX22 for GUI / Visualization

Project Structure:
- `Main.java`: Main program to run / Application entry point.
- `WaveSimulator.java`: Core logic for wave calculations.
- `WaveDisplay.java`: JavaFX scene for drawing waves animation and parameter controls.
- `ExportManager.java`: Handles exporting current visualizations as image files.
- `Utils.java`: Utility functions / tools for calculations.
- *Note that this is subject to change as we progress.*

Libraries and Tools:
- JavaFX22 - GUI and visualization - https://openjfx.io/
- ChartFX - Chart Animation Library - https://github.com/fair-acc/chart-fx
- *Note that this is still mostly To Be Determined (TBD) and subject to change as we progress.*

= Trello

We will be using Trello to plan and track our tasks during the development process. Team members will be assigned to specific tasks and will update the status on Trello as members progress.

=== Trello Board Link

https://trello.com/b/QBSKjbH8/project

= Version Control (GitHub)

We will be using GitHub for version control and each team member will review the work before merging. The repository owner does not mean to be the one who does the most work.

=== GitHub Repository Link

https://github.com/Cloumy074/Vanier_420-SF3-RE_F25_Team-Project.git

= References

- #link("https://openstax.org/details/books/university-physics-volume-1")[OpenStax, University Physics Volume 1]
- #link(
    "https://store.macmillanlearning.com/ca/product/Physics-for-Scientists-and-Engineers-Extended-Version-2020-Media-Update/p/9781319628963",
  )[Macmillan Learning, Physics for Scientists and Engineers 6e]
- Document Template by #link("https://github.com/hzkonor")[hzkonor] on #link("https://typst.app/universe/package/bubble")[Typst]
