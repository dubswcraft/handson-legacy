What is legacy code:
 ☐ Breaks other parts of the system. 
 ☐ 

Steps 1 - Initial Characterization Test:
 ☐ MEETUP: You make assumptions about legacy code, you need to test these assumptions via characterization tests
 ☐ This is the first characterization tests, it is meant to characterize what the system is doing.  
 ☐ MEETUP: Baby steps very important - why? 
 ☐ Renamed SomeTest to GameTests
 ☐ After a couple of tests - we recognize duplication - getConsoleOutput
 ☐ whenRollingDiceSomethingHappens - we don't really know what happens. We get a IndexOutOfBounds.. We learn more about the system as it stands.
 ☐ What did we learn from this - we wrote some tests we started understanding the system.  
 ☐ How useful these tests are: I want some very simple tests when dealing with legacy code.
 
Episode 2 - Golden Master: 
 ☐ MEETUP: Whenever you start dealing with an existing software system, you need to have a basic safety net
 ☐  So we will treat the system as a black box (we do not care about the internal behaviour of the system, we care just about the whole system inputs and outputs) and we will test only the outputs for given inputs.
 ☐ Really like the golden master - need to play around with more tomorrow.

Episode 3 - Fix bugs on legacy code:
 ☐ Steps as before, create your system test calling the function that contains the error
 ☐ Capture the result using the output stream
 ☐ Make baby steps as taking smaller and smaller refactoring step
   ☐ STEPS 
     ☐ Write a black box characterization test, you need to have a safety net before starting the next step   
     ☐ We need to find the area of code where the buggy code is and isolate it.
     ☐ Write another test that expresses the correct behaviour, this should fail. 
     ☐ 

Episode 4 - Add a feature:
 ☐ System test as normal, First step - get all the consoles out of the main class
 ☐ Create a static with a same name as the class
 ☐ Add a feature that adds captures all the game output
 ☐ Use coverage to identify area
 ☐ Introduce unit test for new class
 ☐ Add a new feature
 ☐ Write a system test for this.
   ☐ Introduce the notion of GameOutput - had ConsoleOutput implementing GameOutput, bring in brand new TextOutput 

   Episode 5 - Rules on Refactoring:
    ☐ MEETUP: This was good, extract method - refactor, duplicate steps.

    Episode 6 - Extract and override:
     ☐ MEETUP - Use when you have singletons
     ☐ 
     ☐ 
Pure Functions:
 ☐ MEETUP: Mention this
 ☐ TODO: Didn't know why he had to make the methods static.

Refactoring - Rule of 3:
 ☐ 

Extract Class - Rule of 3 also:
 ☐ rename your pure functions to have noun+verb+situation 
 ☐ ie playerMessageCreateWhenNewPlayerAdded 
 ☐ assume we had 3 methods that had the playerMessageCreate..., rule of 3 determines that we need to extract to a new class
 ☐ PlayerMessage class - extract as singletons - fix method names because you have PlayerMessage.playerMessageCreate.. not needed, so rename to PlayerMessage.create..
 ☐



