# DigitalQueue

Project developed by Daniel Vela, Sergio Ruiz and Wilson Melo for the course of Software
Architecture of the Colombian School of Engineering for the 2019-II semester.

## Problem to be solved

The current solutions for queing customers in businesses are limited in the aspects of customization and customer interactivity. The vendors of these kind of solutions offer systems that cannot be fully customized, depend on additional physical devices like a turn dispenser and can become stale or obsolete pretty quickly.

Usually, other solutions are focused in a particular business model, allowing little to no customization and thus reducing the number of potential clients. Additionally, these turns are based on *first come-first serve* model in which turns cannot be modified due to any possible circumstance (such as a client leaving the business before the correspondent attention is given), this causes dead time periods in which a service representative cannot attend anybody and clients lose their patience and leave, giving room for more similar incidents.

Additionally, these systems offer close to no feedback in terms of performance of the customer attention system.

## Description

The purpose of this project is to implement a digital turn system that is ready to configure and use in any kind of organization. This project should ease customer attention, remove/reduce dead time periods between clients, give full statistic reports in indicators of performance, customer satisfaction and possible bottlenecks or problematic areas, etc, all through a fully customizable, ready to use platform that can be easily adapted to any kind of business model.

The final aggregate value is based in the reports feature: a business can get immediate feedback in terms of performance, customer satisfaction and possible bottlenecks or problematic areas.

![Component diagram](src/main/resources/static/Component%20Diagram.png)
![Class diagram](resources/diagrams/Class%20Diagram/Queue%20Class%20Diagram.png)
![Entity-Relation diagram](resources/diagrams/Entity-Relation%20Diagram.png)

## Architecture

Our solution is to deploy standalone Spring Boot applications compiled from a base code source (this repository) for each business to store their queues, users and configurations, with its own database and URL to access. Only employees can access the company's DigitalQueue deployment.

To begin, and for demonstration purposes, the application will be deployed to Heroku, where a PostgreSQL database will be available for the user to store data.
