# Duke - Developer Guide

1. Setting Up

2. Design 

   1. Architecture
   2. UI
   3. Command Component
   4. Parser Component
   5. Storage Component
   6. Task Component
   7. Exception Component
   8. Recipebook Component
   9. RecipeCommand Component

3. Implementation

4. Documentation

5. Testing

6. Dev Ops

   Appendix A: Product Scope

   Appendix B: User Stories

   Appendix C: Use Cases

   Appendix D: Non Functional Requirements 

   Appendix E: Glossary 

   Appendix F: Product Survey 

   Appendix G: Instructions for Manual



### 1. Setting Up

- see the Guide [Here]() 

### 2. Design 

#### 2.1  Architecture

![architecture]( https://github.com/AY1920S1-CS2113-T14-2/main1/blob/master/docs/images/architectureV1.1.png )

`main` has 1 class called `Duke`. It is responsible for,

- at app launch: Loads all the data in storage into the application, initialize the  components, reads the commands and executes them correctly.
- at shut down: shuts down all component and exits the application

The Application consist of 6 other components 

- `command`: executes the command that is read from the user

- `exception`: handle error messages 
- `parser`: determine the next course of action from the user command
- `storage`: Reads, writes data from and to the hard disk
- `task`: stores a list of deadline/event/todo that needs to be done
- `ui`: The UI of the application

![sequence]( https://github.com/AY1920S1-CS2113-T14-2/main1/blob/master/docs/images/UMLsequence.png )

The diagram above shows the program sequence when the user enters the command `todo buy eggs` or `list`. 

#### 2.2 UI


API: `Ui.java`

The Ui class consists of methods that outputs messages to the user as a response when the user enters a certain command.

The Ui component contains all the messages or replies whenever the User enters a command for example if the user enters:

`dishadd chicken rice /num 2`

`deletedish 1`

The Ui will reply to the User with the following messages:

```
	 ____________________________________________________________
	 you have added the following dish: 
	 chicken rice 	amount: 2
	 ____________________________________________________________
```

```
	 ____________________________________________________________
	 The following dish have been removed:
	 chicken rice
	 ____________________________________________________________
```