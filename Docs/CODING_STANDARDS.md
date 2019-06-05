**Most of this comes from this website:** [Coding Standard](https://javaranch.com/styleLong.jsp#doc)
#   1 - Formatting
### 1.1 - Identation
*   **All indenting is done with TABS :)**
*   **Matching braces always line up vertically in the same column**
*       void foo()
        {
            do some work
        }
*   **All if,while, and for statements must use brackets even if they control one statement**
*       if (foo == bar) System.out.println("Hi");    //BAD!
        if (foo == bar)
        {
            System.out.println("Hi");
        }
### 1.2 - Spacing
*   **All method names should be immediately followed by a left paranthesis!**
*       foo (i, j); //BAD!
        foo(i, j);  //GOOD!
       
*   **All array dereferences should be immediately followed by a left square bracket**
*       args [0];   //BAD!
        args[0];    //GOOD!
       
*   **Binary operators should have a space on either side!**
*       a=b+c;      //BAD!
        a = b + c;  //GOOD!
        z = 2*x + 3*y;  //BAD!
        z = 2 * x + 3 * y;   //GOOD!
*   **Commas and semicolons are always followed by whitespace**
*       for (int i = 0;i < 10;i++);    //BAD!
        for (int i = 0; i < 10; i++);   //GOOD!
        foo(a,b);   //BAD!
        foo(a, b);  //GOOD!
*   **The keywords if, while, for, switch and catch must be followed by a space**
*       if(hungry)  //BAD!
        if (hungry) //GOOD!
        while(pancakes < 7) //BAD!
        while (pancakes < 7)    //GOOD!
### 1.3 - Class Member Ordering
        class Order
        {
            //  Fields (attributes)
            //  Constructors
            //  Methods
        }

#   2 - Identifiers
*   **Do not use dollar signs or non-ascii characters!**
### 2.1 - Classes and Interfaces
*   **The first letter of each word in the name will be UPPERCASE, INCLUDING the first letter of the name (Pascal Case)**
*       salesOrder  //BAD! because it is a class
        SalesOrder  //GOOD! 
*   Naming XML files
*   typeOfView_activityUsedIn_briefDescriptionOfView
*   Example: textView_splashscreen_copyright
### 2.2 - Packages
*   **Package names will use lower case characters only!**
*       Common  //BAD!
        common  //GOOD!
### 2.3 - Other
*   **Use camelCase for other identifiers (e.g. fields, local variables, methods, and parameters)**
*       addTototal()    //BAD!
        addToTotal()    //Good!
*   **Constants names use CONSTANT_CASE**: all UPPERCASE letters, with each word separated from the next by a single underscore.
*       thisIsAConstantVariable //BAD!
        THIS_IS_A_CONSTANT_VARIABLE //GOOD!       
### 2.3.2 - Test Code
*   **Test code is permitted to use underscores in identifiers for methods and fields.**
*       Code to test a method double eatSomePie(double amount) may use variables such as:
            eatSomePie_count
            eatSomePie_amount

#   3 - Coding
### 3.1 - Constructs to Avoid!
*   3.1.1 - Never use do..while loops!
*   3.1.2 - Never use *return* in the middle of the method!  It should be used at the end of the method only!
*   3.1.3 - Never use *continue*
*   3.1.4 - Never use *break* other than in a switch statement
*   3.1.5 - Do not use Compount Increment or Decrement Operators.  Use a separate line for an increment or decrement.
*           foo(x++);   //BAD!
            foo(x);     //GOOD!
            x++;
### 3.2 - Initialization
*   **Declare variables as close as possible to where they are used!**
*           int totalWide;
            int firstWide = 20;
            int secondWide = 12;
            firstWide = doFoo(firstWide, secondWide);
            doBar(firstWide, secondWide);
            totalWide = firstWide + secondWide;         //BAD!!
        
            int firstWide = 20;
            int secondWide = 12;
            firstWide = doFoo(firstWide, secondWide);
            doBar(firstWide, secondWide);
            int totalWide = firstWide + secondWide;     //GOOD!
        
            int secondWide = 12;
            int firstWide = doFoo(20, secondWide);
            doBar(firstWide, secondWide);
            int totalWide = firstWide + secondWide;     //BEST!
### 3.3 - Access
*   All fields must be private, except for some constants!

#   4 - Self-Documenting Code
*   **Instead of attempting to document how you did a complex algorithm, try to make it EASIER to READ by introducing more identifiers**
*       //  color is in the rainbow!
        if ( color == "red" || color == "orange" || color == "yellow" || color = "green" || color = "blue" || color == "indigo" || color == "violet")   //REALLY BAD!
        
        boolean colorInTheRainbow = color == "red" || color == "orange" || color == "yellow" || color = "green" || color = "blue" || color == "indigo" || color == "violet";    //GOOD!
        if (colorInTheRainbow)