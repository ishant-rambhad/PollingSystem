# Polling Project

## Overview

This project is a simple yet efficient polling system designed with two main user roles: **User** and **Admin**. The application allows users to participate in polls, view poll results, and enables admins to create and manage polls with various options.

## Features

### 1. User Registration and Login
- Users can sign up and log in to participate in polls.
- Secure authentication ensures only registered users can vote.

### 2. Polling System
- Users can view and participate in available polls.
- Each poll has a limited time frame during which users can cast their votes.
- Once the poll closes, users can view the total votes for each option.

### 3. Admin Panel
- Admins can create new polls with up to 10 options.
- Admins can track the total votes for each poll.
- Poll data, including voting details, is stored securely in an SQL database.

### 4. Real-time Results Tracking
- Poll results are updated in real-time after the poll closes, showing total votes for each option.

## Technologies Used
- **Backend**: Java Spring Boot
- **Frontend**: HTML, CSS, JavaScript
- **Database**: SQL with Java Hibernate ORM
- **Database Connection**: Java Hibernate for seamless database integration and query management.

