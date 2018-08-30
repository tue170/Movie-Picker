# MoviePicker <img src="https://user-images.githubusercontent.com/40246010/43700408-16a8cf94-9918-11e8-88d3-94672b1238dc.png" width="30" height="30">
Team Exceptionally Mediocre presents a desktop application that will help people find a movie to watch.
#### Table of Contents


1. [ Getting Started. ](#start)
2. [ Usage Instructions & Running the Test. ](#usage)</br>
    </t> a. [ Running the test from Customize Movie List. ](#csv)</br>
    </t> b. [ Running the test from DataBase. ](#db)</br>
    </t> c. [ Navigation Tool. ](#nav)
3. [Breakdown of code (MVC).](#mvc)
4. [ Notes. ](#note)
5. [ Author. ](#contribute)

<a name="start"></a>
## Getting Started :computer:

### Prerequisities
Required to have Eclipse IDE install.</br>
Recommended to run project on an [Eclipse IDE](http://www.cs.utsa.edu/~cs3443/notes/eclipse-hints.html) with Java 8.0 or higher. </br>
Recommended to use and be familar with [EGit](http://www.cs.utsa.edu/~cs3443/git/egit-hints.html) .</br>
Recommended to have installed [Scenebuilder 2.0](http://cs.utsa.edu/~cs3443/javafx/index.html) .

### Cloning the MoviePicker repository.
<ol>
    <li> As shown in red in the image below click on the "clone or download" button </li>
    <li> Copy the link shown in blue in the image below <b> OR </b> Copy this link : https://github.com/UTSA-CS-3443/MoviePicker.git</a></li>
</ol>

![alt tag](https://user-images.githubusercontent.com/40246010/43734426-53b2cbfa-997d-11e8-8615-14276c0f0b0b.PNG)
#### Import to Eclispe
[`Click here for visual aid: "image file"`](https://github.com/UTSA-CS-3443/MoviePicker/files/2264085/file.docx)
<ol>
    <li>Open Eclispe IDE: Select file -> import-> Git -> Projects from Git -> Clone Url</li>
    <li>In empty Text file next to "URL", paste the copied link (https://github.com/UTSA-CS-3443/MoviePicker.git).   
        <ol>
          <li>All the other fields in Location should be filled automatically.</li>
            <li>In the Authentication: fill out accordingly with  gitHub Account (username and password)</li>
            <li>Click Next</li>
    </ol>
    </li>
        <li>(Step 5, <a href = "https://github.com/UTSA-CS-3443/MoviePicker/files/2264085/file.docx">image file</a> ) Branch Selection: check only master -> Click Next</li>
        <li>(Step 6, <a href = "https://github.com/UTSA-CS-3443/MoviePicker/files/2264085/file.docx">image file</a> ) Local Destination: chose a directory -> Click Next</li>
        <li>(Step 7, <a href = "https://github.com/UTSA-CS-3443/MoviePicker/files/2264085/file.docx">image file</a> ) Select a wizard to use for importing project -> Select "import as General Project" -> Click Next -> Finish</li>
    <li><b>Import project conflict</b>-- How to resolve:
    <ol>
        <li>(Step 8, <a href = "https://github.com/UTSA-CS-3443/MoviePicker/files/2264085/file.docx">image file</a> ) Import Project: select cancel.</li>
        <li>(Step 9, <a href = "https://github.com/UTSA-CS-3443/MoviePicker/files/2264085/file.docx">image file</a> ) File -> Open Projects From File System .</li>
        <li>(Step 10, <a href = "https://github.com/UTSA-CS-3443/MoviePicker/files/2264085/file.docx">image file</a> ) Import Project From File System or Archive: Find the Directory in which the git repository was story in (Refer to Step 6) -> Click finish.</li>
        </ol>
    </li>
</ol>
<!---
![alt tag](https://user-images.githubusercontent.com/40246010/43735043-3cd962de-997f-11e8-95d5-aca5083e669e.PNG)
![alt tag](https://user-images.githubusercontent.com/40246010/43736886-e7506cbc-9984-11e8-85ba-f389e7960576.PNG)
-->

<a name="usage"></a>
##  Usage Instructions & Running the tests :bookmark_tabs:
1. Click on the project in the Package Explorer. (Recommended to at least have Main.java Opened)
2. Run the project with the Green run button on Eclipse <b>OR</b> hover over the Run tab and select run from the drop-down menu.
![alt tag](https://user-images.githubusercontent.com/40246010/43701038-75ae627c-991a-11e8-85dc-9a063bb4e66e.PNG)
### Main view
The first view that is introduced to the user, gives the user two options to select from.<br/>
- [`Customize Movie List`](https://github.com/UTSA-CS-3443/MoviePicker#csv): This option allows the user to generate a random movie from their their own customize movie file. The user can create new movie file, delete an existing file, and add/delete their choice of movie into the file.
- [`Generate From Database`](https://github.com/UTSA-CS-3443/MoviePicker#db): This option allows the user to generate a movie from the recent IMDB movie collection. The user can also add their choice of movie to the Database if chose to.

![alt tag](https://user-images.githubusercontent.com/40246010/43694685-305bc46e-98fa-11e8-9702-ac32a752276a.PNG)

<a name="csv"></a>
### Running the test from Customize Movie List (csv file are used)
<ul style="list-style-type:none">
  <li> (Optional) File management: As shown in red in the image below, this option allows the user to create their own movie file, delete an existing file
     <ul>
    <li>To <b>add</b> movie file: enter a file name (the extenstion following the file is not needed), click add file.  </li>
    <li>To <b>delete</b> an existing file: select the file name from the list, click delete.</li>
  </ul>
  </li>
  <li> (Required) Select a Movie File from the list: As shown in blue in the image below, this option allows the application to load the next view with present the user with the list of movie(s) that are in the file.
   <ul>
    <li>TLDR: To advance to the next view: select a file from the list, click select file.  </li>
  </ul>
  </li>
</ul>

![alt tag](https://user-images.githubusercontent.com/40246010/43695728-fdd19ab8-98ff-11e8-9339-c114155c42ed.png)
#### Editing movie list
<ul>
    <li> (Optional/Required) If user selected a newly created file to customize, they are <b>required</b> to add at least one movie to the list; Otherwise adding a movie is <b>optional</b> and is up to the user decision to add as they please.
        <ul>
            <li> To <b>add</b> a movie: enter a title for the movie, and select a best fit genre for the movie. --> click add movie</li>
            <li> Setting the location is optional. The location is for the user to keep track of where they might obtain the movie
           <ul> <li> Example for location: home, dvd, Netflix, friend's place, or in theater.</li></ul></li>
            </ul>
        </li>
     <li> (Optional) Deleting a specific movie in the movie list.
        <ul>
            <li> To <b>delete</b> a movie: click the movie on the list -> click delete button.</li>
             <li> User is unable to delete a movie without selecting a movie.</li>
            <li> If ultimately, the user delete all the movie in the list, the user <b>can not </b>next process to the advance in the application with out at least one movie in the List</li>
            <li>If ultimately, the user <b>wants</b> to delete all the movie in the list, please refer to deleting a file from the library instead of manually deleting all the movie in a specific list.</li>
            </ul>
        </li>
 </ul>
 
![alt tag](https://user-images.githubusercontent.com/40246010/43702044-19b3e60a-991e-11e8-9e1e-6b8d339efafb.PNG)
#### Generating a movie
1. From the Movie list View, click the next button on the bottom left of the application, as shown in the images above in green
2. From the "Select a Genre" view, all the possible 9 genre are shown plus an additional button that allows the user to choose regardless of a specific genre.
   - User must select a button to advance to the next view. 
   - Genre button(s) are disable if there are no movie(s) that has been categorized under that genre
3. If the user is unsatisfied with the resulted random movie, the user can regenerate with the re-generate button.
   - To re-generate for another movie within the same genre: click re-generate.
   - User can [`navigate`](https://github.com/UTSA-CS-3443/MoviePicker#nav) back to the movie list to access the genre selection view to select a different genre
    
![alt tag](https://user-images.githubusercontent.com/40246010/43753664-7b945b26-99cc-11e8-92ba-c527cfee198f.PNG)
<!------TODO: GENERATE SCENE---->

<a name="db"></a>
### Running the test from Generate From Database (imdb data set are used)

<ul style="list-style-type:none">
  <li> (Optional) To <b>add</b> a new movie:  As shown in red in the image below, this option allows the user to add a new movie into the database. All the 4 sub-step are required action to do to successfully add a movie to the database.
     <ul>
    <li>Select a genre to add a movie into.  </li>
    <li> Enter title for the movie.</li>
    <li> Select a date for the release.</li>
     <li> Click submit. After submission, the movie should appear in the movie list when the genre is reselected.</li>
         <ul>
             <li>User is prevented from adding the identical movie</li>
             <li>User is prevented from adding a movie without the following requirements above being filled out</li>
             </ul>
  </ul>
  </li>
  <li> (Required) Randomly Generate a Movie: As shown in blue in the image below, this option allows the user obtain a random movie within a genre and a set range. 
   <ul>
    <li> Select a genre to generate from</li>
     <li> Select a rating boundary to generate from </li>
  </ul>
  </li>
    <li>(Optional) Re-generate for anothor movie: click "generate again" button
      <ul>
    <li> To generate a movie of another genre and rating: click database icon (image: imdb) and select a different genre and rating range --> click generate</li>
  </ul>
    </li>
</ul>

![alt tag](https://user-images.githubusercontent.com/40246010/43698632-908d1ce6-9910-11e8-8f2c-5a6d35517802.png)

#### Feature of Database
<ul style="list-style-type:none">
 <li> Listing of Movies: After selecting a genre for "Enter a movie", Click any of the colmun label, located on the first row of the Movie list in the database library, and the list can be order in that fashion.</li>
  </ul>
  
 Default table view:

 | Genre     | Title         | Date  |
| --------- |:-------------:| :-----|
| Action    | Tangled | 12/10/2009|
| Action    | Avengers: Age of Ultron |   11/24/2010|
| Action    | Superman Return     |  04/22/2015 |

Click on "Title" :

  | Genre     | Title         | Date  |
| --------- |:-------------:| :-----|
| Action    |Avengers: Age of Ultron |   11/24/2010|
| Action    | Superman Return     |  04/22/2015 |
| Action    | Tangled | 12/10/2009|

Click on "Date":

  | Genre     | Title         | Date  |
| --------- |:-------------:| :-----|
| Action    | Tangled | 12/10/2009|
| Action    |Avengers: Age of Ultron |   11/24/2010|
| Action    | Superman Return     |  04/22/2015 |

<a name = "nav"></a>
## Navigation :round_pushpin:
For each view, beside the main view, there is a tool bar fixed on the upper right hand corner as shown below. </br>
The image below displays all the icon possible in this application. </br>

![alt tag](https://user-images.githubusercontent.com/40246010/43753316-c67a25aa-99ca-11e8-969d-446fc2fb6e3a.PNG)
- :house: Home Icon (the far right icon, image: house): when clicked on will redirect the user to the main view.
  - Allows the user to reselect from their two choices: [`Customize Movie List`](https://github.com/UTSA-CS-3443/MoviePicker#csv) and [`Generate From Database`](https://github.com/UTSA-CS-3443/MoviePicker#db)
- :books: Libary Icon (the second from the right icon, image: books):
   - If the user is running the application with the [`Customize Movie List`](https://github.com/UTSA-CS-3443/MoviePicker#csv) option: the library view will display the list of files from the directory folder in which the files are saved in.
      - This icon is only avaliable in the views after the Library view.
   - If the user is running the application with the [`Generate From Database`](https://github.com/UTSA-CS-3443/MoviePicker#db) option: the library view will display the database view library version using the <b> database icon </b>
- :movie_camera: Movie List Icon (the second from the left icon, image: flim roll):
  - This option is only avaliable after Movie List view and not before. 
  - This option is also only avaliable for [`Customize Movie List`](https://github.com/UTSA-CS-3443/MoviePicker#csv) option
    - It is unnecessary for the [`Generate From Database`](https://github.com/UTSA-CS-3443/MoviePicker#db) option to include due to the fact that its Library and Movie List is one in the same and can be access using the database icon. 
- :page_with_curl: Database Icon (the far the left, image: IMDB logo):
   - This icon is only avaliable in the last view of the application, when the movie has been generated.
   - This icon redirect user to the [`Generate From Database`](https://github.com/UTSA-CS-3443/MoviePicker#db) option without having to go to the home (Main view) to access it

<a name="mvc"></a>
## Breakdown of code (MVC)

| Model     | View   | Controller |
| :--------- |:-------------| :-----|
| <ul type="none"> <li> -[x] Main.java</li></ul> |<ul type="none"> <li> [x] Main.fxml </li></ul>| <ul type="none"> <li>- [x] MainController.java</li></ul> |
| <ul type="none"> <li> -[x] Library.java </li></ul>| <ul type="none"> <li> - [x] library.fxml </li></ul>| <ul type="none"> <li> - [x] LibraryController.java </li></ul>|
|           | <ul type="none"> <li> -[x] MovieList1.fxml</li></ul> | <ul type="none"> <li> - [x] MovieListController.java </li></ul>|
| <ul type="none"> <li> - [x] DatabaseLibrary.java </li></ul>| <ul type="none"> <li> - [x] DatabaseLib.fxml</li></ul>|<ul type="none"> <li> - [x] DatabaseLibraryController.java</li></ul> |
|<ul type="none"> <li>  - [x] Movie.java    | <ul type="none"> <li> - [x] MoviePop.fxml </li></ul>| <ul type="none"> <li> - [x] MovieController.java </li></ul>|

#### Additional essential resources for application
- [x] JRE Library
- [x] sqlite-jdbc-3.23.1.jar
- [x] imdb-movies.db
- [x] resources folder
- [x] image folder


<a name = "note"></a>
## Notes
Recognized Problem #1: From the Customize Movie List option, deletion of a movie from a Movie List deletes all movies with the same movie title of the movie
````
Movie List:                         Movie List:
-------------  Delete: cinderella   -------------
cinderella             Results =>   finding nemo
cinderella                          -------------
finding nemo
-------------
````

<a name= "contribute"></a>
## Authors :bowtie:
[Jennifer Nguyen](https://github.com/jnguyenbee) <br/>
[Tiffany Tsai](https://github.com/tue170) <br/>
[Keegan Knisely](https://github.com/kjknisely) <br/>
