Download and install  'Community' version of Intellij IDEA :
https://www.jetbrains.com/idea/download/#section=windows
Download and install JDK 10.0.2
(select "Accept License Agreement" and select right "Product" for your computer):
http://www.oracle.com/technetwork/java/javase/downloads/jdk10-downloads-4416644.html
Make sure you have Firefox version 62 installed
Open Intellij IDEA
Go to Maven and set the path: C:\\apache-maven-3.0.5
Close Intellij IDEA
Go to https://github.com/tomatik12?tab=repositories
Click on the name of project "qaauto-24.09.2018"
Click on "Clone or Download" and select "Clone with SSH",cloned URL clicking on the button to the right of the link
Open Intellij IDEA and select "Check out from Version Control", then select "Git"
Paste the Url on the first line
Select the direct you want to clone it and click "Clone" button
Click on the link "Import Gradle project"
Select "Use auto-import" and unselect "Create separate module per source set"
Click on the button on the left corner, select project
Click on your project, select "Open Module Settings", select "SDK's" and JDK that you installed on your system
Wait until the indexing is done and then you can start using button "Run"


 - pom.xml - fundamental unit of work in Maven.It is an XML file that contains information about the project
and configuration details used by Maven to build the project. It contains default values for most projects.
When executing a task or goal, Maven looks for the POM in the current directory.
It reads the POM, gets the needed configuration information, then executes the goal.

 - The purpose of the .gitignore file is to allow you to ignore files, such as editor backup files, build products or
local configuration overrides that you never want to commit into a repository. Without matching .gitignore rules,
these files will appear in the "untracked files" section of git status output
