#   Architecture

##  Presentation Layer
*   Includes the user interface
*   Purpose of the interface is to translate tasks and results to something the user can understand
*   E.g. a button that says *Get sales total*

##  Business Layer
*   Coordinates our application, processes commands, and makes logical decisions and evaluations, and performs calculations.
*   Also moves and processes data between TWO surrounding layers
*   E.g. the logical layer gets the list of all sales made last year, and queries the database

##  Persistence Layer
*   Information is stored and retrived from a database or file system
*   The information is passed back to the logic tier for processing, and then eventually back to the user
*   E.g. The database does its thing and sends the list of sales back to the logic layer.  The logic layer than uses this information to add all the sales together.  Finally, the logic layer sends this information to the interface to display to the user the *sales total*

##   Domain Objects