Presentation Notes:
 ☐ Introduce
 ☐ GoldenMaster



 ☐ 
 ☐ We are going to take the piece of existing code without tests and write tests for it
 ☐ When it is 100% covered, then we can refactor it
 ☐ Only automated refactorings are allowed till your code is covered by a test
 ☐ Look at trip service, started testing from the shortest branch - this is when a user is not logged in exception
 ☐ The problem that we have is that this is a singleton, we can't inject it, we can't mock it. 
 ☐ What can we do? We can only use automated refactorings
 ☐ We can create a seam - a seam is where the two classes meet, the UserSession and the TripService, what we do is isolate the bit that goes to the other class and that is our seam. 
 ☐ We can create a private class that extends teh trip service
 ☐ Run your code coverage, make sure that the test that you have just written tests the short branch. 
 ☐ Code coverage is a very useful took when working with code coverage. We don't care about percentages, what we do care about is whether the test that we have written covered what we expected it to cover. 
 ☐ This seam is nice, but it's not ideal. Talk over test - where is the notion of a user not logged in. 
 ☐ Here is a good opportunity to bring in a business domain language into source. 
   ☐ Introduce concept of GUEST and user JOE in getTripsByUser   
  
 ☐ Now find the next shortest branch - there is only two left, we know the if (isFriend) is the deepest, as to get to there you need to set the isFriend flag in the snippet above
 ☐ 
   Test to verify no trips are returned when user and logged in user are not friends
   {
     User loggedInUser = UserSession..
     User JOE = new User("Joe")
     JOE.addFriend(SARAH)
     JOE.addTrip(TO_LONDON)
     assertThat(trips.size(), is(0)) trips are empty as they arenot friends  
   }
 ☐ test is green - that happens quite a lot in legacy code as the code is already written.
 ☐ Next test is when they are friends - we need to break the hard wired dependencies static calls - create another seam.

 ☐ We now should have full coverage, now we can start with cleanups - refactoring etc.
 ☐ add a user builder 
 ☐ Remember when testing we start from shortest to deepest
 ☐ In refactoring - if you go straight to deepest, it dosen't depend on anything, the snippet of code has everything that it needs, all flags are set so that conditionals are set.
 ☐ The deepest branch as we know was tripList = tripsByUser(..) - but this dosen't do anything.
 ☐ This block is the snippet that loops over the users. 
   ☐ always be asking when refactoring is whether this responsiblity belongs here. 
   ☐ we -GO- to the User object, we -GET- a list of users from the users, we -ITERATE- through it to see if it contains a user object. This is called feature envy. The trip service wants to be the User class - we need to fix this. 
   ☐ One way to do this is to add a 'isFriendsWith(X)' to the User class.
   ☐ TDD this from the user class
   ☐ should_inform_when_users_are_not_friends in user class
   ☐ assertThat (user.isFriendsWith(PAUL), is(false))
   ☐ create the method - minimum to do to make it pass is set it to false
   ☐ should_inform_when_users_are_friends
  ☐ When working with legacy code - you see variable names your not quite sure what they do - when you do find out what they do, rename to something that makes sense.
 ☐ You should start seeing the same language as seen in your test noTrips in the code if (noTrips etc)
 

 ☐ Retro - discuss what we have done, we have taken a bit of untested legacy code and written tests around it. Still a lot wrong with it.
 ☐ But what if the design is wrong, what we are doing when we are writing tests is perpetuating the bad design, and after you have written a lot of tests, you would be far inclined to changing the design.
 ☐ Whats wrong with the design:
 ☐ We have a TripService -  we assume by the name that it is a server side class. 
 ☐ However, it has a dependency on the user session. 
 ☐ Your TripService shouldn't know anything about your HTTP Session etc. 
 ☐ What we should do is pass in the user as a parameter
 ☐ Try to do as always baby steps
 ☐ add parameter loggedInUser to getTripsByUser
 ☐ Fix the tests that now add a new param
 ☐ You will also need to get rid of our new seam to getLoggedInUser
 ☐ We are almost done
 ☐ However, we don't like the static call - what can we do here?
 ☐ Why is it static? I can't mock it, I can't inject it
 ☐ I want to get rid of the static and create an instance.
 ☐ Write a test for it TripDAOTest,
   ☐ Useful trick, I want an instance method that does exactly the same as the static method    
   ☐ I could just remove the static keyword- I could but then I would have to fix my entire codebase. 
   ☐ We want to do everything in small steps
   ☐ So just create an instance with the same name that calls the real static call
   ☐ Also, we want to get rid of TestableTripService, we can make tripsBy private instead of protected.
   ☐ 
   ☐ 



Sandro Mancuso: Refactoring:
 ☐ Start testing from shortest to deepest branch (otherwise too much setup)
 ☐ Start refactoring from deepest to the shortest branch
 ☐ https://github.com/sandromancuso/trip-service-kata - lots of code areas
 ☐ A lot of time - your tests will be automatically green as the code is already there, you are just wrapping it in characterization tests
 ☐ Finally ready to start refactoring after some tests and most coverage: remember
 ☐ Start testing from the shortest to deepest branch
 ☐ Start refactoring from the deepest to the shortest branch - reason being if you want to start refactoring in the middle of your code, you need to have all flags set - however if you go to the deepest, 
 
 
 ☐ If the user class has the collection of friends, it might be worth asking user, are you friends with X?
 ☐ So create a user class and write the test should_inform_when_users_are_not_friends
 ☐ Meetup: When working with legacy code - try to bring the variable to the block where it is used.
 ☐ Meetup: 
 ☐ Meetup: Guard clause at the top
 ☐ 
Other Resources to review:
 ☐ Martin Fowler - https://www.youtube.com/watch?v=vqEg37e4Mkw
 ☐ Michael Feathers - https://www.youtube.com/watch?v=4cVZvoFGJTU
 ☐ Testing and refactoring legacy code Sandro : https://www.youtube.com/watch?v=_NnElPO5BU0
 ☐ Practical Refactoring - https://www.youtube.com/watch?v=aWiwDdx_rdo


Deck:
 ☐ Baby steps importance
 ☐ Golden master - trip service google this
 ☐ 
















