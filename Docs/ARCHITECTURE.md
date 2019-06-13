#   Architecture
![Architecture](Architecture_Diagram.png)

##  Presentation Layer
*   Includes the user interface
*   Purpose of the interface is to translate tasks and results to something the user can understand

**MainActivity**

*    

**SpashActivity**

*   

**HomePageTabs**

*   TabFragmentAll
*   TabFragmentBooks
*   TabFragmentElectronics
*   TabFragmentEvents
*   TabFragmentLiving
*   TabFragmentServiceJobs
*   TabFragmentTransportation
*   TabFragmentOther
*   TabFragmentAdapter

**Login**

*   

**SignUp**

*   

**Registration**

*   

**EditAd**

*   

**PostAd**

*   


##  Business Layer
*   Coordinates our application, processes commands, and makes logical decisions and evaluations, and performs calculations.
*   Also moves and processes data between TWO surrounding layers

**AccessAds**

*   Insert, get and delete advertisements from the database

**AccessUsers**

*   Insert, and get users from the database

**Search**

*   Filters out advertisements based on categories, and sorts the advertisements by price

**Credentials**

*   Authenticate users, and validates that a password meets the password standard

##  Persistence Layer
*   Information is stored and retrived from a database or file system
*   The information is passed back to the logic tier for processing, and then eventually back to the user

**AdPersistence**

*   Interface for advertisements in the database

**UserPersistence**

*   Interface for users in the database

**AdPersistenceStub**

*   Stub implementation for advertisements in the database

**UserPersistenceStub**

*   Stub implementation for users in the database

##   Domain Objects
**Ad**

*   The object for advertisements posted by a user.

**User**

*   The object for users