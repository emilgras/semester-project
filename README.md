# semester-project
Repository for semester project in our test and database courses on Cphbusiness PBA in software development

### Læringsmål (hvad hedder det på engelsk???)

1. Opnå praktisk erfaring med TDD.
2. Erfaring med Data driven testing, ved at bruge forskellige bibliotekter der kan læse test data fra filer(cvs, Excel, etc.).
3. Mere erfaring med Unit testing og mocking, hvor der indgår objekter med afhængigheder.
4. Erfaring med  Integration testing med henblik test af databasen(DBUnit etc).
5. Erfaring med problemstillinger omhandlende test af databasen.**(Opsætning af test data, mocking)**
6. Erfaring med Continuous Integration og de tilhørende værktøjer som er inkluderet i dette(Travis, Maven). 

### GUIDE: How to Download Gutenberg Books

**PRE-REQUISITES**

* Create an account at DigitalOcean https://www.digitalocean.com.

* Additionally, you have to register your public SSH key at DigitalOcean. If you do not have a pair of keys read on how to do that. (https://www.digitalocean.com/community/tutorials/how-to-use-ssh-keys-with-digitalocean-droplets)

* Furthermore, you have to create a Personal Access Token, see the first part of https://www.digitalocean.com/community/tutorials/how-to-use-the-digitalocean-api-v2.

**GETTING STARTED**

By now, you should have a Digital Ocean access token stored on your computer (somewhere safe). You should also have an SSH key name.

1. Next, on your personal machine add 2 environment variables. You do this by entering the following lines in you terminal if you use a mac:

   export DIGITAL_OCEAN_TOKEN="your_access_token_comes_here"
   export SSH_KEY_NAME="your_key_name"

   or if you have a Windows machine you do it like this:

   set DIGITAL_OCEAN_TOKEN="your_access_token_comes_here"
   set SSH_KEY_NAME="your_key_name"

   (OPTIONAL) When

### Database Design

For this exercise we will use both a relational Oracle database and  a non-relational Neo4j Graph database to store our data. The data will obviously be modelled differently in the 2 types of databases. The Oracle database uses a predefined schema to model the data whereas the Graph database is schema less and therefore has no rules about the structure prior to inserting any data into it.

As mentioned the modelling for the 2 types of databases are 2 completely different tasks and they will be explained in individual sections below.

**Relational Database Modelling**

Write some cool stuffy here…



**Graph Database Modelling**

Graph databases contain collections of nodes and sets of relationships. The main differences between relational and graph databases is that graphs have relationships that connects 2 or more nodes together. Relationships are also often referred to as edges. Neo4j uses what’s called a property graph, meaning that both nodes and edges can have properties attached to them.

In our case we will need 3 types of nodes and 2 types of edges to model our data. 

_Nodes_
* Book 
* Author
* City

_Edges_
* MENTIONED
* WROTE

A simple model is shown below to describe all scenarios in our graph. 

/// ADD IMAGE ///

As we can see there are 3 nodes Book, Author and City. An author WROTE a Book and vice versa a Book was written by an author. In our case, we don’t need to add the extra relationship. A uni-directional graph will fit our purpose just fine. Furthermore, a Book can relate to a city if that city is mentioned in the book. And that is our simple model.
