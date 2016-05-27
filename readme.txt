This is a simple application for handling Rentals data with Solr 6.0 and Mongodb 3.0.0.

It has a single rental.html page. Once deployed it can be accessed through http://host:port/trial/rentals.html

The basic functions is add a new rental data and perform queries.
Each data added is indexed in Solr and stored in a Mongodb database. 
Each data queried takes advance of Solr functionalities and basically uses Solr queries to fetch data and display 
in the data table

The front end manipulation is done via JQuery, wrapping the user queries to Solr Queries.
There is no CSS for now.

The Resources is implemented with Jax-RS (Jersey) and the exchange of information is based on JSON.

A importer tool is provided for adding rentals data into Mongodb and Solr from a CSV file ( dataset.csv ).