#   Important Branches
##  Master Branch
*   Stores the official release history
*   Should tag all commits in the master branch with a version number
*   **Never push directly to master on your own.**

##  Dev Branch
*   Serves as an integration branch for features
*   Contain the complete history of the project, whereas master will contain an abridged version

##  Feature Branch
*   Each new feature should reside in its own branch, which uses *develop* as their parent branch
*   When a feature is complete, it gets merged back into *develop*
*   **Should never interact directly with master!**
*   Creating a feature branch:
            
            git checkout develop
            git checkout -b feature_branch
*   Finishing a feature branch
            
            git checkout develop
            git merge feature_branch
        
##  Example
        git checkout master
        git checkout -b develop         //Create develop branch
        git checkout -b feature_branch  //Create feature branch

        /*
            Do work on feature branch
        */  
        
        //Once you're done with the feature...
        git checkout develop            //Go back to develop brach
        git merge feature_branch        //Merge feature_branch to dev branch
       
        // If done with iteration, merge stuff with master
        git checkout master             //Go back to master branch
        git merge develop               //Merge develop branch to master branch
        
        // Delete feature branch
        git branch -d feature_branch    //Delete feature branch
        
##  Git Workflow (Feature branch to dev branch)
    1.  Create feature branch
            git checkout -b feature_branch
    2.  Do work and commit (how often you commit depends on you)
            git add file_name   // Add a (modified) file to commit
            git add *           // Add all modified files
            git commit -m "Did some work"   //Commit these changes.  Please use the commit message convention when making your message!
    3.  Push your work onto the feature branch
            git push
    4.  Once you are done the feature, create a merge request at GitLab
        -   Code review
        -   Make changes according to code review
        -   Wait for at least 2 approvals
    5.  Merge your branch!
        -   So I am not sure how this works exactly, so we'll see how this whole merge requesting thing happens!
        
#   Important
*   Before merging into the dev branch, please create a merge request first!
*   A minimum of two people should look it over before any merging happens!
        