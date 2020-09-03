# Basic Spring MVC Web App

## Introduction

1. This app demonstrate how to create controller, service and view using MVC terminology
2. This app uses third party dependency, which is be imported from maven central repository
3. The created service uses this dependency and controller uses this service
4. Controller then provide model attributes and view template
5. View template uses thymeleaf template engine to render the data 
6. The dependency which are used in this project are
   1. Spring web
   2. Thymeleaf
   3. Custom joke generating dependency
7. The project is being created using spring initializer

## Project Structure

~~~powershell
JokeApp
    └── src
        └── main
            ├── ---------------------------------
            ├── java
            │   └── CGI.Application.JokeApp
            │       ├── -------------------------
            │       ├── Controller
            │       │   └── JokeController  (C)
            │       ├── -------------------------
            │       ├── Services
            │       │   ├── JokeService     (I)
            │       │   └── JokeServiceImpl (C)
            │       ├── -------------------------
            │       └── JokeAppApplication
            ├── ---------------------------------
            ├── resources
            │   ├── static
            │   └── templates
            │       └── sampleTemplate.html
            └── ---------------------------------
~~~



## Creating Service Layer

1. Create a package name ```Services```. This package will hold all the services

2. Each service will have one interface, and one or more implementation

3. Create service interface ```JokeService```

   ~~~java
   public interface JokeService {
       String getJoke();
   }
   // this interface has one declared method
   ~~~

4. Create the implementation for above created interface ```JokeServiceImpl```

   ~~~java
   @Service	// this annotation will make this class a spring component 
   public class JokeServiceImpl implements JokeService {
       // property declaration,  type: custom dependency
       private final ChuckNorrisQuotes chuckNorrisQuotes;
   
       // constructor
       public JokeServiceImpl() {
           // property instantiation 
           this.chuckNorrisQuotes = new ChuckNorrisQuotes();
       }
   
       // method implementation
       @Override
       public String getJoke() {
           // method access from the custom property
           return this.chuckNorrisQuotes.getRandomQuote();
       }
   }
   ~~~

## Create the Controller

1. Create the package name ```Controllers``` in ```src >> main >> java >> CGI.Application.JokeApp```

2. This package will hold all the controllers

3. Create the controller name ```JokeController```

   ~~~java
   @Controller	// this annotation will register this class as spring component
   public class JokeController {
   
       private JokeService jokeService;
   
       @Autowired	// dependency injection using constructor
       public JokeController(JokeService jokeService) {
           this.jokeService = jokeService;
       }
   
       // request mapping has two paths "/" and ""
       @RequestMapping({"/",""})
       public String getJoke(Model model){
   
           // adding model attribute name and data
           // this model will directly be accessed in the view template
           model.addAttribute("joke",jokeService.getJoke());
   
           // returning the name of the joke
           return "sampleTemplate";
       }
   }
   ~~~

4. When user reaches to "/" or "" the ```getJoke()``` method will be executed

5. This method obtain ```model``` as a method parameter

6. This ```model``` will be used in the view by thymeleaf

7. The method ```getJoke()``` also returns the name of the view template

8. To make multiple request mapping on single method, put the strings in ```{}```

## Creating the View layer

1. Create the template name ```sampleTemplate.html``` in the following location

   ~~~powershell
   src >> main >> resources >> templates >> sampleTemplate.html
   ~~~

2. The name of the template should be same as returned template of the controller

3. Provide thymeleaf namespace using ```xmlns:th="www.thymeleaf.org"``` in ```html``` tag

4. Accessing the model in attribute can be done line this ```th:text="${joke}"```

5. Complete template

   ~~~html
   <!DOCTYPE html>
   <html lang="en" xmlns:th="www.thymeleaf.org">
       
       <head>
           <meta charset="UTF-8">
           <title>Title</title>
       </head>
   
       <body>
       <h2>Jokes</h2>
       <p th:text="${joke}">Insert joke here</p>
       </body>
           
   </html>
   ~~~

   



